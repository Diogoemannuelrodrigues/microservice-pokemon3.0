package br.com.microservice.attack.application.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "attack")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Attack {
    @Id
    private String id;

    private String name;

    private String type;

    private String category;

    private Integer power;

    private Double accurancy;

    private Integer pp;

    private List<String> changes;

    private Effect effect;

    private List<String> effects;

    private Boolean highCriticalHitRatio;
}
