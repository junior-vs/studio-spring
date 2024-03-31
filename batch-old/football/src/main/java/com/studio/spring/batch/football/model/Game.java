package com.studio.spring.batch.football.model;

public record Game(String id, int year, String team, int week, String opponent, int completes, int attempts,
                   int passingYards, int passingTd, int interceptions, int rushes, int rushYards, int receptions,
                   int receptionYards, int totalTd) {

}
