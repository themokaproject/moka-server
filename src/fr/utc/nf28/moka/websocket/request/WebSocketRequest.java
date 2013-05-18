package fr.utc.nf28.moka.websocket.request;

/**
 * This class is a base for all the web socket request
 * between the web socket server and the clients.
 */
public class WebSocketRequest {
	public String type;
	public Object content ;

	public WebSocketRequest(String type) {
		this.type = type;
	}

	public WebSocketRequest(String type, Object content) {
		this.type = type;
		this.content = content;
	}
}
