package fr.utc.nf28.moka.agents;

import fr.utc.nf28.moka.environment.MokaEnvironment;
import fr.utc.nf28.moka.environment.User;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.List;

/**
 * A behaviour that handles deconnections.
 */
public class DeconnectionHandlingBehaviour extends CyclicBehaviour {
    @Override
    public void action() {
        final ACLMessage message = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
        if (message != null) {
            final String ip = message.getContent();
            final MokaEnvironment environment = ((ConnectionAgent) myAgent).getEnvironment();
            for(User user : environment.getUsers()) {
                if(user.getIp().equals(ip)) {
                    System.out.println("Remove user " + user.toString());
                    environment.removeUser(user);
                    return;
                }
            }
            System.out.println("No user with ip " + ip);
        }
    }
}
