package fr.bryanprolong.monpetitbonsai.bonsai.infrastructure.repository;

import fr.bryanprolong.monpetitbonsai.bonsai.domain.exception.BonsaiNotFoundException;
import fr.bryanprolong.monpetitbonsai.bonsai.infrastructure.entity.BonsaiEntity;
import fr.bryanprolong.monpetitbonsai.bonsai.modelMapper.BonsaiMapper;
import fr.bryanprolong.monpetitbonsai.bonsai.domain.model.Bonsai;
import fr.bryanprolong.monpetitbonsai.bonsai.infrastructure.dao.BonsaiDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BonsaiRepository {
    private final BonsaiDao bonsaiDao;

    public BonsaiRepository(BonsaiDao bonsaiDao) {
        this.bonsaiDao = bonsaiDao;
    }

    public List<Bonsai> findAll() {
        return bonsaiDao.findAll().stream()
                .map(BonsaiMapper::mapBonsaiEntityToBonsai)
                .collect(Collectors.toList());
    }

    public Optional<Bonsai> findById(UUID uuid) {
        return bonsaiDao.findById(uuid)
                .map(BonsaiMapper::mapBonsaiEntityToBonsai);
    }

    public Bonsai create(Bonsai bonsai) {
        BonsaiEntity bonsaiInput = BonsaiMapper.mapBonsaiToBonsaiEntity(bonsai);
        BonsaiEntity bonsaiOutput = bonsaiDao.save(bonsaiInput);
        return BonsaiMapper.mapBonsaiEntityToBonsai(bonsaiOutput);
    }

    public void deleteById(UUID uuid) throws BonsaiNotFoundException {
        try {
            bonsaiDao.deleteById(uuid);
        } catch (EmptyResultDataAccessException e) {
            throw new BonsaiNotFoundException();
        }
    }
}
