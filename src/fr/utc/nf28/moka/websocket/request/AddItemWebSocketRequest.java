package fr.utc.nf28.moka.websocket.request;

import fr.utc.nf28.moka.environment.items.MokaItem;

public class AddItemWebSocketRequest extends WebSocketRequest {
	private static final String TYPE = "addItem";

	public AddItemWebSocketRequest(MokaItem item) {
		super(TYPE);
		content = new AddItemContent();
		((AddItemContent)content).type = item.getType();
		((AddItemContent)content).itemId = item.getId();
	}

	private class AddItemContent {
		public String type;
		public int itemId;
	}
}
