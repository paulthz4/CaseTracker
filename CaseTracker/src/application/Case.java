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
	private Button clearCase = new Button("Close Case");
	public int a=0;
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
			if (totalTime == 0)
				return totalTime + "";
			if (totalTime >= 3.6e6)
				return (totalTime / 1000) / 60 / 60 + " hours " + totalTime / 1000 % 60 + " minutes"; // returns hours and minutes
			else if ((totalTime / 1000) >= 60)
				return (totalTime / 1000) / 60 + " minutes " + totalTime / 1000 % 60 + " seconds"; // returns minutes and seconds
			else
				return (totalTime / 1000) + " seconds"; // returns seconds
//		return totalTime + "";
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
			 // the start buttons has been pressed but the stop btn hasn't been pressed
				time = System.currentTimeMillis() - startTime;
			
			if (time == 0)
				return time + "";
			if (time >= 3.6e6)
				return (time / 1000) / 60 / 60 + " hours " + (time / 1000) / 60 + " minutes"; // returns hours and minutes
			else if ((time / 1000) >= 60)
				return (time / 1000) / 60 + " minutes " + time / 1000 % 60 + " seconds"; // returns minutes and seconds
			else
				return (time / 1000) + " seconds"; // returns seconds
			
		}
		// if the case is inactive (free)
		else {
			if (time == 0)
				return time + "";
			if (time >= 3.6e6)
				return (time / 1000) / 60 / 60 + " hours " + time / 1000 % 60 + " minutes"; // returns hours and minutes
			else if ((time / 1000) >= 60)
				return (time / 1000) / 60 + " minutes " + time / 1000 % 60 + " seconds"; // returns minutes and seconds
			else
				return (time / 1000) + " seconds"; // returns seconds
		}
	}

	public void setStopTime() {
		stopTime = System.currentTimeMillis();
		timeList.push(stopTime - startTime);
		for(Long i: timeList) {
			totalTime += i;
		}
		timeList.pop();
		stopTime =0;
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
