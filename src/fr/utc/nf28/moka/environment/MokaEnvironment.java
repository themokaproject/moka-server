package fr.utc.nf28.moka.environment;

import fr.utc.nf28.moka.environment.items.MokaItem;
import fr.utc.nf28.moka.environment.users.User;

import java.util.HashMap;

/**
 * A class that holds all the models of the running Moka platform
 * All MokaAgents have a reference to their MokaEnvironment
 */
public class MokaEnvironment {
    private HashMap<String, User> mUsers = new HashMap<String, User>();
    private HashMap<Integer, MokaItem> mItems = new HashMap<Integer, MokaItem>();

    public MokaEnvironment() {
    }

    public HashMap<String, User> getUsers() {
        return mUsers;
    }

    public HashMap<Integer, MokaItem> getItems() {
        return mItems;
    }

    public void addItem(MokaItem item) {
        if(mItems.put(item.getId(), item) == null) {
            System.out.println("item with id " + item.getId() + " added");
        } else {
            System.out.println("item with id " + item.getId() + " replaced");
        }
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
    }

    public void removeUser(String ip) {
        if (mUsers.remove(ip) == null) {
            System.out.println("no user with ip " + ip);
        } else {
            System.out.println("User " + ip + " removed");
        }
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
