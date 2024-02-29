package br.com.microservice.pokemon.service;

import br.com.microservice.pokemon.domain.Stone;
import br.com.microservice.pokemon.service.clients.StoneFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoneService {

    @Autowired
    private StoneFeignClient stoneFeignClient;

    public Stone getStoneByName(String name){
        return stoneFeignClient.getStoneByName(name);
    }

    public String getAllStones(){
        return stoneFeignClient.getAllStones();
    }
}
