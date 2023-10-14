package br.com.microservice.pokemon.service;

import br.com.microservice.pokemon.domain.*;
import br.com.microservice.pokemon.repository.PokemonRepository;
import br.com.microservice.pokemon.repository.TreinadorRepository;
import br.com.microservice.pokemon.service.clients.GlitchfeignClient;
import br.com.microservice.pokemon.service.clients.PokeFeingClient;
import br.com.microservice.pokemon.utils.ConvertDados;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PokemonService {
    public static final String NAO_PODE_SER_EVOLUIDO = "O pokemon nao pode ser evoluido";
    public static final String POKEMON_EVOLUIDO = "Pokemon evoluido";

    private final PokemonRepository repository;
    private final PokeFeingClient pokeFeingClient;
    private final GlitchfeignClient glitchfeignClient;
    private final ConvertDados convertDados;
    private ModelMapper mapper;
    private final TreinadorRepository treinadorRepository;
    private final StoneService stoneService;

    public Pokemon convertPokemon(String json) {
        var pokemon = convertDados.obterDados(json, Pokemon.class);
        return repository.save(pokemon);
    }

    public Optional<Pokemon> findById(String id) {
        var pokemon = repository.findById(id);
        if (pokemon.isPresent()) {
            var moves = pokemon.get().getMoveInfos().subList(0, 4);
            pokemon.get().setMoveInfos(moves);
            return pokemon;
        }
        return Optional.empty();
    }

    public PokemonDTO getPokemon(String name) {
        return pokeFeingClient.getPokemonDTO(name);
    }

    public Optional<EvolutionDTO> getNextEvolucaoPokemonByGlitch(String name) {
        Gson gson = new Gson();
        var jsonResponse = glitchfeignClient.getPokemonDTO(name);
        EvolutionDTO[] array = gson.fromJson(jsonResponse, EvolutionDTO[].class);
        return Arrays.stream(array).findFirst();
    }

    public String starTheGame() {
        for (int i = 1; i <= 150; i++) {
            var poke = pokeFeingClient.getPkemonById(i);
            var pokeConverted = convertDados.obterDados(poke, Pokemon.class);
            repository.save(pokeConverted);
            log.info(String.valueOf(pokeConverted));
        }

        return "the game can start now";
    }

    public Optional<MoveInfo> getAdicionaMove(Pokemon pokemon, String nameMove) {
        return pokemon.getMoveInfos() //pega os moves
                .stream() //faz o stream
                .filter(moveInfo -> moveInfo.getMove().getName().contains(nameMove)) //filtra por nameMOve
                .findFirst(); //Retorna o 1 que achou
    }

    public List<PokemonDTO> findAllPokemon() {
        mapper = new ModelMapper();
        return repository.
                findAll().
                stream().
                map(pokemon -> mapper.map(pokemon, PokemonDTO.class)).
                collect(Collectors.toList());
    }

    public String getEvoluirPokemon(String pokemon, String treinador1) {
        var pokemonToLowerCase = pokemon.toLowerCase();
        var pokemon1 = repository.findByName(pokemonToLowerCase);
        var treinador = treinadorRepository.findByNameIgnoreCase(treinador1);

        var verificaProximaEvolucao = getNextEvolucaoPokemonByGlitch(pokemon1.getName()).orElse(null);
        var familyEvolution = getEvolutionLine(Optional.ofNullable(verificaProximaEvolucao));

        boolean isPresent = familyEvolution.stream()
                .map(String::toLowerCase)
                .anyMatch(listPokemon -> listPokemon.equals(pokemonToLowerCase));

        if(isPresent) {

            var containPokemon = treinador.getPokemons()
                    .stream()
                    .filter(pokemon2 -> pokemon2.getName().equalsIgnoreCase(pokemonToLowerCase))
                    .findFirst()
                    .orElse(null);

            assert containPokemon != null;
            containPokemon.setMoveInfos(pokemon1.getMoveInfos());

            assert verificaProximaEvolucao != null;
            String next = verificaProximaEvolucao.getFamily().getEvolutionLine()
                    .stream() //Stream
                    .skip(verificaProximaEvolucao.getFamily().getEvolutionStage())
                    .findFirst()
                    .orElse(null);

            var vericaSeFamiliaEhMaiorQueEstagioAtual = verificaProximaEvolucao.getFamily().getEvolutionStage() >
                    verificaProximaEvolucao.getFamily().getEvolutionLine().size();

            if(next != null && !vericaSeFamiliaEhMaiorQueEstagioAtual){
                var pokeNewEvolution = repository.findByName(next.toLowerCase());
                containPokemon.setName(next);
                containPokemon.setStates(pokeNewEvolution.getStates());
                containPokemon.setId(pokeNewEvolution.getId());
                containPokemon.setWeight(pokeNewEvolution.getWeight());
                treinadorRepository.save(treinador);
                return POKEMON_EVOLUIDO;
            }
                return NAO_PODE_SER_EVOLUIDO;
        }

        return NAO_PODE_SER_EVOLUIDO;
    }

    private static List<String> getEvolutionLine(Optional<EvolutionDTO> verificaProximaEvolucao) {
        return verificaProximaEvolucao
                .map(evolutionDTO -> evolutionDTO.getFamily().getEvolutionLine())
                .orElse(null);
    }

    public String getToEvolvePokemonWithStone(String treinador, String namePoke, String stone) {
        var treinador1 = treinadorRepository.findByNameIgnoreCase(treinador);

        var pokemon = getByPokemon(namePoke, treinador1);

        var resultValidate = getVerificaSePodeEvoluir(pokemon.get(), stone);
        if (resultValidate){
            return "Seu pokemon evoluiu com a pedra " + stone;
        }
        return "Seu pokemon nao pode evoluir com a pedra " + stone;

    }

    private static Optional<Pokemon> getByPokemon(String namePoke, Treinador teinador) {
        return teinador
                .getPokemons()
                .stream()
                .filter(p -> p.getName().contains(namePoke))
                .findFirst();
    }

    /**
     *  Metodo para verificar se pode evoluir o pokemon com pedra de evolucao..
     * */
    public Boolean getVerificaSePodeEvoluir(Pokemon pokemon, String pedra) {
        Stone stone = stoneService.getStoneByName(pedra);

        for (Object object : stone.getEffects()) {
            String o = String.valueOf(object);
            if (o.contains(pokemon.getName())) {
                log.info("Pode evoluir pokemon");
                return true;
            }
        }
        return false;
    }

}
