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
			ComboBox<Case> cbo = new ComboBox<>();
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
					a += element.getTitle() + " ";
					casesfield.setText(a);
//					ObservableList<Case> items = FXCollections.observableArrayList(list);
//					cbo.getItems().add(element.getTitle());
//					System.out.print(element.getTitle());
				}
				ObservableList<Case> items = FXCollections.observableArrayList(list);
				cbo.getItems().addAll(items);
			});
			hbox.getChildren().addAll(casesfield);
//			cases.getText().bind(list);
			// HBox for displaying all the cases in the list

			// displays the case times
			TextArea tarea = new TextArea();
			tarea.setEditable(true);
			tarea.setPrefColumnCount(5);
			tarea.setPrefRowCount(8);
			tarea.setWrapText(true);
			tarea.setStyle("-fx-padding: 5px;" + "    -fx-border-insets: 5px;" + "    -fx-background-insets: 5px;");

			// pane for the combo box
			StackPane paneforCbo = new StackPane();
			paneforCbo.getChildren().add(cbo);

			BorderPane root = new BorderPane();
			root.setStyle("-fx-border-color: red");
			root.setPadding(new Insets(15, 15, 15, 10));
			root.setBottom(hbox);
			root.setRight(vbox);
			root.setCenter(tarea);
			root.setTop(paneforCbo);
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
