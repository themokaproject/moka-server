package fr.utc.nf28.moka.agents.itemcreation;

import fr.utc.nf28.moka.agents.A2ATransaction;
import fr.utc.nf28.moka.agents.MokaAgent;
import fr.utc.nf28.moka.environment.items.UmlClass;
import fr.utc.nf28.moka.util.JSONParserUtils;
import fr.utc.nf28.moka.util.JadeUtils;

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
	public void create(String type) throws IOException {
		if (type.equals("umlClass")) {
			final UmlClass uml = new UmlClass("MyClass", 200, 350, "UmlClass");
			uml.setId(getEnvironment().generateNewId());
			getEnvironment().addItem(uml);
			//TODO communicate the item's id to the app
			final A2ATransaction transaction = new A2ATransaction(JadeUtils.TYPE_ADD_ITEM_TO_WEB_SOCKET_AGENT, uml);
			sendMessage(getAgentsWithSkill(JadeUtils.JADE_SKILL_NAME_WEBSOCKET_SERVER),
					JSONParserUtils.serializeA2ATransaction(transaction),
					jade.lang.acl.ACLMessage.PROPAGATE);
		}
	}
}
