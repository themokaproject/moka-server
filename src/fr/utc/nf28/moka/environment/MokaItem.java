package fr.utc.nf28.moka.environment;

import java.awt.*;

public abstract class MokaItem {
    private int mX;
    private int mY;
    private User mLastModifier;
    private boolean mLocked = true;

    public MokaItem(int x, int y) {
        mX = x;
        mY = y;
    }

    public void move(int x, int y) {
        mX = x;
        mY = y;
    }

    public Point getPosition() {
        return new Point(mX,mY);
    }

    public User getLastModifier() {
        return mLastModifier;
    }

    public void setLastModifier(User lastModifier) {
        this.mLastModifier = lastModifier;
    }

    public boolean isLocked() {
        return mLocked;
    }

    public void setLocked(boolean locked) {
        mLocked = locked;
    }
}
