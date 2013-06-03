package fr.utc.nf28.moka.agents.itemedition;

import fr.utc.nf28.moka.agents.A2ATransaction;
import fr.utc.nf28.moka.agents.MokaAgent;
import fr.utc.nf28.moka.environment.items.MokaItem;
import fr.utc.nf28.moka.util.JSONParserUtils;
import fr.utc.nf28.moka.util.JadeUtils;

import java.io.IOException;
import java.util.HashMap;

/**
 * An agent that handles item editions
 * Send a REQUEST that contains your new item's JSON to modify it
 */
public class ItemEditionAgent extends MokaAgent {

	public void setup() {
		super.setup();
		addBehaviour(new ItemEdtionHandlingBehaviour());
		final HashMap<String, String> skills = new HashMap<String, String>();
		skills.put(JadeUtils.JADE_SKILL_NAME_ITEM_MOVEMENT, JadeUtils.JADE_SKILL_TYPE_DEFAULT);
		skills.put(JadeUtils.JADE_SKILL_NAME_ITEM_RESIZING, JadeUtils.JADE_SKILL_TYPE_DEFAULT);
		skills.put(JadeUtils.JADE_SKILL_NAME_ITEM_EDITING,JadeUtils.JADE_SKILL_TYPE_DEFAULT);
		registerSkills(skills);
	}

	public void moveItem(HashMap<String, Integer> itemInfo) throws IOException {
		final MokaItem res = getEnvironment().moveItem(itemInfo.get("itemId"), itemInfo.get("direction"), itemInfo.get("velocity"));
		if (res != null) {
			final A2ATransaction transaction = new A2ATransaction(JadeUtils.TRANSACTION_TYPE_MOVE_ITEM, res);
			sendMessage(getAgentsWithSkill(JadeUtils.JADE_SKILL_NAME_WEBSOCKET_SERVER),
					JSONParserUtils.serializeToJson(transaction),
					jade.lang.acl.ACLMessage.PROPAGATE);
		}
	}

	public void resizeItem(HashMap<String, Integer> itemInfo) throws IOException {
		final MokaItem res = getEnvironment().resizeItem(itemInfo.get("itemId"), itemInfo.get("direction"));
		if (res != null) {
			final A2ATransaction transaction = new A2ATransaction(JadeUtils.TRANSACTION_TYPE_RESIZE_ITEM, res);
			sendMessage(getAgentsWithSkill(JadeUtils.JADE_SKILL_NAME_WEBSOCKET_SERVER),
					JSONParserUtils.serializeToJson(transaction),
					jade.lang.acl.ACLMessage.PROPAGATE);
		}
	}
}
