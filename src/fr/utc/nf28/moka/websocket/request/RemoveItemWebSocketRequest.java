package fr.utc.nf28.moka.websocket.request;

import fr.utc.nf28.moka.environment.items.MokaItem;

public class RemoveItemWebSocketRequest extends WebSocketRequest {
	private static final String TYPE = "removeItem";

	public RemoveItemWebSocketRequest(String itemId) {
		super(TYPE);
		content = new RemoveItemContent();
		((RemoveItemContent)content).itemId = itemId;
	}

	private class RemoveItemContent{
		public String itemId;
	}
}
