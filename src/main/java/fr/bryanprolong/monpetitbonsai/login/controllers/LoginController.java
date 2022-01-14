package fr.bryanprolong.monpetitbonsai.login.controllers;

import fr.bryanprolong.monpetitbonsai.authentication.domain.model.UserCredentials;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<Void> login(@RequestBody UserCredentials userCredentials) {
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredentials.getUsername(), userCredentials.getPassword(), new ArrayList<>()));
            if(auth.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(auth);
                return ResponseEntity.ok().build();
            }
            else return ResponseEntity.status(403).build();
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }
}
