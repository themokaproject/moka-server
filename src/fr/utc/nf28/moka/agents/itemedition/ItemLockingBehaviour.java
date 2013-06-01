package fr.utc.nf28.moka.agents.itemedition;

import fr.utc.nf28.moka.agents.A2ATransaction;
import fr.utc.nf28.moka.util.JSONParserUtils;
import fr.utc.nf28.moka.util.JadeUtils;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.io.IOException;

public class ItemLockingBehaviour extends CyclicBehaviour {
	@Override
	public void action() {
		final ACLMessage message = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
		if (message != null) {
			final String requestString = message.getContent();
			try {
				final A2ATransaction request = JSONParserUtils.deserializeA2ATransaction(requestString);
				final String type = request.getType();
				if (JadeUtils.TRANSACTION_TYPE_UNLOCK_ITEM.equals(type)) {
					((ItemLockingAgent) myAgent).unlockItem((Integer) request.getContent());
				} else if (JadeUtils.TRANSACTION_TYPE_LOCK_ITEM.equals(type)) {
					((ItemLockingAgent) myAgent).lockItem((Integer) request.getContent(), message.getSender(),message.createReply());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
