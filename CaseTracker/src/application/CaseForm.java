package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CaseForm extends VBox{
	public CaseForm() {
		//style and padding
		setPadding(new Insets(10,10,10,10));
		setStyle("-fx-border-color: black");
		//add text-field and button
		TextField newcase = new TextField();
		getChildren().addAll(new Label("Case:"), new TextField(), new Button("create new case"));
		getChildren().addAll(new Button("Case 1"), new Button("Case 2"));
		System.out.println("Create case button was pressed");
	}
	

}
