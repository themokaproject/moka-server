package fr.utc.nf28.moka.rest.resource;

import fr.utc.nf28.moka.environment.MokaEnvironment;
import fr.utc.nf28.moka.util.JSONParserUtils;

import javax.ws.rs.Path;
import java.io.IOException;

@Path("/item/list")
public class MokaItemResource extends MokaRestResource {
	@Override
	public String serialize() throws IOException {
		return JSONParserUtils.serializeToJson(MokaEnvironment.getInstance().getItems().values());
	}
}
