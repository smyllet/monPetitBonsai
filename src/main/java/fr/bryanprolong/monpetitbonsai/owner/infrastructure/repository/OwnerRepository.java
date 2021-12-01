package fr.bryanprolong.monpetitbonsai.owner.infrastructure.repository;

import fr.bryanprolong.monpetitbonsai.owner.modelMapper.OwnerMapper;
import fr.bryanprolong.monpetitbonsai.commons.dao.OwnerDao;
import fr.bryanprolong.monpetitbonsai.owner.domain.model.Owner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OwnerRepository {
    private final OwnerDao ownerDao;

    public OwnerRepository(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    public List<Owner> findAll() {
        return ownerDao.findAll().stream()
                .map(OwnerMapper::mapOwnerEntityToOwner)
                .collect(Collectors.toList());
    }

    public Optional<Owner> findById(UUID id) {
        return ownerDao.findById(id)
                .map(OwnerMapper::mapOwnerEntityToOwner);
    }
}
