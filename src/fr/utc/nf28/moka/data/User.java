package fr.utc.nf28.moka.data;

import java.awt.*;

public class User {
    private String mLastName;
    private String mFirstName;
    private Color mColor;
    private MokaItem mCurrentItem;

    public User(String lastName, String firstName, Color color) {
        mLastName = lastName;
        mFirstName = firstName;
        mColor = color;
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

    public Color getColor() {
        return mColor;
    }

    public void setColor(Color color) {
        mColor = color;
    }

    public MokaItem getCurrentItem() {
        return mCurrentItem;
    }

    public void setCurrentItem(MokaItem currentItem) {
        this.mCurrentItem = currentItem;
    }
}
