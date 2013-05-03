package fr.utc.nf28.moka.agents;

import fr.utc.nf28.moka.data.MokaEnvironment;
import jade.core.Agent;

public class ConnectionAgent extends MokaAgent {

    public void setup() {
        super.setup();
        addBehaviour(new ConnectionHandlingBehaviour());
    }
}
