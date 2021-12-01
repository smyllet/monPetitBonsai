package fr.bryanprolong.monpetitbonsai.commons.dao;

import fr.bryanprolong.monpetitbonsai.commons.entity.BonsaiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BonsaiDao extends JpaRepository<BonsaiEntity, UUID> {
}
