package com.studio.spring.batch.football.jobs;

import com.studio.spring.batch.football.mappers.GameFieldSetMapper;
import com.studio.spring.batch.football.mappers.PlaFieldSetMapper;
import com.studio.spring.batch.football.mappers.PlayerSummaryMapper;
import com.studio.spring.batch.football.model.Game;
import com.studio.spring.batch.football.model.Player;
import com.studio.spring.batch.football.model.PlayerSummary;
import com.studio.spring.batch.football.persistence.JdbcGameDao;
import com.studio.spring.batch.football.steps.writters.JdbcPlayerWriter;
import com.studio.spring.batch.football.steps.writters.JdbcPlayerSummaryWriter;
import com.studio.spring.batch.football.steps.writters.PlayerItemWriter;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.JdbcTransactionManager;

import javax.sql.DataSource;

@Configuration
public class FootballJobConfig {

    private final JobRepository jobRepository;
    private final JdbcTransactionManager transactionManager;

    public FootballJobConfig(JobRepository jobRepository, JdbcTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @SuppressWarnings("null")
    @Bean
    public Job job(Step playerLoad, Step gameLoad, Step summarizationStep) {
        return new JobBuilder("footballJob", this.jobRepository)
                .start(playerLoad) //step 1
                .next(gameLoad) // step 2
                .next(summarizationStep)//step 3
                .build();
    }

    // step 1 configuration

    @SuppressWarnings("null")
    @Bean
    public Step playerLoad(FlatFileItemReader<Player> playerFlatFileItemReader, PlayerItemWriter playerItemWriter) {
        return new StepBuilder("playerLoad", this.jobRepository)
                .<Player, Player>chunk(2, this.transactionManager)
                .reader(playerFlatFileItemReader)
                .writer(playerItemWriter)
                .build();
    }

    @SuppressWarnings("null")
    @Bean
    @StepScope
    public FlatFileItemReader<Player> playerFlatFileItemReader(@Value("${player.path.file}") String pathToFile) {

        return new FlatFileItemReaderBuilder<Player>().name("playerFileItemReader")
                .resource(new PathResource(pathToFile))
                .delimited()
                .names("ID", "lastName", "firstName", "position", "birthYear", "debutYear")
                .fieldSetMapper(new PlaFieldSetMapper())
                .build();
    }

    @Bean
    public PlayerItemWriter playerItemWriter(DataSource dataSource) {
        return new PlayerItemWriter(new JdbcPlayerWriter(dataSource));
    }

    // step 2 configuration

    @SuppressWarnings("null")
    @Bean
    public Step gameLoad(
            FlatFileItemReader<Game> gameFileItemReader, JdbcGameDao gameWriter) {
        return new StepBuilder("gameLoad", this.jobRepository).<Game, Game>chunk(2, this.transactionManager)
                .reader(gameFileItemReader)
                .writer(gameWriter)
                .build();
    }

    @SuppressWarnings("null")
    @Bean
    @StepScope
    public FlatFileItemReader<Game> gameFileItemReader(@Value("${game.path.file}") String pathToFile) {
        return new FlatFileItemReaderBuilder<Game>().name("gameFileItemReader")
                .resource(new PathResource(pathToFile))
                .delimited()
                .names("id", "year", "team", "week", "opponent", "completes", "attempts", "passingYards", "passingTd",
                        "interceptions", "rushes", "rushYards", "receptions", "receptionYards", "totalTd")
                .fieldSetMapper(new GameFieldSetMapper())
                .build();
    }

    @SuppressWarnings("null")
    @Bean
    public JdbcGameDao gameWriter(DataSource dataSource) {
        JdbcGameDao jdbcGameDao = new JdbcGameDao();
        jdbcGameDao.setDataSource(dataSource);
        return jdbcGameDao;
    }

    //step 3 configuration
    @SuppressWarnings("null")
    @Bean
    public Step summarizationStep(JdbcCursorItemReader<PlayerSummary> playerSummarizationSource, JdbcPlayerSummaryWriter summaryWriter) {
        return new StepBuilder("summarizationStep", this.jobRepository)
                .<PlayerSummary, PlayerSummary>chunk(2, this.transactionManager)
                .reader(playerSummarizationSource)
                .writer(summaryWriter)
                //   .faultTolerant()
                .build();
    }

    @SuppressWarnings("null")
    @Bean
    public JdbcCursorItemReader<PlayerSummary> playerSummarizationSource(DataSource dataSource) {
        String sql = """
				SELECT GAMES.player_id, GAMES.year_no, SUM(COMPLETES),
				SUM(ATTEMPTS), SUM(PASSING_YARDS), SUM(PASSING_TD),
				SUM(INTERCEPTIONS), SUM(RUSHES), SUM(RUSH_YARDS),
				SUM(RECEPTIONS), SUM(RECEPTIONS_YARDS), SUM(TOTAL_TD)
				from GAMES, PLAYERS where PLAYERS.player_id =
				GAMES.player_id group by GAMES.player_id, GAMES.year_no
				""";
        return new JdbcCursorItemReaderBuilder<PlayerSummary>().name("playerSummarizationSource")
                .ignoreWarnings(true)
                .sql(sql)
                .dataSource(dataSource)
                .rowMapper(new PlayerSummaryMapper())
                .build();
    }

    @Bean
    public JdbcPlayerSummaryWriter summaryWriter(DataSource dataSource) {
        return new JdbcPlayerSummaryWriter(dataSource);
    }


}
