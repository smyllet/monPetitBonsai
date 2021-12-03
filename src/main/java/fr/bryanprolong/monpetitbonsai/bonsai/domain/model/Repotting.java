package fr.bryanprolong.monpetitbonsai.bonsai.domain.model;

import java.util.Date;
import java.util.UUID;

public class Repotting {
    private UUID id;

    private Date datetime;

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
}
