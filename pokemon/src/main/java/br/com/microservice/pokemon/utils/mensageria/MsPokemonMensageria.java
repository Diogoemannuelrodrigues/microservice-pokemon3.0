package br.com.microservice.pokemon.utils.mensageria;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MsPokemonMensageria {

    @RabbitListener(queues = "${mq.queues.ms_pokemon}")
    public void recebeMensagemMsPokemon(@Payload String payload){
        System.out.println(payload);
    }
}
