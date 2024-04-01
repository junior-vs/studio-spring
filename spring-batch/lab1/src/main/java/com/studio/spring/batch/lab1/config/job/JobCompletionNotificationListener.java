package com.studio.spring.batch.lab1.config.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.studio.spring.batch.lab1.config.step.Person;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

    private static final Logger logger = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        switch (jobExecution.getStatus()) {
            case BatchStatus.COMPLETED:
                logger.info("!!! JOB FINISHED! Time to verify the results");
                jdbcTemplate
                        .query("SELECT first_name, last_name FROM business.PEOPLE", new DataClassRowMapper<>(Person.class))
                        .forEach(person -> logger.info("Found <{{}}> in the database.", person));
                break;
            case BatchStatus.FAILED:
                logger.info("!!! JOB FAILED!");
                break;

            default:
                logger.info(String.format("The job finished with status: %s", jobExecution.getStatus()),
                        BatchStatus.UNKNOWN);
                break;
        }
    }

}
