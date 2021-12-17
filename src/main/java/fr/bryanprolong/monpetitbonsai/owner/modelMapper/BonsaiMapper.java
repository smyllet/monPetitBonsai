package fr.bryanprolong.monpetitbonsai.owner.modelMapper;

import fr.bryanprolong.monpetitbonsai.commons.entity.BonsaiEntity;
import fr.bryanprolong.monpetitbonsai.owner.domain.model.Bonsai;
import fr.bryanprolong.monpetitbonsai.owner.exposition.dto.OwnerBonsaiDTO;

public class BonsaiMapper {
    public static Bonsai mapBonsaiDTOtoBonsai(OwnerBonsaiDTO bonsaiDTO) {
        Bonsai bonsai = new Bonsai();

        bonsai.setId(bonsaiDTO.getId());
        bonsai.setName(bonsaiDTO.getName());
        bonsai.setAcquisition_age(bonsaiDTO.getAge());
        bonsai.setSpecies(bonsaiDTO.getSpecies());

        return bonsai;
    }

    public static OwnerBonsaiDTO mapBonsaiToBonsaiDTO(Bonsai bonsai) {
        OwnerBonsaiDTO bonsaiDTO = new OwnerBonsaiDTO();

        bonsaiDTO.setId(bonsai.getId());
        bonsaiDTO.setName(bonsai.getName());
        bonsaiDTO.setAge(bonsai.getAcquisition_age());
        bonsaiDTO.setSpecies(bonsai.getSpecies());

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
