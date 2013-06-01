package fr.utc.nf28.moka.agents.itemedition;

import fr.utc.nf28.moka.agents.A2ATransaction;
import fr.utc.nf28.moka.agents.MokaAgent;
import fr.utc.nf28.moka.environment.MokaEnvironment;
import fr.utc.nf28.moka.environment.users.User;
import fr.utc.nf28.moka.util.JSONParserUtils;
import fr.utc.nf28.moka.util.JadeUtils;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

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
	 * @param itemId   item id
	 * @param userAID  AndroidAgent requester AID
	 * @param response response which will be send to requester
	 */
	public void lockItem(int itemId, AID userAID, ACLMessage response) throws IOException {
		final MokaEnvironment environment = getEnvironment();
		final User locker = environment.lockItem(itemId, userAID.toString());
		final A2ATransaction transaction;
		if (locker == null) {
			transaction = new A2ATransaction(JadeUtils.TRANSACTION_TYPE_LOCK_ITEM_ERROR, "");
		} else if (locker.getAID().equals(userAID.toString())) {
			transaction = new A2ATransaction(JadeUtils.TRANSACTION_TYPE_LOCK_ITEM_SUCCESS, "");
		} else {
			transaction = new A2ATransaction(JadeUtils.TRANSACTION_TYPE_LOCK_ITEM_ALREADY, locker.makePseudo());
		}
		response.setContent(JSONParserUtils.serializeA2ATransaction(transaction));
		send(response);
	}
}
