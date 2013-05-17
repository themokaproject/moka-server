package fr.utc.nf28.moka.agents.websocket;

import fr.utc.nf28.moka.agents.BaseAgent;
import fr.utc.nf28.moka.websocket.MokaWebSocketServer;

import java.net.UnknownHostException;

public class WebSocketAgent extends BaseAgent {

	public static final String SKILL_NAME = "WebSocketService";
	public static final int DEFAULT_PORT = 8887;

	private MokaWebSocketServer mServer;

	public void setup() {
		super.setup();
		registerSkill(SKILL_NAME);
		startServer();
	}

	/**
	 * Start the wenSocket server
	 * on the DEFAULT_PORT
	 * or on the port provided as arguments
	 */
	private void startServer() {
		int port = DEFAULT_PORT;
		final Object[] args = getArguments();
		if( args != null) {
			port = (Integer)(getArguments()[0]);
		}
		try {
			mServer = new MokaWebSocketServer(port);
			mServer.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
