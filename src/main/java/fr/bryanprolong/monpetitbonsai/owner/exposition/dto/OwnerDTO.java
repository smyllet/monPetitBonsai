package fr.bryanprolong.monpetitbonsai.owner.exposition.dto;

import fr.bryanprolong.monpetitbonsai.bonsai.exposition.dto.BonsaiDTO;

import java.util.List;
import java.util.UUID;

public class OwnerDTO {
    private UUID id;
    private String name;
    private List<BonsaiDTO> bonsais;

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

    public List<BonsaiDTO> getBonsais() {
        return bonsais;
    }

    public void setBonsais(List<BonsaiDTO> bonsais) {
        this.bonsais = bonsais;
    }
}