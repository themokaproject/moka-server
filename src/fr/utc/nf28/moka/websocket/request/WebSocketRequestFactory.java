package fr.utc.nf28.moka.websocket.request;

import java.util.HashMap;

public class WebSocketRequestFactory {
	private static final String TYPE_ADD_ITEM = "addItem";
	private static final String TYPE_REMOVE_ITEM = "removeItem";
	private static final String TYPE_ADD_USER = "addUser";
	private static final String TYPE_REMOVE_USER = "removeUser";
	private static final String TYPE_MOVE_ITEM = "moveItem";
	private static final String TYPE_SELECT_ITEM = "selectItem";

	public static WebSocketRequest createAddItemRequest(String type, String itemId) {
		HashMap<String, String> content = new HashMap<String, String>();
		content.put("type", type);
		content.put("itemId", itemId);
		return createWebSocket(TYPE_ADD_ITEM, content);
	}

	public static WebSocketRequest createRemoveItemRequest(String itemId) {
		HashMap<String, String> content = new HashMap<String, String>();
		content.put("itemId", itemId);
		return  new WebSocketRequest(TYPE_REMOVE_ITEM, content);
	}

	public static WebSocketRequest createAddUserRequest(String userId, String name) {
		HashMap<String, String> content = new HashMap<String, String>();
		content.put("userId", userId);
		content.put("name", name);
		return  new WebSocketRequest(TYPE_ADD_USER, content);
	}

	public static WebSocketRequest createRemoveUserRequest(String userId) {
		HashMap<String, String> content = new HashMap<String, String>();
		content.put("userId", userId);
		return  new WebSocketRequest(TYPE_REMOVE_USER, content);
	}

	public static WebSocketRequest createMoveItemRequest(String itemId, int left, int top) {
		HashMap<String, String> content = new HashMap<String, String>();
		content.put("itemId", itemId);
		content.put("left", String.valueOf(left));
		content.put("top", String.valueOf(top));
		return new WebSocketRequest(TYPE_MOVE_ITEM, content);
	}

	public static WebSocketRequest createSelectItemRequest(String userId, String itemId) {
		HashMap<String, String> content = new HashMap<String, String>();
		content.put("userId", userId);
		content.put("itemId", itemId);
		return new WebSocketRequest(TYPE_SELECT_ITEM, content);
	}

	private static WebSocketRequest createWebSocket(String type, Object content) {
		return new WebSocketRequest(type, content);
	}
}
