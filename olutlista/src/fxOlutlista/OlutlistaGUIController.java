package fxOlutlista;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import kayttamattomat.Mausteet;
import olutlista.Humala;
import olutlista.Olut;
import olutlista.Olutlista;
import olutlista.SailoException;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static fxOlutlista.OlutLisaysController.getFieldId;

import java.util.Collection;
import java.util.List;
//import java.util.List;
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
    
    @FXML private   TextField editNimi;
    @FXML private   TextField editVahvuus;
    @FXML private   TextField editTyyli;
    @FXML private   TextField editArvio;
    @FXML private   TextField editMaltaat;
    @FXML private   TextField editResepti;
    @FXML private   TextField editHuomioita;
    
    @FXML private   StringGrid<Humala>  tableHumalat;
    
    @FXML private   GridPane            gridOlut;
    
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    
    
    @FXML private void handleHakuehto() {
       hae(0);
        
        // String hakukentta = cbKentat.getSelectedText();
       // String ehto = hakuehto.getText(); 
       // if ( ehto.isEmpty() )
       //     naytaVirhe(null);
       // else
       //     naytaVirhe("Ei osata vielä hakea " + hakukentta + ": " + ehto);
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
       poistaOlut();
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

   @FXML private void handleMuokkaaOlut() {
       muokkaa(kentta);
   }
  
   @FXML private void handleMuokkaaHumala() {
       muokkaaHumalaa();
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
        poistaHumala();
    }

    //=====================================================================================================================================================================================================
    
    private     Olutlista       olutlista;
    private     Olut            olutKohdalla;
    private     String          olutlistannimi = "oluet";
    private     TextField[]     edits;
    private     int             kentta = 0;
    private     static Humala   apuhumala = new Humala();
    private     static Olut     apuolut = new Olut();

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
    
   // private void naytaVirhe(String virhe) {
   //     if ( virhe == null || virhe.isEmpty() ) {
   //         labelVirhe.setText("");
   //         labelVirhe.getStyleClass().removeAll("virhe");
   //         return;
   //     }
   //     labelVirhe.setText(virhe);
   //     labelVirhe.getStyleClass().add("virhe");
   // }

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
           // olutlista.lueHumalatTiedostosta();
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
     * @param jnr järjestysnumero oluelle
     */
    protected void hae(int jnr) {
        int jnro = jnr;
        chooserOluet.clear();
        if (jnro <= 0) {
            Olut kohdalla = olutKohdalla;
            if(kohdalla != null) jnro = kohdalla.getTunnusNro();
        }
        
        int k = cbKentat.getSelectedIndex() + apuolut.ekaKentta();
        String ehto = hakuehto.getText();
        if(ehto.indexOf('*')<0) ehto = "*" + ehto + "*";
        int index =0;
        Collection<Olut> oluet;
        try {
            
        oluet = olutlista.etsi(ehto, k);
        int i = 0;
        for (Olut olut : oluet) {
       
           if(olut.getTunnusNro()== jnro) index = i;
           chooserOluet.add(olut.getNimi(), olut);
           i++;
        }
        
        }catch (SailoException ex) {
        Dialogs.showMessageDialog("Onkelmia hakemisessa!" + ex.getMessage());
        }
        chooserOluet.getSelectionModel().clearAndSelect(index);
    }
    
    private void uusiOlut() {
        Olut uusi = new Olut();
        uusi = OlutLisaysController.kysyOlut(null, uusi,1);
        if(uusi == null) return;
        uusi.rekisteroi();
        //uusi.taytaOlut();
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
    public void uusiHumala()  { 
        if ( olutKohdalla == null ) return;
        try {
        Humala uusi = new Humala(olutKohdalla.getTunnusNro());
        uusi = TietueDialogController.kysyTietue(null, uusi, 0);
        if ( uusi == null ) return;
        uusi.rekisteroi();
        olutlista.lisaa(uusi);
        naytaHumalat(olutKohdalla); 
        tableHumalat.selectRow(1000);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Lisääminen epäonnistui: " + e.getMessage());
        }
    } 
    
    private void poistaOlut() {
        Olut olut = olutKohdalla;
        if (olut == null) return;
        if (!Dialogs.showQuestionDialog("poisto", "Ootko varma ettet juonut:" + olut.getNimi(), "Suattaapi olla", "Suottaapi olla etten"))
            return;
        olutlista.poista(olut);
        int index = chooserOluet.getSelectedIndex();
        hae(0);
        chooserOluet.setSelectedIndex(index);
    }
    
    private void poistaHumala() {
        int rivi = tableHumalat.getRowNr();
        if ( rivi < 0 ) return;
        Humala humala = tableHumalat.getObject();
        if ( humala == null ) return;
        olutlista.poistaHumala(humala);
        naytaHumalat(olutKohdalla);
        int humalia = tableHumalat.getItems().size(); 
        if ( rivi >= humalia ) rivi = humalia -1;
        tableHumalat.getFocusModel().focus(rivi);
        tableHumalat.getSelectionModel().select(rivi);
    }
    
    /**
     * 
     */
    protected void alusta() {
        //panelOlut.setContent(areaOlut);
        //areaOlut.setFont (new Font ("Courier New", 12));
        //panelOlut.setFitToHeight(true);
        
        chooserOluet.clear();
        chooserOluet.addSelectionListener(e->naytaOlut());
        edits = OlutLisaysController.luoKentta(gridOlut);
        for (TextField edit: edits)
            if (edit !=null) {
                edit.setEditable(false);
                edit.setOnMouseClicked(e ->  { if ( e.getClickCount() > 1 ) muokkaa(getFieldId(e.getSource(),kentta)); });  
                edit.focusedProperty().addListener((a,o,n) -> kentta = getFieldId(edit,kentta));  
            }
        
        int eka = apuhumala.ekaKentta(); 
        int lkm = apuhumala.getKenttia(); 
        String[] headings = new String[lkm-eka]; 
        for (int i=0, k=eka; k<lkm; i++, k++) headings[i] = apuhumala.getKysymys(k); 
        tableHumalat.initTable(headings); 
        tableHumalat.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
        tableHumalat.setEditable(false); 
        tableHumalat.setPlaceholder(new Label("Humaloimaton")); 
         
        // Tämä on vielä huono, ei automaattisesti muutu jos kenttiä muutetaan. 
        tableHumalat.setColumnSortOrderNumber(1); 
        tableHumalat.setColumnSortOrderNumber(2); 
        tableHumalat.setColumnWidth(1, 60); 
        
        //edits = new TextField[] {editNimi, editTyyli,
        //editArvio,
       //editVahvuus,
        //editMaltaat,
        //editResepti,
        //editHuomioita};
        
    
        
    }
    
    
    private void muokkaa(int k) {
        if (olutKohdalla == null) return;
        try {
            Olut olut = OlutLisaysController.kysyOlut(null, olutKohdalla.clone(), k);
            if (olut == null) return;
            olutlista.korvaaTaiLisaa(olut);
            hae(olut.getTunnusNro());
        }catch (CloneNotSupportedException e) {
            //
        }catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
        
        
    }
    
    /**
     * 
     */
    protected void naytaOlut() {
        olutKohdalla = chooserOluet.getSelectedObject();
        
        if(olutKohdalla == null) return;
        
        TietueDialogController.naytaTietue(edits, olutKohdalla);
        naytaHumalat(olutKohdalla);
        
        
        //editTyyli.setText(olutKohdalla.getTyyli());
        //editArvio.setText(olutKohdalla.getArvio());
        //editVahvuus.setText(olutKohdalla.getVahvuus());
        //editMaltaat.setText(olutKohdalla.getMaltaat());
        //editResepti.setText(olutKohdalla.getResepti());
        //editHuomioita.setText(olutKohdalla.getHuomioita());
        
        //Humala humala;
        //humala = olutlista.annaHumalat(olutKohdalla);
       

       // areaOlut.setText("");
       // try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaOlut)) {
        //    olutKohdalla.tulosta(os);
        //    humala.tulosta(os);
       // }

    }
    
    private void naytaHumalat(Olut olut) {
        tableHumalat.clear();
        if(olut == null) return;
        List<Humala> humalat = olutlista.annaHumalat(olut);
        if ( humalat.size() == 0 ) return;
        for (Humala hum: humalat)
            naytaHumala(hum);
        
        
        //naytaHumala(humala);
        //tableHumalat.set
        //textPaikkakunta.setText(paikkakunta.toString());

    }
    
    private void naytaHumala(Humala hum) {
        int kenttia = hum.getKenttia(); 
        String[] rivi = new String[kenttia-hum.ekaKentta()]; 
        for (int i=0, k=hum.ekaKentta(); k < kenttia; i++, k++) 
            rivi[i] = hum.anna(k); 
        tableHumalat.add(hum,rivi);
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
    
    private void muokkaaHumalaa() {
        int r = tableHumalat.getRowNr();
        if ( r < 0 ) return; 
        Humala humala = tableHumalat.getObject();
        if ( humala == null ) return;
        int k = tableHumalat.getColumnNr()+humala.ekaKentta();
        try {
            humala = TietueDialogController.kysyTietue(null, humala.clone(), k);
            if ( humala == null ) return;
            olutlista.korvaaTaiLisaa(humala); 
            naytaHumalat(olutKohdalla); 
            tableHumalat.selectRow(r);  
        } catch (CloneNotSupportedException  e) { /* clone on tehty */  
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia lisäämisessä: " + e.getMessage());
        }
    }
}
