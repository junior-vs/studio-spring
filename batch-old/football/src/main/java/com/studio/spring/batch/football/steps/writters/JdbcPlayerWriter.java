package com.studio.spring.batch.football.steps.writters;

import com.studio.spring.batch.football.model.Player;
import com.studio.spring.batch.football.persistence.PlayerDao;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

public class JdbcPlayerWriter implements PlayerDao {

    public static final String INSERT_PLAYER = "INSERT into PLAYERS (player_id, last_name, first_name, pos, year_of_birth, year_drafted)"
            + " values (:id, :lastName, :firstName, :position, :birthYear, :debutYear)";

    private final NamedParameterJdbcOperations namedParameterJdbcTemplate;

    @SuppressWarnings("null")
    public JdbcPlayerWriter(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @SuppressWarnings("null")
    @Override
    public void savePlayer(Player player) {
        namedParameterJdbcTemplate.update(INSERT_PLAYER, new BeanPropertySqlParameterSource(player));

    }
}
