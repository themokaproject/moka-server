package fr.utc.nf28.moka.websocket.request;

import fr.utc.nf28.moka.environment.items.MokaItem;
import fr.utc.nf28.moka.environment.users.User;

public class SelectItemWebSocketRequest extends WebSocketRequest {
	private static final String TYPE = "selectItem";

	public SelectItemWebSocketRequest(String userId, String itemId) {
		super(TYPE);
		content = new SelectItemContent();
		((SelectItemContent)content).userId = userId;
		((SelectItemContent)content).itemId = itemId;
	}

	private class SelectItemContent {
		public String userId;
		public String itemId;
	}

}
