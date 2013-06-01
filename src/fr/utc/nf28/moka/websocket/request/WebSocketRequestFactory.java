package fr.utc.nf28.moka.websocket.request;

import java.util.HashMap;

public class WebSocketRequestFactory {
	private static final String TYPE_ADD_ITEM = "addItem";
	private static final String TYPE_REMOVE_ITEM = "removeItem";
	private static final String TYPE_ADD_USER = "addUser";
	private static final String TYPE_REMOVE_USER = "removeUser";
	private static final String TYPE_MOVE_ITEM = "moveItem";
	private static final String TYPE_RESIZE_ITEM = "resizeItem";
	private static final String TYPE_SELECT_ITEM = "selectItem";
	private static final String TYPE_SAVE_WORSPACE = "saveWorkSpace";

	/**
	 * Create a {@link WebSocketRequest} that is aimed at
	 * adding a new item on the client platforms
	 *
	 * @param 	type the type of the new item
	 * @param 	itemId the id of the new item
	 * @return  {@link WebSocketRequest}
	 */
	public static WebSocketRequest createAddItemRequest(String type, int itemId, int left, int top) {
		WebSocketRequest request = new WebSocketRequest(TYPE_ADD_ITEM);
		request.put("type", type);
		request.put("itemId", String.valueOf(itemId));
		request.put("top", String.valueOf(top));
		request.put("left", String.valueOf(left));
		return request;
	}

	/**
	 * Create a {@link WebSocketRequest} that is aimed at
	 * removing an item form the client platforms
	 *
	 * @param 	itemId the id of the item
	 * @return  {@link WebSocketRequest}
	 */
	public static WebSocketRequest createRemoveItemRequest(String itemId) {
		WebSocketRequest request = new WebSocketRequest(TYPE_REMOVE_ITEM);
		request.put("itemId", itemId);
		return request;
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
		WebSocketRequest request = new WebSocketRequest(TYPE_ADD_USER);
		request.put("userId", userId);
		request.put("name", name);
		return request;
	}

	/**
	 * Create a {@link WebSocketRequest} that is aimed at
	 * removing a user from the client platforms
	 *
	 * @param 	userId the id of the user
	 * @return  {@link WebSocketRequest}
	 */
	public static WebSocketRequest createRemoveUserRequest(String userId) {
		WebSocketRequest request = new WebSocketRequest(TYPE_REMOVE_USER);
		request.put("userId", userId);
		return request;
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
	public static WebSocketRequest createMoveItemRequest(int itemId, int left, int top) {
		WebSocketRequest request = new WebSocketRequest(TYPE_MOVE_ITEM);
		request.put("itemId", String.valueOf(itemId));
		request.put("left", String.valueOf(left));
		request.put("top", String.valueOf(top));
		return request;
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
		WebSocketRequest request = new WebSocketRequest(TYPE_SELECT_ITEM);
		request.put("userId", userId);
		request.put("itemId", itemId);
		return request;
	}

	/**
	 * Create a {@link WebSocketRequest} that is aimed at
	 * resizing an item on the client platforms.
	 *
	 * @param 	itemId the id of the id
	 * @param 	width the width of the item
	 * @param 	height the height position of the item
	 * @return  {@link WebSocketRequest}
	 */
	public static WebSocketRequest createResizeItemRequest(int itemId, int width, int height) {
		WebSocketRequest request = new WebSocketRequest(TYPE_RESIZE_ITEM);
		request.put("itemId", String.valueOf(itemId));
		request.put("width", String.valueOf(width));
		request.put("height", String.valueOf(height));
		return request;
	}


	public static WebSocketRequest createSaveWorkSpace(String workSpace) {
		WebSocketRequest request = new WebSocketRequest(TYPE_SAVE_WORSPACE);
		request.put("workSpace", workSpace);
		request.put("date", String.valueOf(System.currentTimeMillis()));
		return request;
	}





}
