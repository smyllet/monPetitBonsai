package fr.bryanprolong.monpetitbonsai.owner.infrastructure.repository;

import fr.bryanprolong.monpetitbonsai.commons.dao.BonsaiDao;
import fr.bryanprolong.monpetitbonsai.commons.entity.BonsaiEntity;
import fr.bryanprolong.monpetitbonsai.commons.entity.OwnerEntity;
import fr.bryanprolong.monpetitbonsai.owner.domain.exception.OwnerNotFoundException;
import fr.bryanprolong.monpetitbonsai.owner.domain.model.Bonsai;
import fr.bryanprolong.monpetitbonsai.owner.exposition.dto.OwnerBonsaiDTO;
import fr.bryanprolong.monpetitbonsai.owner.modelMapper.OwnerMapper;
import fr.bryanprolong.monpetitbonsai.commons.dao.OwnerDao;
import fr.bryanprolong.monpetitbonsai.owner.domain.model.Owner;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
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

    public void deleteById(UUID id) throws OwnerNotFoundException {
        Optional<OwnerEntity> optionalOwnerEntity = ownerDao.findById(id);
        if(optionalOwnerEntity.isPresent()) {
            OwnerEntity ownerEntity = optionalOwnerEntity.get();
            ownerDao.unBindBonsaiOfOwner(ownerEntity);
            try {
                ownerDao.deleteById(id);
            } catch (EmptyResultDataAccessException e) {
                throw new OwnerNotFoundException();
            }
        } else {
            throw new OwnerNotFoundException();
        }
    }

    public void setBonsaiOwner(Bonsai bonsai, Owner owner) {
        bonsaiDao.updateOwnerOfBonsais(OwnerMapper.mapOwnerToOwnerEntity(owner), Collections.singletonList(bonsai.getId()));
    }

    public void updateOwnerOfBonsaisWithNoOwner(Owner owner, List<UUID> bonsaisUUID) {
        bonsaiDao.updateOwnerOfBonsaisWithNoOwner(OwnerMapper.mapOwnerToOwnerEntity(owner), bonsaisUUID);
    }
}
