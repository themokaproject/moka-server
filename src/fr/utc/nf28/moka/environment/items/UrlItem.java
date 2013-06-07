package fr.utc.nf28.moka.environment.items;

import java.util.ArrayList;

abstract public class UrlItem extends MokaItem {
	public static final String CONTENT_FIELD_URL = "url";
	protected String mUrl;

	public UrlItem() {
	}

	public UrlItem(String title, int x, int y, String type) {
		super(title, x, y, type);
	}

	public String getUrl() {
		return mUrl;
	}

	public void setUrl(String url) {
		this.mUrl = url;
	}

	@Override
	public ArrayList<ContentEntry> getContentEntries() {
		ArrayList<ContentEntry> entries = super.getContentEntries();
		entries.add(new ContentEntry(UrlItem.CONTENT_FIELD_URL, mUrl));
		return entries;
	}

	@Override
	public void update(String field, String newValue) {
		if (UrlItem.CONTENT_FIELD_URL.equals(field)) {
			mUrl = newValue;
		} else {
			super.update(field, newValue);
		}
	}

}
