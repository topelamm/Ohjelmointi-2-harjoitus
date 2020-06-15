package fxOlutlista;
	
import javafx.scene.layout.Pane;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;



/**
 * @author tonip
 * @version 3.6.2020
 *
 */
public class OlutlistaMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		    FXMLLoader ldr = new FXMLLoader(getClass().getResource("OlutlistaGUIView.fxml")); 
	        final Pane root = (Pane)ldr.load();
	        final OlutlistaGUIController olutlistaCtrl = (OlutlistaGUIController)ldr.getController();   
	        
	        final Scene scene = new Scene(root);
	        scene.getStylesheets().add(getClass().getResource("olutlista.css").toExternalForm()); 
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Olutlista"); 
	        
	        //Platform.setImplicitExit(false); 
	        
	        primaryStage.setOnCloseRequest((event) -> {
	            // Kutsutaan voikoSulkea-metodia
	            if ( !olutlistaCtrl.voikoSulkea() ) event.consume();
	        });
	        
	        primaryStage.show();
	    } catch(Exception e) {
	        e.printStackTrace();
		}
	}
	
	/**
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
