package fr.utc.nf28.moka.environment;

import fr.utc.nf28.moka.environment.items.MokaItem;
import fr.utc.nf28.moka.environment.users.User;

import java.util.HashMap;

/**
 * A class that holds all the models of the running Moka platform
 * All MokaAgents have a reference to their MokaEnvironment
 */
public class MokaEnvironment {
	private int sItemIdGenCurrentIndex = 0;

	private HashMap<String, User> mUsers = new HashMap<String, User>();
	private HashMap<Integer, MokaItem> mItems = new HashMap<Integer, MokaItem>();

	public MokaEnvironment() {
	}

	public int generateNewId() {
		return sItemIdGenCurrentIndex++;
	}

	public void addItem(MokaItem item) {
		if (mItems.put(item.getId(), item) == null) {
			System.out.println("item with id " + item.getId() + " added");
		} else {
			System.out.println("item with id " + item.getId() + " replaced");
		}
		System.out.println(toString());
	}

	public void addUser(User user) {
		if (mUsers.put(user.getIp(), user) == null) {
			System.out.println("user with ip " + user.getIp() + " added");
		} else {
			System.out.println("user with ip " + user.getIp() + " replaced");
		}
		System.out.println(toString());
	}

	public void removeItem(int itemId) {
		if (mItems.remove(itemId) == null) {
			System.out.println("no item with id " + itemId);
		} else {
			System.out.println("Item " + itemId + " removed");
		}
		System.out.println(toString());
	}

	public void removeUser(String ip) {
		if (mUsers.remove(ip) == null) {
			System.out.println("no user with ip " + ip);
		} else {
			System.out.println("User " + ip + " removed");
		}
		System.out.println(toString());
	}

	public MokaItem moveItem(int itemId, int direction, int velocity) {
		//TODO implement getById !
		final MokaItem res = mItems.get(itemId);
		final int dd = (4 * velocity);
		System.out.println(dd);
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
		final int dd = 4;
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

	public void updateItem(MokaItem newValue) {
		// TODO correctly implement udpate
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
