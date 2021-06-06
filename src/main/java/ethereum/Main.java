package ethereum;
	
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Main extends Application {	
	public static Stage connectStage;	
	@Override
	public void start(Stage primaryStage) {
		try {
		    connectStage = primaryStage;
	        Parent root = FXMLLoader.load(this.getClass().getResource("connectionXML.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Protect your document");		
			primaryStage.setResizable(false);
			//labelState.setVisible(false);			
			primaryStage.show();			
		} catch(Exception e) {
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
