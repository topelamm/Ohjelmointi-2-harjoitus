package olutlista;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * osaa lisätä ja poistaa maltaan
 *
 * lukee ja kirjoittaa maltaat tiedostoon |
 *
 * osaa etsiä ja lajitella
 * @author tonip
 * @version 9.7.2020
 *
 */
public class Maltaat implements Iterable<Mallas> {
    
    private String tiedostonNimi = "";
    
    private final Collection<Mallas> alkiot = new ArrayList<Mallas>();
    
    /**
     * Alustus
     */
    public Maltaat() {
        
    }
    
    /**
     * Lisää uuden maltaan tietorakenteesen
     * @param mal lisättävä mallas
     */
    public void lisaa(Mallas mal) {
        alkiot.add(mal);
    }
    
    /**
     * Maltaiden lukeminen tiedostosta
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException poikkeus lukemisen epäonnistuessa
     */
    public void lueTiedostosta(String hakemisto) throws SailoException{
        tiedostonNimi = hakemisto + ".mal";
        throw new SailoException("Ei osata vielä lukea tiedostoa" + tiedostonNimi);
    }
    
    /**
     * Tallentaa maltaat tiedostoon
     * @throws SailoException tallennuksen epäonnistuessa
     */
    public void tallenna() throws SailoException {
        throw new SailoException ("Ei osata vielä tallentaa tiedostoa" + tiedostonNimi);
    }
    
    /**
     * Palauttaa maltaiden lukumäärän
     * @return maltaiden määrän
     */
    public int getLkm() {
        return alkiot.size();
    }
    
    
    /**
     * Iteraattori kaikkien maltaiden läpikäymiseen
     * @return mallasiteraattori
     * 
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     *  Maltaat maltaat = new Maltaat();
     *   
     *  Mallas ohra = new Mallas(2); maltaat.lisaa(ohra);
     *  Mallas ohra2 = new Mallas(1); maltaat.lisaa(ohra2);
     *  Mallas ohra3 = new Mallas(2); maltaat.lisaa(ohra3);
     *  Mallas ohra4 = new Mallas(1); maltaat.lisaa(ohra4);
     *  Mallas ohra5 = new Mallas(2); maltaat.lisaa(ohra5);
     * 
     *  Iterator<Mallas> i2=maltaat.iterator();
     *  i2.next() === ohra;
     *  i2.next() === ohra2;
     *  i2.next() === ohra3;
     *  i2.next() === ohra4;
     *  i2.next() === ohra5;
     *  i2.next() === ohra4;  #THROWS NoSuchElementException  
     *  
     *  int n = 0;
     *  int jnrot[] = {2,1,2,1,2};
     *  
     *  for ( Mallas mal:maltaat ) { 
     *    mal.getOlutNro() === jnrot[n]; n++;  
     *  }
     *  
     *  n === 5;
     *  
     * </pre>
     */
    @Override
    public Iterator<Mallas> iterator(){
        return alkiot.iterator();
    }
    
    /**
     * Haetaan kaikki oluen maltaat
     * @param tunnusnro oluen numero jolle maltaat haetaan
     * @return viiteet löydettyihin maltaisiin
     * @example
     * <pre name="test">
     * 
     * 
     * Maltaat maltaat = new Maltaat();
     *  Mallas ohra = new Mallas(2); maltaat.lisaa(ohra);
     *  Mallas ohra2 = new Mallas(1); maltaat.lisaa(ohra2);
     *  Mallas ohra3 = new Mallas(2); maltaat.lisaa(ohra3);
     *  Mallas ohra4 = new Mallas(1); maltaat.lisaa(ohra4);
     *  Mallas ohra5 = new Mallas(2); maltaat.lisaa(ohra5);
     *  
     *  List<Mallas> loytyneet;
     *  loytyneet = maltaat.annaMaltaat(3);
     *  loytyneet.size() === 0;
     *  loytyneet = maltaat.annaMaltaat(1);
     *  loytyneet.size() === 2;
     *  loytyneet.get(0) == ohra2 === true;
     *  loytyneet.get(1) == ohra4 === true;
     *  
     * </pre>
     */
    public List<Mallas> annaMaltaat (int tunnusnro){
        List<Mallas> loydetyt = new ArrayList<Mallas>();
        for (Mallas mal : alkiot)
            if (mal.getOlutNro()==tunnusnro) loydetyt.add(mal);
        return loydetyt;
    }
    
    /**
     * testiohjelma
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Maltaat maltaat = new Maltaat();
        Mallas ohra = new Mallas();
        ohra.taytaMallas(2);
        Mallas ohra2 = new Mallas();
        ohra2.taytaMallas(1);
        Mallas ohra3 = new Mallas();
        ohra3.taytaMallas(2);
        Mallas ohra4 = new Mallas();
        ohra4.taytaMallas(2);
        
        maltaat.lisaa(ohra);
        maltaat.lisaa(ohra2);
        maltaat.lisaa(ohra3);
        maltaat.lisaa(ohra2);
        maltaat.lisaa(ohra4);
        
        System.out.println("========= Maltaat testi ================");
        
        List<Mallas> maltaat2 = maltaat.annaMaltaat(2);
        
        for(Mallas mal : maltaat2) {
            System.out.print(mal.getTunnusNro()  +" ");
            mal.tulosta(System.out);
        }
        
    }
    
}
