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
			vbox.setStyle("-fx-border-color: black");
			TextField field = new TextField();
			Button newcasebtn = new Button("New Case");
			vbox.getChildren().addAll(new Label("New Case:"), field, newcasebtn);

			// create combobox (dropdown) (doesnt work yet)
			ListView<String> lview = new ListView<>();
			lview.setPrefSize(20,100);
//			cbo.getItems().addAll(items);

			// displays the case titles
			HBox hbox = new HBox(15);
			hbox.setMaxWidth(100);
			Text casesfield = new Text();
			// register and handle 'new case' button
			newcasebtn.setOnAction(e -> {
				list.add(new Case(field.getText()));
				System.out.println(list.toString());
				String a = "";
				for (Case element : list) {
					// display each case title in the text node
					a += element.getTitle() + " ";
					casesfield.setText(a);
//					ObservableList<Case> items = FXCollections.observableArrayList(list);
//					for(String s: titleList) {
//						if(items.contains(s))
//							lview.getItems().add(s);
//					}
					if(!titleList.contains(element.getTitle()))
						titleList.add(element.getTitle());
				}
				
				ObservableList<String> items = FXCollections.observableArrayList(titleList);
				System.out.println(titleList.toString());
				lview.getItems().clear();
				lview.setItems(items);
			});
				
			hbox.getChildren().addAll(casesfield);
			// HBox for displaying all the cases in the list

			// displays the case times
			TextArea tarea = new TextArea();
			tarea.setEditable(true);
			tarea.setPrefColumnCount(5);
			tarea.setPrefRowCount(8);
			tarea.setWrapText(true);
			tarea.setStyle("-fx-padding: 5px;" + "    -fx-border-insets: 5px;" + "    -fx-background-insets: 5px;");

			// pane for the combo box
			StackPane paneforListView = new StackPane();
			paneforListView.setPadding(new Insets(5,10,10,10));
			paneforListView.getChildren().add(lview);

			BorderPane root = new BorderPane();
			root.setStyle("-fx-border-color: red");
			root.setPadding(new Insets(15, 15, 15, 10));
			root.setBottom(hbox);
			root.setRight(vbox);
			root.setCenter(tarea);
			root.setTop(paneforListView);
//			root.setRight(new CaseForm());
			Scene scene = new Scene(root, 500, 400);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
