package ufcg.edu.br.models.enums;

/**
 * Created by Italo on 07/02/2017.
 */
public enum Priority {

    ALTA("Alta"),
    MEDIA("Media"),
    BAIXA("Baixa");

    private final String name;

    private Priority(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
