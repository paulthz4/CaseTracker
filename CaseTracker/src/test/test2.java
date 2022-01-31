package test;
import application.Case;
import java.util.ArrayList;
import java.util.stream.IntStream;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/* TEST CLASS IN TestApp 
 * code base on https://stackoverflow.com/questions/42628472/javafx-search-in-listview
 *   DELETE PROJECT WHEN DONE */

public class test2 extends Application {
	@Override
	public void start(Stage primaryStage) {

	    ObservableList<Case> data = FXCollections.observableArrayList();
//	    IntStream.range(0, 100).mapToObj(Integer::toString).forEach(data::add);
	    data.add(new Case("case 1"));
	    data.add(new Case("case 2"));
	    data.add(new Case("case 4"));
	    ObservableList<String> datas = FXCollections.observableArrayList();
	    datas.add("case 1");
	    datas.add("case 2");
	    datas.add("case 3");
	    datas.add("case 4");
	    datas.add("part 3 act 1");
	    datas.add("part 5 act 3");
	    datas.add("part 5 act 1");
	    FilteredList<String> filteredData = new FilteredList<>(datas, s -> true);
	    
	    TextArea tarea = new TextArea();
	    
	    TextField filterInput = new TextField();
	    filterInput.textProperty().addListener(obs->{
	        String filter = filterInput.getText(); 
	        if(filter == null || filter.length() == 0) {
	            filteredData.setPredicate(s -> true);
	        }
	        else {
	            filteredData.setPredicate(s -> s.contains(filter));
	        }
	    });
	    ListView<String> lview = new ListView<>(filteredData);
	    lview.getSelectionModel().selectedItemProperty().addListener(ov -> {
	    	// add logic to display case attributes here
	    	String str = ov.toString().split("value:")[1];
	    	tarea.setText("you clicked on " + str.substring(0, str.length()-1));
	    });
	    
	    tarea.setEditable(true);
		tarea.setPrefColumnCount(5);
		tarea.setPrefRowCount(8);
		tarea.setWrapText(true);
	    VBox content = new VBox(filterInput, lview,tarea);
//	    content.setBottom(filterInput);
//	    content.setCenter(tarea);

	    Scene scene = new Scene(content, 500, 500);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}

}
