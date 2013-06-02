package fr.utc.nf28.moka.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.utc.nf28.moka.agents.A2ATransaction;
import fr.utc.nf28.moka.environment.HistoryEntry;
import fr.utc.nf28.moka.environment.MokaEnvironment;
import fr.utc.nf28.moka.environment.items.ImageLink;
import fr.utc.nf28.moka.environment.items.MokaItem;
import fr.utc.nf28.moka.environment.items.PostIt;
import fr.utc.nf28.moka.environment.items.UmlClass;
import fr.utc.nf28.moka.environment.users.User;
import fr.utc.nf28.moka.websocket.request.WebSocketRequest;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

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
		return deserializeItem(sMapper.readTree(json));
	}

	public static MokaItem deserializeItem(final JsonNode itemNode) throws  IOException {
		String className = itemNode.path("type").asText();
		if (className.equals("umlClass")) {
			return sMapper.treeToValue(itemNode, UmlClass.class);
		} else if (className.equals("post-it")) {
			return sMapper.treeToValue(itemNode, PostIt.class);
		} else if (className.equals("image")) {
			return sMapper.treeToValue(itemNode, ImageLink.class);
		}
		return null;
	}

	public static String serializeWebSocketRequest(WebSocketRequest request) throws IOException {
		return sMapper.writeValueAsString(request);
	}

	public static String serializeHistory(Collection<HistoryEntry> historyEntries) throws IOException {
		return sMapper.writeValueAsString(historyEntries);
	}

	public static WebSocketRequest deserializeWebSocketRequest(String json) throws IOException {
		final JsonNode rootNode = sMapper.readTree(json);
		final JsonNode typeNode = rootNode.get("type");
		final JsonNode contentNode = rootNode.get("content");
		final WebSocketRequest result = new WebSocketRequest("DummyType");

		if (typeNode != null && contentNode != null) {
			result.setType(typeNode.asText());
			if (!contentNode.asText().isEmpty()) {
				HashMap<String, String> content = sMapper.readValue(contentNode.asText(), new TypeReference<HashMap<String, String>>() {
				});
				result.setContent(content);
			}
		}

		return result;
	}

	public static List<User> deserializeUsers(String json) throws IOException {
		return sMapper.readValue(json, new TypeReference<List<User>>() {});
	}

	public static List<MokaItem> deserializeItems(String json) throws IOException {
		final JsonNode rootNode = sMapper.readTree(json);
		final List<MokaItem> result = new ArrayList<MokaItem>();

		for (Iterator<JsonNode> iter = rootNode.elements(); iter.hasNext(); ) {
			MokaItem newItem = deserializeItem(iter.next());
			if(newItem != null) result.add(newItem);
		}
		return result;
	}

}
