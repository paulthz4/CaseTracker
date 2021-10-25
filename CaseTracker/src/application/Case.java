package application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Case {
	private boolean active;
	private LocalTime time;
	private LocalDate date;
	private long timeWorked;
	private String title;

	public Case() {
		active = false;
		timeWorked = System.currentTimeMillis();
		time = LocalTime.now();
		date = LocalDate.now();
		title = "case "+this.getClass();
	}

	public Case(String title) {
		active = false;
		timeWorked = System.currentTimeMillis();
		time = LocalTime.now();
		date = LocalDate.now();
		this.title = title;
	}

	public String getTime() {
		return time.toString();
	}

	public String getDate() {
		return date.toString();
	}

	public String getDateTime() {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
		String formattedDate = myDateObj.format(myFormatObj);
		return formattedDate;
	}

	public boolean isActive() {
		return active;
	}

	public long getTimeWorked() {
		return timeWorked - System.currentTimeMillis();
	}

	public String getTitle() {
		return title;
	}
}
