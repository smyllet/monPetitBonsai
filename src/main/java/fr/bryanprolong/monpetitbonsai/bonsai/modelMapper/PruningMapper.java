package fr.bryanprolong.monpetitbonsai.bonsai.modelMapper;

import fr.bryanprolong.monpetitbonsai.bonsai.domain.model.Pruning;
import fr.bryanprolong.monpetitbonsai.bonsai.domain.model.Repotting;
import fr.bryanprolong.monpetitbonsai.bonsai.domain.model.Watering;
import fr.bryanprolong.monpetitbonsai.bonsai.exposition.dto.PruningDTO;
import fr.bryanprolong.monpetitbonsai.bonsai.exposition.dto.WateringDTO;
import fr.bryanprolong.monpetitbonsai.commons.entity.PruningEntity;
import fr.bryanprolong.monpetitbonsai.commons.entity.RepottingEntity;

public class PruningMapper {
    public static Pruning mapPruningEntityToPruning(PruningEntity pruningEntity) {
        Pruning pruning = new Pruning();

        pruning.setId(pruningEntity.getId());
        pruning.setDatetime(pruningEntity.getDatetime());

        return pruning;
    }

    public static PruningDTO mapPruningToPruningDTO(Pruning pruning) {
        PruningDTO pruningDTO = new PruningDTO();

        pruningDTO.setId(pruning.getId());
        pruningDTO.setDatetime(pruning.getDatetime());

        return pruningDTO;
    }
}
