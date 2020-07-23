package fxOlutlista;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import olutlista.Mallas;
import olutlista.Mauste;
import olutlista.Mausteet;
import olutlista.Olut;
import olutlista.Olutlista;
import olutlista.SailoException;

import java.io.PrintStream;
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
    
    private String olutlistannimi = "oluet";
    
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
       //uusiMallas(); //lisää mallas relaatioon/mauste, mahdollinen useampi (loop?)

       //uusiMauste(); //toteuta maustekohdalla ja maustelista, jokin kahva sille mitä tehdään, ks metadin sisältö ja setolutnro
       //uusiHumala(); //sama tähän kuin mallas
       
       
       //ModalController.showModal(OlutlistaGUIController.class.getResource("Uusiolut.fxml"), "Lisää olut", null,"");
   }
   
   @FXML private void handleUusiMauste() {
       uusiMallas();
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
       
       TulostusController tulostusCtrl = TulostusController.tulosta(null); 
       tulostaValitut(tulostusCtrl.getTextArea()); 

       
       //ModalController.showModal(OlutlistaGUIController.class.getResource("Tulostus.fxml"), "Tulosta", null,"");
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

    //=====================================================================================================================================================================================================
    
    private     Olutlista       olutlista;
    private     Olut            olutKohdalla;
    private     TextArea        areaOlut = new TextArea();
    private     Mausteet        mausteetlista; //TARKISTA MITEN TOTEUTAT LOPULTA
    private     Mausteet        mausteetKohdalla;
    private     TextArea        areaMausteet = new TextArea();

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
     */
    protected void lueTiedosto(String nimi) {
        olutlistannimi = nimi;
        setTitle("Olutlista - " + olutlistannimi);
        String virhe = "Ei osata lukea vielä";  // TODO: tähän oikea tiedoston lukeminen
        // if (virhe != null) 
            Dialogs.showMessageDialog(virhe);
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
        
        Mauste mauste = new Mauste();
        mauste.rekisteroi();
        mauste.taytaMauste(uusi.getTunnusNro(), 1, 1);
        
        olutlista.lisaa(mauste);
    }
    
    /** 
     * Tekee uuden tyhjät mausteet editointia varten 
     */ 
    public void uusiMauste() { 
        if ( olutKohdalla == null ) return;  
        Mauste uusi = new Mauste();
        mausteetlista.lisaa(uusi);
        uusi.rekisteroi();
        uusi.setOlutNro(olutKohdalla.getTunnusNro());
    } 
    
    
    /** 
     * Tekee uuden tyhjät mausteet editointia varten 
     */ 
    public void uusiMallas() { 
        if ( olutKohdalla == null ) return;  
        Mallas mal = new Mallas();  
        mal.rekisteroi();  
        mal.taytaMallas(olutKohdalla.getTunnusNro());  
        //mausteetlista.lisaa(mal);
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
     * 
     */
    protected void naytaMausteet() {
        mausteetKohdalla = chooserMausteet.getSelectedObject();
        
        if(mausteetKohdalla == null) return;
        
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
        //naytaMausteet();
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
        List<Mauste> mausteet = olutlista.annaMausteet(olut);   
        for (Mauste mau:mausteet)
            mau.tulosta(os);  
    }
    
    /**
     * Tulostaa oluen tiedot
     * @param os tietovirta johon tulostetaan
     * @param mauste tulostettava jäsen
     */
    public void tulosta(PrintStream os, final Mauste mauste) {
        os.println("----------------------------------------------");
        mauste.tulosta(os);
        os.println("----------------------------------------------");
        //List<Mauste> mausteet = olutlista.annaMausteet(mauste);   
        //for (Mauste mau:mausteet)
        //    mau.tulosta(os);  
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
}
