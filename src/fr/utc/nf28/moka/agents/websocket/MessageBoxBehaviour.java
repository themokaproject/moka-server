package fr.utc.nf28.moka.agents.websocket;

import fr.utc.nf28.moka.agents.A2ATransaction;
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
					final User user = (User)transaction.getContent();
					request = WebSocketRequestFactory.createAddUserRequest(user.getIp(), user.getFirstName() + " " + user.getLastName().substring(0,1));
				} else if(transaction.getType().equals(JadeUtils.TRANSACTION_TYPE_ADD_ITEM)) {
					final UmlClass uml = (UmlClass)transaction.getContent();
					request = WebSocketRequestFactory.createAddItemRequest("umlClass", "umlClass_1", String.valueOf(uml.getX()), String.valueOf(uml.getY()));
				} else if(transaction.getType().equals(JadeUtils.TRANSACTION_TYPE_DELETE_ITEM)) {
					request = WebSocketRequestFactory.createRemoveItemRequest("umlClass_1");
				}

				if(request != null) {
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
