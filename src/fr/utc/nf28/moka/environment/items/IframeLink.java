package fr.utc.nf28.moka.environment.items;


import java.util.ArrayList;

public class IframeLink extends UrlItem {
	private static final String DEFAULT_URL = "http://themokaproject.github.io/";
	private static final String DEFAULT_TITLE = "Iframe";
	private static final String sType = "iframe";
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 400;

	public IframeLink() {
	}

	public IframeLink(int x, int y) {
		super(DEFAULT_TITLE, x, y, sType);
		mUrl = DEFAULT_URL;
		mWidth = DEFAULT_WIDTH;
		mHeight = DEFAULT_HEIGHT;
	}


	public String toString() {
		return "iframe:" + mUrl + " " + super.toString();
	}

	@Override
	public void makeDefaultTitle() {
		mTitle = DEFAULT_TITLE + " " + String.valueOf(mId);
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

