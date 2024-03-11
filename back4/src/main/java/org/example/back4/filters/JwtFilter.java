package org.example.back4.filters;

import org.example.back4.utils.JWTManager;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;

@Provider
public class JwtFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {

        String authToken = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        String path = requestContext.getUriInfo().getPath();

        if (!path.startsWith("/data/")) {
            return;
        }

        if (authToken == null) {
            throw new ForbiddenException("No token provided");
        }

        boolean token_is_valid = JWTManager.validateToken(authToken);

        if (!token_is_valid) {
            throw new ForbiddenException("Invalid token");
        }

    }
}
