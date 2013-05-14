package fr.utc.nf28.moka.websocket.request;

import fr.utc.nf28.moka.environment.items.MokaItem;

public class RemoveItemWebSocketRequest extends WebSocketRequest {
	private static final String TYPE = "removeItem";

	public RemoveItemWebSocketRequest(MokaItem item) {
		super(TYPE);
		content = new RemoveItemContent();
		((RemoveItemContent)content).itemId = item.getId();
	}

	private class RemoveItemContent{
		public int itemId;
	}
}
