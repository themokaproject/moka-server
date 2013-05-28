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
	 * @param type     typeof this new item
	 * @param response use to communicate item id to androidAgent which
	 *                 request this item creation
	 * @throws IOException
	 */
	public void create(final String type, final ACLMessage response) throws IOException {
		if (type.equals("umlClass")) {
			final UmlClass uml = new UmlClass("MyClass", 200, 350, "UmlClass");
			final int newItemId = getEnvironment().generateNewId();
			uml.setId(newItemId);
			getEnvironment().addItem(uml);
			//send back item id to the creator
			final A2ATransaction responseTransaction =
					new A2ATransaction(JadeUtils.TRANSACTION_TYPE_ITEM_CREATION_SUCCESS, newItemId);
			response.setPerformative(ACLMessage.REQUEST);
			response.setContent(JSONParserUtils.serializeA2ATransaction(responseTransaction));
			send(response);
			//propagate creation to Ui platform
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
