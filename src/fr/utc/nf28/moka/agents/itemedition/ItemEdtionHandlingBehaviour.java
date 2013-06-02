package fr.utc.nf28.moka.agents.itemedition;

import fr.utc.nf28.moka.agents.A2ATransaction;
import fr.utc.nf28.moka.util.JSONParserUtils;
import fr.utc.nf28.moka.util.JadeUtils;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.io.IOException;
import java.util.HashMap;

/**
 * A behaviour that handles item itemedition.
 */
public class ItemEdtionHandlingBehaviour extends CyclicBehaviour {
	@Override
	public void action() {
		final ACLMessage message = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
		if (message != null) {
			final String requestString = message.getContent();
			try {
				final A2ATransaction request = JSONParserUtils.deserializeA2ATransaction(requestString);
				final String type = request.getType();
				if (JadeUtils.TRANSACTION_TYPE_MOVE_ITEM.equals(type)) {
					((ItemEditionAgent) myAgent).moveItem((HashMap<String, Integer>) request.getContent());
				} else if (JadeUtils.TRANSACTION_TYPE_RESIZE_ITEM.equals(type)) {
					((ItemEditionAgent) myAgent).resizeItem((HashMap<String, Integer>) request.getContent());
				} else {
					throw new IOException();
				}
			} catch (IOException e) {
				System.out.println("Edition request syntax is wrong");
			}
		}
	}
}
