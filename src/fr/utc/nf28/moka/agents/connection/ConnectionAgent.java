package fr.utc.nf28.moka.agents.connection;

import fr.utc.nf28.moka.agents.MokaAgent;
import fr.utc.nf28.moka.util.JadeUtils;

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
}
