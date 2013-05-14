package fr.utc.nf28.moka.websocket.request;

import fr.utc.nf28.moka.environment.users.User;

public class AddUserWebSocketRequest extends WebSocketRequest {
	private static final String TYPE = "addUser";

	public AddUserWebSocketRequest(String userId, String name){
		super(TYPE);
		content = new AddUserContent();
		((AddUserContent)content).userId = userId;
		((AddUserContent)content).name = name;
	}

	private class AddUserContent{
		public String name;
		public String userId;
	}
}


