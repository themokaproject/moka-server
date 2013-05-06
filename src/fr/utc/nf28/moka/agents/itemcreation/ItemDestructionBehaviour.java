package fr.utc.nf28.moka.agents.itemcreation;

import fr.utc.nf28.moka.agents.MokaAgent;
import fr.utc.nf28.moka.environment.MokaEnvironment;
import fr.utc.nf28.moka.environment.items.MokaItem;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * A behaviour that handles item deletion.
 */
public class ItemDestructionBehaviour extends CyclicBehaviour {
    @Override
    public void action() {
        final ACLMessage message = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
        if (message != null) {
            int itemId = Integer.parseInt(message.getContent());
            final MokaEnvironment environment = ((MokaAgent) myAgent).getEnvironment();
            for (MokaItem item : environment.getItems()) {
                if (item.getId() == itemId) {
                    environment.removeItem(item);
                    return;
                }
            }
            System.out.println("no item with id " + itemId);
        }
    }
}
