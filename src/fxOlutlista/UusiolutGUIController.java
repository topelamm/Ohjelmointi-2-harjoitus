package fxOlutlista;

import javafx.application.Platform;
import javafx.fxml.FXML;



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
    public void setDefault(String arg0) {
        // TODO Auto-generated method stub
        
    }

}
