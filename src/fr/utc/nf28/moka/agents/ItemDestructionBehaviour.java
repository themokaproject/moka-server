package fr.utc.nf28.moka.agents;

import fr.utc.nf28.moka.environment.MokaEnvironment;
import fr.utc.nf28.moka.environment.MokaItem;
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
            final MokaEnvironment environment = ((ItemCreationAgent) myAgent).getEnvironment();
            for(MokaItem item : environment.getItems()) {
                if(item.getId() == itemId) {
                    System.out.println("Remove item " + item.toString());
                    environment.removeItem(item);
                    return;
                }
            }
            System.out.println("No items with id " + itemId);
        }
    }
}
