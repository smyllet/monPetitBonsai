package fr.bryanprolong.monpetitbonsai.owner.domain.service;

import fr.bryanprolong.monpetitbonsai.owner.domain.exception.BonsaiNotFoundException;
import fr.bryanprolong.monpetitbonsai.owner.domain.exception.OwnerNotFoundException;
import fr.bryanprolong.monpetitbonsai.owner.domain.model.Bonsai;
import fr.bryanprolong.monpetitbonsai.owner.domain.model.Owner;
import fr.bryanprolong.monpetitbonsai.owner.exposition.dto.OwnerBonsaiDTO;
import fr.bryanprolong.monpetitbonsai.owner.infrastructure.repository.OwnerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public Bonsai transferBonsaiToAnOwner(UUID owner_id, UUID bonsai_id, UUID new_owner_id) throws OwnerNotFoundException, BonsaiNotFoundException {
        Optional<Owner> optionalOwner = ownerRepository.findById(owner_id);

        if(optionalOwner.isPresent()) {
            Owner owner = optionalOwner.get();

            Optional<Bonsai> optionalBonsai = owner.getBonsais().stream().filter(bonsai -> bonsai.getId().equals(bonsai_id)).findAny();

            if(optionalBonsai.isPresent()) {
                Bonsai bonsai = optionalBonsai.get();

                Optional<Owner> optionalNewOwner = ownerRepository.findById(new_owner_id);

                if(optionalNewOwner.isPresent()) {
                    Owner newOwner = optionalNewOwner.get();
                    ownerRepository.setBonsaiOwner(bonsai, newOwner);
                    return bonsai;
                } else throw new OwnerNotFoundException();
            } else throw new BonsaiNotFoundException();
        } else throw new OwnerNotFoundException();
    }
}
