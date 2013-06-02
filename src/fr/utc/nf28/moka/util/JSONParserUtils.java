package fr.utc.nf28.moka.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.utc.nf28.moka.agents.A2ATransaction;
import fr.utc.nf28.moka.environment.HistoryEntry;
import fr.utc.nf28.moka.environment.MokaEnvironment;
import fr.utc.nf28.moka.environment.items.*;
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

	public static String serializeToJson(final Object object) throws IOException {
		return  sMapper.writeValueAsString(object);
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

	public static User deserializeUser(final String json) throws IOException {
		return sMapper.readValue(json, User.class);
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
		} else if (className.equals("video")) {
            return sMapper.treeToValue(itemNode, VideoLink.class);
        }
		return null;
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

	public static List<HistoryEntry> deserializeHistoryEntries(String json) throws  IOException {
		return sMapper.readValue(json, new TypeReference<List<HistoryEntry>>(){});
	}

}
