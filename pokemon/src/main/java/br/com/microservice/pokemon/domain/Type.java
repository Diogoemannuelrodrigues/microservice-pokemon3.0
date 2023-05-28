package br.com.microservice.pokemon.domain;

public enum Type {

    Bug(1, "Bug"),
    Dark(2, "Dark"),
    Dragon(3, "Dragon"),
    Electric(4, "Eletric"),
    Fair(5, "Fair"),
    Fighting(6, "Fighting"),
    Fire(7,"Fire"),
    Flying(8, "Flying"),
    Ghost(9, "Ghost"),
    Grass(10, "Grass"),
    Ground(11, "Ground"),
    Ice(12, "Ice"),
    Normal(13, "Normal"),
    Poison(14, "Poison"),
    Psychic(15, "Psychic"),
    Rock(16, "Rock"),
    Steel(17, "Steel"),
    Water(18, "Water");
    private int cod;
    private String descricao;

    private Type(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Type toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (Type x : Type.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}
