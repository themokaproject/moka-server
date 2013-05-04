package fr.utc.nf28.moka.environment;

public abstract class MokaItem {
    private int mX;
    private int mY;
    private boolean mLocked = true;

    public MokaItem() {

    }

    public MokaItem(int x, int y) {
        mX = x;
        mY = y;
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

    public boolean isLocked() {
        return mLocked;
    }

    public void setLocked(boolean locked) {
        mLocked = locked;
    }

    public String toString() {
        return "x:" + mX + " y:" + mY;
    }
}
