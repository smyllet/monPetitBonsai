package fr.bryanprolong.monpetitbonsai.bonsai.domain.model;

import fr.bryanprolong.monpetitbonsai.commons.entity.WateringEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Bonsai {
    private UUID id;

    private String name;

    private Owner owner;

    private String species;

    private Date acquisition_date;

    private int acquisition_age;

    private String status;

    private List<Watering> watering = new ArrayList<>();

    private List<Repotting> repotting = new ArrayList<>();

    public Bonsai() {
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
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

    public List<Watering> getWatering() {
        return watering;
    }

    public void setWatering(List<Watering> watering) {
        this.watering = watering;
    }

    public List<Repotting> getRepotting() {
        return repotting;
    }

    public void setRepotting(List<Repotting> repotting) {
        this.repotting = repotting;
    }
}
