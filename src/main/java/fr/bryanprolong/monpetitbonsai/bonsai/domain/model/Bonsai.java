package fr.bryanprolong.monpetitbonsai.bonsai.domain.model;

import fr.bryanprolong.monpetitbonsai.commons.type.Status;

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

    private Status status;

    private List<Watering> waterings = new ArrayList<>();

    private List<Repotting> repottings = new ArrayList<>();

    private List<Pruning> prunings = new ArrayList<>();

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Watering> getWaterings() {
        return waterings;
    }

    public void setWaterings(List<Watering> waterings) {
        this.waterings = waterings;
    }

    public List<Repotting> getRepottings() {
        return repottings;
    }

    public void setRepottings(List<Repotting> repottings) {
        this.repottings = repottings;
    }

    public List<Pruning> getPrunings() {
        return prunings;
    }

    public void setPrunings(List<Pruning> prunings) {
        this.prunings = prunings;
    }
}
