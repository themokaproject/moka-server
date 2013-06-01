package fr.utc.nf28.moka.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.utc.nf28.moka.agents.A2ATransaction;
import fr.utc.nf28.moka.agents.connection.ConnectionRequest;
import fr.utc.nf28.moka.agents.itemcreation.CreationRequest;
import fr.utc.nf28.moka.agents.itemedition.EditionRequest;
import fr.utc.nf28.moka.environment.HistoryEntry;
import fr.utc.nf28.moka.environment.items.LockingRequest;
import fr.utc.nf28.moka.environment.items.MokaItem;
import fr.utc.nf28.moka.environment.items.UmlClass;
import fr.utc.nf28.moka.environment.users.User;
import fr.utc.nf28.moka.websocket.request.WebSocketRequest;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;

/**
 * a JSON serializer/deserializer that uses Jackson
 */
public class JSONParserUtils {
	private static final ObjectMapper sMapper = new ObjectMapper();

	public static String serializeA2ATransaction(final A2ATransaction transaction) throws IOException {
		return sMapper.writeValueAsString(transaction);
	}

	public static A2ATransaction deserializeA2ATransaction(final String json) throws IOException {
		final JsonNode rootNode = sMapper.readTree(json);
		final JsonNode typeNode = rootNode.get("type");
		final JsonNode contentNode = rootNode.get("content");
		final JsonNode contentClassNode = rootNode.get("contentClass");

		if (typeNode != null && contentNode != null && contentClassNode != null) {
			Class contentClass = Object.class;
			try {
				contentClass = Class.forName(contentClassNode.asText());
			} catch (ClassNotFoundException e) {
				System.out.println("deserializeA2ATransaction:ClassNotFound : " + contentClassNode.asText());
				System.out.println("use " + contentClass.toString() + " instead");
			}
			Object content = sMapper.treeToValue(contentNode, contentClass);
			return new A2ATransaction(typeNode.asText(), content);
		}

		return null;
	}

	public static String serializeUser(User user) throws IOException {
		StringWriter sw = new StringWriter();
		sMapper.writeValue(sw, user);
		return sw.toString();
	}

	public static String serializeUsers(Collection<User> users) throws IOException {
		StringWriter sw = new StringWriter();
		sMapper.writeValue(sw, users);
		return sw.toString();
	}

	public static User deserializeUser(final String json) throws IOException {
		return sMapper.readValue(json, User.class);
	}

	public static String serializeItem(MokaItem item) throws IOException {
		StringWriter sw = new StringWriter();
		sMapper.writeValue(sw, item);
		return sw.toString();
	}

	public static String serializeItems(Collection<MokaItem> mokaItems) throws IOException {
		return sMapper.writeValueAsString(mokaItems);
	}

	public static MokaItem deserializeItem(final String json) throws IOException {
		// TODO deserialize the appropriate class using the "type" key
		return sMapper.readValue(json, UmlClass.class);
	}

	public static String serializeWebSocketRequest(WebSocketRequest request) throws IOException {
		return sMapper.writeValueAsString(request);
	}

	public static String serializeHistory(Collection<HistoryEntry> historyEntries) throws IOException {
		return sMapper.writeValueAsString(historyEntries);
	}
}
