package fr.utc.nf28.moka.agents.itemcreation;

import fr.utc.nf28.moka.agents.A2ATransaction;
import fr.utc.nf28.moka.agents.MokaAgent;
import fr.utc.nf28.moka.environment.items.MokaItem;
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
		if (type.equals("UML")) {
			final UmlClass newItem = new UmlClass("MyClass", 200, 350, "UmlClass");
			final int newItemId = getEnvironment().generateNewId();
			newItem.setId(newItemId);
			getEnvironment().addItem(newItem);

			//send back item id to the creator
			sendBackItemId(response, newItemId);

			//propagate creation to Ui platform
			propagateCreation(newItem);
		} else if (type.equals("post-it")) {

		}

		//request refreshing current item list for all android device
		final A2ATransaction refreshTransaction =
				new A2ATransaction(JadeUtils.TRANSACTION_TYPE_REFRESH_CURRENT_ITEMS,
						"new item created, refresh list.");
		sendMessageToAndroidDevice(ACLMessage.REQUEST,
				JSONParserUtils.serializeA2ATransaction(refreshTransaction));
	}

	/**
	 * Use to send back id of the new created item
	 *
	 * @param response  ACL response for AndroidAgent which send the creation request
	 * @param newItemId item id
	 */
	public void sendBackItemId(final ACLMessage response, final int newItemId) {
		final A2ATransaction responseTransaction =
				new A2ATransaction(JadeUtils.TRANSACTION_TYPE_ITEM_CREATION_SUCCESS, newItemId);
		response.setPerformative(ACLMessage.REQUEST);
		try {
			response.setContent(JSONParserUtils.serializeA2ATransaction(responseTransaction));
		} catch (IOException e) {
			e.printStackTrace();
		}
		send(response);
	}

	/**
	 * Propagate item creation to the WebsocketAgent
	 *
	 * @param newItem item created
	 */
	public void propagateCreation(MokaItem newItem) {
		final A2ATransaction transaction =
				new A2ATransaction(JadeUtils.TRANSACTION_TYPE_ADD_ITEM, newItem);
		try {
			sendMessage(getAgentsWithSkill(JadeUtils.JADE_SKILL_NAME_WEBSOCKET_SERVER),
					JSONParserUtils.serializeA2ATransaction(transaction),
					ACLMessage.PROPAGATE);
		} catch (IOException e) {
			e.printStackTrace();
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
