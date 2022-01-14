package fr.bryanprolong.monpetitbonsai.authentication.domain.model;

import java.util.List;
import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private boolean enabled;
    private List<String> authority;

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getAuthority() {
        return authority;
    }

    public void setAuthority(List<String> authority) {
        this.authority = authority;
    }
}
