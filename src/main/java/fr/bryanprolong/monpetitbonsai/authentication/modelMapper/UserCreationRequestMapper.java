package fr.bryanprolong.monpetitbonsai.authentication.modelMapper;

import fr.bryanprolong.monpetitbonsai.authentication.domain.model.UserCreationRequest;
import fr.bryanprolong.monpetitbonsai.authentication.exposition.dto.UserCreationRequestDTO;

public class UserCreationRequestMapper {
    public static UserCreationRequest mapUserCreationRequestDTOToUserCreationRequest(UserCreationRequestDTO userCreationRequestDTO) {
        UserCreationRequest userCreationRequest = new UserCreationRequest();

        userCreationRequest.setUsername(userCreationRequestDTO.getUsername());
        userCreationRequest.setPassword(userCreationRequestDTO.getPassword());

        return userCreationRequest;
    }
}
