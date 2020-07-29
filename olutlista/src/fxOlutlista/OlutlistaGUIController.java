package fxOlutlista;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import olutlista.Humala;
import olutlista.Mausteet;
import olutlista.Olut;
import olutlista.Olutlista;
import olutlista.SailoException;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.*;

/**
 * Luokka olutlistan tapahtumiin
 * @author tonip
 * @version 3.6.2020
 *
 */
public class OlutlistaGUIController implements Initializable {
    

    @FXML private   ScrollPane panelOlut;
    @FXML private   TextField hakuehto;
    @FXML private   ComboBoxChooser<String> cbKentat;
    @FXML private   Label labelVirhe;
    @FXML private   ListChooser<Olut>chooserOluet;
    @FXML private   ListChooser<Mausteet>chooserMausteet;
    
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    
    
    @FXML private void handleHakuehto() {
        String hakukentta = cbKentat.getSelectedText();
        String ehto = hakuehto.getText(); 
        if ( ehto.isEmpty() )
            naytaVirhe(null);
        else
            naytaVirhe("Ei osata vielä hakea " + hakukentta + ": " + ehto);
    }
 
   /**
    * Käsitellään oluen lisäys uudessa ikkunassa
    */
   @FXML private void handleUusiOlut() {
       uusiOlut();
   }
   
   /**
    * Käsitellään uuden humalan lisääminen
    */
   @FXML private void handleUusiHumala() {
       uusiHumala();
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
       avustus();
   }
   
   /**
    * Avausikkuna
    */
   @FXML private void handleAvaa() {
      avaa(); 
   }
   
   /**
    * Tulostusikkuna
    */
   @FXML private void handleTulosta() {
       TulostusController tulostusCtrl = TulostusController.tulosta(null); 
       tulostaValitut(tulostusCtrl.getTextArea()); 
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
     * Käsitellään humalan poistaminen
     */
    @FXML private void handlePoistaHumala() {
        Dialogs.showMessageDialog("Ei osata vielä poistaa humalaa!");
    }

    //=====================================================================================================================================================================================================
    
    private     Olutlista       olutlista;
    private     Olut            olutKohdalla;
    private     TextArea        areaOlut = new TextArea();
    private     String          olutlistannimi = "oluet";

    /**
     * Tietojen tallennus
     */
    private String tallenna() {
        try {
            olutlista.tallenna();
            return null;
        }catch (SailoException ex) {
            Dialogs.showMessageDialog("Tallennuksessa ongelmia!" + ex.getMessage());
            return ex.getMessage();
        }
    }
    
    
    /**
     * Tarkistetaan onko tallennus tehty
     * @return true jos saa sulkaa sovelluksen, false jos ei
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }
    
    /**
     * tiedoston nimen kysyntä
     * @return true onnistuessa, false epäonnistuessa
     */
    public boolean avaa() {
        String uusinimi = ListaNimiController.kysyNimi(null, olutlistannimi);
        if(uusinimi == null) return false;
        lueTiedosto(uusinimi);
        return true;
    }
    
    private void naytaVirhe(String virhe) {
        if ( virhe == null || virhe.isEmpty() ) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }

    private void setTitle(String title) {
        ModalController.getStage(hakuehto).setTitle(title);
    }
    
    /**
     * Alustaa olutlistan lukemalla sen valitun nimisestä tiedostosta
     * @param nimi tiedosto josta olutlistan tiedot luetaan
     * @return null onnistuessa, jos ei niin virheilmoitus
     */
    protected String lueTiedosto(String nimi) {
        olutlistannimi = nimi;
        setTitle("Olutlista - " + olutlistannimi);
        try {
            olutlista.lueTiedostosta(nimi);
            hae(0);
            return null;
        } catch (SailoException e) {
            hae(0);
            String virhe = e.getMessage();
            if ( virhe != null)  Dialogs.showMessageDialog(virhe);
            return virhe;
        }
           
    }

    
    /**
     * @param jnro järjestysnumero oluelle
     */
    protected void hae(int jnro) {
        chooserOluet.clear();
        
        int index =0;
        for(int i =0; i < olutlista.getOluet(); i++){
           Olut olut = olutlista.annaOlut(i);
           if(olut.getTunnusNro()== jnro) index = i;
           chooserOluet.add(olut.getNimi(), olut);
        }
        chooserOluet.getSelectionModel().clearAndSelect(index);
    }
    
    private void uusiOlut() {
        Olut uusi = new Olut();
        uusi.rekisteroi();
        uusi.taytaOlut();
       try {
            olutlista.lisaa(uusi);
        } catch(SailoException e) {
           Dialogs.showMessageDialog("Ongelmia uuden luomisessa"+ " " + e.getMessage());
            return;
        }
        hae(uusi.getTunnusNro());
 
    }
    
    /** 
     * Tekee uuden tyhjän humalan editointia varten 
     */ 
    public void uusiHumala() { 
        if ( olutKohdalla == null ) return;  
        Humala uusi = new Humala();
        uusi.rekisteroi();
        uusi.taytaHumala(olutKohdalla.getTunnusNro());
        olutlista.lisaa(uusi);
        hae(olutKohdalla.getTunnusNro());
        
    } 
    
    /**
     * 
     */
    protected void alusta() {
        panelOlut.setContent(areaOlut);
        areaOlut.setFont (new Font ("Courier New", 12));
        panelOlut.setFitToHeight(true);
        
        chooserOluet.clear();
        chooserOluet.addSelectionListener(e->naytaOlut());
    }
    
    /**
     * 
     */
    protected void naytaOlut() {
        olutKohdalla = chooserOluet.getSelectedObject();
        
        if(olutKohdalla == null) return;
        
        areaOlut.setText("");
        try(PrintStream os = TextAreaOutputStream.getTextPrintStream(areaOlut)){
            tulosta(os,olutKohdalla);  
        }
    }
    
    
    /**
     * @param olutlista olutlista, jota käsitellään
     */
    public void setOlutlista(Olutlista olutlista) {
        this.olutlista = olutlista;
        naytaOlut();
    }
    
    /**
     * Tulostaa oluen tiedot
     * @param os tietovirta johon tulostetaan
     * @param olut tulostettava jäsen
     */
    public void tulosta(PrintStream os, final Olut olut) {
        os.println("----------------------------------------------");
        olut.tulosta(os);
        os.println("----------------------------------------------");
        List<Humala> humalat = olutlista.annaHumalat(olut);   
        for (Humala hum:humalat)
            hum.tulosta(os);  
    }

    /**
     * Tulostaa listassa olevat oluet tekstialueeseen
     * @param text alue johon tulostetaan
     */
    public void tulostaValitut(TextArea text) {
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(text)) {
            os.println("Tulostetaan kaikki jäsenet");
            for (int i = 0; i < olutlista.getOluet() ; i++) {
                Olut olut = olutlista.annaOlut(i);
                tulosta(os, olut);
                os.println("\n\n");
            }
        }
   }
   /**
    * Näytetään ohjelman suunnitelma erillisessä selaimessa.
    */
    private void avustus() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2020k/ht/topelamm#7IVAd1NXujQJ");
            desktop.browse(uri);
            } catch (URISyntaxException e) {
                return;
            } catch (IOException e) {
                return;
            }
    }
}
