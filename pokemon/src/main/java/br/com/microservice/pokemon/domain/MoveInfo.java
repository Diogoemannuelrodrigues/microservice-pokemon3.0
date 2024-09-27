package br.com.microservice.pokemon.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveInfo {

    @JsonProperty("move")
    private Move move;

    @JsonProperty("version_group_details")
    private List<GroupDetails> versionGroupDetails;
}