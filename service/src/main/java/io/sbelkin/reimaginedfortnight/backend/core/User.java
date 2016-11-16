package io.sbelkin.reimaginedfortnight.backend.core;

import java.security.Principal;
import java.util.Set;

/**
 * Created by sbelkin on 11/8/2016.
 */
public class User implements Principal {

    private final String name;

    private final Set<String> roles;

    public User(String name) {
        this.name = name;
        this.roles = null;
    }

    public User(String name, Set<String> roles) {
        this.name = name;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
