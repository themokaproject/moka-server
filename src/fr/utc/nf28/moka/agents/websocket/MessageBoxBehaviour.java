package fr.utc.nf28.moka.agents.websocket;

import fr.utc.nf28.moka.agents.A2ATransaction;
import fr.utc.nf28.moka.environment.items.UmlClass;
import fr.utc.nf28.moka.environment.users.User;
import fr.utc.nf28.moka.util.JSONParserUtils;
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
				if (transaction.getType().equals("addUser")) {
					final User user = (User)transaction.getContent();
					final WebSocketRequest request = WebSocketRequestFactory.createAddUserRequest(user.getIp(), user.getFirstName() + " " + user.getLastName().substring(0,1));
					((WebSocketAgent) myAgent).sendToPlatform(JSONParserUtils.serializeWebSocketRequest(request));
				} else if(transaction.getType().equals("addItem")) {
					final UmlClass uml = (UmlClass)transaction.getContent();
					final WebSocketRequest request = WebSocketRequestFactory.createAddItemRequest("umlClass", "umlClass_1");
					((WebSocketAgent) myAgent).sendToPlatform(JSONParserUtils.serializeWebSocketRequest(request));
				} else {
					((WebSocketAgent) myAgent).sendToPlatform(message.getContent());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}


		}
	}

}
