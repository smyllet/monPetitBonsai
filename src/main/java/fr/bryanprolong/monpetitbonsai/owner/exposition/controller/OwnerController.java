package fr.bryanprolong.monpetitbonsai.owner.exposition.controller;

import fr.bryanprolong.monpetitbonsai.owner.domain.service.OwnerService;
import fr.bryanprolong.monpetitbonsai.owner.exposition.dto.OwnerDTO;
import fr.bryanprolong.monpetitbonsai.owner.modelMapper.OwnerMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
}
