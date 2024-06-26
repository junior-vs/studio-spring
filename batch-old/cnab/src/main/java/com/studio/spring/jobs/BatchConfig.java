package com.studio.spring.jobs;

import com.studio.spring.model.Transacao;
import com.studio.spring.model.TransacaoCNAB;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Configuration
public class BatchConfig {

    private static final String QUERY_INSERT = """
            insert into transacao ( tipo, data, valor, cpf, cartao, hora, dono_da_loja, nome_da_loja)
            values (:tipo, :data, :valor, :cpf, :cartao, :hora, :donoDaLoja, :nomeDaLoja)
            """;

    private final PlatformTransactionManager transactionManager;
    private final JobRepository jobRepository;

    public BatchConfig(PlatformTransactionManager transactionManager, JobRepository jobRepository) {
        this.transactionManager = transactionManager;
        this.jobRepository = jobRepository;
    }

    @SuppressWarnings("null")
    @Bean
    Job job(Step step) {
        return new JobBuilder("job", this.jobRepository)
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @SuppressWarnings("null")
    @Bean
    Step step(ItemReader<TransacaoCNAB> itemReader,
              ItemProcessor<TransacaoCNAB, Transacao> itemProcessor,
              ItemWriter<Transacao> itemWriter) {
        return new StepBuilder("step", jobRepository)
                .<TransacaoCNAB, Transacao>chunk(1000, transactionManager)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @SuppressWarnings("null")
    @Bean
    @StepScope
    FlatFileItemReader<TransacaoCNAB> itemReader(@Value("#{jobParameters['cnabFile']}") Resource resource) {
        return new FlatFileItemReaderBuilder<TransacaoCNAB>()
                .name("reader")
                .resource(resource)
                .fixedLength()
                .columns(new Range(1, 1), new Range(2, 9),
                        new Range(10, 19), new Range(20, 30),
                        new Range(31, 42), new Range(43, 48),
                        new Range(49, 62), new Range(63, 80))
                .names("tipo", "data",
                        "valor", "cpf",
                        "cartao", "hora",
                        "donoDaLoja", "nomeDaLoja")
                .targetType(TransacaoCNAB.class)
                .build();
    }

    @Bean
    ItemProcessor<TransacaoCNAB, Transacao> itemProcessor() {
        return item -> {
            return new Transacao(null, item.tipo(),
                    null, item.valor().divide(BigDecimal.valueOf(100), 2, RoundingMode.CEILING),
                    item.cpf(), item.cartao(),
                    null, item.donoDaLoja().trim(), item.nomeDaLoja().trim())
                    .withData(item.data())
                    .withHora(item.hora());
        };
    }

    @SuppressWarnings("null")
    @Bean
    ItemWriter<Transacao> itemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Transacao>()
                .dataSource(dataSource)
                .sql(QUERY_INSERT)
                .beanMapped()
                .build();
    }

    @SuppressWarnings("null")
    @Bean
    JobLauncher jobLauncherAsync(JobRepository jobRepository) throws Exception {
        var jobLauncher = new TaskExecutorJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        jobLauncher.afterPropertiesSet();

        return jobLauncher;
    }


}