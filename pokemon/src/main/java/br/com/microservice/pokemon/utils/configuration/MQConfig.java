package br.com.microservice.pokemon.utils.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    @Value("${mq.queues.ms-pokemon}")
    private String msPokemon;

    @Bean
    public Queue queueMSPokemon(){
        return new Queue(msPokemon, true);
    }
}
