package fr.utc.nf28.moka.agents.websocket;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class MessageBoxBehaviour extends CyclicBehaviour {

	@Override
	public void action() {
		final ACLMessage message = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.PROPAGATE));
		if(message == null) {
			block();
		} else {
			((WebSocketAgent)myAgent).sendToPlatform(message.getContent());
		}
	}

}
