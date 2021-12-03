package fr.bryanprolong.monpetitbonsai.bonsai.exposition.dto;

import java.util.Date;
import java.util.UUID;

public class BonsaiDTO {
    private UUID id;

    private String name;

    private UUID owner_id;

    private String species;

    private Date acquisition_date;

    private int acquisition_age;

    private String status;

    private Date last_watering;

    private Date last_repotting;

    private Date last_pruning;

    public BonsaiDTO() {
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

    public UUID getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(UUID owner_id) {
        this.owner_id = owner_id;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Date getAcquisition_date() {
        return acquisition_date;
    }

    public void setAcquisition_date(Date acquisition_date) {
        this.acquisition_date = acquisition_date;
    }

    public int getAcquisition_age() {
        return acquisition_age;
    }

    public void setAcquisition_age(int acquisition_age) {
        this.acquisition_age = acquisition_age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLast_watering() {
        return last_watering;
    }

    public void setLast_watering(Date last_watering) {
        this.last_watering = last_watering;
    }

    public Date getLast_repotting() {
        return last_repotting;
    }

    public void setLast_repotting(Date last_repotting) {
        this.last_repotting = last_repotting;
    }

    public Date getLast_pruning() {
        return last_pruning;
    }

    public void setLast_pruning(Date last_pruning) {
        this.last_pruning = last_pruning;
    }
}
