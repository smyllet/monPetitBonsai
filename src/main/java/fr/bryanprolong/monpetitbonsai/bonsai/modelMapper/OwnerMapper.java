package fr.bryanprolong.monpetitbonsai.bonsai.modelMapper;

import fr.bryanprolong.monpetitbonsai.bonsai.domain.model.Owner;
import fr.bryanprolong.monpetitbonsai.commons.entity.OwnerEntity;

import java.util.UUID;

public class OwnerMapper {
    public static Owner mapOwnerIdToOwner(UUID owner_id) {
        Owner owner = new Owner();
        owner.setId(owner_id);
        return owner;
    }

    public static Owner mapOwnerEntityToOwner(OwnerEntity ownerEntity) {
        Owner owner = new Owner();

        owner.setId(ownerEntity.getId());
        owner.setName(ownerEntity.getName());

        return owner;
    }

    public static OwnerEntity mapOwnerToOwnerEntity(Owner owner) {
        OwnerEntity ownerEntity = new OwnerEntity();

        ownerEntity.setId(owner.getId());
        ownerEntity.setName(owner.getName());

        return ownerEntity;
    }
}
