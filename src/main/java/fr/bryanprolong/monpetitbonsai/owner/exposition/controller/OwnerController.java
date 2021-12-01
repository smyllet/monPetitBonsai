package fr.bryanprolong.monpetitbonsai.owner.exposition.controller;

import fr.bryanprolong.monpetitbonsai.owner.domain.service.OwnerService;
import fr.bryanprolong.monpetitbonsai.owner.exposition.dto.OwnerDTO;
import fr.bryanprolong.monpetitbonsai.owner.modelMapper.OwnerMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
    public ResponseEntity<OwnerDTO> getBonsaiByUUID(@PathVariable("uuid") String uuid) {
        return ownerService.findById(UUID.fromString(uuid))
                .map(OwnerMapper::mapOwnerToOwnerDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
