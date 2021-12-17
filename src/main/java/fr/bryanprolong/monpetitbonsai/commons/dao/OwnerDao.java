package fr.bryanprolong.monpetitbonsai.commons.dao;

import fr.bryanprolong.monpetitbonsai.commons.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.UUID;

public interface OwnerDao extends JpaRepository<OwnerEntity, UUID> {
    @Modifying
    @Transactional
    @Query(value = "update bonsai set owner = null where owner = :owner")
    void unBindBonsaiOfOwner(@Param("owner") OwnerEntity owner);
}
