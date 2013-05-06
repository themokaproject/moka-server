package fr.utc.nf28.moka.agents.edition;

import fr.utc.nf28.moka.agents.MokaAgent;

/**
 * An agent that handles item editions
 * Send a REQUEST that contains your new item's JSON to modify it
 */
public class EditionAgent extends MokaAgent {

    public void setup() {
        super.setup();
        addBehaviour(new EdtionHandlingBehaviour());
    }
}
