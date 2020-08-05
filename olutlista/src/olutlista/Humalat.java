package olutlista;

//import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
//import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;


/**
 * osaa lisätä ja poistaa humalan
 *
 * lukee ja kirjoittaa humalat tiedostoon |
 *
 * osaa etsiä ja lajitella
 * @author tonip
 * @version 9.7.2020
 *
 */
public class Humalat implements Iterable<Humala> {
    
    private String           tiedostonNimi      = "";
    private boolean          muutettu           = false;
    private String           tiedostonPerusNimi = "humalat";

    
    private final List<Humala> alkiot = new ArrayList<Humala>();
    
    /**
     * Alustus
     */
    public Humalat() {
        
    }
    
    /**
     * Lisää uuden humalan tietorakenteesen
     * @param hum lisättävä humala
     */
    public void lisaa(Humala hum) {
        alkiot.add(hum);
        muutettu = true;
    }
    
    /**
     * Lukee oluet tiedostosta
     * @throws SailoException epäonnistuessa
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * #import java.io.File;
     * 
     * Oluet oluet = new Oluet();
     * Olut lappari = new Olut();
     * Olut koff = new Olut();
     * lappari.taytaOlut();
     * koff.taytaOlut();
     * String hakemisto = "testioluet";
     * String tiedNimi = hakemisto + "/nimet";
     * File ftied = new File(tiedNimi+ ".dat");
     * ftied.delete();
     * oluet.lisaa(lappari);
     * oluet.lisaa(koff);
     * oluet.talleta();
     * oluet = new Oluet();
     * oluet.lueTiedostosta();
     * Iterator<Olut> i = oluet.iterator();
     * i.next() === lappari;
     * i.next() === koff;
     * i.hasNext() === false;
     * oluet.lisaa(koff);
     * oluet.talleta();
     * 
     * </pre>
     */
    public void lueTiedostosta() throws SailoException{
        tiedostonNimi = getTiedostonNimi();
        try (Scanner fi = new Scanner (new FileInputStream(tiedostonNimi))){
            while (fi.hasNext()) {
                String rivi = fi.nextLine();
                rivi = rivi.trim();
                Humala humala = new Humala();
                humala.parse(rivi);
                lisaa(humala);
            }
            muutettu = false;
        }catch (FileNotFoundException e) {
            throw new SailoException("Tiedosto" + getTiedostonNimi() + "ei aukea");
            
        }
    }
    
    /**
     * Lukeminen aikasemmin annetun nimisestä tiedostosta
     * @throws SailoException jos lukeminen epäonnistuu
     */
   // public void lueTiedostosta() throws SailoException {
   //     lueTiedostosta(getTiedostonPerusNimi());
   // }
    
    /**
     * Tallentaa humalat tiedostoon
     * @throws SailoException tallennuksen epäonnistuessa
     */
    public void tallenna() throws SailoException {
        if (!muutettu) return;
        
        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete();
        ftied.renameTo(fbak);
        
        try ( PrintStream fo = new PrintStream(new FileOutputStream(ftied.getCanonicalPath())) ){
            //fo.println(getTiedostonNimi());
            //fo.println(alkiot.size());
            for (Humala humala: this) {
                fo.println(humala.toString());
            }
        } catch (FileNotFoundException ex) {
            throw new SailoException("Tiedosto" + ftied.getName() + "ei aukea");
        } catch(IOException ex) {
            throw new SailoException("Tiedosto" + ftied.getName() + "kirjoittamisessa onkelmia");
        }
        
        muutettu = false;
    }
    
    /**
     * Palauttaa humaloiden lukumäärän
     * @return humaloiden määrän
     */
    public int getLkm() {
        return alkiot.size();
    }
    
    /**
     * tiedoston nimi tallennusta varten
     * @return tallennustiedoston nimi
     */
    public String getTiedostonPerusNimi() {
        return tiedostonPerusNimi;
    }
    
    /**
     * Perusnimen asetus
     * @param nimi oletusnimi
     */
    public void setTiedostonPerusNimi(String nimi) {
        tiedostonPerusNimi = nimi;
    }
    
    /**
     * tallennukseen käytettävä tiedoston nimi
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return getTiedostonPerusNimi() +".dat";
    }
    
    /**
     * varakopiotiedoston nimi
     * @return varakopiotiedoston nimen
     */
    public String getBakNimi(){
        return tiedostonPerusNimi + ".bak";
    }
    
    /**
     * Iteraattori kaikkien humaloiden läpikäymiseen
     * @return humalaiteraattori
     * 
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     *  Humalat humalat = new Humalat();
     *   
     *  Humala saaz = new Humala(2); humalat.lisaa(saaz);
     *  Humala saaz2 = new Humala(1); humalat.lisaa(saaz2);
     *  Humala saaz3 = new Humala(2); humalat.lisaa(saaz3);
     *  Humala saaz4 = new Humala(1); humalat.lisaa(saaz4);
     *  Humala saaz5 = new Humala(2); humalat.lisaa(saaz5);
     * 
     *  Iterator<Humala> i2=humalat.iterator();
     *  i2.next() === saaz;
     *  i2.next() === saaz2;
     *  i2.next() === saaz3;
     *  i2.next() === saaz4;
     *  i2.next() === saaz5;
     *  i2.next() === saaz4;  #THROWS NoSuchElementException  
     *  
     *  int n = 0;
     *  int jnrot[] = {2,1,2,1,2};
     *  
     *  for ( Humala hum:humalat ) { 
     *    hum.getOlutNro() === jnrot[n]; n++;  
     *  }
     *  
     *  n === 5;
     *  
     * </pre>
     */
    @Override
    public Iterator<Humala> iterator(){
        return alkiot.iterator();
    }
    
    /**
     * Haetaan kaikki oluen humalat
     * @param tunnusnro oluen numero jolle humalat haetaan
     * @return viiteet löydettyihin humaliin
     * @example
     * <pre name="test">
    
     * 
     * Humalat humalat = new Humalat();
     *  Humala saaz = new Humala(2); humalat.lisaa(saaz);
     *  Humala saaz2 = new Humala(1); humalat.lisaa(saaz2);
     *  Humala saaz3 = new Humala(2); humalat.lisaa(saaz3);
     *  Humala saaz4 = new Humala(1); humalat.lisaa(saaz4);
     *  Humala saaz5 = new Humala(2); humalat.lisaa(saaz5);
     *  
     *  Humala loytyneet = humalat.annaHumalat(3);
     *  Humala loytyneet2 = humalat.annaHumalat(1);
     *  loytyneet.getTunnusNro() === 0;
     *  loytyneet2.getTunnusNro() === 0;
   
     *  
     * </pre>
     */
   
    
    public List<Humala> annaHumalat(int tunnusnro) {
        List<Humala> loydetyt = new ArrayList<Humala>();
        for (Humala hum : alkiot)
            if ( hum.getTunnusNro() == tunnusnro ) loydetyt.add(hum);
        return loydetyt;
    }
    
    /**
     * @param humala korvattava tai lisättävä humala
     * @throws SailoException ongelmissa
     */
    public void korvaaTaiLisaa(Humala humala) throws SailoException{
        int id = humala.getTunnusNro();
        for (int i = 0; i<getLkm(); i++) {
            if (alkiot.get(i).getTunnusNro() == id) {
                alkiot.set(i, humala);
                muutettu = true;
                return;
            }
        }
        lisaa(humala);
    }
    
    /**
     * Poistaa oluen humalat
     * @param tunnusNro mitkä poistetaan
     * @return montako poistettiin
     */
    public int poistaHumalat(int tunnusNro) {
        int n = 0;
        for (Iterator<Humala> it = alkiot.iterator(); it.hasNext();) {
            Humala hum = it.next();
            if ( hum.getTunnusNro() == tunnusNro ) {
                it.remove();
                n++;
            }
        }
        if (n > 0) muutettu = true;
        return n;
    }
    
    /**
     * Poistetaan yksi humala
     * @param humala poistettava humala
     * @return true poistettavan löytyessä
     */
    public boolean poista(Humala humala) {
        boolean ret = alkiot.remove(humala);
        if (ret) muutettu = true;
        return ret;
    }

    
    /**
     * testiohjelma
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Humalat humalat = new Humalat();
        Humala saaz = new Humala();
        saaz.taytaHumala(2);
        Humala saaz2 = new Humala();
        saaz2.taytaHumala(1);
        Humala saaz3 = new Humala();
        saaz3.taytaHumala(2);
        Humala saaz4 = new Humala();
        saaz4.taytaHumala(2);
        
        humalat.lisaa(saaz);
        humalat.lisaa(saaz2);
        humalat.lisaa(saaz3);
        humalat.lisaa(saaz2);
        humalat.lisaa(saaz4);
        
        System.out.println("========= Humalat testi ================");
        
        //Humala humalat2 = humalat.annaHumalat(2);
        
        //for(Humala hum : humalat2) {
        //    System.out.print(hum.getTunnusNro()  +" ");
        //    hum.tulosta(System.out);
        //    System.out.println(" ");
        //}
        
    }
    
}
