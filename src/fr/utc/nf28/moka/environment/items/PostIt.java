package fr.utc.nf28.moka.environment.items;

import java.util.ArrayList;

public class PostIt extends MokaItem {
	public static final String CONTENT_FIELD_TEXT_CONTENT = "content";
	private static final String sType = "post-it";
	private static final String DEFAULT_TITLE = "Post-it";
	private static final String DEFAULT_CONTENT = "";
	private String mContent;

	public PostIt() {
	}

	public PostIt(int x, int y) {
		super(DEFAULT_TITLE, x, y, sType);
		mContent = DEFAULT_CONTENT;
	}



	public String getContent() {
		return mContent;
	}

	public void setContent(String content) {
		this.mContent = content;
	}

	public String toString() {
		return "post-it: " + super.toString();
	}

	@Override
	public void makeDefaultTitle() {
		mTitle = DEFAULT_TITLE+ " " + String.valueOf(mId);
	}

	@Override
	public void update(String field, String newValue) {
		if ("content".equals(field)) {
			mContent = newValue;
		} else {
			super.update(field, newValue);
		}
	}

	@Override
	public ArrayList<ContentEntry> getContentEntries() {
		ArrayList<ContentEntry> entries = super.getContentEntries();
		entries.add(new ContentEntry(PostIt.CONTENT_FIELD_TEXT_CONTENT, mContent));
		return entries;
	}
}
