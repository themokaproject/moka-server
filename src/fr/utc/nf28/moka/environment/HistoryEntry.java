package fr.utc.nf28.moka.environment;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Calendar;
import java.util.Date;

public class HistoryEntry {
	private String mAction;
	private Date mDate;

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

	@JsonSerialize(using = JsonDate.Serializer.class)
	public Date getDate() {
		return mDate;
	}

	@JsonDeserialize(using = JsonDate.Deserializer.class)
	public void setDate(Date date) {
		mDate = date;
	}
}
