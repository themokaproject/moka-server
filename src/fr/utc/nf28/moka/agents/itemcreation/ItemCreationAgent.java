package fr.utc.nf28.moka.agents.itemcreation;

import fr.utc.nf28.moka.agents.A2ATransaction;
import fr.utc.nf28.moka.agents.MokaAgent;
import fr.utc.nf28.moka.environment.MokaEnvironment;
import fr.utc.nf28.moka.environment.items.*;
import fr.utc.nf28.moka.environment.users.User;
import fr.utc.nf28.moka.util.JSONParserUtils;
import fr.utc.nf28.moka.util.JadeUtils;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.HashMap;

/**
 * An agent that creates items. Send a REQUEST with a creation JSON to this agent to ad an item
 */
public class ItemCreationAgent extends MokaAgent {

	private static final int START_X_POSITION = 200;
	private static final int START_X_LIMIT_POSITION = 150;
	private static final int START_Y_POSITION = 30;
	private static final int START_Y_LIMIT_POSITION = 100;
	private int mStartXPosition;

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
	public void create(final String type, final ACLMessage response, final AID creator) throws IOException {
		MokaItem newItem = null;
		final MokaEnvironment environment = getEnvironment();
		final int newItemId = environment.generateNewId();
		final int startXPosition = START_X_POSITION + (newItemId * 50) % START_X_LIMIT_POSITION;
		final int startYPosition = START_Y_POSITION + (newItemId * 20) % START_Y_LIMIT_POSITION;

		if ("umlClass".equals(type)) {
			newItem = new UmlClass(startXPosition, startYPosition);
		} else if ("post-it".equals(type)) {
			newItem = new PostIt(startXPosition, startYPosition);
		} else if ("image".equals(type)) {
			newItem = new ImageLink(startXPosition, startYPosition);
		} else if ("video".equals(type)) {
			newItem = new VideoLink(startXPosition, startYPosition);
		} else if ("iframe".equals(type)) {
			newItem = new IframeLink(startXPosition, startYPosition);
		}

		if (newItem == null) {
			//server side creation failed
			//TODO implement callback error in order to warn AndroidDevice which has requested this creation
			return;
		}


		//set creator as Locker
		newItem.lock(environment.getUserByAID(creator.toString()));

		//set item id
		newItem.setId(newItemId);
		environment.addItem(newItem);

		//make default title
		newItem.makeDefaultTitle();

		//send back item id to the creator
		sendBackItem(response, newItem);

		//propagate creation to Ui platform
		propagateCreation(newItem, environment.getUserByAID(creator.toString()));

		//request refreshing current item list for all android device
		requestAndroidCurrentItemsListRefresh();
	}

	/**
	 * Use to send back id of the new created item
	 *
	 * @param response ACL response for AndroidAgent which send the creation request
	 * @param newItem  item
	 * @throws IOException
	 */
	public void sendBackItem(final ACLMessage response, final MokaItem newItem) throws IOException {
		final A2ATransaction responseTransaction =
				new A2ATransaction(JadeUtils.TRANSACTION_TYPE_ITEM_CREATION_SUCCESS, JSONParserUtils.serializeToJson(newItem));
		response.setPerformative(ACLMessage.REQUEST);
		response.setContent(JSONParserUtils.serializeToJson(responseTransaction));
		send(response);
	}

	/**
	 * Propagate item creation to the WebsocketAgent
	 *
	 * @param newItem item created
	 * @throws IOException
	 */
	public void propagateCreation(MokaItem newItem, User user) throws IOException {
		final A2ATransaction transaction =
				new A2ATransaction(JadeUtils.TRANSACTION_TYPE_ADD_ITEM, newItem);
		sendMessage(getAgentsWithSkill(JadeUtils.JADE_SKILL_NAME_WEBSOCKET_SERVER),
				JSONParserUtils.serializeToJson(transaction),
				ACLMessage.PROPAGATE);

		//TODO do it in one request ? create an item directly with a locker
		final HashMap<String, String> info = new HashMap<String, String>();
		info.put("itemId", String.valueOf(newItem.getId()));
		info.put("userId", user.getIp());
		final A2ATransaction transactionToWebSocketAgent = new A2ATransaction(JadeUtils.TRANSACTION_TYPE_LOCK_ITEM_SUCCESS, info);
		sendMessage(getAgentsWithSkill(JadeUtils.JADE_SKILL_NAME_WEBSOCKET_SERVER),
				JSONParserUtils.serializeToJson(transactionToWebSocketAgent),
				jade.lang.acl.ACLMessage.PROPAGATE);
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
				JSONParserUtils.serializeToJson(transaction),
				jade.lang.acl.ACLMessage.PROPAGATE);

		//request refreshing history for all android device
		requestAndroidHistoryRefresh();

		//request refreshing current item list for all android device
		requestAndroidCurrentItemsListRefresh();
	}
}
