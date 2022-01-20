package fr.bryanprolong.monpetitbonsai.authentication.exposition.controllers;

import fr.bryanprolong.monpetitbonsai.authentication.domain.AppUser;
import fr.bryanprolong.monpetitbonsai.authentication.domain.UserService;
import fr.bryanprolong.monpetitbonsai.authentication.domain.exception.InvalidePasswordException;
import fr.bryanprolong.monpetitbonsai.authentication.domain.model.User;
import fr.bryanprolong.monpetitbonsai.authentication.exposition.dto.PasswordChangeRequestDTO;
import fr.bryanprolong.monpetitbonsai.authentication.exposition.dto.UserCreationRequestDTO;
import fr.bryanprolong.monpetitbonsai.authentication.exposition.dto.UserDTO;
import fr.bryanprolong.monpetitbonsai.authentication.modelMapper.PasswordChangeRequestMapper;
import fr.bryanprolong.monpetitbonsai.authentication.modelMapper.UserCreationRequestMapper;
import fr.bryanprolong.monpetitbonsai.authentication.modelMapper.UserMapper;
import fr.bryanprolong.monpetitbonsai.commons.type.AuthorityType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @PutMapping("/me/password")
    public ResponseEntity<Void> changePassword(@RequestBody PasswordChangeRequestDTO passwordChangeRequestDTO) {
        if(Objects.equals(passwordChangeRequestDTO.getPassword(), passwordChangeRequestDTO.getConfirm_password())) {
            AppUser credentials = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            try {
                userService.updatePasswordUserByUsername(credentials.getUsername(), PasswordChangeRequestMapper.mapPasswordChangeRequestDTOToPasswordChangeRequest(passwordChangeRequestDTO));
                return ResponseEntity.ok().build();
            } catch (InvalidePasswordException e) {
                return ResponseEntity.status(403).build();
            } catch (Exception e) {
                return ResponseEntity.status(500).build();
            }
        } else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getProfil() {
        AppUser credentials = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(UserMapper.mapModelToDTO(userService.getUserByUsername(credentials.getUsername())));
    }

    @PreAuthorize("hasRole('STAFF')")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAllUser().stream().map(UserMapper::mapModelToDTO).collect(Collectors.toList()));
    }

    @PutMapping("/{username}/authority")
    public ResponseEntity<User> updateUserAuthority(@PathVariable String username, @RequestBody AuthorityType authorityType) {
        User user = userService.changeAuthorityUserByUsername(username, authorityType);
        return ResponseEntity.ok(user);
    }
}
