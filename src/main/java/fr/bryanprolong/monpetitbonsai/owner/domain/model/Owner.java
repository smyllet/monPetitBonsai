package fr.bryanprolong.monpetitbonsai.owner.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Owner {
    private UUID id;
    private String name;
    private List<Bonsai> bonsais = new ArrayList<>();

    public Owner() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bonsai> getBonsais() {
        return bonsais;
    }

    public void setBonsais(List<Bonsai> bonsais) {
        this.bonsais = bonsais;
    }
}
