package fr.utc.nf28.moka.websocket;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * User: Vbarthel
 * Date: 04/05/13
 * Time: 22:11
 */
public class MokaWebsocketServer extends WebSocketServer {

	public MokaWebsocketServer(int port) throws UnknownHostException {
		super( new InetSocketAddress(port));
	}

	public MokaWebsocketServer(InetSocketAddress add){
		super(add);
	}

	@Override
	public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
		//TODO onOpen
	}

	@Override
	public void onClose(WebSocket webSocket, int i, String s, boolean b) {
		//TODO onClose
	}

	@Override
	public void onMessage(WebSocket webSocket, String s) {
		//TODO onMessage
	}

	@Override
	public void onError(WebSocket webSocket, Exception e) {
		//TODO onError
	}
}
