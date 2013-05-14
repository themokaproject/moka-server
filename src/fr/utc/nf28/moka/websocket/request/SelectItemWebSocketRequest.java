package fr.utc.nf28.moka.websocket.request;

import fr.utc.nf28.moka.environment.items.MokaItem;
import fr.utc.nf28.moka.environment.users.User;

public class SelectItemWebSocketRequest extends WebSocketRequest {
	private static final String TYPE = "selectItem";

	public SelectItemWebSocketRequest(User user, MokaItem item) {
		super(TYPE);
		content = new SelectItemContent();
		((SelectItemContent)content).userId = user.getIp(); //Use the ip as Id :s  (need a User.id ??)
		((SelectItemContent)content).itemId = item.getId();
	}

	private class SelectItemContent {
		public String userId;
		public int itemId;
	}

}
