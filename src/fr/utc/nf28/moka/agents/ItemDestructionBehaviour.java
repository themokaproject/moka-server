package fr.utc.nf28.moka.agents;

import fr.utc.nf28.moka.environment.MokaItem;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.List;

/**
 * A behaviour that handles item deletion.
 */
public class ItemDestructionBehaviour extends CyclicBehaviour {
    @Override
    public void action() {
        final ACLMessage message = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
        if (message != null) {
            int itemId = Integer.parseInt(message.getContent());
            final List<MokaItem> items = ((ItemCreationAgent) myAgent).getEnvironment().getItems();
            for(MokaItem item : items) {
                if(item.getId() == itemId) {
                    System.out.println("Remove item " + item.toString());
                    items.remove(item);
                    return;
                }
            }
            System.out.println("No items with id " + itemId);
        }
    }
}
