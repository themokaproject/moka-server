package fr.utc.nf28.moka.util;

import fr.utc.nf28.moka.environment.items.UmlClass;
import fr.utc.nf28.moka.environment.users.User;

import java.util.HashMap;

public class JadeUtils {

	/**
	 * default type for Jade skill registering
	 */
	public static final String JADE_SKILL_TYPE_DEFAULT = "MokaDefaultSkillType";

	/**
	 * name for Jade skill registering of AndroidAgent
	 */
	public static final String JADE_SKILL_NAME_ANDROID = "AndroidAgentSkillName";

	/**
	 * name for Jade skill registering of ConnectionAgent
	 */
	public static final String JADE_SKILL_NAME_CONNECTION = "ConnectionAgentSkillName";

	/**
	 * name for Jade skill registering of CreationAgent
	 */
	public static final String JADE_SKILL_NAME_CREATION = "CreationAgentSkillName";

	public static final String JADE_SKILL_NAME_WEBSOCKET_SERVER = "WebSocketService";


	//Transactions to ConnectionAgent
	public static final String TYPE_CONNECTION_TO_CONNECTION_AGENT = "connectionToConnectionAgent";

	//Transactions to ItemCreationAgent
	public static final String TYPE_ADD_ITEM_TO_ITEM_CREATION_AGENT = "addItemToItemCreationAgent";

	//Transactions To WebSocketAgent
	public static final String TYPE_ADD_USER_TO_WEB_SOCKET_AGENT = "addUserToWebSocketAgent";
	public static final String TYPE_ADD_ITEM_TO_WEB_SOCKET_AGENT = "addItemToWebSocketAgent";

	public static final HashMap<String, Class> SPECIFIC_CONTENT_CLASS = new HashMap<String, Class>(){
		private final Class defaultClass = Object.class;

		{
			put(TYPE_ADD_USER_TO_WEB_SOCKET_AGENT, User.class);
			put(TYPE_ADD_ITEM_TO_WEB_SOCKET_AGENT, UmlClass.class);
		}

		@Override
		public Class get(Object key) {
			Class result = super.get(key);
			return (result == null) ? defaultClass : result;
		}
	};
}
