package fr.utc.nf28.moka.agents.itemedition;

import fr.utc.nf28.moka.agents.MokaAgent;
import fr.utc.nf28.moka.environment.MokaEnvironment;
import fr.utc.nf28.moka.environment.users.User;
import fr.utc.nf28.moka.util.JadeUtils;
import jade.core.AID;

/**
 * An agent that handles mutual exclusivity when a user edits an item
 */
public class ItemLockingAgent extends MokaAgent {

	public void setup() {
		super.setup();
		registerSkill(JadeUtils.JADE_SKILL_NAME_LOCKING);
		addBehaviour(new ItemLockingBehaviour());
	}

	/**
	 * use to unlock item
	 *
	 * @param itemId item id
	 */
	public void unlockItem(int itemId) {
		final MokaEnvironment environment = getEnvironment();
		environment.unlockItem(itemId);
	}

	/**
	 * use to lock item
	 *
	 * @param itemId item id
	 */
	public void lockItem(int itemId, AID userAID) {
		final MokaEnvironment environment = getEnvironment();
		final User locker = environment.lockItem(itemId, userAID.toString());
		if (locker == null) {
			//TODO send error
		} else if (locker.getAID().equals(userAID)) {
			//TODO send success
		} else {
			//TODO send already locked
		}
	}
}
