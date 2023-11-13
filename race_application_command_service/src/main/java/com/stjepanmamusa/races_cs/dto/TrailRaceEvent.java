package com.stjepanmamusa.races_cs.dto;

import com.stjepanmamusa.races_cs.entity.TrailRace;
import lombok.Data;

@Data

public class TrailRaceEvent {
    private EventType eventType;
    private TrailRace trailRace;

}
