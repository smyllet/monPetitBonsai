package fr.bryanprolong.monpetitbonsai.owner.exposition.dto;

import java.util.UUID;

public class OwnerBonsaiDTO {
    private UUID id;

    private String name;

    private String species;

    private int age;

    public OwnerBonsaiDTO() {
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
