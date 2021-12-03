package fr.bryanprolong.monpetitbonsai.bonsai.modelMapper;

import fr.bryanprolong.monpetitbonsai.bonsai.domain.model.Watering;
import fr.bryanprolong.monpetitbonsai.bonsai.exposition.dto.WateringDTO;
import fr.bryanprolong.monpetitbonsai.commons.entity.WateringEntity;

public class WateringMapper {
    public static Watering mapWateringEntityToWatering(WateringEntity wateringEntity) {
        Watering watering = new Watering();

        watering.setId(wateringEntity.getId());
        watering.setDatetime(wateringEntity.getDatetime());

        return watering;
    }

    public static WateringDTO mapWateringToWateringDTO(Watering watering) {
        WateringDTO wateringDTO = new WateringDTO();

        wateringDTO.setId(watering.getId());
        wateringDTO.setDatetime(watering.getDatetime());

        return wateringDTO;
    }
}
