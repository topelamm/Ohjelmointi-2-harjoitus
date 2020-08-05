package fxOlutlista;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import olutlista.Olut;

/**
 * Jäsenien tietojen kysyminen uudessa ikkunassa
 * @author tonip
 * @version 30.7.2020
 *
 */
public class OlutLisaysController implements ModalControllerInterface<Olut>, Initializable {
    
    @FXML private Label labelVirhe;
    @FXML private TextField editNimi;
    @FXML private TextField editVahvuus;
    @FXML private TextField editTyyli;
    @FXML private TextField editArvio;
    @FXML private TextField editMaltaat;
    @FXML private TextField editResepti;
    @FXML private TextField editHuomioita;
    @FXML private ScrollPane panelOlut;
    @FXML private GridPane gridOlut;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
       alusta();
        
    }
    
    @FXML private void handleOK() {
        if(olutKohdalla != null && olutKohdalla.getNimi().trim().contentEquals("")) {
            naytaVirhe("Nimi ei saa olla tyhjä");
            return;
        }
        ModalController.closeStage(labelVirhe);
    }
    
    @FXML private void handleCancel() {
        olutKohdalla = null;
        ModalController.closeStage(labelVirhe);

    }
    
    //===================================================================================================================================
    
    private Olut olutKohdalla;
    private TextField edits[];
    private static Olut apuolut = new Olut();
    private int kentta = 0;
    
   /**
    * tarvittavat alustukset
    */
    protected void alusta() {
       //edits = new TextField[] {editNimi, editTyyli,
       //        editArvio,
       //        editVahvuus,
       //        editMaltaat,
       //        editResepti,
       //        editHuomioita};
       //int i = 0;
       //for(TextField edit : edits) {
       //    final int k = ++i;
       //    edit.setOnKeyReleased(e -> kasitteleMuutosOlueen(k, (TextField) (e.getSource())));
           
           edits= luoKentta(gridOlut); 
           for(TextField edit : edits)
              if(edit !=null)
                  edit.setOnKeyReleased(e -> kasitteleMuutosOlueen((TextField)(e.getSource())));
           panelOlut.setFitToHeight(true);
    }
  
    /**
     * oluen tiedot
     * @param gridOlut mihin 
     * @return tekstikentät
     */
    public static TextField[] luoKentta(GridPane gridOlut) {
        gridOlut.getChildren().clear();
        TextField[] edits = new TextField[apuolut.getKenttia()];
        
        for (int i=0, k=apuolut.ekaKentta(); k<apuolut.getKenttia(); k++,i++) {
            Label label = new Label(apuolut.getKysymys(k));
            gridOlut.add(label, 0, i);
            TextField edit = new TextField();
            edits[k] = edit;
            edit.setId("e"+k);
            gridOlut.add(edit, 1, i);
        }
        return edits;
    }
    
    private void naytaVirhe(String virhe) {
        if (virhe == null || virhe.isEmpty()) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }
    
    /**
     * Käsitellään olueen tullut muutos
     * @param edit muuttunut kenttä
     */
    private void kasitteleMuutosOlueen(TextField edit) {
        if (olutKohdalla == null) return;
        int k = getFieldId(edit,apuolut.ekaKentta());
        String s = edit.getText();
        String virhe = null;
        virhe = olutKohdalla.aseta(k,s); 
        if (virhe == null) {
            Dialogs.setToolTipText(edit,"");
            edit.getStyleClass().removeAll("virhe");
            naytaVirhe(virhe);
        } else {
            Dialogs.setToolTipText(edit,virhe);
            edit.getStyleClass().add("virhe");
            naytaVirhe(virhe);
        }
    }
    
    /**
     * objektin id:n luku
     * @param obj tutkittava objekti
     * @param oletus oletusarvo
     * @return objektin id:n luku
     */
    public static int getFieldId(Object obj, int oletus) {
        if(!(obj instanceof Node)) return oletus;
        Node node = (Node)obj;
        String s = node.getId();
        if (s.length()<1) return oletus;
        return Mjonot.erotaInt(s.substring(1), oletus);
    }
    
    @Override
    public Olut getResult() {
        // TODO Auto-generated method stub
        return olutKohdalla;
    }

    @Override
    public void handleShown() {
        kentta = Math.max(apuolut.ekaKentta(), Math.min(kentta, apuolut.getKenttia()-1));
        edits[kentta].requestFocus();        
    }

    @Override
    public void setDefault(Olut oletus) {
        olutKohdalla = oletus;
        naytaOlut(edits, olutKohdalla);
    }
    
    /**Oluen tiedot TextFieldissä
     * @param edits tekstikentällinen taulukko
     * @param olut näytettävä olut
     */
    public static void naytaOlut(TextField[] edits, Olut olut) {
        if (olut == null) return;
        
        for(int k = olut.ekaKentta(); k<olut.getKenttia(); k++) {
            edits[k].setText(olut.anna(k));
        }
        //edits[0].setText(olut.getNimi());
        //edits[1].setText(olut.getTyyli());
        //edits[2].setText(olut.getArvio());
        //edits[3].setText(olut.getVahvuus());
        //edits[4].setText(olut.getMaltaat());
        //edits[5].setText(olut.getResepti());
        //edits[6].setText(olut.getHuomioita());
    }
    
    /**
     * näytettään oluen tiedot
     * @param olut näytettävä olut
     */
    public void naytaOlut(Olut olut) {
        naytaOlut(edits, olut);
    }
    
    private void setKentta(int kentta) {
        this.kentta = kentta;
    }
    
    
    /**
     * Oluen kyselydialogi
     * @param modalityStage mille modaalisuus
     * @param oletus mitä näytetään oletuksena
     * @param kentta mikä kenttä
     * @return null, jos cancel, muuten täytetyt
     */
    public static Olut kysyOlut(Stage modalityStage, Olut oletus, int kentta) {
        return ModalController.<Olut, OlutLisaysController>showModal(
                OlutLisaysController.class.getResource("OlutLisays.fxml"),
                "Olutlista",
                modalityStage, oletus,
                ctrl -> ctrl.setKentta(kentta) 
            );    
        }

}
