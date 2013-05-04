package fr.utc.nf28.moka.agents.connection;

import fr.utc.nf28.moka.agents.MokaAgent;

/**
 * An agent that handles connections and deconnections of clients to Moka
 * A REQUEST has to be sent for connection, an INFORM for deconnection
 */
public class ConnectionAgent extends MokaAgent {

    public void setup() {
        super.setup();
        addBehaviour(new ConnectionHandlingBehaviour());
        addBehaviour(new DeconnectionHandlingBehaviour());
    }
}
