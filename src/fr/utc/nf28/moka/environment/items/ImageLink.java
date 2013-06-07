package fr.utc.nf28.moka.environment.items;

import java.util.ArrayList;

/**
 * An image item.
 */
public class ImageLink extends UrlItem {
	private static final String DEFAULT_TITLE = "Image";
	private static final String DEFAULT_URL = "./images/default_picture.gif";
	private static final int DEFAULT_WIDTH = 200;
	private static final int DEFAULT_HEIGHT = 200;
	private static final String sType = "image";

	public ImageLink() {
	}

	public ImageLink(int x, int y) {
		super(DEFAULT_TITLE, x, y, sType);
		mUrl = DEFAULT_URL;
		mHeight = DEFAULT_HEIGHT;
		mWidth = DEFAULT_WIDTH;
	}

	public String toString() {
		return "image:" + mUrl + " " + super.toString();
	}

	@Override
	public void makeDefaultTitle() {
		mTitle = DEFAULT_TITLE+ " " + String.valueOf(mId);
	}

}
