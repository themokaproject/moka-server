package fr.utc.nf28.moka.environment.items;

import java.util.ArrayList;

public class VideoLink extends UrlItem {
	private static final String DEFAULT_TITLE = "Video";
	private static final String DEFAULT_URL = "http://www.youtube.com/watch?v=anwy2MPT5RE";
	private static final String sType = "video";
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 300;

	public VideoLink() {
	}

	public VideoLink(int x, int y) {
		super(DEFAULT_TITLE, x, y, sType);
		mUrl = DEFAULT_URL;
		mWidth = DEFAULT_WIDTH;
		mHeight = DEFAULT_HEIGHT;
	}


	public String toString() {
		return "video:" + mUrl + " " + super.toString();
	}

	@Override
	public void makeDefaultTitle() {
		mTitle = DEFAULT_TITLE+ " " + String.valueOf(mId);
	}

	@Override
	public void update(String field, String newValue) {
		if ("link".equals(field)) {
			mUrl = newValue;
		} else {
			super.update(field, newValue);
		}
	}
}
