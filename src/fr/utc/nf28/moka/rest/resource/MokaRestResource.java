package fr.utc.nf28.moka.rest.resource;

import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Singleton
public abstract class MokaRestResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String get() {
		try {
			return serialize();
		} catch (IOException e) {
			return null;
		}
	}

	public abstract String serialize() throws IOException;
}
