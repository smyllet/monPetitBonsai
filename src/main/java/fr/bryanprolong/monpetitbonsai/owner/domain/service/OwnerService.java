package fr.bryanprolong.monpetitbonsai.owner.domain.service;

import fr.bryanprolong.monpetitbonsai.owner.domain.exception.OwnerNotFoundException;
import fr.bryanprolong.monpetitbonsai.owner.domain.model.Owner;
import fr.bryanprolong.monpetitbonsai.owner.infrastructure.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    public Optional<Owner> findById(UUID id) {
        return ownerRepository.findById(id);
    }

    public Owner create(Owner owner) {
        return ownerRepository.create(owner);
    }

    public void deleteById(UUID id) throws OwnerNotFoundException {ownerRepository.deleteById(id);}
}
