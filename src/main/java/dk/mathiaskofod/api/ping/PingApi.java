package dk.mathiaskofod.api.ping;

import dk.mathiaskofod.api.ping.models.Pong;
import io.smallrye.common.annotation.NonBlocking;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/ping")
@NonBlocking
public class PingApi {

    @ConfigProperty(name = "quarkus.application.name", defaultValue = "unknown")
    String applicationName;

    @ConfigProperty(name = "env")
    String environment;

    @GET
    public Pong ping() {
        return Pong.create(applicationName, environment);
    }
}
