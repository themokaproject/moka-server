package fr.utc.nf28.moka.agents.connection;

import fr.utc.nf28.moka.environment.users.User;
import fr.utc.nf28.moka.util.JSONParserUtils;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.io.IOException;

/**
 * A behaviour that handles connections.
 */
public class ConnectionHandlingBehaviour extends CyclicBehaviour {
    @Override
    public void action() {
        final ACLMessage message = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
        if (message != null) {
            final String connectionRequest = message.getContent();
            try {
                User user = JSONParserUtils.deserializeUser(connectionRequest);
                ((ConnectionAgent)myAgent).getEnvironment().addUser(user);
            } catch (IOException e) {
                System.out.println("Connection request syntax is wrong");
            }


        }
    }
}
