package fr.utc.nf28.moka.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.utc.nf28.moka.agents.itemedition.EditionRequest;
import fr.utc.nf28.moka.environment.items.LockingRequest;
import fr.utc.nf28.moka.environment.items.MokaItem;
import fr.utc.nf28.moka.environment.users.User;
import fr.utc.nf28.moka.websocket.request.WebSocketRequest;

import java.io.IOException;
import java.io.StringWriter;

/**
 * a JSON serializer/deserializer that uses Jackson
 */
public class JSONParserUtils {
    private static final ObjectMapper sMapper = new ObjectMapper();

    public static String serializeUser(User user) throws IOException {
        StringWriter sw = new StringWriter();
        sMapper.writeValue(sw, user);
        return sw.toString();
    }

    public static User deserializeUser(final String json) throws IOException {
        return sMapper.readValue(json, User.class);
    }

    public static String serializeItem(MokaItem item) throws IOException{
        StringWriter sw = new StringWriter();
        sMapper.writeValue(sw, item);
        return sw.toString();
    }

    public static MokaItem deserializeItem(final String json) throws IOException {
        return sMapper.readValue(json, MokaItem.class);
    }

    public static String serializeLockingRequest(LockingRequest request) throws IOException{
        StringWriter sw = new StringWriter();
        sMapper.writeValue(sw, request);
        return sw.toString();
    }
    public static LockingRequest deserializeLockingRequest(final String json) throws IOException {
        return sMapper.readValue(json, LockingRequest.class);
    }

	public static String serializeWebSocketRequest(WebSocketRequest request) throws IOException {
		return sMapper.writeValueAsString(request);
	}

    public static EditionRequest deserializeEditionRequest(String editionRequest) throws  IOException{
        return  sMapper.readValue(editionRequest, EditionRequest.class);
    }
}
