package fr.utc.nf28.moka.agents.connection;

import fr.utc.nf28.moka.agents.MokaAgent;
import fr.utc.nf28.moka.environment.MokaEnvironment;
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
            final String connectionRequestString = message.getContent();
            try {
                ConnectionRequest request = JSONParserUtils.deserializeConnectionRequest(connectionRequestString);
                String requestType = request.getType();
                if (requestType.equals("connection")) {
                    connection(request.getRequest());
                } else if (requestType.equals("disconnection")) {
                    disconnection(request.getRequest());
                }
            } catch (IOException e) {
                System.out.println("Connection/disconnection request syntax is wrong");
            }
        } else {
            block();
        }
    }

    private void connection(String request) throws IOException {
        User user = JSONParserUtils.deserializeUser(request);
        ((MokaAgent)myAgent).getEnvironment().addUser(user);
    }

    private void disconnection(String ip) {
        ((MokaAgent)myAgent).getEnvironment().removeUser(ip);
    }
}
