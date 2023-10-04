package br.com.microservice.pokemon.domain;

public enum HttpStatusEnum {

    SALVANDO(1, "Salvando"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private int code;
    private String description;

    private HttpStatusEnum(int cod, String descricao) {
        this.code = cod;
        this.description = descricao;
    }
    public int getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }

    public static HttpStatusEnum toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (HttpStatusEnum x : HttpStatusEnum.values()) {
            if (cod.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
