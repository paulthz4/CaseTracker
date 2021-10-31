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
	private LocalDateTime myDateObj = LocalDateTime.now();
	private long timeWorked = 0;
	private String title;
	private Button start = new Button("Start");
	private Button stop = new Button("Stop");
	private Button refresh = new Button("Refresh");

	public Case() {
		active = false;
		time = LocalTime.now();
		date = LocalDate.now();
		title = "case " + this.getClass();
	}

	public Case(String title) {
		active = false;
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

	public String getTimeWorked() {
		Long time = System.currentTimeMillis() - timeWorked;
		if(timeWorked == 0) return timeWorked+"";
		if (time >= 3.6e6)
			return (time / 1000) / 60 / 60 + " hours " + time / 1000 % 60 + " minutes"; // returns hours and minutes
		if ((time / 1000) > 60)
			return (time / 1000) / 60 + " minutes " + time / 1000 % 60 + " seconds"; // returns minutes and seconds
		else
			return (time / 1000) + " seconds"; // returns seconds
	}

	public void startTime() {
		timeWorked = System.currentTimeMillis();
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
