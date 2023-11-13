package com.stjepanmamusa.races_cs.service;

import com.stjepanmamusa.races_cs.dto.EventType;
import com.stjepanmamusa.races_cs.dto.TrailRaceEvent;
import com.stjepanmamusa.races_cs.entity.TrailRace;
import com.stjepanmamusa.races_cs.rabbitmq.producer.RabbitMqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TrailRacesCommandService {
    @Autowired
    private RabbitMqProducer rabbitMqProducer;

    public void createRace(TrailRace race) {
        TrailRaceEvent createTrailRaceEvent = new TrailRaceEvent();
        createTrailRaceEvent.setEventType(EventType.CREATE);
        createTrailRaceEvent.setTrailRace(race);
        rabbitMqProducer.sendEvent(createTrailRaceEvent);
    }

    public void updateRace(TrailRace race) {
        TrailRaceEvent updateTrailRaceEvent = new TrailRaceEvent();
        updateTrailRaceEvent.setEventType(EventType.UPDATE);
        updateTrailRaceEvent.setTrailRace(race);
        rabbitMqProducer.sendEvent(updateTrailRaceEvent);
    }

    public void deleteRace(UUID uuid) {
        TrailRaceEvent deleteTrailRaceEvent = new TrailRaceEvent();
        TrailRace trailRace = new TrailRace();
        trailRace.setUuid(uuid);
        deleteTrailRaceEvent.setEventType(EventType.DELETE);
        deleteTrailRaceEvent.setTrailRace(trailRace);
        rabbitMqProducer.sendEvent(deleteTrailRaceEvent);

    }

}
