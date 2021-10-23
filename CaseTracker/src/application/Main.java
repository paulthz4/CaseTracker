package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			root.setStyle("-fx-border-color: black");
			root.setPadding(new Insets(10,10,10,10));
			root.setLeft(new Label("Where case times go"));
			root.setRight(getBox());
//			root.setRight(new CaseForm());
			Scene scene = new Scene(root,400,400);
			
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	public VBox getBox() {
		VBox vbox = new VBox(15);
		vbox.setPadding(new Insets(15,15,15,15));
		vbox.setStyle("-fx-border-color: black");
		vbox.getChildren().addAll(new Label("Case:"), new TextField(),
				new Button("create case"), new Button("Case 1"), new Button("Case 2"));
		
		return vbox;
	}
}
