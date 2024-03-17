package com.studio.spring.batch.football.steps.writters;

import com.studio.spring.batch.football.model.Player;
import com.studio.spring.batch.football.persistence.PlayerDao;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class PlayerItemWriter implements ItemWriter<Player> {

    private final PlayerDao playerDao;

    public PlayerItemWriter(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public void write(Chunk<? extends Player> players) {
        players.getItems().forEach(playerDao::savePlayer);
    }
}
