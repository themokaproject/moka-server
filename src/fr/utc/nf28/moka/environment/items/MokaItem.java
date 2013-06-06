package fr.utc.nf28.moka.environment.items;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.utc.nf28.moka.environment.JsonDate;
import fr.utc.nf28.moka.environment.users.User;

import java.util.Calendar;
import java.util.Date;

/**
 * A generic visual item.
 */
public abstract class MokaItem {
	private static final int DEFAULT_WIDTH = 175;
	private static final int DEFAULT_HEIGHT = 100;
	private static int sIdIndex = 0;
	protected int mId;
	protected String mTitle;
	private int mX;
	private int mY;
	protected int mHeight;
	protected int mWidth;
	private User mLocker;
	private String mType;
	private Date mCreationDate;
	private User mCreator;
	private int mRotateX;
	private int mRotateY;
	private int mRotateZ;

	public MokaItem() {
		mId = sIdIndex++;
		mHeight = DEFAULT_HEIGHT;
		mWidth = DEFAULT_WIDTH;
		mCreationDate = Calendar.getInstance().getTime();
		mRotateX = 0;
		mRotateY = 0;
		mRotateZ = 0;
	}

	public MokaItem(String title, int x, int y, String type) {
		this();
		mTitle = title;
		mX = x;
		mY = y;
		mType = type;
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

	public int getHeight() {
		return mHeight;
	}

	public void setHeight(int height) {
		mHeight = height;
	}

	public int getWidth() {
		return mWidth;
	}

	public void setWidth(int width) {
		mWidth = width;
	}

	public void move(int x, int y) {
		mX = x;
		mY = y;
	}

	public void resize(int width, int height) {
		mWidth = width;
		mHeight = height;
	}

	@JsonIgnore
	public boolean isLocked() {
		return mLocker != null;
	}

	@JsonIgnore
	public User getLocker() {
		return mLocker;
	}

	public void lock(User locker) {
		if (mCreator == null) {
			mCreator = locker;
		}
		if (mLocker != null) {
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

	public String getType() {
		return mType;
	}

	public void setType(String type) {
		mType = type;
	}

	public void update(String field, String newValue) {
		if ("title".equals(field)) {
			mTitle = newValue;
		}
	}

	@JsonIgnore
	public String getCreatorName() {
		if (mCreator != null) {
			return mCreator.makePseudo();
		}
		return null;
	}

	@JsonSerialize(using = JsonDate.Serializer.class)
	public Date getCreationDate() {
		return mCreationDate;
	}

	@JsonDeserialize(using = JsonDate.Deserializer.class)
	public void setCreationDate(Date creationDate) {
		mCreationDate = creationDate;
	}

	public int getRotateY() {
		return mRotateY;
	}

	public void setRotateY(int rotateY) {
		mRotateY = rotateY;
	}

	public int getRotateX() {
		return mRotateX;
	}

	public void setRotateX(int rotateX) {
		mRotateX = rotateX;
	}

	public int getRotateZ() {
		return mRotateZ;
	}

	public void setRotateZ(int rotateZ) {
		mRotateZ = rotateZ;
	}

	abstract public void makeDefaultTitle();

}
