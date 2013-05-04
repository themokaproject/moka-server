package fr.utc.nf28.moka.environment.items;

/**
 * An image item.
 */
public class Image extends MokaItem {
    private String mUrl;

    public Image() {
    }

    public Image(int x, int y, String url) {
        super(x,y);
        mUrl = url;
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

}
