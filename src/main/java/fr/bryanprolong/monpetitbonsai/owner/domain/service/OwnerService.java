package fr.bryanprolong.monpetitbonsai.owner.domain.service;

import fr.bryanprolong.monpetitbonsai.owner.domain.model.Owner;
import fr.bryanprolong.monpetitbonsai.owner.infrastructure.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }
}
