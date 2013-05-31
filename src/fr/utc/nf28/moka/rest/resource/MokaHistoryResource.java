package fr.utc.nf28.moka.rest.resource;

import fr.utc.nf28.moka.environment.MokaEnvironment;
import fr.utc.nf28.moka.util.JSONParserUtils;

import javax.ws.rs.Path;
import java.io.IOException;

@Path("/history/list")
public class MokaHistoryResource extends MokaRestResource {
	@Override
	public String serialize() throws IOException {
		return JSONParserUtils.serializeHistory(MokaEnvironment.getInstance().getHistory());
	}
}
