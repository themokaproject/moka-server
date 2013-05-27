package fr.utc.nf28.moka.environment;

public class HistoryEntry {
	private String mAction;

	public HistoryEntry(String action) {
		mAction = action;
	}

	public String getAction() {
		return mAction;
	}

	public void setAction(String action) {
		mAction = action;
	}
}
