package fr.utc.nf28.moka.agents.itemcreation;

import fr.utc.nf28.moka.agents.A2ATransaction;
import fr.utc.nf28.moka.agents.MokaAgent;
import fr.utc.nf28.moka.environment.items.UmlClass;
import fr.utc.nf28.moka.util.JSONParserUtils;
import fr.utc.nf28.moka.util.JadeUtils;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

/**
 * An agent that creates items. Send a REQUEST with a creation JSON to this agent to ad an item
 */
public class ItemCreationAgent extends MokaAgent {

	public void setup() {
		super.setup();
		registerSkill(JadeUtils.JADE_SKILL_NAME_CREATION);
		addBehaviour(new ItemCreationBehaviour());
	}

	/**
	 * create new item
	 *
	 * @param type typeof this new item
	 * @throws IOException
	 */
	public void create(String type, ACLMessage response) throws IOException {
		if (type.equals("umlClass")) {
			final UmlClass uml = new UmlClass("MyClass", 200, 350, "UmlClass");
			uml.setId(getEnvironment().generateNewId());
			getEnvironment().addItem(uml);
			//TODO communicate the item's id to the app
			final A2ATransaction transaction = new A2ATransaction(JadeUtils.TRANSACTION_TYPE_ADD_ITEM, uml);
			sendMessage(getAgentsWithSkill(JadeUtils.JADE_SKILL_NAME_WEBSOCKET_SERVER),
					JSONParserUtils.serializeA2ATransaction(transaction),
					jade.lang.acl.ACLMessage.PROPAGATE);
			//TODO construct a well formed new item message
			sendMessageToAndroidDevice(ACLMessage.REQUEST, "new Item created !");
		}
	}

	/**
	 * delete an item
	 *
	 * @param itemId
	 * @throws IOException
	 */
	public void deleteItem(int itemId) throws IOException {
		getEnvironment().removeItem(itemId);
		final A2ATransaction transaction = new A2ATransaction(JadeUtils.TRANSACTION_TYPE_DELETE_ITEM, itemId);
		sendMessage(getAgentsWithSkill(JadeUtils.JADE_SKILL_NAME_WEBSOCKET_SERVER),
				JSONParserUtils.serializeA2ATransaction(transaction),
				jade.lang.acl.ACLMessage.PROPAGATE);
	}
}
