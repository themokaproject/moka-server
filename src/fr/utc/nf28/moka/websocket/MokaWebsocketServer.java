package fr.utc.nf28.moka.websocket;

import fr.utc.nf28.moka.environment.users.User;
import fr.utc.nf28.moka.util.JSONParserUtils;
import fr.utc.nf28.moka.websocket.request.AddUserWebSocketRequest;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

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

	private void addUser(User user) throws IOException{
		AddUserWebSocketRequest request = new AddUserWebSocketRequest(user);
		sendAll(JSONParserUtils.serializeWebSocketRequest(request));
	}


	public void sendAll(String message){
		for(WebSocket connection : this.connections()){
			  connection.send(message);
		}
	}


}
