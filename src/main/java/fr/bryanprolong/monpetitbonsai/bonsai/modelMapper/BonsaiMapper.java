package fr.bryanprolong.monpetitbonsai.bonsai.modelMapper;

import fr.bryanprolong.monpetitbonsai.bonsai.domain.model.Bonsai;
import fr.bryanprolong.monpetitbonsai.bonsai.exposition.dto.BonsaiDTO;
import fr.bryanprolong.monpetitbonsai.bonsai.infrastructure.entity.BonsaiEntity;

public class BonsaiMapper {
    public static Bonsai mapBonsaiDTOtoBonsai(BonsaiDTO bonsaiDTO) {
        Bonsai bonsai = new Bonsai();

        bonsai.setId(bonsaiDTO.getId());
        bonsai.setName(bonsaiDTO.getName());
        bonsai.setAcquisition_age(bonsaiDTO.getAcquisition_age());
        bonsai.setSpecies(bonsaiDTO.getSpecies());
        bonsai.setStatus(bonsaiDTO.getStatus());
        bonsai.setAcquisition_date(bonsaiDTO.getAcquisition_date());

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

        return bonsaiEntity;
    }
}
