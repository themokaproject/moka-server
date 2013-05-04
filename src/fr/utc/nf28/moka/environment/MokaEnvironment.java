package fr.utc.nf28.moka.environment;

import fr.utc.nf28.moka.environment.items.MokaItem;
import fr.utc.nf28.moka.environment.users.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that holds all the models of the running Moka platform
 * All MokaAgents have a reference to their MokaEnvironment
 */
public class MokaEnvironment {
    private List<User> mUsers = new ArrayList<User>();
    private List<MokaItem> mItems = new ArrayList<MokaItem>();

    public MokaEnvironment() {
    }

    public List<User> getUsers() {
        return mUsers;
    }

    public List<MokaItem> getItems() {
        return mItems;
    }

    public void addItem(MokaItem item) {
        mItems.add(item);
        System.out.println(toString());
    }

    public void addUser(User user) {
        mUsers.add(user);
        System.out.println(toString());
    }

    public void removeItem(MokaItem item) {
        mItems.remove(item);
        System.out.println(toString());
    }

    public void removeUser(User user) {
        mUsers.remove(user);
        System.out.println(toString());
    }

    public String toString() {
        String s = "";
        s+="== Users ==\n";
        for (User u : mUsers) {
            s+= u.toString() + "\n";
        }
        s+="== Items ==\n";
        for (MokaItem mi : mItems) {
            s+= mi.toString() + "\n";
        }
        return s;
    }


}
