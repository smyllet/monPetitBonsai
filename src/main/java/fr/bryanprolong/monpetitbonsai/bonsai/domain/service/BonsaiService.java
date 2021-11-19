package fr.bryanprolong.monpetitbonsai.bonsai.domain.service;

import fr.bryanprolong.monpetitbonsai.bonsai.domain.exception.BonsaiNotFoundException;
import fr.bryanprolong.monpetitbonsai.bonsai.domain.model.Bonsai;
import fr.bryanprolong.monpetitbonsai.bonsai.infrastructure.repository.BonsaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BonsaiService {
    private final BonsaiRepository bonsaiRepository;

    @Autowired
    public BonsaiService(BonsaiRepository bonsaiRepository) {
        this.bonsaiRepository = bonsaiRepository;
    }

    public List<Bonsai> findAll() {
        return bonsaiRepository.findAll();
    }

    public Optional<Bonsai> findById(UUID uuid) {
        return bonsaiRepository.findById(uuid);
    }

    public Bonsai create(Bonsai bonsai) {
        return bonsaiRepository.create(bonsai);
    }

    public void deleteById(UUID uuid) throws BonsaiNotFoundException {
        bonsaiRepository.deleteById(uuid);
    }

    public Bonsai updateById(UUID uuid, Bonsai bonsai) throws BonsaiNotFoundException {
        Optional<Bonsai> optionalBonsai = findById(uuid);
        if(optionalBonsai.isPresent()) {
            Bonsai bonsaiToUpdate = optionalBonsai.get();

            if(bonsai.getName() != null) bonsaiToUpdate.setName(bonsai.getName());
            if(bonsai.getSpecies() != null) bonsaiToUpdate.setSpecies(bonsai.getSpecies());
            if(bonsai.getAcquisition_date() != null) bonsaiToUpdate.setAcquisition_date(bonsai.getAcquisition_date());
            if(bonsai.getAcquisition_age() != 0) bonsaiToUpdate.setAcquisition_age(bonsai.getAcquisition_age());

            return bonsaiRepository.updateById(bonsaiToUpdate);
        } else {
            throw new BonsaiNotFoundException();
        }
    }
}
