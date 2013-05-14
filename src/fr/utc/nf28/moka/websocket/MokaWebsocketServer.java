package fr.utc.nf28.moka.websocket;

import fr.utc.nf28.moka.environment.items.MokaItem;
import fr.utc.nf28.moka.environment.users.User;
import fr.utc.nf28.moka.util.JSONParserUtils;
import fr.utc.nf28.moka.websocket.request.*;
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
		//TODO onOpen
		System.out.println(connection.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!");
	}

	@Override
	public void onClose(WebSocket connection, int i, String s, boolean b) {
		//TODO onClose
		System.out.println(connection + " has left the room!");
	}

	@Override
	public void onMessage(WebSocket connection, String message) {
		//TODO onMessage
		System.out.println(connection + ": " + message);
	}

	@Override
	public void onError(WebSocket webSocket, Exception ex) {
		//TODO onError
		ex.printStackTrace();
	}

	public void addUser(User user) throws IOException {
		WebSocketRequest request = WebSocketRequestFactory.createAddUserRequest(user.getIp(), user.getFirstName());
		sendAll(request);
	}

	public void removeUser(User user) throws IOException {
		WebSocketRequest request = WebSocketRequestFactory.createRemoveUserRequest(user.getIp());
		sendAll(request);
	}

	public void addItem(MokaItem item) throws IOException {
		WebSocketRequest request = WebSocketRequestFactory.createAddItemRequest(item.getType(), String.valueOf(item.getId()));
		sendAll(request);
	}

	public void removeItem(MokaItem item) throws IOException {
		WebSocketRequest request = WebSocketRequestFactory.createRemoveItemRequest(String.valueOf(item.getId()));
		sendAll(request);
	}

	public void moveItem(MokaItem item) throws IOException {
		WebSocketRequest request = WebSocketRequestFactory.createMoveItemRequest(String.valueOf(item.getId()), item.getY(), item.getX());
		sendAll(request);
	}

	public void selectItem(User user, MokaItem item) throws IOException {
		WebSocketRequest request = WebSocketRequestFactory.createSelectItemRequest(user.getIp(), String.valueOf(item.getId()));
		sendAll(request);
	}

	public void sendAll(String message) {
		for(WebSocket connection : this.connections()) {
			connection.send(message);
		}
	}

	public void sendAll(WebSocketRequest request) throws IOException {
		sendAll(JSONParserUtils.serializeWebSocketRequest(request));
	}


}
