package fr.utc.nf28.moka.agents.itemcreation;

import fr.utc.nf28.moka.agents.MokaAgent;
import fr.utc.nf28.moka.util.JadeUtils;

/**
 * An agent that creates items. Send a REQUEST with a creation JSON to this agent to ad an item
 */
public class ItemCreationAgent extends MokaAgent {

    public void setup() {
        super.setup();
		registerSkill(JadeUtils.JADE_SKILL_NAME_CREATION);
        addBehaviour(new ItemCreationBehaviour());
    }
}
