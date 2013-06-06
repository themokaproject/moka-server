package fr.utc.nf28.moka.rest.resource;

import fr.utc.nf28.moka.environment.MokaEnvironment;
import fr.utc.nf28.moka.environment.items.MokaItem;
import fr.utc.nf28.moka.util.JSONParserUtils;

import javax.ws.rs.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@Path("/item/list")
public class MokaItemResource extends MokaRestResource {
	@Override
	public String serialize() throws IOException {
		//TODO improve way to reverse items....
		final ArrayList<MokaItem> list = new ArrayList<MokaItem>(MokaEnvironment.getInstance().getItems().values());
		Collections.reverse(list);
		return JSONParserUtils.serializeToJson(list);
	}
}
