package fr.utc.nf28.moka.agents;

import fr.utc.nf28.moka.environment.MokaEnvironment;
import fr.utc.nf28.moka.environment.User;
import fr.utc.nf28.moka.util.JSONParserUtils;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.io.IOException;
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
            final List<User> users = ((ConnectionAgent) myAgent).getEnvironment().getUsers();
            for(User user : users) {
                if(user.getIp().equals(ip)) {
                    System.out.println("Remove user " + user.getIp() + " : " + user.getFirstName() + " " + user.getLastName());
                    users.remove(user);
                    return;
                }
            }
            System.out.println("No user with ip " + ip);
        }
    }
}
