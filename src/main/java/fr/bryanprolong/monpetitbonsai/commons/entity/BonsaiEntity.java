package fr.bryanprolong.monpetitbonsai.commons.entity;

import fr.bryanprolong.monpetitbonsai.commons.type.Status;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(name = "bonsai")
@Table(name = "bonsai")
public class BonsaiEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private OwnerEntity owner;

    @Column(name = "species")
    private String species;

    @Column(name = "acquisition_date")
    private Date acquisition_date;

    @Column(name = "acquisition_age")
    private int acquisition_age;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(targetEntity = WateringEntity.class, mappedBy = "bonsai", cascade = CascadeType.ALL)
    @OrderBy("datetime")
    private List<WateringEntity> waterings;

    @OneToMany(targetEntity = RepottingEntity.class, mappedBy = "bonsai", cascade = CascadeType.ALL)
    @OrderBy("datetime")
    private List<RepottingEntity> repottings;

    @OneToMany(targetEntity = PruningEntity.class, mappedBy = "bonsai", cascade = CascadeType.ALL)
    @OrderBy("datetime")
    private List<PruningEntity> prunings;

    public BonsaiEntity() {
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

    public OwnerEntity getOwner() {
        return owner;
    }

    public void setOwner(OwnerEntity owner) {
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

    public List<WateringEntity> getWaterings() {
        return waterings;
    }

    public void setWaterings(List<WateringEntity> watering) {
        this.waterings = watering;
    }

    public List<RepottingEntity> getRepottings() {
        return repottings;
    }

    public void setRepottings(List<RepottingEntity> repotting) {
        this.repottings = repotting;
    }

    public List<PruningEntity> getPrunings() {
        return prunings;
    }

    public void setPrunings(List<PruningEntity> pruning) {
        this.prunings = pruning;
    }
}
