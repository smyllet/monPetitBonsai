package fr.bryanprolong.monpetitbonsai.bonsai.exposition.controller;

import fr.bryanprolong.monpetitbonsai.bonsai.domain.exception.BonsaiNotFoundException;
import fr.bryanprolong.monpetitbonsai.bonsai.domain.model.Bonsai;
import fr.bryanprolong.monpetitbonsai.bonsai.domain.service.BonsaiService;
import fr.bryanprolong.monpetitbonsai.bonsai.exposition.dto.BonsaiDTO;
import fr.bryanprolong.monpetitbonsai.bonsai.modelMapper.BonsaiMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bonsais")
public class BonsaiController {
    private final BonsaiService bonsaiService;

    public BonsaiController(BonsaiService bonsaiService) {
        this.bonsaiService = bonsaiService;
    }

    @GetMapping
    public List<BonsaiDTO> getBonsais() {
        return bonsaiService.findAll().stream()
                .map(BonsaiMapper::mapBonsaiToBonsaiDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<BonsaiDTO> getBonsaiByUUID(@PathVariable("uuid") String uuid) {
        return bonsaiService.findById(UUID.fromString(uuid))
                .map(BonsaiMapper::mapBonsaiToBonsaiDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public BonsaiDTO addBonsai(@RequestBody BonsaiDTO bonsaiDTO) {
        Bonsai bonsaiInput = BonsaiMapper.mapBonsaiDTOtoBonsai(bonsaiDTO);
        Bonsai bonsaiOutput = bonsaiService.create(bonsaiInput);
        return BonsaiMapper.mapBonsaiToBonsaiDTO(bonsaiOutput);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteBonsai(@PathVariable("uuid") String uuid) {
        try {
            bonsaiService.deleteById(UUID.fromString(uuid));
            return ResponseEntity.noContent().build();
        } catch (BonsaiNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
