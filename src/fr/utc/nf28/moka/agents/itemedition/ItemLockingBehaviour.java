package fr.utc.nf28.moka.agents.itemedition;

import fr.utc.nf28.moka.agents.MokaAgent;
import fr.utc.nf28.moka.environment.MokaEnvironment;
import fr.utc.nf28.moka.environment.items.LockingRequest;
import fr.utc.nf28.moka.environment.items.MokaItem;
import fr.utc.nf28.moka.environment.users.User;
import fr.utc.nf28.moka.util.JSONParserUtils;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.io.IOException;

public class ItemLockingBehaviour extends CyclicBehaviour {
    @Override
    public void action() {
        final ACLMessage message = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
        if (message != null) {
            final String lockingRequestJson = message.getContent();
            try {
                LockingRequest lockingRequest = JSONParserUtils.deserializeLockingRequest(lockingRequestJson);
                MokaEnvironment environment = ((MokaAgent) myAgent).getEnvironment();
                // TODO reimplement locking
                System.out.println("no item with id " + lockingRequest.itemId);
            } catch (IOException e) {
                System.out.println("Locking request syntax is wrong");
            }


        }
    }
}
