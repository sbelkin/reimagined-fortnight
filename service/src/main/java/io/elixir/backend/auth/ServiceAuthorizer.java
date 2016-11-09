package io.elixir.backend.auth;

import io.dropwizard.auth.Authorizer;
import io.elixir.backend.core.User;

/**
 * Created by sbelkin on 11/8/2016.
 */
public class ServiceAuthorizer implements Authorizer<User> {

    @Override
    public boolean authorize(User user, String role) {
        return user.getRoles() != null && user.getRoles().contains(role);
    }
}
