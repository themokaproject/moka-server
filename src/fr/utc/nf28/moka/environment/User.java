package fr.utc.nf28.moka.environment;

public class User {
    private String mLastName;
    private String mFirstName;
    private int mColor;
    private MokaItem mCurrentItem;

    public User() {
    }

    public User(String lastName, String firstName) {
        mLastName = lastName;
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public MokaItem getCurrentItem() {
        return mCurrentItem;
    }

    public void setCurrentItem(MokaItem currentItem) {
        this.mCurrentItem = currentItem;
    }

    @Override
    public String toString() {
        return mFirstName + " " + mLastName;
    }
}
