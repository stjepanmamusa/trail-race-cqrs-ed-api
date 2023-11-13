package com.stjepanmamusa.races_cs.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class TrailRace {
    @JsonProperty("id")
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String club;
    private String distance;

}
