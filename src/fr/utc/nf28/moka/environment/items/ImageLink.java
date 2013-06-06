package fr.utc.nf28.moka.environment.items;

/**
 * An image item.
 */
public class ImageLink extends MokaItem {
	private static final int DEFAULT_WIDTH = 200;
	private static final int DEFAULT_HEIGHT = 200;
	private static final String sType = "image";
	private String mUrl;

	public ImageLink() {
	}

	public ImageLink(String title, int x, int y, String url) {
		super(title, x, y, sType);
		mUrl = url;
		mHeight = DEFAULT_HEIGHT;
		mWidth = DEFAULT_WIDTH;
	}

	public String getUrl() {
		return mUrl;
	}

	public void setUrl(String url) {
		this.mUrl = url;
	}

	public String toString() {
		return "image:" + mUrl + " " + super.toString();
	}

	@Override
	public void makeDefaultTitle() {
		mTitle = "Image " + String.valueOf(mId);
	}

	@Override
	public void update(String field, String newValue) {
		if ("url".equals(field)) {
			mUrl = newValue;
		} else {
			super.update(field, newValue);
		}
	}
}
