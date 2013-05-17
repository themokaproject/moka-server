package fr.utc.nf28.moka.agents.itemcreation;

import fr.utc.nf28.moka.agents.MokaAgent;
import fr.utc.nf28.moka.environment.MokaEnvironment;
import fr.utc.nf28.moka.environment.items.MokaItem;
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
            final String requestString = message.getContent();
            try {
                CreationRequest creationRequest = JSONParserUtils.deserializeCreationRequest(requestString);
                String creationType = creationRequest.getType();
                if(creationType.equals("creation")) {
                    create(creationRequest.getRequest());
                } else if (creationType.equals("destruction")) {
                    destroy(creationRequest.getRequest());
                } else {
                    throw new IOException();
                }
            } catch (IOException e) {
                System.out.println("Item creation/destruction request syntax is wrong");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void destroy(String idString) {
        ((MokaAgent)myAgent).getEnvironment().removeItem(Integer.valueOf(idString));
    }

    private void create(String itemString) throws IOException {
        MokaItem item = JSONParserUtils.deserializeItem(itemString);
        MokaEnvironment environment = ((MokaAgent)myAgent).getEnvironment();
        int newId = environment.generateNewId();
        // TODO communicate the item's id to the app
        item.setId(newId);
        environment.addItem(item);
    }
    
}
