package fr.bryanprolong.monpetitbonsai.owner.modelMapper;

import fr.bryanprolong.monpetitbonsai.commons.entity.OwnerEntity;
import fr.bryanprolong.monpetitbonsai.owner.domain.model.Owner;
import fr.bryanprolong.monpetitbonsai.owner.exposition.dto.OwnerDTO;

import java.util.stream.Collectors;

public class OwnerMapper {
    public static Owner mapOwnerDTOToOwner(OwnerDTO ownerDTO) {
        Owner owner = new Owner();

        owner.setId(ownerDTO.getId());
        owner.setName(ownerDTO.getName());

        owner.setBonsais(ownerDTO.getBonsais().stream()
                .map(BonsaiMapper::mapBonsaiDTOtoBonsai)
                .collect(Collectors.toList())
        );

        return owner;
    }

    public static OwnerDTO mapOwnerToOwnerDTO(Owner owner) {
        OwnerDTO ownerDTO = new OwnerDTO();

        ownerDTO.setId(owner.getId());
        ownerDTO.setName(owner.getName());

        ownerDTO.setBonsais(owner.getBonsais().stream()
                .map(BonsaiMapper::mapBonsaiToBonsaiDTO)
                .collect(Collectors.toList())
        );

        return ownerDTO;
    }

    public static Owner mapOwnerEntityToOwner(OwnerEntity ownerEntity) {
        Owner owner = new Owner();

        owner.setId(ownerEntity.getId());
        owner.setName(ownerEntity.getName());

        owner.setBonsais(ownerEntity.getBonsais().stream()
                .map(BonsaiMapper::mapBonsaiEntityToBonsai)
                .collect(Collectors.toList())
        );

        return owner;
    }

    public static OwnerEntity mapOwnerToOwnerEntity(Owner owner) {
        OwnerEntity ownerEntity = new OwnerEntity();

        ownerEntity.setId(owner.getId());
        ownerEntity.setName(owner.getName());

        ownerEntity.setBonsais(owner.getBonsais().stream()
                .map(BonsaiMapper::mapBonsaiToBonsaiEntity)
                .collect(Collectors.toList())
        );

        return ownerEntity;
    }
}
