package fr.bryanprolong.monpetitbonsai.authentication.exposition.controllers;

import fr.bryanprolong.monpetitbonsai.authentication.domain.UserService;
import fr.bryanprolong.monpetitbonsai.authentication.exposition.dto.UserCreationRequestDTO;
import fr.bryanprolong.monpetitbonsai.authentication.modelMapper.UserCreationRequestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserCreationRequestDTO userCreationRequestDTO) {
        if(userCreationRequestDTO.getUsername() == null || (!Objects.equals(userCreationRequestDTO.getPassword(), userCreationRequestDTO.getConfirm_password()))) return ResponseEntity.badRequest().build();
        else {
            userService.create(UserCreationRequestMapper.mapUserCreationRequestDTOToUserCreationRequest(userCreationRequestDTO));
            return ResponseEntity.created(URI.create("ea")).build();
        }
    }
}
