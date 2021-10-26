package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
			
			// displays the case titles
			HBox hbox = new HBox(15);
			hbox.setMaxWidth(100);
			Text cases = new Text();
			// register and handle 'new case' button
			newcasebtn.setOnAction(e -> {
				list.add(new Case(field.getText()));
				System.out.println(list.toString());
				String a = "";
				for (Case element : list) {
					a += element.getTitle() + " ";
					cases.setText(a);
//					System.out.print(element.getTitle());
				}
			});

//			cases.getText().bind(list);
			// HBox for displaying all the cases in the list
			
			// displays the case times
			TextArea tarea = new TextArea();
			tarea.setEditable(true);
			tarea.setPrefColumnCount(5);
			tarea.setPrefRowCount(8);
			tarea.setWrapText(true);
			tarea.setStyle("-fx-padding: 5px;"
					+ "    -fx-border-insets: 5px;"
					+ "    -fx-background-insets: 5px;");
			
			hbox.getChildren().addAll(cases);

			BorderPane root = new BorderPane();
			root.setStyle("-fx-border-color: red");
			root.setPadding(new Insets(15, 15, 15, 10));
			root.setBottom(hbox);
			root.setRight(vbox);
			root.setCenter(tarea);
//			root.setRight(new CaseForm());
			Scene scene = new Scene(root, 500, 400);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void newCase() {
//		list.add((field.getText()));
//		System.out.println(list);
//	}

	public static void main(String[] args) {
		launch(args);
	}

//	public VBox getConsol() {
//		VBox vbox = new VBox(15);
//		vbox.setStyle("-fx-background-color: #FFFFFF;");
//		vbox.setStyle("-fx-border-color: white");
//		vbox.getChildren().add(new Label("where times go"));
//		return vbox;
//	}
}
