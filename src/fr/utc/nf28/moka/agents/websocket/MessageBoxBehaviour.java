package fr.utc.nf28.moka.agents.websocket;

import fr.utc.nf28.moka.agents.A2ATransaction;
import fr.utc.nf28.moka.environment.items.MokaItem;
import fr.utc.nf28.moka.environment.items.UmlClass;
import fr.utc.nf28.moka.environment.users.User;
import fr.utc.nf28.moka.util.JSONParserUtils;
import fr.utc.nf28.moka.util.JadeUtils;
import fr.utc.nf28.moka.websocket.request.WebSocketRequest;
import fr.utc.nf28.moka.websocket.request.WebSocketRequestFactory;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.io.IOException;

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
				String messageToSend = "";

				if (transaction.getType().equals(JadeUtils.TRANSACTION_TYPE_CONNECTION)) {
					final User user = (User) transaction.getContent();
					request = WebSocketRequestFactory.createAddUserRequest(user.getIp(), user.makePseudo());
				} else if (transaction.getType().equals(JadeUtils.TRANSACTION_TYPE_ADD_ITEM)) {
					final MokaItem item = (MokaItem) transaction.getContent();
					request = WebSocketRequestFactory.createAddItemRequest(item.getType(), item.getId(),item.getX(), item.getY());
				} else if (transaction.getType().equals(JadeUtils.TRANSACTION_TYPE_DELETE_ITEM)) {
					String id = String.valueOf(transaction.getContent());
					request = WebSocketRequestFactory.createRemoveItemRequest(id);
				} else if (transaction.getType().equals(JadeUtils.TRANSACTION_TYPE_MOVE_ITEM)) {
					final MokaItem item = (MokaItem) transaction.getContent();
					request = WebSocketRequestFactory.createMoveItemRequest(item.getId(), item.getX(), item.getY());
				} else if (transaction.getType().equals(JadeUtils.TRANSACTION_TYPE_RESIZE_ITEM)) {
					final MokaItem item = (MokaItem) transaction.getContent();
					request = WebSocketRequestFactory.createResizeItemRequest(item.getId(), item.getWidth(), item.getHeight());
				}

				if (request != null) {
					messageToSend = JSONParserUtils.serializeWebSocketRequest(request);
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
