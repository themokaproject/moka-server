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
public class MokaEnvironment {
    private HashMap<String, User> mUsers = new HashMap<String, User>();
    private List<MokaItem> mItems = new ArrayList<MokaItem>();

    public MokaEnvironment() {
    }

    public HashMap<String, User> getUsers() {
        return mUsers;
    }

    public List<MokaItem> getItems() {
        return mItems;
    }

    public void addItem(MokaItem item) {
        mItems.add(item);
        System.out.println("add item " + item.toString());
        System.out.println(toString());
    }

    public void addUser(User user) {
        mUsers.put(user.getIp(), user);
        System.out.println("add user " + user.toString());
        System.out.println(toString());
    }

    public void removeItem(MokaItem item) {
        if (mItems.remove(item)) {
            System.out.println("remove item " + item.toString());
            System.out.println(toString());
        } else {
            System.out.println("no item " + item.toString());
        }

    }

    public void removeUser(String ip) {
        if(mUsers.remove(ip) == null) {
            System.out.println("no user with ip " + ip);
        } else {
            System.out.println("User " + ip + " removed");
        }
    }

    public void updateItem(MokaItem newValue) {
        for (MokaItem i : mItems) {
            if (i.getId() == newValue.getId()) {
                mItems.remove(i);
                mItems.add(newValue);
                System.out.println("modify item " + newValue.toString());
                System.out.println(toString());
                return;
            }
        }
        System.out.println("no item with id " + newValue.getId());
    }

    public String toString() {
        String s = "";
        s += "== Users ==\n";
        for (User u : mUsers.values()) {
            s += u.toString() + "\n";
        }
        s += "== Items ==\n";
        for (MokaItem mi : mItems) {
            s += mi.toString() + "\n";
        }
        return s;
    }


}
