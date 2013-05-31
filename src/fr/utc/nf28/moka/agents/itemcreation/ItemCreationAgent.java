package fr.utc.nf28.moka.agents.itemcreation;

import fr.utc.nf28.moka.agents.A2ATransaction;
import fr.utc.nf28.moka.agents.MokaAgent;
import fr.utc.nf28.moka.environment.items.MokaItem;
import fr.utc.nf28.moka.environment.items.PostIt;
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
		MokaItem newItem = null;
		final int newItemId = getEnvironment().generateNewId();

		if (type.equals("UML")) {
			newItem = new UmlClass("MyClass", 200, 350, "UmlClass");

		} else if (type.equals("post-it")) {
            newItem = new PostIt("Post-it", 300, 350, "Post-it", "Post-it content");
		}

		if (newItem == null) {
			//server side creation failed
			//TODO implement callback error in order to warn AndroidDevice which has requested this creation
			return;
		}

        newItem.setId(newItemId);
        getEnvironment().addItem(newItem);

		//send back item id to the creator
		sendBackItemId(response, newItemId);

		//propagate creation to Ui platform
		propagateCreation(newItem);

		//request refreshing current item list for all android device
		requestAndroidCurrentItemsListRefresh();
	}

	/**
	 * send ACL request to all android device to inform that a new item has been created
	 * refresh history and current list
	 *
	 * @throws IOException
	 */
	public void requestAndroidCurrentItemsListRefresh() throws IOException {
		final A2ATransaction refreshTransaction =
				new A2ATransaction(JadeUtils.TRANSACTION_TYPE_REFRESH_CURRENT_ITEMS,
						"new item created, refresh list.");
		sendMessageToAndroidDevice(ACLMessage.REQUEST,
				JSONParserUtils.serializeA2ATransaction(refreshTransaction));
	}

	/**
	 * send ACL request to all android device to inform that item has been deleted
	 * refresh only history
	 *
	 * @throws IOException
	 */
	public void requestAndroidHistoryRefresh() throws IOException {
		final A2ATransaction refreshTransaction =
				new A2ATransaction(JadeUtils.TRANSACTION_TYPE_REFRESH_HISTORY,
						"new item created, refresh list.");
		sendMessageToAndroidDevice(ACLMessage.REQUEST,
				JSONParserUtils.serializeA2ATransaction(refreshTransaction));
	}

	/**
	 * Use to send back id of the new created item
	 *
	 * @param response  ACL response for AndroidAgent which send the creation request
	 * @param newItemId item id
	 * @throws IOException
	 */
	public void sendBackItemId(final ACLMessage response, final int newItemId) throws IOException {
		final A2ATransaction responseTransaction =
				new A2ATransaction(JadeUtils.TRANSACTION_TYPE_ITEM_CREATION_SUCCESS, newItemId);
		response.setPerformative(ACLMessage.REQUEST);
		response.setContent(JSONParserUtils.serializeA2ATransaction(responseTransaction));
		send(response);
	}

	/**
	 * Propagate item creation to the WebsocketAgent
	 *
	 * @param newItem item created
	 * @throws IOException
	 */
	public void propagateCreation(MokaItem newItem) throws IOException {
		final A2ATransaction transaction =
				new A2ATransaction(JadeUtils.TRANSACTION_TYPE_ADD_ITEM, newItem);
		sendMessage(getAgentsWithSkill(JadeUtils.JADE_SKILL_NAME_WEBSOCKET_SERVER),
				JSONParserUtils.serializeA2ATransaction(transaction),
				ACLMessage.PROPAGATE);
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
		requestAndroidHistoryRefresh();
	}
}
