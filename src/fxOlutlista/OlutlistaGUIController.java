package fxOlutlista;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.*;

/**
 * Luokka olutlistan tapahtumiin
 * @author tonip
 * @version 3.6.2020
 *
 */
public class OlutlistaGUIController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        //
    }
    
   /**
    * Käsitellään oluen lisäys uudessa ikkunassa
    */
   @FXML private void handleUusiOlut() {
       
       ModalController.showModal(OlutlistaGUIController.class.getResource("Uusiolut.fxml"), "Lisää olut", null,"");
   }
   
   /**
    * Käsitellään oluen poistaminen uudessa ikkunassa
    */
   @FXML private void handlePoistaOlut() {
       
       ModalController.showModal(OlutlistaGUIController.class.getResource("Uusiolut.fxml"), "Poista olut", null,"");
   }
   
   /**
    * Infoikkuna
    */
   @FXML private void handleInfo() {
       
       ModalController.showModal(OlutlistaGUIController.class.getResource("Info.fxml"), "Tietoja", null,"");
   }
   
   /**
    * Apuaikkuna
    */
   @FXML private void handleApua() {
       
       ModalController.showModal(OlutlistaGUIController.class.getResource("Apua.fxml"), "Apua", null,"");
   }
   
   /**
    * Avausikkuna
    */
   @FXML private void handleAvaa() {
       
       ModalController.showModal(OlutlistaGUIController.class.getResource("Avaa.fxml"), "Avaa", null,"");
   }
   
   /**
    * Tulostusikkuna
    */
   @FXML private void handleTulosta() {
       
       ModalController.showModal(OlutlistaGUIController.class.getResource("Tulostus.fxml"), "Tulosta", null,"");
   }

    /**
     * Käsitellään tallennuskäsky
     */
    @FXML private void handleTallenna() {
        tallenna();
    }
    
    
    /**
     * Käsitellään lopetuskäsky
     */
    @FXML private void handleLopeta() {
        tallenna();
        Platform.exit();
    }

    
    /**
     * Tietojen tallennus
     */
    private void tallenna() {
        Dialogs.showMessageDialog("Tallennetetaan! Mutta ei toimi vielä");
    }
    
    /**
     * Tarkistetaan onko tallennus tehty
     * @return true jos saa sulkaa sovelluksen, false jos ei
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }

}
