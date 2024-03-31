package com.studio.spring.batch.udemy.first.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class ImprimiOlaTasklet implements Tasklet {

    @Value("#{jobParameters['nome']}")
    private String nome;
    private static final Logger logger = LoggerFactory.getLogger(ImprimiOlaTasklet.class);

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        logger.info(String.format("Oi, %s!", nome));
        return RepeatStatus.FINISHED;
    }
}
