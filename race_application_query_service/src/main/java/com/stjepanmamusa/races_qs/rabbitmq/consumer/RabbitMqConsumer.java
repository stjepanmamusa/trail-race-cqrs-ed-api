package com.stjepanmamusa.races_qs.rabbitmq.consumer;

import com.stjepanmamusa.races_qs.dto.EventType;
import com.stjepanmamusa.races_qs.dto.TrailRaceEvent;
import com.stjepanmamusa.races_qs.service.TrailRacesQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMqConsumer {
    @Autowired
    TrailRacesQueryService queryService;

    @RabbitListener(queues = {"${spring.rabbitmq.queue.name}"})
    public void receiveEvent(TrailRaceEvent event) {
        log.info("[EVENT RECEIVED] [TYPE:{}] [PAYLOAD:{}]", event.getEventType(), event.getTrailRace());
        handleEvent(event);
    }

    private void handleEvent(TrailRaceEvent event) {
        switch (event.getEventType()) {
            case CREATE:
                queryService.createRace(event.getTrailRace());
                break;
            case UPDATE:
                queryService.updateRace(event.getTrailRace());
                break;
            case DELETE:
                queryService.deleteRace(event.getTrailRace().getUuid());
                break;
            default: log.warn("Unknown event type received {}", event.getEventType());
        }

    }
}
