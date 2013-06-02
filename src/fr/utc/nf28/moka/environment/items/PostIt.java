package fr.utc.nf28.moka.environment.items;

public class PostIt extends MokaItem {
	private static final String sType = "post-it";
	private String mHeader;
	private String mContent;

	public PostIt() {
	}

	public PostIt(String title, int x, int y, String header, String content) {
		super(title, x, y, sType);
		mHeader = header;
		mContent = content;
	}

	public String getHeader() {
		return mHeader;
	}

	public void setHeader(String header) {
		mHeader = header;
	}

	public String getContent() {
		return mContent;
	}

	public void setContent(String content) {
		this.mContent = content;
	}

	public String toString() {
		return "post-it:" + mHeader + " " + super.toString();
	}

	@Override
	public void makeDefaultTitle() {
		mTitle = "Post-it "+String.valueOf(mId);
	}

}
