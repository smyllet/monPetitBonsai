package fr.bryanprolong.monpetitbonsai.bonsai.exposition.dto;

import java.util.Date;
import java.util.UUID;

public class PruningDTO {
    private UUID id;

    private Date datetime;

    public PruningDTO() {
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
