package fr.utc.nf28.moka.websocket.request;

import fr.utc.nf28.moka.environment.items.MokaItem;

public class MoveItemWebSocketRequest extends WebSocketRequest {
	private static final String TYPE = "moveItem";

	public MoveItemWebSocketRequest(String itemId, int top, int left) {
		super(TYPE);
		content = new MoveItemContent();
		((MoveItemContent)content).itemId = itemId;
		((MoveItemContent)content).top = top;
		((MoveItemContent)content).left = left;
	}

	private class MoveItemContent {
		public String itemId;
		public int top;
		public int left;
	}
}
