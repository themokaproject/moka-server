package fr.utc.nf28.moka.environment;

import java.util.Calendar;
import java.util.Date;

public class HistoryEntry {
	private String mAction;
	private Date mDate; // TODO: deserialize correctly using Jackson

	//Used in Json deserialization
	public HistoryEntry() {
	}

	public HistoryEntry(String action) {
		mAction = action;
		mDate = Calendar.getInstance().getTime();
	}

	public String getAction() {
		return mAction;
	}

	public void setAction(String action) {
		mAction = action;
	}

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date date) {
		mDate = date;
	}
}
