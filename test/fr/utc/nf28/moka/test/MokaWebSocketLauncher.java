package fr.utc.nf28.moka.test;

import fr.utc.nf28.moka.websocket.MokaWebSocketServer;
import org.java_websocket.WebSocketImpl;

import java.net.UnknownHostException;

public class MokaWebSocketLauncher {

	public static void main(String[] args) throws UnknownHostException {
		WebSocketImpl.DEBUG = true;
		int port = 8887;

		MokaWebSocketServer test = new MokaWebSocketServer(port);
		test.start();

		System.out.println( "Server started on port: " + test.getPort() );
	}
}
