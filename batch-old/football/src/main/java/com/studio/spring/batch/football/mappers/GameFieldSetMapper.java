package com.studio.spring.batch.football.mappers;

import com.studio.spring.batch.football.model.Game;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class GameFieldSetMapper implements FieldSetMapper<Game> {
    @SuppressWarnings("null")
    @Override
    public Game mapFieldSet(FieldSet fs) throws BindException {
        if (fs == null) {
            return null;
        }

        return new Game(fs.readString("id"),
                fs.readInt("year"),
                fs.readString("team"),
                fs.readInt("week"),
                fs.readString("opponent"),
                fs.readInt("completes"),
                fs.readInt("attempts"),
                fs.readInt("passingYards"),
                fs.readInt("passingTd"),
                fs.readInt("interceptions"),
                fs.readInt("rushes"),
                fs.readInt("rushYards"),
                fs.readInt("receptions", 0),
                fs.readInt("receptionYards"),
                fs.readInt("totalTd"));

    }
}
