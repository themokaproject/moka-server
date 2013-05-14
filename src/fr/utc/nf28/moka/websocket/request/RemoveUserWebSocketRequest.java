package fr.utc.nf28.moka.websocket.request;

import fr.utc.nf28.moka.environment.users.User;

public class RemoveUserWebSocketRequest extends WebSocketRequest {
	private static final String TYPE = "removeUser";

	public RemoveUserWebSocketRequest(User user){
		type = TYPE;
		content = new RemoveUserContent();
		((RemoveUserContent)content).userId = user.getIp(); //Use the ip as Id :s  (need a User.id ??)
	}

	private class RemoveUserContent{
		public String userId;
	}
}
