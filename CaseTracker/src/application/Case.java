package application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Stack;

import javafx.scene.control.Button;

public class Case {
	private boolean active;
	private LocalDateTime myDateObj = LocalDateTime.now();
	private long startTime = 0;
	private long stopTime;
	private long totalTime;
	private String title;
	private Button start = new Button("Start");
	private Button stop = new Button("Stop");
	private Button refresh = new Button("Refresh");
	private Button clearCase = new Button("Clear Case");
	private Stack<Long> timeList = new Stack<>();

	public Case() {
		active = false;
		title = "case " + this.getClass();
	}

	public Case(String title) {
		active = false;
		this.title = title;
	}

	public String getDateTime() {
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
		String formattedDate = myDateObj.format(myFormatObj);
		return formattedDate;
	}
	
	public String getTotalTime() {
		return totalTime +"";
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean a) {
		active = a;
	}

	public String getTimeWorked() {
		long time = 0;
		long newTime;
		if (active) {
			if ( stopTime == 0) { // startTime != 0 checks if the start buttons has been pressed & the
													// stop btn hasn't been pressed
				time = System.currentTimeMillis() - startTime;
				totalTime += time;
			} else if (startTime != 0 && stopTime != 0) {
				newTime = stopTime - startTime;
				timeList.add(newTime); // not important
				totalTime += newTime;
			}
//		long time;
//		if (stopTime != 0 && startTime != 0) {
//			time = stopTime - startTime;
//			timeList.add(time);
//			for(Long i: timeList) {
//				time += i;
//			}
//		}
			else {
				time = 0;
			}
			if (time == 0)
				return time + "";
			if (time >= 3.6e6)
				return (time / 1000) / 60 / 60 + " hours " + time / 1000 % 60 + " minutes"; // returns hours and minutes
			else if ((time / 1000) >= 60)
				return (time / 1000) / 60 + " minutes " + time / 1000 % 60 + " seconds"; // returns minutes and seconds
			else
				return (totalTime+time / 1000) + " seconds"; // returns seconds
		} 
		else {
			if (time == 0)
				return time + "";
			if (time >= 3.6e6)
				return (time / 1000) / 60 / 60 + " hours " + time / 1000 % 60 + " minutes"; // returns hours and minutes
			else if ((time / 1000) >= 60)
				return (time / 1000) / 60 + " minutes " + time / 1000 % 60 + " seconds"; // returns minutes and seconds
			else
				return (totalTime / 1000) + " seconds"; // returns seconds
		}
	}

	public void setStopTime() {
		stopTime = System.currentTimeMillis();
	}

	public void setStartTime() {
		startTime = System.currentTimeMillis();
	}

	public String getTitle() {
		return title;
	}

	public Button getStartBtn() {
//		start.setStyle("-fx-text-fill: #22C628");
		return start;
	}

	public Button getStopBtn() {
		return stop;
	}

	public Button getRefreshBtn() {
		return refresh;
	}

	public Button getClearCaseBtn() {
		clearCase.setStyle("-fx-text-fill: #F43838");
		return clearCase;
	}
}
