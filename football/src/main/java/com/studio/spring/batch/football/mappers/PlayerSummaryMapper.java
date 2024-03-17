package com.studio.spring.batch.football.mappers;

import com.studio.spring.batch.football.model.PlayerSummary;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerSummaryMapper implements RowMapper<PlayerSummary> {
    @Override
    public PlayerSummary mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new PlayerSummary(rs.getString(1),
                rs.getInt(2),
                rs.getInt(3),
                rs.getInt(4),
                rs.getInt(5),
                rs.getInt(6),
                rs.getInt(7),
                rs.getInt(8),
                rs.getInt(9),
                rs.getInt(10),
                rs.getInt(11),
                rs.getInt(12));
    }
}
