package fr.utc.nf28.moka.websocket.request;

import fr.utc.nf28.moka.environment.users.User;

public class AddUserWebSocketRequest extends WebSocketRequest {
	private static final String TYPE = "addUser";

	public AddUserWebSocketRequest(User user){
		type = TYPE;
		content = new AddUserContent();
		((AddUserContent)content).name = user.getFirstName();
		((AddUserContent)content).userId = user.getIp(); //Use the ip as Id :s  (need a User.id ??)
	}

	private class AddUserContent{
		public String name;
		public String userId;
	}
}


