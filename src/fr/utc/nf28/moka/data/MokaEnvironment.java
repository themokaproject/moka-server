package fr.utc.nf28.moka.data;

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
    }

    public void addUser(User user) {
        mUsers.add(user);
    }

    public void removeItem(MokaItem item) {
        mItems.remove(item);
    }

    public void removeUser(User user) {
        mUsers.remove(user);
    }


}
