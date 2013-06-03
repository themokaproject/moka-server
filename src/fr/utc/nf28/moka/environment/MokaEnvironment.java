package fr.utc.nf28.moka.environment;

import fr.utc.nf28.moka.environment.items.MokaItem;
import fr.utc.nf28.moka.environment.users.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A class that holds all the models of the running Moka platform
 * All MokaAgents have a reference to their MokaEnvironment
 */
public final class MokaEnvironment {
	private static MokaEnvironment sInstance = null;
	private final List<HistoryEntry> mHistoryEntries = new ArrayList<HistoryEntry>();
	private int sItemIdGenCurrentIndex = 0;
	private HashMap<String, User> mUsers = new HashMap<String, User>();
	private HashMap<Integer, MokaItem> mItems = new HashMap<Integer, MokaItem>();

	private MokaEnvironment() {
	}

	public static MokaEnvironment getInstance() {
		if (sInstance == null) {
			sInstance = new MokaEnvironment();
		}
		return sInstance;
	}

	public int generateNewId() {
		return sItemIdGenCurrentIndex++;
	}

	public void setItemIdGenCurrentIndex(int index) {
		sItemIdGenCurrentIndex = index;
	}

	public void clearItems() {
		mItems.clear();
	}

	public void clearHistory() {
		mHistoryEntries.clear();
	}

	public void addHistoryEntry(HistoryEntry historyEntry) {
		mHistoryEntries.add(historyEntry);
	}

	public void addItem(MokaItem item) {
		final int id = item.getId();
		if (mItems.put(id, item) == null) {
			System.out.println("item with id " + id + " added");
		} else {
			System.out.println("item with id " + id + " replaced");
		}
		final User locker = item.getLocker();
		if (locker != null) {
			mHistoryEntries.add(new HistoryEntry(locker.makePseudo() + " a ajouté " + item.getType() + " " + id));
		}
		System.out.println(toString());
	}

	/**
	 * use to unlock item
	 *
	 * @param itemId item id
	 */
	public void unlockItem(int itemId) {
		final MokaItem item = mItems.get(itemId);
		if (item != null) {
			item.unlock();
			System.out.println("item  " + item.getId() + " unlock.");
		}
	}

	public User lockItem(int itemId, String userAID) {
		final MokaItem item = mItems.get(itemId);
		if (item == null) {
			return null;
		}
		if (!item.isLocked()) {
			item.lock(getUserByAID(userAID));
			System.out.println(item.getType() + " " + item.getId() + " locked by " + item.getLocker().makePseudo());
		} else {
			System.out.println(item.getType() + " " + item.getId() + " already locked by " + item.getLocker().makePseudo());
		}
		return item.getLocker();
	}

	public void addUser(User user) {
		final String ip = user.getIp();
		if (mUsers.put(ip, user) == null) {
			System.out.println("user with ip " + ip + " added");
		} else {
			System.out.println("user with ip " + ip + " replaced");
		}
		mHistoryEntries.add(new HistoryEntry(user.makePseudo() + " s'est connecté"));
		System.out.println(toString());
	}

	public HashMap<String, User> getUsers() {
		return mUsers;
	}

	public User getUserByAID(String userAID) {
		User found = null;
		for (User u : mUsers.values()) {
			if (u.getAID().equals(userAID)) {
				found = u;
				break;
			}
		}
		return found;
	}

	public void removeItem(int itemId) {
		final MokaItem item = mItems.get(itemId);
		if (mItems.remove(itemId) == null) {
			System.out.println("no item with id " + itemId);
		} else {
			System.out.println("Item " + itemId + " removed");
			mHistoryEntries.add(new HistoryEntry(item.getLocker().makePseudo()
					+ " a supprimé " + item.getType() + " " + item.getId()));
		}
		System.out.println(toString());
	}

	public void removeUser(String ip) {
		if (mUsers.remove(ip) == null) {
			System.out.println("no user with ip " + ip);
		} else {
			System.out.println("User " + ip + " removed");
			mHistoryEntries.add(new HistoryEntry("Un utilisateur s'est déconnecté"));
		}
		System.out.println(toString());
	}

	public MokaItem moveItem(int itemId, int direction, int velocity) {
		//TODO implement getById !
		final MokaItem res = mItems.get(itemId);
		final int dd = (4 * velocity);
		if (res != null && dd < 100) {
			if (direction % 10 == 1) {
				res.setX(res.getX() + dd);
			} else if (direction % 10 == 2) {
				res.setX(res.getX() - dd);
			}

			if (direction >= 20) {
				res.setY(res.getY() - dd);
			} else if (direction >= 10) {
				res.setY(res.getY() + dd);
			}
		}

		return res;
	}

	public MokaItem resizeItem(int itemId, int direction) {
		//TODO implement getById !
		final MokaItem res = mItems.get(itemId);
		final int dd = 15;
		if (res != null) {
			if (direction % 10 == 1) {
				res.setWidth(res.getWidth() + dd);
			} else if (direction % 10 == 2) {
				res.setWidth(res.getWidth() - dd);
			}

			if (direction >= 20) {
				res.setHeight(res.getHeight() - dd);
			} else if (direction >= 10) {
				res.setHeight(res.getHeight() + dd);
			}
		}

		return res;
	}

	public MokaItem editItem(int itemId, String field, String newValue) {
		final MokaItem res = mItems.get(itemId);
		//TODO call update(String field, String newVal) of item
		return res;
	}

	public void updateItem(MokaItem newValue) {
		// TODO correctly implement udpate
	}

	public List<HistoryEntry> getHistory() {
		return mHistoryEntries;
	}

	public HashMap<Integer, MokaItem> getItems() {
		return mItems;
	}

	public String toString() {
		String s = "";
		s += "== Users ==\n";
		for (User u : mUsers.values()) {
			s += u.toString() + "\n";
		}
		s += "== Items ==\n";
		for (MokaItem mi : mItems.values()) {
			s += mi.toString() + "\n";
		}
		return s;
	}

}
