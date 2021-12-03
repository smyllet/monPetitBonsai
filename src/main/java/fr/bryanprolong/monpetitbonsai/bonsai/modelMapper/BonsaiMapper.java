package fr.bryanprolong.monpetitbonsai.bonsai.modelMapper;

import fr.bryanprolong.monpetitbonsai.bonsai.domain.model.Bonsai;
import fr.bryanprolong.monpetitbonsai.bonsai.exposition.dto.BonsaiDTO;
import fr.bryanprolong.monpetitbonsai.commons.entity.BonsaiEntity;

import java.util.stream.Collectors;

public class BonsaiMapper {
    public static Bonsai mapBonsaiDTOtoBonsai(BonsaiDTO bonsaiDTO) {
        Bonsai bonsai = new Bonsai();

        bonsai.setId(bonsaiDTO.getId());
        bonsai.setName(bonsaiDTO.getName());
        bonsai.setAcquisition_age(bonsaiDTO.getAcquisition_age());
        bonsai.setSpecies(bonsaiDTO.getSpecies());
        bonsai.setStatus(bonsaiDTO.getStatus());
        bonsai.setAcquisition_date(bonsaiDTO.getAcquisition_date());

        bonsai.setOwner(OwnerMapper.mapOwnerIdToOwner(bonsaiDTO.getOwner_id()));

        return bonsai;
    }

    public static BonsaiDTO mapBonsaiToBonsaiDTO(Bonsai bonsai) {
        BonsaiDTO bonsaiDTO = new BonsaiDTO();

        bonsaiDTO.setId(bonsai.getId());
        bonsaiDTO.setName(bonsai.getName());
        bonsaiDTO.setAcquisition_age(bonsai.getAcquisition_age());
        bonsaiDTO.setSpecies(bonsai.getSpecies());
        bonsaiDTO.setStatus(bonsai.getStatus());
        bonsaiDTO.setAcquisition_date(bonsai.getAcquisition_date());

        if(bonsai.getWaterings().size() > 0) {
            bonsaiDTO.setLast_watering(bonsai.getWaterings().get(bonsai.getWaterings().size()-1).getDatetime());
        }

        if(bonsai.getRepottings().size() > 0) {
            bonsaiDTO.setLast_repotting(bonsai.getRepottings().get(bonsai.getRepottings().size()-1).getDatetime());
        }

        if(bonsai.getPrunings().size() > 0) {
            bonsaiDTO.setLast_pruning(bonsai.getPrunings().get(bonsai.getPrunings().size()-1).getDatetime());
        }

        bonsaiDTO.setOwner_id(bonsai.getOwner().getId());

        return bonsaiDTO;
    }

    public static Bonsai mapBonsaiEntityToBonsai(BonsaiEntity bonsaiEntity) {
        Bonsai bonsai = new Bonsai();

        bonsai.setId(bonsaiEntity.getId());
        bonsai.setName(bonsaiEntity.getName());
        bonsai.setAcquisition_age(bonsaiEntity.getAcquisition_age());
        bonsai.setSpecies(bonsaiEntity.getSpecies());
        bonsai.setStatus(bonsaiEntity.getStatus());
        bonsai.setAcquisition_date(bonsaiEntity.getAcquisition_date());

        bonsai.setOwner(OwnerMapper.mapOwnerEntityToOwner(bonsaiEntity.getOwner()));

        bonsai.setWaterings(
                bonsaiEntity.getWaterings().stream()
                        .map(WateringMapper::mapWateringEntityToWatering)
                        .collect(Collectors.toList())
        );

        bonsai.setRepottings(
                bonsaiEntity.getRepottings().stream()
                        .map(RepottingMapper::mapRepottingEntityToRepotting)
                        .collect(Collectors.toList())
        );

        bonsai.setPrunings(
                bonsaiEntity.getPrunings().stream()
                        .map(PruningMapper::mapPruningEntityToPruning)
                        .collect(Collectors.toList())
        );

        return bonsai;
    }

    public static BonsaiEntity mapBonsaiToBonsaiEntity(Bonsai bonsai) {
        BonsaiEntity bonsaiEntity = new BonsaiEntity();

        bonsaiEntity.setId(bonsai.getId());
        bonsaiEntity.setName(bonsai.getName());
        bonsaiEntity.setAcquisition_age(bonsai.getAcquisition_age());
        bonsaiEntity.setSpecies(bonsai.getSpecies());
        bonsaiEntity.setStatus(bonsai.getStatus());
        bonsaiEntity.setAcquisition_date(bonsai.getAcquisition_date());

        bonsaiEntity.setOwner(OwnerMapper.mapOwnerToOwnerEntity(bonsai.getOwner()));

        return bonsaiEntity;
    }
}
