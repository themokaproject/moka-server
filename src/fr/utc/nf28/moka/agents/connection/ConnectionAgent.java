package fr.utc.nf28.moka.agents.connection;

import fr.utc.nf28.moka.agents.A2ATransaction;
import fr.utc.nf28.moka.agents.MokaAgent;
import fr.utc.nf28.moka.environment.users.User;
import fr.utc.nf28.moka.util.JSONParserUtils;
import fr.utc.nf28.moka.util.JadeUtils;
import jade.domain.introspection.ACLMessage;

import java.io.IOException;
import java.util.HashMap;

/**
 * An agent that handles connections and disconnections of clients to Moka
 * A REQUEST has to be sent for connection or a disconnection
 */
public class ConnectionAgent extends MokaAgent {

    public void setup() {
        super.setup();
		registerSkill(JadeUtils.JADE_SKILL_NAME_CONNECTION);
        addBehaviour(new ConnectionHandlingBehaviour());
    }

	public void connection(final HashMap<String,String> userInfo) throws IOException {
		User user = new User(userInfo.get("firstName"),userInfo.get("lastName"));
		user.setIp(userInfo.get("ip"));
		getEnvironment().addUser(user);
		final A2ATransaction transaction = new A2ATransaction("addUser",user);
		sendMessage(getAgentsWithSkill(JadeUtils.JADE_SKILL_NAME_WEBSOCKET_SERVER),
				JSONParserUtils.serializeA2ATransaction(transaction),
				jade.lang.acl.ACLMessage.PROPAGATE);
	}

}
