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


	//connection transaction
	public static final String TRANSACTION_TYPE_CONNECTION = "connection";

	//addItem transaction
	public static final String TRANSACTION_TYPE_ADD_ITEM = "addItem";

}
