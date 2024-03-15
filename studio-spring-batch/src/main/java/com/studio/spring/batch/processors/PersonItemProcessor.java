package com.studio.spring.batch.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.studio.spring.batch.domain.Person;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    @Nullable
    public Person process(@NonNull final Person person) throws Exception {

        final var firstName = person.firstName().toLowerCase();
        final var lastName = person.lastName().toUpperCase();

        Person createdPerson = new Person(firstName, lastName);

        log.info("Converting (" + person + ") into (" + createdPerson + ")");
        return createdPerson;
    }

}
