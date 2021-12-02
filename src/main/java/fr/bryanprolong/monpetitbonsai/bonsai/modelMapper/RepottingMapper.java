package fr.bryanprolong.monpetitbonsai.bonsai.modelMapper;

import fr.bryanprolong.monpetitbonsai.bonsai.domain.model.Repotting;
import fr.bryanprolong.monpetitbonsai.commons.entity.RepottingEntity;

public class RepottingMapper {
    public static Repotting mapRepottingEntityToRepotting(RepottingEntity repottingEntity) {
        Repotting repotting = new Repotting();

        repotting.setId(repottingEntity.getId());
        repotting.setDatetime(repottingEntity.getDatetime());

        return repotting;
    }
}
