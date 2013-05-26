package fr.utc.nf28.moka.websocket;

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

	//for test purpose only
	//TODO DELETE
	public void addUser(String userId, String pseudo) throws IOException {
		WebSocketRequest request = WebSocketRequestFactory.createAddUserRequest(userId, pseudo);
		sendAll(request);
	}

	//for test purpose only
	//TODO DELETE
	public void removeUser(String userId) throws IOException {
		WebSocketRequest request = WebSocketRequestFactory.createRemoveUserRequest(userId);
		sendAll(request);
	}

	//for test purpose only
	//TODO DELETE
	public void addItem(String type, String itemId) throws IOException {
		WebSocketRequest request = WebSocketRequestFactory.createAddItemRequest(type, itemId, "200","350");
		sendAll(request);
	}

	//for test purpose only
	//TODO DELETE
	public void removeItem(String itemId) throws IOException {
		WebSocketRequest request = WebSocketRequestFactory.createRemoveItemRequest(itemId);
		sendAll(request);
	}

	//for test purpose only
	//TODO DELETE
	public void moveItem(String itemId, int x, int y) throws IOException {
		WebSocketRequest request = WebSocketRequestFactory.createMoveItemRequest(itemId, x, y);
		sendAll(request);
	}

	//for test purpose only
	//TODO DELETE
	public void selectItem(String userId, String itemId) throws IOException {
		WebSocketRequest request = WebSocketRequestFactory.createSelectItemRequest(userId, itemId);
		sendAll(request);
	}

	public void sendAll(String message) {
		for(WebSocket connection : this.connections()) {
			connection.send(message);
		}
	}

	//for test purpose only
	//TODO DELETE
	public void sendAll(WebSocketRequest request) throws IOException {
		sendAll(JSONParserUtils.serializeWebSocketRequest(request));
	}


}
