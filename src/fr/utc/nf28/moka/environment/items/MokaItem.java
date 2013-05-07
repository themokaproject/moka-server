package fr.utc.nf28.moka.environment.items;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.utc.nf28.moka.environment.users.User;

/**
 * A generic visual item.
 * The JsonTypeInfo annotation allows Jackson to include the real type (for example UmlClass) in the serialized string
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class MokaItem {
    private static int sIdIndex = 0;
    private int mId;
    private String mTitle;
    private int mX;
    private int mY;
    private transient User mLocker;

    public MokaItem() {
        mId = sIdIndex++;
    }

    public MokaItem(String title, int x, int y) {
        this();
        mTitle = title;
        mX = x;
        mY = y;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public int getY() {
        return mY;
    }

    public void setY(int mY) {
        this.mY = mY;
    }

    public int getX() {
        return mX;
    }

    public void setX(int x) {
        this.mX = x;
    }

    public void move(int x, int y) {
        mX = x;
        mY = y;
    }

    @JsonIgnore
    public boolean isLocked() {
        return mLocker == null;
    }

    @JsonIgnore
    public User getLocker() {
        return mLocker;
    }

    public void lock(User locker) {
        if(mLocker != null) {
            System.out.println("Item " + toString() + "already locked");
            return;
        }
        mLocker = locker;
    }

    public void unlock() {
        mLocker = null;
    }

    public String toString() {
        return "title:" + mTitle + " id:" + mId + " x:" + mX + " y:" + mY;
    }
}
