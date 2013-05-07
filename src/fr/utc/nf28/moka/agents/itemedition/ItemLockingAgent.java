package fr.utc.nf28.moka.agents.itemedition;

import fr.utc.nf28.moka.agents.MokaAgent;

/**
 * An agent that handles mutual exclusivity when a user edits an item
 */
public class ItemLockingAgent extends MokaAgent {

    public void setup() {
        super.setup();
        addBehaviour(new ItemLockingBehaviour());
    }
}
