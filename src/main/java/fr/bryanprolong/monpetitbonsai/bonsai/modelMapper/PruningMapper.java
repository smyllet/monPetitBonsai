package fr.bryanprolong.monpetitbonsai.bonsai.modelMapper;

import fr.bryanprolong.monpetitbonsai.bonsai.domain.model.Pruning;
import fr.bryanprolong.monpetitbonsai.bonsai.domain.model.Repotting;
import fr.bryanprolong.monpetitbonsai.commons.entity.PruningEntity;
import fr.bryanprolong.monpetitbonsai.commons.entity.RepottingEntity;

public class PruningMapper {
    public static Pruning mapPruningEntityToPruning(PruningEntity pruningEntity) {
        Pruning pruning = new Pruning();

        pruning.setId(pruningEntity.getId());
        pruning.setDatetime(pruningEntity.getDatetime());

        return pruning;
    }
}
