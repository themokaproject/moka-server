package fr.utc.nf28.moka.agents.itemedition;

import fr.utc.nf28.moka.agents.MokaAgent;
import fr.utc.nf28.moka.environment.items.MokaItem;
import fr.utc.nf28.moka.util.JSONParserUtils;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.io.IOException;

/**
 * A behaviour that handles item itemedition.
 */
public class ItemEdtionHandlingBehaviour extends CyclicBehaviour {
    @Override
    public void action() {
        final ACLMessage message = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
        if (message != null) {
            final String editionRequest = message.getContent();
            try {
                MokaItem item = JSONParserUtils.deserializeItem(editionRequest);
                ((MokaAgent) myAgent).getEnvironment().updateItem(item);
            } catch (IOException e) {
                System.out.println("Edtion request syntax is wrong");
            }
        }
    }
}
