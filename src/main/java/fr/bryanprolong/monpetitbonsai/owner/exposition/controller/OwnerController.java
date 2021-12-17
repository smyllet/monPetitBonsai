package fr.bryanprolong.monpetitbonsai.owner.exposition.controller;

import fr.bryanprolong.monpetitbonsai.owner.domain.model.Bonsai;
import fr.bryanprolong.monpetitbonsai.owner.domain.model.Owner;
import fr.bryanprolong.monpetitbonsai.owner.domain.service.OwnerService;
import fr.bryanprolong.monpetitbonsai.owner.exposition.dto.BonsaiDTO;
import fr.bryanprolong.monpetitbonsai.owner.exposition.dto.OwnerDTO;
import fr.bryanprolong.monpetitbonsai.owner.modelMapper.BonsaiMapper;
import fr.bryanprolong.monpetitbonsai.owner.modelMapper.OwnerMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public List<OwnerDTO> getOwners() {
        return ownerService.findAll().stream()
                .map(OwnerMapper::mapOwnerToOwnerDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<OwnerDTO> getOwnerByUUID(@PathVariable("uuid") String uuid) {
        return ownerService.findById(UUID.fromString(uuid))
                .map(OwnerMapper::mapOwnerToOwnerDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OwnerDTO> addOwner(@RequestBody OwnerDTO ownerDTO, @RequestHeader HttpHeaders headers) {
        Owner ownerInput = OwnerMapper.mapOwnerDTOToOwner(ownerDTO);
        Owner ownerOutput = ownerService.create(ownerInput);
        return ResponseEntity.created(URI.create("http://" + headers.getHost() + "/owners/" + ownerOutput.getId())).body(OwnerMapper.mapOwnerToOwnerDTO(ownerOutput));
    }

    @GetMapping("/{uuid}/bonsais")
    public ResponseEntity<List<BonsaiDTO>> getBonsaiByOwnerID(@PathVariable("uuid") String uuid) {
        Optional<Owner> ownerOptional = ownerService.findById(UUID.fromString(uuid));

        if(ownerOptional.isPresent()) {
            List<Bonsai> bonsais = ownerOptional.get().getBonsais();

            return ResponseEntity.ok(bonsais.stream()
                    .map(BonsaiMapper::mapBonsaiToBonsaiDTO)
                    .collect(Collectors.toList())
            );
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
