package com.stjepanmamusa.races_qs.dto;

import com.stjepanmamusa.races_qs.entity.TrailRace;
import lombok.Data;

@Data

public class TrailRaceEvent {
    private EventType eventType;
    private TrailRace trailRace;

}
