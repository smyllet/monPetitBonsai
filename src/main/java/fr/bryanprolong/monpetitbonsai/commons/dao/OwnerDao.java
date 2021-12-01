package fr.bryanprolong.monpetitbonsai.commons.dao;

import fr.bryanprolong.monpetitbonsai.commons.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OwnerDao extends JpaRepository<OwnerEntity, UUID> {
}
