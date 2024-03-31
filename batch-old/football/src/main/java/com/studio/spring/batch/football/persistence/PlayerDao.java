package com.studio.spring.batch.football.persistence;

import com.studio.spring.batch.football.model.Player;

public interface PlayerDao {

    void savePlayer(Player player);
}
