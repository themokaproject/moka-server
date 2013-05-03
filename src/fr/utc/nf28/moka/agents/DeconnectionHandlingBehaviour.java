package fr.utc.nf28.moka.agents;

import fr.utc.nf28.moka.environment.User;
import fr.utc.nf28.moka.util.JSONParserUtils;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.io.IOException;

/**
 * A behaviour that handles deconnections.
 */
public class DeconnectionHandlingBehaviour extends CyclicBehaviour{
    @Override
    public void action() {
        final ACLMessage message = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
        if (message != null) {
            final String deconnectionRequest = message.getContent();
            try {
                User user = JSONParserUtils.deserializeUser(deconnectionRequest);
                System.out.println(user.toString());
                ((ConnectionAgent)myAgent).getEnvironment().removeUser(user);
            } catch (IOException e) {
                System.out.println("Deconnection request syntax is wrong");
            }
        }
    }
}
