package com.studio.spring.batch.football.mappers;

import com.studio.spring.batch.football.model.Player;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class PlaFieldSetMapper implements FieldSetMapper<Player> {
    @SuppressWarnings("null")
    @Override
    public Player mapFieldSet(FieldSet fs) throws BindException {

        if(fs==null){
            return null;
        }

        return new Player(fs.readString("ID"),
        fs.readString("lastName"),
        fs.readString("firstName"),
        fs.readString("position"),
        fs.readInt("birthYear"),
        fs.readInt("debutYear"));
    }
}
