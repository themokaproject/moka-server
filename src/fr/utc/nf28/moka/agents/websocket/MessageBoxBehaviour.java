package fr.utc.nf28.moka.agents.websocket;

import fr.utc.nf28.moka.agents.A2ATransaction;
import fr.utc.nf28.moka.environment.items.MokaItem;
import fr.utc.nf28.moka.environment.users.User;
import fr.utc.nf28.moka.util.JSONParserUtils;
import fr.utc.nf28.moka.util.JadeUtils;
import fr.utc.nf28.moka.websocket.request.WebSocketRequest;
import fr.utc.nf28.moka.websocket.request.WebSocketRequestFactory;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.io.IOException;
import java.util.HashMap;

public class MessageBoxBehaviour extends CyclicBehaviour {

	@Override
	public void action() {
		final ACLMessage message = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.PROPAGATE));
		if (message == null) {
			block();
		} else {
			try {
				final A2ATransaction transaction = JSONParserUtils.deserializeA2ATransaction(message.getContent());
				WebSocketRequest request = null;
				final String messageToSend;
				if (JadeUtils.TRANSACTION_TYPE_CONNECTION.equals(transaction.getType())) {
					final User user = (User) transaction.getContent();
					request = WebSocketRequestFactory.createAddUserRequest(user.getIp(), user.makePseudo());
				} else if (JadeUtils.TRANSACTION_TYPE_ADD_ITEM.equals(transaction.getType())) {
					final MokaItem item = (MokaItem) transaction.getContent();
					request = WebSocketRequestFactory.createAddItemRequest(item.getType(), item.getId(), item.getX(),
							item.getY(), item.getWidth(), item.getHeight(), item.getTitle());
				} else if (JadeUtils.TRANSACTION_TYPE_DELETE_ITEM.equals(transaction.getType())) {
					final String id = String.valueOf(transaction.getContent());
					request = WebSocketRequestFactory.createRemoveItemRequest(id);
				} else if (JadeUtils.TRANSACTION_TYPE_MOVE_ITEM.equals(transaction.getType())) {
					final MokaItem item = (MokaItem) transaction.getContent();
					request = WebSocketRequestFactory.createMoveItemRequest(item.getId(), item.getX(), item.getY());
				} else if (JadeUtils.TRANSACTION_TYPE_RESIZE_ITEM.equals(transaction.getType())) {
					final MokaItem item = (MokaItem) transaction.getContent();
					request = WebSocketRequestFactory.createResizeItemRequest(item.getId(), item.getWidth(), item.getHeight());
				} else if (JadeUtils.TRANSACTION_TYPE_LOCK_ITEM_SUCCESS.equals(transaction.getType())) {
					final HashMap<String, String> info = (HashMap<String, String>) transaction.getContent();
					request = WebSocketRequestFactory.createSelectItemRequest(info.get("userId"), info.get("itemId"));
				} else if (JadeUtils.TRANSACTION_TYPE_UNLOCK_ITEM.equals(transaction.getType())) {
					final int itemId = (Integer) transaction.getContent();
					request = WebSocketRequestFactory.createUnselectItemRequest(itemId);
				} else if (JadeUtils.TRANSACTION_TYPE_ROTATE_ITEM.equals(transaction.getType())) {
					final MokaItem item = (MokaItem) transaction.getContent();
					request = WebSocketRequestFactory.createRotateItemRequest(item.getId(), 0, 0, item.getRotateZ());
				}


				if (request != null) {
					messageToSend = JSONParserUtils.serializeToJson(request);
				} else {
					messageToSend = message.getContent();
				}

				((WebSocketAgent) myAgent).sendToPlatform(messageToSend);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
