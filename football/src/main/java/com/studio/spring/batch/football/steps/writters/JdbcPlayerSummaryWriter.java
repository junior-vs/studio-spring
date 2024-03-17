package com.studio.spring.batch.football.steps.writters;

import com.studio.spring.batch.football.model.PlayerSummary;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

public class JdbcPlayerSummaryWriter implements ItemWriter<PlayerSummary> {


    private static final String INSERT_SUMMARY = "INSERT into PLAYER_SUMMARY(ID, YEAR_NO, COMPLETES, ATTEMPTS, PASSING_YARDS, PASSING_TD, "
            + "INTERCEPTIONS, RUSHES, RUSH_YARDS, RECEPTIONS, RECEPTIONS_YARDS, TOTAL_TD) "
            + "values(:id, :year, :completes, :attempts, :passingYards, :passingTd, "
            + ":interceptions, :rushes, :rushYards, :receptions, :receptionYards, :totalTd)";
    private final  NamedParameterJdbcOperations namedParameterJdbcTemplate;

    public JdbcPlayerSummaryWriter(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void write(Chunk<? extends PlayerSummary> playerSummaries) throws Exception {

        playerSummaries.getItems().forEach((summary) -> {
            MapSqlParameterSource args = new MapSqlParameterSource().addValue("id", summary.id())
                    .addValue("year", summary.year())
                    .addValue("completes", summary.completes())
                    .addValue("attempts", summary.attempts())
                    .addValue("passingYards", summary.passingYards())
                    .addValue("passingTd", summary.passingTd())
                    .addValue("interceptions", summary.interceptions())
                    .addValue("rushes", summary.rushes())
                    .addValue("rushYards", summary.rushYards())
                    .addValue("receptions", summary.receptions())
                    .addValue("receptionYards", summary.receptionYards())
                    .addValue("totalTd", summary.totalTd());

            namedParameterJdbcTemplate.update(INSERT_SUMMARY, args);
        });

    }
}
