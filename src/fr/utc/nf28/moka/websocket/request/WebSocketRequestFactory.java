package fr.utc.nf28.moka.websocket.request;

import java.util.HashMap;

public class WebSocketRequestFactory {
	private static final String TYPE_ADD_ITEM = "addItem";
	private static final String TYPE_REMOVE_ITEM = "removeItem";
	private static final String TYPE_ADD_USER = "addUser";
	private static final String TYPE_REMOVE_USER = "removeUser";
	private static final String TYPE_MOVE_ITEM = "moveItem";
	private static final String TYPE_SELECT_ITEM = "selectItem";

	/**
	 * Create a {@link WebSocketRequest} that is aimed at
	 * adding a new item on the client platforms
	 *
	 * @param 	type the type of the new item
	 * @param 	itemId the id of the new item
	 * @return  {@link WebSocketRequest}
	 */
	public static WebSocketRequest createAddItemRequest(String type, String itemId) {
		HashMap<String, String> content = new HashMap<String, String>();
		content.put("type", type);
		content.put("itemId", itemId);
		return new WebSocketRequest(TYPE_ADD_ITEM, content);
	}

	/**
	 * Create a {@link WebSocketRequest} that is aimed at
	 * removing an item form the client platforms
	 *
	 * @param 	itemId the id of the item
	 * @return  {@link WebSocketRequest}
	 */
	public static WebSocketRequest createRemoveItemRequest(String itemId) {
		HashMap<String, String> content = new HashMap<String, String>();
		content.put("itemId", itemId);
		return  new WebSocketRequest(TYPE_REMOVE_ITEM, content);
	}

	/**
	 * Create a {@link WebSocketRequest} that is aimed at
	 * adding a new user on the client platforms
	 *
	 * @param 	userId the id of the new user
	 * @param 	name the name of the user
	 * @return  {@link WebSocketRequest}
	 */
	public static WebSocketRequest createAddUserRequest(String userId, String name) {
		HashMap<String, String> content = new HashMap<String, String>();
		content.put("userId", userId);
		content.put("name", name);
		return  new WebSocketRequest(TYPE_ADD_USER, content);
	}

	/**
	 * Create a {@link WebSocketRequest} that is aimed at
	 * removing a user from the client platforms
	 *
	 * @param 	userId the id of the user
	 * @return  {@link WebSocketRequest}
	 */
	public static WebSocketRequest createRemoveUserRequest(String userId) {
		HashMap<String, String> content = new HashMap<String, String>();
		content.put("userId", userId);
		return  new WebSocketRequest(TYPE_REMOVE_USER, content);
	}

	/**
	 * Create a {@link WebSocketRequest} that is aimed at
	 * moving an item on the client platforms.
	 *
	 * @param 	itemId the id of the id
	 * @param 	left the left position of the item
	 * @param 	top the top position of the item
	 * @return  {@link WebSocketRequest}
	 */
	public static WebSocketRequest createMoveItemRequest(String itemId, int left, int top) {
		HashMap<String, String> content = new HashMap<String, String>();
		content.put("itemId", itemId);
		content.put("left", String.valueOf(left));
		content.put("top", String.valueOf(top));
		return new WebSocketRequest(TYPE_MOVE_ITEM, content);
	}

	/**
	 * Create a {@link WebSocketRequest} that is aimed at
	 * selecting a item by a user on the client platforms.
	 *
	 * @param 	userId the id of the user
	 * @param 	itemId the id of the item
	 * @return  {@link WebSocketRequest}
	 */
	public static WebSocketRequest createSelectItemRequest(String userId, String itemId) {
		HashMap<String, String> content = new HashMap<String, String>();
		content.put("userId", userId);
		content.put("itemId", itemId);
		return new WebSocketRequest(TYPE_SELECT_ITEM, content);
	}

}
