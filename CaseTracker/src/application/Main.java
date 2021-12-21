package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	private ArrayList<Case> list = new ArrayList<>();
	private ArrayList<String> titleList = new ArrayList<>();
	private boolean free = true;
	private String summaryStr = "";
	private HashMap<String, Long> map = new HashMap<>();

	// TODO:
	@Override
	public void start(Stage primaryStage) {
		try {
			// pane for creating the cases
			VBox vbox = new VBox(15);
			vbox.setPadding(new Insets(15, 15, 15, 15));
//			vbox.setStyle("-fx-border-color: black");
			TextField field = new TextField();
			Button newcasebtn = new Button("New Case");
			Button summary = new Button("Summary");
			Button labels = new Button("Labels");
//			Image img = new Image("../images/ventionLogo.png");
			vbox.getChildren().addAll(new Label("New Case:"), field, newcasebtn, summary, labels);

			// create ListView
			ListView<String> lview = new ListView<>();
			lview.setPrefSize(20, 110);

			// register and handle 'new case' button
			newcasebtn.setOnAction(e -> {
				// make sure not to make duplicates
				if (!titleList.contains(field.getText()))
					list.add(new Case(field.getText()));
				// System.out.println(list.toString());
				for (Case element : list) {
					// add the case title to the titleList if it is not already in
					if (!titleList.contains(element.getTitle()))
						titleList.add(element.getTitle());
				}
				// adds the case titles to the ListView
				ObservableList<String> items = FXCollections.observableArrayList(titleList);
				// System.out.println(titleList.toString());
				lview.getItems().clear();
				lview.setItems(items);
			});

			// displays the case times
			TextArea tarea = new TextArea();
			tarea.setEditable(true);
			tarea.setPrefColumnCount(5);
			tarea.setPrefRowCount(8);
			tarea.setWrapText(true);
			tarea.setStyle("-fx-padding: 5px; -fx-border-insets: 5px;-fx-background-insets: 5px;");

			// set action on summary button
			summary.setOnAction(e -> {
				summaryStr = "";
				long totalTime = 0;
				for (Case i : list) {
					summaryStr += i.toString() + "\n";
					totalTime += i.getTotalTimeOnly();

					if (tarea.getText().contains("(")) {
						String[] a = i.getTitle().split("\\(", 0);
						// TODO: check if the hashmap already contains the key in it (use contains
						// method)
						if (!map.containsKey(a[1].substring(a[1].length() - 1)))
							map.put(a[1].substring(0, a[1].length() - 1), i.getTotalTimeOnly());
						else
							map.replace(a[1].substring(0, a[1].length() - 1),
									map.get(a[1].substring(0, a[1].length() - 1)) + i.getTotalTimeOnly());
					}
				}
				System.out.println(map.toString());
//				Iterator<Case> it = list.iterator();
//				while(it.hasNext()) {
//					summaryStr += it.next().toString() + "\n";
//				}
				tarea.setText(summaryStr + "\n Total Time for all is " + totalTime);
			});

			labels.setOnAction(e -> {
				for (Case i : list) {
					String[] a = i.getTitle().split("\\(", 0);
					// TODO: check if the hashmap already contains the key in it (use contains
					// method)
					if (!map.containsKey(a[1].substring(a[1].length() - 1)))
						map.put(a[1].substring(0, a[1].length() - 1), i.getTotalTimeOnly());
					else
						map.replace(a[1].substring(0, a[1].length() - 1),
								map.get(a[1].substring(0, a[1].length() - 1)) + i.getTotalTimeOnly());
				}
				System.out.println(map.toString());
			});
			// pane for the ListView
			StackPane paneforListView = new StackPane();
			paneforListView.setPadding(new Insets(1, 10, 10, 10));
			paneforListView.getChildren().add(lview);

			// pane for case button "start", "stop", and 'clear case' btns
			HBox casebtns = new HBox(15);

			// add listener to ListView
			lview.getSelectionModel().selectedItemProperty().addListener(ov -> {
				int i = 0;
				for (String s : titleList) {
					if (s == lview.getSelectionModel().getSelectedItem())
						i = titleList.indexOf(s);
				}
				// creates temp case
				Case temp = list.get(i);
				// gets the description about the case
				if (list.size() > 0) {

					tarea.setText(temp.toString());

					// shows the 'start' and 'stop' buttons
					casebtns.getChildren().clear();
					casebtns.getChildren().addAll(list.get(i).getStartBtn(), list.get(i).getStopBtn(),
							list.get(i).getRefreshBtn(), list.get(i).getClearCaseBtn());

					temp.getStartBtn().setOnAction(e -> {
						if (free) {
							temp.setActive(true);
							free = false;
							temp.setStartTime();
							temp.a += 1;
						}
					});

					temp.getStopBtn().setOnAction(e -> {
						if (!free) {
							temp.setActive(false);
							free = true;
							temp.setStopTime();
							temp.a += 1;
						}

					});

					temp.getRefreshBtn().setOnAction(e -> {
						tarea.setText(temp.toString());
					});

					temp.getClearCaseBtn().setOnAction(e -> {
						free = true;
						int index = titleList.indexOf(lview.getSelectionModel().getSelectedItem());
						if (titleList.size() > 1) {
							titleList.remove(index);
							list.remove(index);
							lview.getSelectionModel().clearAndSelect(1);
							lview.getItems().remove(index);
							ObservableList<String> items = FXCollections.observableArrayList(titleList);
							lview.setItems(items);
						} else if (titleList.size() == 1) {
							titleList.clear();
							list.clear();
							lview.getSelectionModel().clearSelection();
							ObservableList<String> items = FXCollections.observableArrayList(titleList);
							lview.setItems(items);
						}
						tarea.setText(" ");

					});
				}

			});

			BorderPane root = new BorderPane();
			// colours #15344f and #007ce7, #eff2f8, #bccace, #1ac876 -> for buttons
//			root.setStyle("-fx-background-color: #007ce7");
			root.setPadding(new Insets(15, 15, 15, 10));
			root.setRight(vbox);
			root.setCenter(tarea);
			root.setTop(paneforListView);
			root.setBottom(casebtns);

			Scene scene = new Scene(root, 450, 400, Color.GREEN);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Vention Case Tracker");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
