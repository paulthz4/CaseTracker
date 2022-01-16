package application;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class test extends Application {
	private ArrayList<Case> list = new ArrayList<>();
	private ArrayList<String> titleList = new ArrayList<>();
	private boolean free = true;
	private String summaryStr = "";

	@Override
	public void start(Stage primaryStage) {
		try {
			// pane for creating the cases
			VBox vbox = new VBox(15);
			vbox.setPadding(new Insets(15, 15, 15, 15));
			TextField field = new TextField();
			Button newcasebtn = new Button("New Case");
			Button summary = new Button("Summary");
			Label activeCase = new Label("");
			vbox.getChildren().addAll(new Label("New Case:"), field, newcasebtn, summary, activeCase);

			// create ListView
			ListView<String> lview = new ListView<>();
			lview.setPrefSize(20, 110);

			// add search bar above list view
			ObservableList<String> itemsTemp = FXCollections.observableArrayList(titleList);
			// find a way to declare the ListView to have global scope and
			FilteredList<String> data = new FilteredList<>(itemsTemp, s -> true);
			TextField searchBar = new TextField();
			searchBar.textProperty().addListener(obs -> {
				String filter = searchBar.getText();
				if (filter == null || filter.length() == 0) {
					data.setPredicate(s -> true);
				} else
					data.setPredicate(s -> s.contains(filter));
			});
			// TODO: make search bar retrieve current case
			// pane for the ListView
			VBox paneforListView = new VBox(10);
			paneforListView.setPadding(new Insets(1, 10, 10, 10));
			paneforListView.getChildren().addAll(searchBar, lview);

			// register and handle 'new case' button
			newcasebtn.setOnAction(e -> {
				// make sure not to make duplicates
				if (!titleList.contains(field.getText())) {
					list.add(new Case(field.getText()));
					titleList.add(field.getText());
				}
				 System.out.println(list.toString());
				// adds the case titles to the ListView
				ObservableList<String> items = FXCollections.observableArrayList(titleList);
				// System.out.println(titleList.toString());
				lview.getItems().clear();
				lview.setItems(items);
				field.setText("");
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
				String s = "";
				for (Case i : list) {
					summaryStr += i.toString() + "\n";
					totalTime += i.getTotalTimeOnly();
				}
				if (totalTime == 0)
					s += totalTime + "";
				if (totalTime >= 3.6e6)
					s += (totalTime / 1000) / 60 / 60 % 24 + " hours " + ((totalTime / 1000) / 60) % 60 + " minutes"; // returns
																														// hours
																														// and
																														// minutes
				else if ((totalTime / 1000) >= 60)
					s += (totalTime / 1000) / 60 + " minutes " + totalTime / 1000 % 60 + " seconds"; // returns minutes
																										// and seconds
				else
					s += (totalTime / 1000) + " seconds"; // returns seconds
				tarea.setText(summaryStr + "Total Time for all is " + s);
			});

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
			root.setPadding(new Insets(15, 15, 15, 10));
			root.setRight(vbox);
			root.setCenter(tarea);
			root.setTop(paneforListView);
			root.setBottom(casebtns);

			Scene scene = new Scene(root, 450, 400);
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
