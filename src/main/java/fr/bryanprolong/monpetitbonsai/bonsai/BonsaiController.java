package fr.bryanprolong.monpetitbonsai.bonsai;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bonsais")
public class BonsaiController {
    private final BonsaiDao bonsaiDao;

    public BonsaiController(BonsaiDao bonsaiDao) {
        this.bonsaiDao = bonsaiDao;
    }

    @GetMapping
    public List<BonsaiEntity> getBonsais() {
        return bonsaiDao.findAll();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<BonsaiEntity> getBonsaiByUUID(@PathVariable("uuid") String uuid) {
        return bonsaiDao.findById(UUID.fromString(uuid)).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public BonsaiEntity addBonsai(@RequestBody BonsaiEntity bonsai) {
        return bonsaiDao.save(bonsai);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteBonsai(@PathVariable("uuid") String uuid) {
        try {
            bonsaiDao.deleteById(UUID.fromString(uuid));
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
