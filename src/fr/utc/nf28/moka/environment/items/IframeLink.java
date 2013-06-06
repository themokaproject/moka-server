package fr.utc.nf28.moka.environment.items;


public class IframeLink extends MokaItem {
	private static final String sType = "iframe";
	private String mLink;

	public IframeLink() {
	}

	public IframeLink(String title, int x, int y, String link) {
		super(title, x, y, sType);
		mLink = link;
	}

	public String getLink() {
		return mLink;
	}

	public void setLink(String link) {
		mLink = link;
	}

	public String toString() {
		return "iframe:" + mLink + " " + super.toString();
	}

	@Override
	public void makeDefaultTitle() {
		mTitle = "Iframe " + String.valueOf(mId);
	}

	@Override
	public void update(String field, String newValue) {
		if ("link".equals(field)) {
			mLink = newValue;
		} else {
			super.update(field, newValue);
		}
	}
}

