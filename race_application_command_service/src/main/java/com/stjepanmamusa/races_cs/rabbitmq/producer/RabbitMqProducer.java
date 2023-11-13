package com.stjepanmamusa.races_cs.rabbitmq.producer;

import com.stjepanmamusa.races_cs.dto.TrailRaceEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMqProducer {
    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;

    @Value("${spring.rabbitmq.routing.key}")
    private String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendEvent(TrailRaceEvent event){
        log.info("[EVENT PRODUCED] [TYPE:{}] [PAYLOAD:{}]", event.getEventType(), event.getTrailRace());
        rabbitTemplate.convertAndSend(exchange, routingKey, event);
    }
}
