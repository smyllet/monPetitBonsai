package fr.bryanprolong.monpetitbonsai.commons.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "pruning")
@Table(name = "pruning")
public class PruningEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "datetime")
    private Date datetime;

    @ManyToOne
    @JoinColumn(name="bonsai_id")
    private BonsaiEntity bonsai;

    public PruningEntity() {
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

    public void setBonsai(BonsaiEntity bonsaiEntity) {
        this.bonsai = bonsaiEntity;
    }
}
