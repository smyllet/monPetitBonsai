package fr.bryanprolong.monpetitbonsai.authentication.modelMapper;

import fr.bryanprolong.monpetitbonsai.authentication.domain.model.PasswordChangeRequest;
import fr.bryanprolong.monpetitbonsai.authentication.exposition.dto.PasswordChangeRequestDTO;

public class PasswordChangeRequestMapper {
    public static PasswordChangeRequest mapPasswordChangeRequestDTOToPasswordChangeRequest(PasswordChangeRequestDTO passwordChangeRequestDTO) {
        PasswordChangeRequest passwordChangeRequest = new PasswordChangeRequest();

        passwordChangeRequest.setOld_password(passwordChangeRequestDTO.getOld_password());
        passwordChangeRequest.setPassword(passwordChangeRequestDTO.getPassword());
        passwordChangeRequest.setConfirm_password(passwordChangeRequestDTO.getConfirm_password());

        return passwordChangeRequest;
    }
}
