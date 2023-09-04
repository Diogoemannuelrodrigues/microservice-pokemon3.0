package br.com.microservice.pokemon.service;

import br.com.microservice.pokemon.domain.Move;
import br.com.microservice.pokemon.domain.MoveInfo;
import br.com.microservice.pokemon.domain.Pokemon;
import br.com.microservice.pokemon.repository.PokemonRepository;
import br.com.microservice.pokemon.utils.ConvertDados;
import lombok.var;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PokemonServiceTest {
    @Mock
    private PokemonService service;

    @Mock
    private ConvertDados convertDados;

    @Mock
    private PokemonRepository repository;

    @Test
    @DisplayName("Test SAVE")
    void testSave(){
        Pokemon pokemon = Pokemon.builder().id("1").build();
        String json = json();
        Mockito.when(repository.save(Mockito.any())).thenReturn(pokemon);
        Mockito.when(convertDados.obterDados(json, Pokemon.class)).thenReturn(pokemon);

        var result = service.convertPokemon(json);
        assertNotNull(result);
        assertEquals(result.getId(), pokemon.getId());

    }

    @Test
    @DisplayName("Test FINDBYID")
    void findById(){
        var id = "1";
        var move1 = MoveInfo.builder().build();
        move1.setMove(Move.builder().name("test").url("test").build());

        Pokemon pokemon =
                Pokemon.builder()
                        .id(id)
                        .moveInfos(Arrays.asList(move1, move1, move1, move1, move1))
                .build();

        Mockito.when(service.findById(id)).thenReturn(Optional.ofNullable(pokemon));

        var result = service.findById(id);
        assertNotNull(result);
    }

    public String json(){
        return "{\"abilities\":[{\"ability\":{\"name\":\"rock-head\",\"url\":\"https://pokeapi.co/api/v2/ability/69/\"},\"is_hidden\":false,\"slot\":1},{\"ability\":{\"name\":\"sturdy\",\"url\":\"https://pokeapi.co/api/v2/ability/5/\"},\"is_hidden\":false,\"slot\":2},{\"ability\":{\"name\":\"sand-veil\",\"url\":\"https://pokeapi.co/api/v2/ability/8/\"},\"is_hidden\":true,\"slot\":3}],\"base_experience\":60,\"height\":4,\"id\":74,\"is_default\":true,\"location_area_encounters\":\"https://pokeapi.co/api/v2/pokemon/74/encounters\",\"name\":\"geodude\",\"order\":115,\"past_types\":[],\"species\":{\"name\":\"geodude\",\"url\":\"https://pokeapi.co/api/v2/pokemon-species/74/\"},\"stats\":[{\"base_stat\":40,\"effort\":0,\"stat\":{\"name\":\"hp\",\"url\":\"https://pokeapi.co/api/v2/stat/1/\"}},{\"base_stat\":80,\"effort\":0,\"stat\":{\"name\":\"attack\",\"url\":\"https://pokeapi.co/api/v2/stat/2/\"}},{\"base_stat\":100,\"effort\":1,\"stat\":{\"name\":\"defense\",\"url\":\"https://pokeapi.co/api/v2/stat/3/\"}},{\"base_stat\":30,\"effort\":0,\"stat\":{\"name\":\"special-attack\",\"url\":\"https://pokeapi.co/api/v2/stat/4/\"}},{\"base_stat\":30,\"effort\":0,\"stat\":{\"name\":\"special-defense\",\"url\":\"https://pokeapi.co/api/v2/stat/5/\"}},{\"base_stat\":20,\"effort\":0,\"stat\":{\"name\":\"speed\",\"url\":\"https://pokeapi.co/api/v2/stat/6/\"}}],\"types\":[{\"slot\":1,\"type\":{\"name\":\"rock\",\"url\":\"https://pokeapi.co/api/v2/type/6/\"}},{\"slot\":2,\"type\":{\"name\":\"ground\",\"url\":\"https://pokeapi.co/api/v2/type/5/\"}}],\"weight\":200}";
    }
}
