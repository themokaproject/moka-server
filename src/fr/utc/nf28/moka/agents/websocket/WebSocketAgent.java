package fr.utc.nf28.moka.agents.websocket;

import fr.utc.nf28.moka.agents.BaseAgent;
import fr.utc.nf28.moka.util.JadeUtils;
import fr.utc.nf28.moka.websocket.MokaWebSocketServer;

import java.io.IOException;
import java.net.UnknownHostException;

public class WebSocketAgent extends BaseAgent implements MokaWebSocketServer.Callback {
	public static final int DEFAULT_PORT = 8887;

	private MokaWebSocketServer mServer;

	public void setup() {
		super.setup();
		registerSkill(JadeUtils.JADE_SKILL_NAME_WEBSOCKET_SERVER);
		startServer();
		addBehaviour(new MessageBoxBehaviour());
	}

	/**
	 * Start the webSocket server
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
			mServer = new MokaWebSocketServer(port, this);
			mServer.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send a message to the platforms
	 *
	 * @param message
	 */
	public void sendToPlatform(String message) {
		mServer.sendAll(message);
	}

	@Override
	public void uploadSucceed() {
		try {
			requestAndroidCurrentItemsListRefresh();
			requestAndroidHistoryRefresh();
		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}
}
