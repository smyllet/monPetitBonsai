package fr.bryanprolong.monpetitbonsai.commons.dao;

import fr.bryanprolong.monpetitbonsai.commons.entity.BonsaiEntity;
import fr.bryanprolong.monpetitbonsai.commons.entity.OwnerEntity;
import fr.bryanprolong.monpetitbonsai.commons.type.Status;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface BonsaiDao extends JpaRepository<BonsaiEntity, UUID> {
    @Query("select b from bonsai b WHERE (b.status = :status OR :status is null ) AND b.acquisition_age > :olderThan")
    List<BonsaiEntity> findAllFiltered(@Param("status") Status status, @Param("olderThan") int olderThan, Sort sort);

    @Modifying
    @Transactional
    @Query(value = "update bonsai set owner = :owner where id in (:bonsais_id)")
    void updateOwnerOfBonsais(@Param("owner") OwnerEntity owner, @Param("bonsais_id") List<UUID> bonsais_id);
}
