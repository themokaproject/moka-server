package fr.utc.nf28.moka.environment.items;

public class VideoLink extends MokaItem{
    private String mLink;
    private static String sType = "video";

    public VideoLink() {
    }

    public VideoLink(String title, int x, int y, String mLink) {
        super(title, x, y, sType);
        this.mLink = mLink;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public String toString() {
        return "video:" + mLink + " " + super.toString();
    }
}
