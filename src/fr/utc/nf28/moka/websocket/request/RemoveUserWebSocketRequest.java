package fr.utc.nf28.moka.websocket.request;

import fr.utc.nf28.moka.environment.users.User;

public class RemoveUserWebSocketRequest extends WebSocketRequest {
	private static final String TYPE = "removeUser";

	public RemoveUserWebSocketRequest(String userId){
		super(TYPE);
		content = new RemoveUserContent();
		((RemoveUserContent)content).userId = userId;
	}

	private class RemoveUserContent{
		public String userId;
	}
}
