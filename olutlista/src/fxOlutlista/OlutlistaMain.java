package fxOlutlista;
	
import javafx.scene.layout.Pane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import olutlista.Olutlista;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;



/**
 * Ohjelmassa on tehty listaus sovellus, jolla voi pitää kirjaa nautituista oluista. 
 * Nykyisessä yhdelle oluelle voi laittaa useamman humalan.
 * 
 * Alkuperäisestä suunnitelmasta jäi toteuttamatta vastaava lisäys mahdollisuus maltaille kuin humalille
 * Mausteet relaatioluokan avulla.
 * 
 * 
 * @author tonip
 * @version 3.6.2020    
 *     
 */
public class OlutlistaMain extends Application {
	@Override      
	public void start(Stage primaryStage) {
		try {    
		    final FXMLLoader ldr = new FXMLLoader(getClass().getResource("OlutlistaGUIView.fxml")); 
	        final Pane root = (Pane)ldr.load();
	        final OlutlistaGUIController olutlistaCtrl = (OlutlistaGUIController)ldr.getController();   
	        
	        final Scene scene = new Scene(root);
	        scene.getStylesheets().add(getClass().getResource("olutlista.css").toExternalForm()); 
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Olutlista"); 
	          
	        Olutlista olutlista = new Olutlista();
            olutlistaCtrl.setOlutlista(olutlista);
            
	        //Platform.setImplicitExit(false); 
	        
	        primaryStage.setOnCloseRequest((event) -> {
	            // Kutsutaan voikoSulkea-metodia
	            if ( !olutlistaCtrl.voikoSulkea() ) event.consume();
	        });
	        
	 
	        primaryStage.show();
	        
	        Application.Parameters params = getParameters();
	        if (params.getRaw().size()>0)
	                olutlistaCtrl.lueTiedosto(params.getRaw().get(0));
	        else
	                if(!olutlistaCtrl.avaa()) Platform.exit();
	        
	    } catch(Exception e) {
	        e.printStackTrace();
	        System.err.println(e.getMessage());
		}
	}
	
	/**
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
