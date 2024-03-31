package com.studio.spring.batch.football.model;

public record PlayerSummary(String id, int year, int completes, int attempts, int passingYards, int passingTd,
                            int interceptions, int rushes, int rushYards, int receptions, int receptionYards,
                            int totalTd) {}
