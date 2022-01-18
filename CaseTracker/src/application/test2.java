package application;
	
import java.util.stream.IntStream;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

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
	    FilteredList<Case> filteredData = new FilteredList<>(data, s -> true);

	    TextField filterInput = new TextField();
	    filterInput.textProperty().addListener(obs->{
	        String filter = filterInput.getText(); 
	        if(filter == null || filter.length() == 0) {
	            filteredData.setPredicate(s -> true);
	        }
	        else {
	            filteredData.setPredicate(s -> s.getTitle().contains(filter));
	        }
	    });


	    BorderPane content = new BorderPane(new ListView<>(filteredData));
	    content.setBottom(filterInput);

	    Scene scene = new Scene(content, 500, 500);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}

}
