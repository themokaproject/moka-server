package fr.utc.nf28.moka.environment.items;

import java.util.ArrayList;

abstract public class UrlItem extends MokaItem {
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
		entries.add(new ContentEntry(MokaItem.CONTENT_FIELD_URL, mUrl));
		return entries;
	}

}
