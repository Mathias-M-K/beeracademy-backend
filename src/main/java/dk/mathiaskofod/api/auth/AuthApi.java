package dk.mathiaskofod.api.auth;

import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;

@Path("/auth")
public class AuthApi {

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/permit-all")
    @Produces(MediaType.TEXT_PLAIN)
    public String permitAll(@Context SecurityContext securityContext) {
        return getResponseString(securityContext);
    }

    @GET
    @Path("roles-allowed")
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.TEXT_PLAIN)
    public String playersAllowed(@Context SecurityContext securityContext) {
        return getResponseString(securityContext);
    }

    @GET
    @Path("token")
    @Produces(MediaType.TEXT_PLAIN)
    public String getToken(){
        return Jwt.issuer("https://example.com/issuer")
                .subject("Steffen")
                .groups(new HashSet<>(List.of("User", "Admin")))
                .claim(Claims.birthdate.name(), "2001-07-13")
                .claim("gameId", 12345)
                .claim("playerId", 67890)
                .expiresIn(Duration.ofHours(5))
                .sign();
    }

    private String getResponseString(SecurityContext ctx) {
        String name;
        if (ctx.getUserPrincipal() == null) {
            name = "anonymous";
        } else if (!ctx.getUserPrincipal().getName().equals(jwt.getName())) {
            throw new InternalServerErrorException("Principal and JsonWebToken names do not match");
        } else {
            name = ctx.getUserPrincipal().getName();
        }
        return String.format("hello %s,"
                        + " isHttps: %s,"
                        + " authScheme: %s,"
                        + " hasJWT: %s",
                name, ctx.isSecure(), ctx.getAuthenticationScheme(), hasJwt());
    }

    private boolean hasJwt() {
        return jwt.getClaimNames() != null;
    }
}
