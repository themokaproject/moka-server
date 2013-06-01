package fr.utc.nf28.moka.websocket.request;

import java.util.HashMap;

/**
 * This class is a base for all the web socket request
 * between the web socket server and the clients.
 */
public class WebSocketRequest {
	public String type;
	public HashMap<String, String> content ;

	public WebSocketRequest(String type) {
		this.type = type;
		this.content = new HashMap<String, String>();
	}

	public WebSocketRequest(String type, HashMap<String, String> content) {
		this.type = type;
		this.content = content;
	}

	public void put(String key, String value) {
		this.content.put(key, value);
	}

	public String getType() {
		return  type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public HashMap<String, String> getContent(){
		return content;
	}

	public void setContent(HashMap<String, String> content) {
		this.content = content;
	}
}
