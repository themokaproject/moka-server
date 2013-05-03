package fr.utc.nf28.moka.agents;

public class ConnectionAgent extends MokaAgent {

    public void setup() {
        super.setup();
        addBehaviour(new ConnectionHandlingBehaviour());
    }
}
