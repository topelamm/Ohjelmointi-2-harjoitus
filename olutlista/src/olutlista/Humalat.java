package olutlista;

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
    
    private String tiedostonNimi = "";
    
    private final Collection<Humala> alkiot = new ArrayList<Humala>();
    
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
    }
    
    /**
     * Humalien lukeminen tiedostosta
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException poikkeus lukemisen epäonnistuessa
     */
    public void lueTiedostosta(String hakemisto) throws SailoException{
        tiedostonNimi = hakemisto + ".hum";
        throw new SailoException("Ei osata vielä lukea tiedostoa" + tiedostonNimi);
    }
    
    /**
     * Tallentaa humalat tiedostoon
     * @throws SailoException tallennuksen epäonnistuessa
     */
    public void tallenna() throws SailoException {
        throw new SailoException ("Ei osata vielä tallentaa tiedostoa" + tiedostonNimi);
    }
    
    /**
     * Palauttaa humaloiden lukumäärän
     * @return humaloiden määrän
     */
    public int getLkm() {
        return alkiot.size();
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
     *  List<Humala> loytyneet;
     *  loytyneet = humalat.annaHumalat(3);
     *  loytyneet.size() === 0;
     *  loytyneet = humalat.annaHumalat(1);
     *  loytyneet.size() === 2;
     *  loytyneet.get(0) == saaz2 === true;
     *  loytyneet.get(1) == saaz4 === true;
     *  
     * </pre>
     */
    public List<Humala> annaHumalat (int tunnusnro){
        List<Humala> loydetyt = new ArrayList<Humala>();
        for (Humala hum : alkiot)
            if (hum.getOlutNro() == tunnusnro) loydetyt.add(hum);
        return loydetyt;
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
        
        List<Humala> humalat2 = humalat.annaHumalat(2);
        
        for(Humala hum : humalat2) {
            System.out.print(hum.getTunnusNro()  +" ");
            hum.tulosta(System.out);
            System.out.println(" ");
        }
        
    }
    
}
