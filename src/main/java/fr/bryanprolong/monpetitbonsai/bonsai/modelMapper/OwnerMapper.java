package fr.bryanprolong.monpetitbonsai.bonsai.modelMapper;

import fr.bryanprolong.monpetitbonsai.bonsai.domain.model.Owner;
import fr.bryanprolong.monpetitbonsai.commons.entity.OwnerEntity;

import java.util.UUID;

public class OwnerMapper {
    public static Owner mapOwnerIdToOwner(UUID owner_id) {
        if(owner_id != null) {
            Owner owner = new Owner();
            owner.setId(owner_id);
            return owner;
        } else return null;
    }

    public static Owner mapOwnerEntityToOwner(OwnerEntity ownerEntity) {
        if(ownerEntity != null) {
            Owner owner = new Owner();

            owner.setId(ownerEntity.getId());
            owner.setName(ownerEntity.getName());

            return owner;
        } else return null;
    }

    public static OwnerEntity mapOwnerToOwnerEntity(Owner owner) {
        if(owner != null) {
            OwnerEntity ownerEntity = new OwnerEntity();

            ownerEntity.setId(owner.getId());
            ownerEntity.setName(owner.getName());

            return ownerEntity;
        } else return null;
    }
}
