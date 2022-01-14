package fr.bryanprolong.monpetitbonsai.authentication.modelMapper;

import fr.bryanprolong.monpetitbonsai.authentication.domain.model.User;
import fr.bryanprolong.monpetitbonsai.authentication.exposition.dto.UserDTO;
import fr.bryanprolong.monpetitbonsai.commons.entity.AuthorityEntity;
import fr.bryanprolong.monpetitbonsai.commons.entity.UserEntity;

import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO mapModelToDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEnabled(user.isEnabled());
        userDTO.setAuthority(user.getAuthority());

        return userDTO;
    }

    public static User mapEntityToModel(UserEntity userEntity) {
        User user = new User();

        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setEnabled(userEntity.isEnabled());
        user.setAuthority(userEntity.getAuthorities().stream().map(AuthorityEntity::getAuthority).collect(Collectors.toList()));

        return user;
    }
}
