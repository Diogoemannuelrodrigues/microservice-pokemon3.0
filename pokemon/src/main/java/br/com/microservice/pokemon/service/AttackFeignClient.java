package br.com.microservice.pokemon.service;

import br.com.microservice.pokemon.domain.Attack;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

//@FeignClient(name = "${feign.name}", url = "${feign.url}")
public interface AttackFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/stores")
    List<Attack> getStores();

    @RequestMapping(method = RequestMethod.POST, value = "/stores/{storeId}", consumes = "application/json")
    Attack update(@PathVariable("storeId") Long storeId, Attack attack);
}
