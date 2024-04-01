package com.example.demo_kafka_stream_producer.domain;

record TelemetryData(
        String probeId,
        String timestamp,
        Double currentSpeedMph,
        Double traveledDistanceFeet,
        SpaceAgency spaceAgency
){ }

enum SpaceAgency {
    NASA,
    ESA
}