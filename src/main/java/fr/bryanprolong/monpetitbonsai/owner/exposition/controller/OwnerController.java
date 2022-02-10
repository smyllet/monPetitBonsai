package fr.bryanprolong.monpetitbonsai.owner.exposition.controller;

import fr.bryanprolong.monpetitbonsai.authentication.domain.AppUser;
import fr.bryanprolong.monpetitbonsai.commons.type.AuthorityType;
import fr.bryanprolong.monpetitbonsai.owner.domain.exception.BonsaiNotFoundException;
import fr.bryanprolong.monpetitbonsai.owner.domain.exception.OwnerNotFoundException;
import fr.bryanprolong.monpetitbonsai.owner.domain.model.Bonsai;
import fr.bryanprolong.monpetitbonsai.owner.domain.model.Owner;
import fr.bryanprolong.monpetitbonsai.owner.domain.service.OwnerService;
import fr.bryanprolong.monpetitbonsai.owner.exposition.dto.OwnerBonsaiDTO;
import fr.bryanprolong.monpetitbonsai.owner.exposition.dto.OwnerDTO;
import fr.bryanprolong.monpetitbonsai.owner.modelMapper.BonsaiMapper;
import fr.bryanprolong.monpetitbonsai.owner.modelMapper.OwnerMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Arrays;
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

    @PreAuthorize("hasAuthority('ADMIN')")
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
    public ResponseEntity<List<OwnerBonsaiDTO>> getBonsaiByOwnerID(@PathVariable("uuid") String uuid) {
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

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteOwner(@PathVariable("uuid") String uuid) {
        try {
            ownerService.deleteById(UUID.fromString(uuid));
            return ResponseEntity.noContent().build();
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{owner_id}/bonsais/{bonsai_id}/transfer")
    public ResponseEntity<OwnerBonsaiDTO> transferBonsaiToAnOwner(@PathVariable("owner_id") String owner_id, @PathVariable("bonsai_id") String bonsai_id, @RequestBody String new_owner_id) {
        try {
            AppUser credentials = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            boolean isAdmin = credentials.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN"));
            boolean isOwner = credentials.getId().toString().equals(owner_id);

            if(isAdmin || isOwner) {
                Bonsai bonsai = ownerService.transferBonsaiToAnOwner(UUID.fromString(owner_id), UUID.fromString(bonsai_id), UUID.fromString(new_owner_id));

                OwnerBonsaiDTO bonsaiDTO = BonsaiMapper.mapBonsaiToBonsaiDTO(bonsai);

                return ResponseEntity.ok(bonsaiDTO);
            } else return ResponseEntity.status(403).build();
        } catch (BonsaiNotFoundException | OwnerNotFoundException bonsaiNotFoundException) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{owner_id}/bonsais")
    public ResponseEntity<Void> updateOwnerOfBonsaisWithNoOwner(@PathVariable("owner_id") String owner_id, @RequestBody List<String> bonsaisUUID) {
        try {
            ownerService.updateOwnerOfBonsaisWithNoOwner(UUID.fromString(owner_id), bonsaisUUID.stream().map(UUID::fromString).collect(Collectors.toList()));
            return ResponseEntity.noContent().build();
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
