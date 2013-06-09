package fr.utc.nf28.moka.agents.connection;

import fr.utc.nf28.moka.agents.A2ATransaction;
import fr.utc.nf28.moka.agents.MokaAgent;
import fr.utc.nf28.moka.environment.users.User;
import fr.utc.nf28.moka.util.JSONParserUtils;
import fr.utc.nf28.moka.util.JadeUtils;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

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

	public void connection(final HashMap<String, String> userInfo, AID userAID) throws IOException {
		final User user = new User(userInfo.get("firstName"), userInfo.get("lastName"));
		user.setIp(userInfo.get("ip"));
		user.setAID(userAID.toString());
		getEnvironment().addUser(user);
		final A2ATransaction transaction = new A2ATransaction(JadeUtils.TRANSACTION_TYPE_CONNECTION, user);
		sendMessage(getAgentsWithSkill(JadeUtils.JADE_SKILL_NAME_WEBSOCKET_SERVER),
				JSONParserUtils.serializeToJson(transaction),
				ACLMessage.PROPAGATE);

		requestAndroidRefresh();
	}

	public void disconnection(AID sender) throws IOException {
		String ip = getEnvironment().getUserByAID(sender.toString()).getIp();
		getEnvironment().removeUser(ip);
		final A2ATransaction transaction = new A2ATransaction(JadeUtils.TRANSACTION_TYPE_LOGOUT, ip);
		sendMessage(getAgentsWithSkill(JadeUtils.JADE_SKILL_NAME_WEBSOCKET_SERVER),
				JSONParserUtils.serializeToJson(transaction),
				ACLMessage.PROPAGATE);
	}

	/**
	 * send ACL request to all android device to inform that a new user is connected
	 *
	 * @throws IOException
	 */
	public void requestAndroidRefresh() throws IOException {
		final A2ATransaction refreshTransaction =
				new A2ATransaction(JadeUtils.TRANSACTION_TYPE_REFRESH_HISTORY,
						"new user, refresh history.");
		sendMessageToAndroidDevice(ACLMessage.REQUEST,
				JSONParserUtils.serializeToJson(refreshTransaction));
	}


}
