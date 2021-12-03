package fr.bryanprolong.monpetitbonsai.owner.infrastructure.repository;

import fr.bryanprolong.monpetitbonsai.commons.dao.BonsaiDao;
import fr.bryanprolong.monpetitbonsai.commons.entity.BonsaiEntity;
import fr.bryanprolong.monpetitbonsai.commons.entity.OwnerEntity;
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
    private final BonsaiDao bonsaiDao;

    public OwnerRepository(OwnerDao ownerDao, BonsaiDao bonsaiDao) {
        this.ownerDao = ownerDao;
        this.bonsaiDao = bonsaiDao;
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

    public Owner create(Owner owner) {
        OwnerEntity ownerInput = OwnerMapper.mapOwnerToOwnerEntity(owner);
        OwnerEntity ownerOutput = ownerDao.save(ownerInput);
        bonsaiDao.updateOwnerOfBonsais(
                ownerOutput,
                ownerInput.getBonsais().stream()
                        .map(BonsaiEntity::getId)
                        .collect(Collectors.toList())
        );

        return OwnerMapper.mapOwnerEntityToOwner(ownerDao.getById(ownerOutput.getId()));
    }
}
