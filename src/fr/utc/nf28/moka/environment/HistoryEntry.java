package fr.utc.nf28.moka.environment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Calendar;
import java.util.Date;

public class HistoryEntry {
	private String mAction;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd,HH:00", timezone = "CET")
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

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getDate() {
		return mDate;
	}

	public void setDate(Date date) {
		mDate = date;
	}
}
