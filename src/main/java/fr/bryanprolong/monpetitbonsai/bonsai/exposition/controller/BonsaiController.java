package fr.bryanprolong.monpetitbonsai.bonsai.exposition.controller;

import fr.bryanprolong.monpetitbonsai.bonsai.domain.exception.BonsaiNotFoundException;
import fr.bryanprolong.monpetitbonsai.bonsai.domain.model.Bonsai;
import fr.bryanprolong.monpetitbonsai.bonsai.domain.model.Pruning;
import fr.bryanprolong.monpetitbonsai.bonsai.domain.model.Repotting;
import fr.bryanprolong.monpetitbonsai.bonsai.domain.model.Watering;
import fr.bryanprolong.monpetitbonsai.bonsai.domain.service.BonsaiService;
import fr.bryanprolong.monpetitbonsai.commons.type.Status;
import fr.bryanprolong.monpetitbonsai.bonsai.exposition.dto.BonsaiDTO;
import fr.bryanprolong.monpetitbonsai.bonsai.exposition.dto.PruningDTO;
import fr.bryanprolong.monpetitbonsai.bonsai.exposition.dto.RepottingDTO;
import fr.bryanprolong.monpetitbonsai.bonsai.exposition.dto.WateringDTO;
import fr.bryanprolong.monpetitbonsai.bonsai.modelMapper.BonsaiMapper;
import fr.bryanprolong.monpetitbonsai.bonsai.modelMapper.PruningMapper;
import fr.bryanprolong.monpetitbonsai.bonsai.modelMapper.RepottingMapper;
import fr.bryanprolong.monpetitbonsai.bonsai.modelMapper.WateringMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
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
    public List<BonsaiDTO> getBonsais(@RequestParam(required = false) Status status, @RequestParam(required = false, defaultValue = "0") int olderThan) {
        return bonsaiService.findAll(status, olderThan).stream()
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
    public ResponseEntity<BonsaiDTO> addBonsai(@RequestBody BonsaiDTO bonsaiDTO, @RequestHeader HttpHeaders headers) {
        Bonsai bonsaiInput = BonsaiMapper.mapBonsaiDTOtoBonsai(bonsaiDTO);
        Bonsai bonsaiOutput = bonsaiService.create(bonsaiInput);
        return ResponseEntity.created(URI.create("http://" + headers.getHost() + "/bonsai/" + bonsaiOutput.getId())).body(BonsaiMapper.mapBonsaiToBonsaiDTO(bonsaiOutput));
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

    @PatchMapping("/{uuid}")
    public ResponseEntity<BonsaiDTO> updateById(@PathVariable("uuid") String uuid, @RequestBody BonsaiDTO bonsaiDTO) {
        try {
            Bonsai bonsaiInput = BonsaiMapper.mapBonsaiDTOtoBonsai(bonsaiDTO);
            Bonsai bonsaiOutput = bonsaiService.updateById(UUID.fromString(uuid), bonsaiInput);
            return ResponseEntity.ok(BonsaiMapper.mapBonsaiToBonsaiDTO(bonsaiOutput));
        } catch (BonsaiNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{uuid}/status")
    public ResponseEntity<BonsaiDTO> changeBonsaiStatusById(@PathVariable("uuid") String uuid, @RequestBody Status status) {
        try {
            bonsaiService.changeBonsaiStatusById(UUID.fromString(uuid), status);

            return ResponseEntity.noContent().build();
        } catch (BonsaiNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{uuid}/watering")
    public ResponseEntity<List<WateringDTO>> getWateringByBonsaiId(@PathVariable("uuid") String uuid) {
        Optional<Bonsai> optionalBonsai = bonsaiService.findById(UUID.fromString(uuid));

        if(optionalBonsai.isPresent()) {
            List<Watering> waterings = optionalBonsai.get().getWaterings();
            return ResponseEntity.ok(waterings.stream()
                    .map(WateringMapper::mapWateringToWateringDTO)
                    .collect(Collectors.toList())
            );
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{uuid}/pruning")
    public ResponseEntity<List<PruningDTO>> getPruningByBonsaiId(@PathVariable("uuid") String uuid) {
        Optional<Bonsai> optionalBonsai = bonsaiService.findById(UUID.fromString(uuid));

        if(optionalBonsai.isPresent()) {
            List<Pruning> prunings = optionalBonsai.get().getPrunings();
            return ResponseEntity.ok(prunings.stream()
                    .map(PruningMapper::mapPruningToPruningDTO)
                    .collect(Collectors.toList())
            );
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{uuid}/repotting")
    public ResponseEntity<List<RepottingDTO>> getRepottingByBonsaiId(@PathVariable("uuid") String uuid) {
        Optional<Bonsai> optionalBonsai = bonsaiService.findById(UUID.fromString(uuid));

        if(optionalBonsai.isPresent()) {
            List<Repotting> repottings = optionalBonsai.get().getRepottings();
            return ResponseEntity.ok(repottings.stream()
                    .map(RepottingMapper::mapRepottingToRepottingDTO)
                    .collect(Collectors.toList())
            );
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
