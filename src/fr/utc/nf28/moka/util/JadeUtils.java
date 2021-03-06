package fr.utc.nf28.moka.util;

public final class JadeUtils {
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
	 * name for Jade item movement skill
	 */
	public static final String JADE_SKILL_NAME_ITEM_MOVEMENT = "ItemMovementSkillName";
	/**
	 * name for Jade item resizing skill
	 */
	public static final String JADE_SKILL_NAME_ITEM_RESIZING = "ItemResizingSkillName";
	/**
	 * name for Jade skill registering of CreationAgent
	 */
	public static final String JADE_SKILL_NAME_CREATION = "CreationAgentSkillName";
	/**
	 * name for Jade websocket skill
	 */
	public static final String JADE_SKILL_NAME_WEBSOCKET_SERVER = "WebSocketService";
	/**
	 * name for Jade locking skill
	 */
	public static final String JADE_SKILL_NAME_LOCKING = "LockingSkillName";
	/**
	 * name for Jade editing skill
	 */
	public static final String JADE_SKILL_NAME_ITEM_EDITING = "ItemEditingSkillName";
	/**
	 * name for Jade item rotating skill
	 */
	public static final String JADE_SKILL_NAME_ITEM_ROTATING = "ItemRotatingSkillName";
	/**
	 * connection transaction
	 */
	public static final String TRANSACTION_TYPE_CONNECTION = "connection";
    /**
     * logout transaction
     */
    public static final String TRANSACTION_TYPE_LOGOUT = "logout";
	/**
	 * addItem transaction
	 */
	public static final String TRANSACTION_TYPE_ADD_ITEM = "addItem";
	/**
	 * deleteItem transaction
	 */
	public static final String TRANSACTION_TYPE_DELETE_ITEM = "deleteItem";
	/**
	 * moveItem transaction
	 */
	public static final String TRANSACTION_TYPE_MOVE_ITEM = "moveItem";
	/**
	 * editItem transaction
	 */
	public static final String TRANSACTION_TYPE_EDIT_ITEM = "editItem";
	/**
	 * creation success transaction
	 */
	public static final String TRANSACTION_TYPE_ITEM_CREATION_SUCCESS = "creationSuccess";
	/**
	 * refresh current items transaction
	 */
	public static final String TRANSACTION_TYPE_REFRESH_CURRENT_ITEMS = "refreshCurrentItems";
	/**
	 * refresh history
	 */
	public static final String TRANSACTION_TYPE_REFRESH_HISTORY = "refreshHistory";
	/**
	 * resizeItem transaction
	 */
	public static final String TRANSACTION_TYPE_RESIZE_ITEM = "resizeItem";
	/**
	 * unlock transaction
	 */
	public static final String TRANSACTION_TYPE_UNLOCK_ITEM = "unlockItem";
	/**
	 * lock transaction
	 */
	public static final String TRANSACTION_TYPE_LOCK_ITEM = "lockItem";
	/**
	 * rotateItem transaction
	 */
	public static final String TRANSACTION_TYPE_ROTATE_ITEM = "rotateItem";
	/**
	 * lock success
	 */
	public static final String TRANSACTION_TYPE_LOCK_ITEM_SUCCESS = "lockItemSuccess";
	/**
	 * item already locked
	 */
	public static final String TRANSACTION_TYPE_LOCK_ITEM_ALREADY = "lockItemAlready";
	/**
	 * lock error
	 */
	public static final String TRANSACTION_TYPE_LOCK_ITEM_ERROR = "lockItemError";
}
