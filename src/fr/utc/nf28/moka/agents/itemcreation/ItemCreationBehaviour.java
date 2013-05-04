package fr.utc.nf28.moka.agents.itemcreation;

import fr.utc.nf28.moka.environment.MokaItem;
import fr.utc.nf28.moka.util.JSONParserUtils;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.io.IOException;

/**
 * A behaviour that handles item creation.
 */
public class ItemCreationBehaviour extends CyclicBehaviour {
    @Override
    public void action() {
        final ACLMessage message = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
        if (message != null) {
            final String itemJson = message.getContent();
            try {
                MokaItem item = JSONParserUtils.deserializeItem(itemJson);
                ((ItemCreationAgent)myAgent).getEnvironment().addItem(item);
            } catch (IOException e) {
                System.out.println("Item creation request syntax is wrong");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
