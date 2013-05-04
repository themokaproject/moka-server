package fr.utc.nf28.moka.environment;

public class User {
    private String mLastName;
    private String mFirstName;
    private String mIpAdress;
    private int mColor;
    private MokaItem mCurrentItem;

    public User() {
    }

    public User(String firstName, String lastName) {
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

    public String getIpAdress() {
        return mIpAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.mIpAdress = ipAdress;
    }

    @Override
    public String toString() {
        return mFirstName + " " + mLastName;
    }
}
