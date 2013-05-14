package fr.utc.nf28.moka.websocket.request;

import fr.utc.nf28.moka.environment.items.MokaItem;

public class MoveItemWebSocketRequest extends WebSocketRequest {
	private static final String TYPE = "moveItem";

	public MoveItemWebSocketRequest(MokaItem item) {
		super(TYPE);
		content = new MoveItemContent();
		((MoveItemContent)content).itemId = item.getId();
		((MoveItemContent)content).top = item.getY();
		((MoveItemContent)content).left = item.getX();
	}

	private class MoveItemContent {
		public int itemId;
		public int top;
		public int left;
	}
}
