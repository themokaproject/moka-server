package fr.utc.nf28.moka.environment.items;

public class PostIt extends MokaItem {
    private String mHeader;
    private String mContent;
    private static String sType = "post-it";

    public PostIt() {
    }

    public PostIt(String title, int x, int y, String mHeader, String mContent) {
        super(title, x, y, sType);
        this.mHeader = mHeader;
        this.mContent = mContent;
    }

    public String getHeader() {
        return mHeader;
    }

    public void setHeader(String mHeader) {
        this.mHeader = mHeader;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public String toString() {
        return "post-it:" + mHeader + " " + super.toString();
    }

}
