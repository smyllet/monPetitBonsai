package fr.bryanprolong.monpetitbonsai.owner.exposition.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OwnerDTO {
    private UUID id;
    private String name;
    private List<OwnerBonsaiDTO> bonsais = new ArrayList<>();

    public OwnerDTO() {
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

    public List<OwnerBonsaiDTO> getBonsais() {
        return bonsais;
    }

    public void setBonsais(List<OwnerBonsaiDTO> bonsais) {
        this.bonsais = bonsais;
    }
}
