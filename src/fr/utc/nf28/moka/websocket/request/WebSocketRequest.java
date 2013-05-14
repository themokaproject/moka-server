package fr.utc.nf28.moka.websocket.request;

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
