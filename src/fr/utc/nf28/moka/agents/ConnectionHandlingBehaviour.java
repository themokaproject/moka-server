package fr.utc.nf28.moka.agents;

import fr.utc.nf28.moka.environment.User;
import fr.utc.nf28.moka.util.JSONParserUtils;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * A behaviour that handles connections.
 */
public class ConnectionHandlingBehaviour extends CyclicBehaviour {
    @Override
    public void action() {
        final ACLMessage message = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
        if (message != null) {
            final String connectionRequest = message.getContent();
            User user = JSONParserUtils.deserializeUser(connectionRequest);
            System.out.println(user.toString());
        }
    }
}
