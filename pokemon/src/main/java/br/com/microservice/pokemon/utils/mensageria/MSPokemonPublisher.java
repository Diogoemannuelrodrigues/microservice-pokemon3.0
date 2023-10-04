package br.com.microservice.pokemon.utils.mensageria;

import br.com.microservice.pokemon.domain.Attack;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MSPokemonPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final Queue msPokemonFila;
    private ObjectMapper mapper;

    public void solicitahAttaque(Attack ataque) throws JsonProcessingException {
        var json = convertJson(ataque);
        rabbitTemplate.convertAndSend(msPokemonFila.getName(), json);
    }

    private String convertJson(Attack ataque) throws JsonProcessingException {
        return mapper.writeValueAsString(ataque);
    }

}
