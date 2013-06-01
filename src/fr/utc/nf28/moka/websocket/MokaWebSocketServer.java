package fr.utc.nf28.moka.websocket;

import fr.utc.nf28.moka.environment.MokaEnvironment;
import fr.utc.nf28.moka.environment.items.MokaItem;
import fr.utc.nf28.moka.environment.users.User;
import fr.utc.nf28.moka.util.JSONParserUtils;
import fr.utc.nf28.moka.websocket.request.WebSocketRequest;
import fr.utc.nf28.moka.websocket.request.WebSocketRequestFactory;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class MokaWebSocketServer extends WebSocketServer {

	public MokaWebSocketServer(int port) throws UnknownHostException {
		super(new InetSocketAddress(port));
	}

	public MokaWebSocketServer(InetSocketAddress add) {
		super(add);
	}

	@Override
	public void onOpen(WebSocket connection, ClientHandshake clientHandshake) {
		System.out.println(connection.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!");
		connectionCheckIn(connection);
	}

	@Override
	public void onClose(WebSocket connection, int i, String s, boolean b) {
		System.out.println(connection + " has left the room!");
	}

	@Override
	public void onMessage(WebSocket connection, String message) {
		System.out.println(connection + ": " + message);
		if ("askToSave".equals(message)) {
			sendBackUpRequest(connection);
		}
	}

	@Override
	public void onError(WebSocket webSocket, Exception ex) {
		ex.printStackTrace();
	}

	private void connectionCheckIn(WebSocket connection) {
		MokaEnvironment environment = MokaEnvironment.getInstance();
		for (User user : environment.getUsers().values()) {
			sendRequest(WebSocketRequestFactory.createAddUserRequest(user.getIp(), user.makePseudo()), connection);
		}

		for (MokaItem item : environment.getItems().values()) {
			sendRequest(WebSocketRequestFactory.createAddItemRequest(item.getType(), item.getId(), item.getX(), item.getY()), connection);
			//TODO send in one request ?
			sendRequest(WebSocketRequestFactory.createResizeItemRequest(item.getId(), item.getWidth(), item.getHeight()), connection);
			//TODO send in one request ?
			if (item.isLocked())
				sendRequest(WebSocketRequestFactory.createSelectItemRequest(item.getLocker().getIp(), String.valueOf(item.getId())), connection);

		}
	}

	private void sendBackUpRequest(WebSocket connection) {
		sendRequest(WebSocketRequestFactory.createBackUpRequest(MokaEnvironment.getInstance().toString()), connection);
	}


	public void sendAll(String message) {
		for (WebSocket connection : this.connections()) {
			connection.send(message);
		}
	}

	private void sendAll(WebSocketRequest request) throws IOException {
		sendAll(JSONParserUtils.serializeWebSocketRequest(request));
	}

	private void sendRequest(WebSocketRequest request, WebSocket connection) {
		try {
			connection.send(JSONParserUtils.serializeWebSocketRequest(request));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
