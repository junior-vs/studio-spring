package com.studio.spring.batch.football.persistence;

import com.studio.spring.batch.football.model.Game;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JdbcGameDao extends JdbcDaoSupport implements ItemWriter<Game> {

    private SimpleJdbcInsert insertGame;

    @Override
    protected void initDao() throws Exception {
        super.initDao();
        insertGame = new SimpleJdbcInsert(getDataSource()).withTableName("GAMES")
                .usingColumns("player_id", "year_no", "team", "week", "opponent", " completes", "attempts", "passing_yards",
                        "passing_td", "interceptions", "rushes", "rush_yards", "receptions", "receptions_yards",
                        "total_td");
    }

    @Override
    public void write(Chunk<? extends Game> games) throws Exception {
        games.getItems().forEach(game ->{
                SqlParameterSource values = new MapSqlParameterSource().addValue("player_id", game.id())
                .addValue("year_no", game.year())
                .addValue("team", game.team())
                .addValue("week", game.week())
                .addValue("opponent", game.opponent())
                .addValue("completes", game.completes())
                .addValue("attempts", game.attempts())
                .addValue("passing_yards", game.passingYards())
                .addValue("passing_td", game.passingTd())
                .addValue("interceptions", game.interceptions())
                .addValue("rushes", game.rushes())
                .addValue("rush_yards", game.rushYards())
                .addValue("receptions", game.receptions())
                .addValue("receptions_yards", game.receptionYards())
                .addValue("total_td", game.totalTd());
        this.insertGame.execute(values);
        });

    }
}
