package fr.utc.nf28.moka.agents.itemedition;

import fr.utc.nf28.moka.agents.MokaAgent;
import fr.utc.nf28.moka.util.JadeUtils;

/**
 * An agent that handles mutual exclusivity when a user edits an item
 */
public class ItemLockingAgent extends MokaAgent {

    public void setup() {
        super.setup();
		registerSkill(JadeUtils.JADE_SKILL_NAME_LOCKING);
        addBehaviour(new ItemLockingBehaviour());
    }
}
