package fr.utc.nf28.moka.websocket.request;

import fr.utc.nf28.moka.environment.items.MokaItem;

public class AddItemWebSocketRequest extends WebSocketRequest {
	private static final String TYPE = "addItem";

	public AddItemWebSocketRequest(String type, String itemId) {
		super(TYPE);
		content = new AddItemContent();
		((AddItemContent)content).type = type;
		((AddItemContent)content).itemId = itemId;
	}

	private class AddItemContent {
		public String type;
		public String itemId;
	}
}
