package fxOlutlista;

import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalControllerInterface;



/**
 * Luokka olutlistan tapahtumiin
 * @author tonip
 * @version 3.6.2020
 *
 */
public class UusiolutGUIController implements ModalControllerInterface<String> {

    

    

    /**
     * Käsitellään tallennuskäsky
     */
    @FXML private void handleTallenna() {
        tallenna();
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



  
    
    @FXML private TextField textVastaus;

    
    @FXML private void handleOK() {
        ModalController.closeStage(textVastaus);
    }

    
    @FXML private void handleCancel() {
        ModalController.closeStage(textVastaus);
    }



    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }



    @Override
    public void setDefault(String oletus) {
        // TODO Auto-generated method stub
        
    }


   
    

}
