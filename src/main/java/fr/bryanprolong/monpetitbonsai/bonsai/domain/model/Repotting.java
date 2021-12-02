package fr.bryanprolong.monpetitbonsai.bonsai.domain.model;

import fr.bryanprolong.monpetitbonsai.commons.entity.BonsaiEntity;

import java.util.Date;
import java.util.UUID;

public class Repotting {
    private UUID id;

    private Date datetime;

    private BonsaiEntity bonsai;

    public Repotting() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public BonsaiEntity getBonsai() {
        return bonsai;
    }

    public void setBonsai(BonsaiEntity bonsai) {
        this.bonsai = bonsai;
    }
}
