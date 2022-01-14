package fr.bryanprolong.monpetitbonsai.commons.dao;

import fr.bryanprolong.monpetitbonsai.commons.entity.AuthorityEntity;
import fr.bryanprolong.monpetitbonsai.commons.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface UserDao extends JpaRepository<UserEntity, UUID> {
    UserEntity findByUsername(String username);

    @Transactional
    @Query("select a.authorityId.authority from AuthorityEntity a where a.authorityId.uuid = :id")
    List<String> findAuthorityByUserId(UUID id);
}