package fr.utc.nf28.moka.agents.connection;

import fr.utc.nf28.moka.agents.A2ATransaction;
import fr.utc.nf28.moka.agents.BaseAgent;
import fr.utc.nf28.moka.agents.MokaAgent;
import fr.utc.nf28.moka.environment.MokaEnvironment;
import fr.utc.nf28.moka.environment.users.User;
import fr.utc.nf28.moka.util.JSONParserUtils;
import fr.utc.nf28.moka.util.JadeUtils;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.io.IOException;
import java.util.HashMap;

/**
 * A behaviour that handles connections and disconnections.
 */
public class ConnectionHandlingBehaviour extends CyclicBehaviour {
    @Override
    public void action() {
        final ACLMessage message = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
        if (message != null) {
            final String connectionRequestString = message.getContent();
            try {
                final A2ATransaction request = JSONParserUtils.deserializeA2ATransaction(connectionRequestString);
				final String type = request.getType();
                if (type.equals("connection")) {
					((ConnectionAgent)myAgent).connection((HashMap<String, String>) request.getContent());
                } else if (type.equals("disconnection")) {
                    disconnection("disconnect");
                }
            } catch (IOException e) {
				e.printStackTrace();
                System.out.println("Connection/disconnection request syntax is wrong");
            }
        } else {
            block();
        }
    }


    private void disconnection(String ip) {
        ((MokaAgent)myAgent).getEnvironment().removeUser(ip);
    }
}
