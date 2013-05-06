package fr.utc.nf28.moka.agents.edition;

import fr.utc.nf28.moka.agents.MokaAgent;

public class EditionAgent extends MokaAgent {

    public void setup() {
        super.setup();
        addBehaviour(new EdtionHandlingBehaviour());
    }
}
