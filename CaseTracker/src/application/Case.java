package application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Button;

public class Case {
	private boolean active;
	private LocalTime time;
	private LocalDate date;
	private long timeWorked;
	private String title;
	private Button start = new Button("Start");
	private Button stop = new Button("Stop");
	private Button refresh = new Button("Refresh");

	public Case() {
		active = false;
		timeWorked = System.currentTimeMillis();
		time = LocalTime.now();
		date = LocalDate.now();
		title = "case " + this.getClass();
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

	public void setActive(boolean a) {
		active = a;
	}
	public long getTimeWorked() {
		return (System.currentTimeMillis() - timeWorked) / 1000;
	}

	public String getTitle() {
		return title;
	}

	public Button getStartBtn() {
		return start;
	}

	public Button getStopBtn() {
		return stop;
	}
	
	public Button getRefreshBtn() {
		return refresh;
	}
}
