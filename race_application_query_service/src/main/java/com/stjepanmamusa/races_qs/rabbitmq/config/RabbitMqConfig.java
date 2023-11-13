package com.stjepanmamusa.races_qs.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class RabbitMqConfig {
    @Value("${spring.rabbitmq.queue.name}")
    private String queue;
    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;
    @Value("${spring.rabbitmq.routing.key}")
    private String routingKey;

    private final CachingConnectionFactory connectionFactory;

    public RabbitMqConfig(CachingConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter);
        return template;
    }
}
