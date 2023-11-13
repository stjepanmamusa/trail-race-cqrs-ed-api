package com.stjepanmamusa.races_qs.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Transactional
@Table(name = "TRAIL_RACES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrailRace {
    @GeneratedValue
    @Id
    @JsonProperty("id")
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String club;
    private String distance;

}
