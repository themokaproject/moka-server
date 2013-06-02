package fr.utc.nf28.moka.websocket;

import fr.utc.nf28.moka.environment.HistoryEntry;
import fr.utc.nf28.moka.environment.MokaEnvironment;
import fr.utc.nf28.moka.environment.items.MokaItem;
import fr.utc.nf28.moka.environment.users.User;
import fr.utc.nf28.moka.util.JSONParserUtils;
import fr.utc.nf28.moka.websocket.request.WebSocketRequest;
import fr.utc.nf28.moka.websocket.request.WebSocketRequestFactory;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

public class MokaWebSocketServer extends WebSocketServer {
	private final Callback mCallback;

	public MokaWebSocketServer(int port, Callback c) throws UnknownHostException {
		super(new InetSocketAddress(port));
		mCallback = c;
	}

	public MokaWebSocketServer(InetSocketAddress add, Callback c) {
		super(add);
		mCallback = c;
	}

	@Override
	public void onOpen(WebSocket connection, ClientHandshake clientHandshake) {
		System.out.println(connection.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!");
		connectionCheckIn(connection);
	}

	@Override
	public void onClose(WebSocket connection, int i, String s, boolean b) {
		System.out.println(connection + " has left the room!");
	}

	@Override
	public void onMessage(WebSocket connection, String message) {
		System.out.println(connection + ": " + message);
		try {
			final WebSocketRequest request = JSONParserUtils.deserializeWebSocketRequest(message);
			if ("backUp".equals(request.getType())) {
				sendBackUpRequest(connection);
			} else if ("upload".equals(request.getType())) {
				System.out.println(request.getContent().toString());
				uploadBackUp(request.getContent());
				connectionCheckIn(connection);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(message);
		}

	}

	public void uploadBackUp(HashMap<String, String> backUp) {
		final MokaEnvironment environment = MokaEnvironment.getInstance();

		//restore items
		if (backUp.containsKey("Items")) {
			try {
				final List<MokaItem> items = JSONParserUtils.deserializeItems(backUp.get("Items"));
				environment.clearItems();
				int maxId = -1;
				for (MokaItem i : items) {
					environment.addItem(i);
					if (maxId < i.getId()) maxId = i.getId();
				}
				environment.setItemIdGenCurrentIndex(++maxId);
			} catch (IOException exception) {
				System.out.println("uploadBackUp : restore item : failed !");
				exception.printStackTrace();
			}
		}

		//restore history
		if (backUp.containsKey("History")) {
			try {
				List<HistoryEntry> historyEntries = JSONParserUtils.deserializeHistoryEntries(backUp.get("History"));
				environment.clearHistory();
				for (HistoryEntry h : historyEntries) {
					environment.addHistoryEntry(h);
				}
			} catch (IOException exception) {
				System.out.println("uploadBackUp : restore hsitory : failed !");
				exception.printStackTrace();
			}
		}

		mCallback.uploadSucceed();
	}

	@Override
	public void onError(WebSocket webSocket, Exception ex) {
		ex.printStackTrace();
	}

	private void connectionCheckIn(WebSocket connection) {
		final MokaEnvironment environment = MokaEnvironment.getInstance();
		for (User user : environment.getUsers().values()) {
			sendRequest(WebSocketRequestFactory.createAddUserRequest(user.getIp(), user.makePseudo()), connection);
		}

		for (MokaItem item : environment.getItems().values()) {
			sendRequest(WebSocketRequestFactory.createAddItemRequest(item.getType(), item.getId(), item.getX(),
					item.getY(), item.getWidth(), item.getHeight(), item.getTitle()), connection);
			if (item.isLocked())
				sendRequest(WebSocketRequestFactory.createSelectItemRequest(item.getLocker().getIp(), String.valueOf(item.getId())), connection);

		}
	}

	private void sendBackUpRequest(WebSocket connection) {
		try {
			sendRequest(WebSocketRequestFactory.createBackUpRequest(
					JSONParserUtils.serializeToJson(MokaEnvironment.getInstance().getUsers().values()),
					JSONParserUtils.serializeToJson(MokaEnvironment.getInstance().getItems().values()),
					JSONParserUtils.serializeToJson(MokaEnvironment.getInstance().getHistory())),
					connection);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendAll(String message) {
		for (WebSocket connection : this.connections()) {
			connection.send(message);
		}
	}

	private void sendAll(WebSocketRequest request) throws IOException {
		sendAll(JSONParserUtils.serializeToJson(request));
	}

	private void sendRequest(WebSocketRequest request, WebSocket connection) {
		try {
			connection.send(JSONParserUtils.serializeToJson(request));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public interface Callback {
		void uploadSucceed();
	}
}
