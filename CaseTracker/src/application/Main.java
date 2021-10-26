package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	private TextField field = new TextField();
	private ArrayList<Case> list = new ArrayList<>(20);
	private ArrayList<String> titleList = new ArrayList<>();

	@Override
	public void start(Stage primaryStage) {
		try {
			// pane for creating the cases
			VBox vbox = new VBox(15);
			vbox.setPadding(new Insets(15, 15, 15, 15));
//			vbox.setStyle("-fx-border-color: black");
			TextField field = new TextField();
			Button newcasebtn = new Button("New Case");
			vbox.getChildren().addAll(new Label("New Case:"), field, newcasebtn);

			// create ListView
			ListView<String> lview = new ListView<>();
			lview.setPrefSize(20, 110);

			// register and handle 'new case' button
			newcasebtn.setOnAction(e -> {
				// make sure not to make duplicates
				if (!titleList.contains(field.getText()))
					list.add(new Case(field.getText()));
				System.out.println(list.toString());
				for (Case element : list) {
					// add the case title to the titleList if it is not already in
					if (!titleList.contains(element.getTitle()))
						titleList.add(element.getTitle());
				}
				// adds the case titles to the ListView
				ObservableList<String> items = FXCollections.observableArrayList(titleList);
				System.out.println(titleList.toString());
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

			// pane for the ListView
			StackPane paneforListView = new StackPane();
			paneforListView.setPadding(new Insets(1, 10, 10, 10));
			paneforListView.getChildren().add(lview);
			
			// add listener to ListView
			lview.getSelectionModel().selectedItemProperty().addListener(ov->{
				int i = 0;
				for(String s: titleList) {
					if(s == lview.getSelectionModel().getSelectedItem()) {
						i = titleList.indexOf(s);
					}
				}
				tarea.setText(list.get(i).getTitle());
			});
			
			BorderPane root = new BorderPane();
//			root.setStyle("-fx-border-color: red");
			root.setPadding(new Insets(15, 15, 15, 10));
			root.setRight(vbox);
			root.setCenter(tarea);
			root.setTop(paneforListView);
			
			Scene scene = new Scene(root, 500, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Case Tracker");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
