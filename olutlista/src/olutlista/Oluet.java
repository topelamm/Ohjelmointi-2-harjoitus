package olutlista;

import java.io.BufferedReader;
//import java.io.BufferedReader;
import java.io.File;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
//import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * osaa lisätä ja poistaa oluen
 * lukee ja kirjoittaa oluet tiedostoon |
 * osaa etsiä ja lajitella
 * 
 * @author tonip
 * @version 8.7.2020
 *
 */
public class Oluet implements Iterable<Olut> {
    private static final int MAX_JASENIA        = 8;
    private int              lkm                = 0;
    private String           tiedostonNimi      = "";
    private Olut             alkiot[]           = new Olut[MAX_JASENIA];
    private String           tiedostonPerusNimi = "nimet";
    private boolean          muutettu           = false;



    
    /**
     * Muodostaja
     */
    public Oluet() {
        
    }
    
    /**
     * Lisää uuden jäsenen tietorakenteeseen.  Ottaa jäsenen omistukseensa.
     * @param olut viite olueen
     * @throws SailoException jos tietorakenne on jo täynnä
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * Oluet oluet = new Oluet();
     * Olut lappari = new Olut();
     * Olut koff = new Olut();
     * oluet.getLkm() === 0;
     * oluet.lisaa(lappari); oluet.getLkm() === 1;
     * oluet.lisaa(koff); oluet.getLkm() === 2;
     * oluet.lisaa(lappari); oluet.getLkm() === 3;
     * oluet.anna(0) === lappari;
     * oluet.anna(1) === koff;
     * oluet.anna(2) === lappari;
     * oluet.anna(1) == lappari === false;
     * oluet.anna(1) == koff === true;
     * oluet.anna(3) === lappari; #THROWS IndexOutOfBoundsException 
     * oluet.lisaa(lappari); oluet.getLkm() === 4;
     * oluet.lisaa(lappari); oluet.getLkm() === 5;
     * oluet.lisaa(lappari); oluet.getLkm() === 6;
     * oluet.lisaa(lappari); oluet.getLkm() === 7;
     * oluet.lisaa(lappari); oluet.getLkm() === 8;
     
     * </pre>
     */
    public void lisaa(Olut olut) throws SailoException {
        if (lkm >= alkiot.length) alkiot = Arrays.copyOf(alkiot, lkm+10);
        alkiot[lkm] = olut;
        lkm++;
        muutettu = true;
    }
 
    /**
     * Palauttaa viitteen i:teen jäseneen.
     * @param i monennenko jäsenen viite halutaan
     * @return viite jäseneen, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella  
     */
    public Olut anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }
    
    /**
     * Lukee oluet tiedostosta
     * @param nimi tiedoston perusnimi
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
     * File dir = new File(hakemisto);
     * dir.mkdir();
     * ftied.delete();
     * oluet.lueTiedostosta(tiedNimi); #THROWS SailoException
     * oluet.lisaa(lappari);
     * oluet.lisaa(koff);
     * oluet.talleta();
     * oluet = new Oluet();
     * oluet.lueTiedostosta(tiedNimi);
     * Iterator<Olut> i = oluet.iterator();
     * i.next() === lappari;
     * i.next() === koff;
     * i.hasNext() === false;
     * oluet.lisaa(koff);
     * oluet.talleta();
     * ftied.delete() === true;
     * File fbak = new File(tiedNimi +".bak");
     * fbak.delete() === true;
     * dir.delete() === true;
     * 
     * </pre>
     */
    public void lueTiedostosta(String nimi) throws SailoException{
        setTiedostonPerusNimi(nimi);
        //try ( PrintStream fi = new PrintStream (new FileOutputStream (getTiedostonNimi()))){
        //    tiedostonNimi = fi.;
        //    if (tiedostonNimi == null) throw new SailoException ("Listan nimi puuttuu");
        //    String rivi = fi.toString();
        //    if(rivi == null) throw new SailoException ("Maksimikoko puuttu");
            
        //    while((rivi = fi.toString()) != null) {
        //        rivi = rivi.trim();
        //        if ("".contentEquals(rivi)|| rivi.charAt(0) == ';') continue;
        //        Olut olut = new Olut();
        //        olut.parse(rivi);
        //        lisaa(olut);
        try ( BufferedReader fi = new BufferedReader (new FileReader (getTiedostonNimi()))){
                //tiedostonNimi = fi.readLine();
                if (tiedostonNimi == null) throw new SailoException ("Listan nimi puuttuu");
                //String rivi = fi.nextLine();
                //if(rivi == null) throw new SailoException ("Maksimikoko puuttu");
                
                while((tiedostonNimi = fi.readLine())  != null) {
                    tiedostonNimi = tiedostonNimi.trim();
                    if ("".contentEquals(tiedostonNimi)|| tiedostonNimi.charAt(0) == ';') continue;
                    Olut olut = new Olut();
                    olut.parse(tiedostonNimi);
                    lisaa(olut);
            }
            muutettu = false;
        } catch (FileNotFoundException e) {
            throw new SailoException ( "Tiedosto" + " " + getTiedostonNimi() + " " + "ei aukea");
        } catch (IOException e) {
           throw new SailoException ("Ongelmia tiedoston kanssa:" + e.getMessage());
        }
    }
    
    /**
     * Lukeminen aikasemmin annetun nimisestä tiedostosta
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta() throws SailoException {
        lueTiedostosta(getTiedostonPerusNimi());
    }
    
    

    
    /**
     * Tallentaa oluet tiedostoon.  
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta() throws SailoException {
        if (!muutettu) return;
        
        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete();
        ftied.renameTo(fbak);
        
        try ( PrintStream fo = new PrintStream(new FileOutputStream(ftied.getCanonicalPath())) ){
            //fo.println(getTiedostonKokoNimi());
            //fo.println(alkiot.length);
            for (Olut olut: this) {
                fo.println(olut.toString());
            }
        } catch (FileNotFoundException ex) {
            throw new SailoException("Tiedosto" + " " + ftied.getName() + " " + "ei aukea");
        } catch(IOException ex) {
            throw new SailoException("Tiedosto" + ftied.getName() + "kirjoittamisessa onkelmia");
        }
        
        muutettu = false;
   }

    /**
     * Palauttaa oluiden lukumäärän
     * @return oluiden lukumäärä
     */
    public int getLkm() {
        return lkm;
    }

    /**
     * Olutlistan koko nimi
     * @return Olutlistan koko nimi merkkijonona 
     */
    public String getTiedostonKokoNimi() {
        return tiedostonNimi;
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
     * Luokka oluiden iteroimiseksi.
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     * Oluet oluet = new Oluet();
     * Olut lappari = new Olut(); 
     * Olut koff = new Olut();
     * lappari.rekisteroi(); 
     * koff.rekisteroi();
     *
     * oluet.lisaa(lappari); 
     * oluet.lisaa(koff); 
     * oluet.lisaa(lappari); 
     * 
     * StringBuilder ids = new StringBuilder(30);
     * for (Olut olut: oluet)   
     *   ids.append(" "+olut.getTunnusNro());           
     * 
     * String tulos = " " + lappari.getTunnusNro() + " " + koff.getTunnusNro() + " " + lappari.getTunnusNro();
     * 
     * ids.toString() === tulos; 
     * 
     * ids = new StringBuilder(30);
     * for (Iterator<Olut>  i=oluet.iterator(); i.hasNext(); ) { 
     *   Olut olut = i.next();
     *   ids.append(" "+olut.getTunnusNro());           
     * }
     * 
     * ids.toString() === tulos;
     * 
     * Iterator<Olut>  i=oluet.iterator();
     * i.next() == lappari  === true;
     * i.next() == koff  === true;
     * i.next() == lappari  === true;
     * 
     * i.next();  #THROWS NoSuchElementException
     *  
     * </pre>
     */
    public class OluetIterator implements Iterator<Olut> {
        private int kohdalla = 0;


        /**
         * Onko olemassa vielä seuraavaa olutta
         * @see java.util.Iterator#hasNext()
         * @return true jos on vielä jäseniä
         */
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }


        /**
         * Annetaan seuraava olut
         * @return seuraava olut
         * @throws NoSuchElementException jos seuraava alkiota ei enää ole
         * @see java.util.Iterator#next()
         */
        @Override
        public Olut next() throws NoSuchElementException {
            if ( !hasNext() ) throw new NoSuchElementException("Ei ole");
            return anna(kohdalla++);
        }


        /**
         * Tuhoamista ei ole toteutettu
         * @throws UnsupportedOperationException aina
         * @see java.util.Iterator#remove()
         */
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Eipäs peitellä juomista!");
        }
    }


    /**
     * Palautetaan iteraattori oluista.
     * @return olut iteraattori
     */
    
    @Override
    public Iterator<Olut> iterator() {
        return new OluetIterator();
    }


    /** 
     * Palauttaa "taulukossa" hakuehtoon vastaavien oluiden viitteet 
     * @param hakuehto hakuehto 
     * @param k etsittävän kentän indeksi  
     * @return tietorakenteen löytyneistä oluista 
     * @example 
     * <pre name="test"> 
     * #THROWS SailoException  
     *   Oluet oluet = new Oluet(); 
     *   Olut olut1 = new Olut(); olut1.parse("1|Lapin Kulta|Lager|ohramallas|"); 
     *   Olut olut2 = new Olut(); olut2.parse("2|Koff||ohra|"); 
     *   Olut olut3 = new Olut(); olut3.parse("3|Karhu|Lager||saaz|4,5"); 
     *   Olut olut4 = new Olut(); olut4.parse("4|Karjala|Lager|ohra"); 
     *   Olut olut5 = new Olut(); olut5.parse("5|Kukko|Lager|ohra"); 
     *   oluet.lisaa(olut1); oluet.lisaa(olut2); oluet.lisaa(olut3); oluet.lisaa(olut4); oluet.lisaa(olut5);
     *   // TODO: toistaiseksi palauttaa kaikki oluet 
     * </pre> 
     */ 
    @SuppressWarnings("unused")
    public Collection<Olut> etsi(String hakuehto, int k) { 
        Collection<Olut> loytyneet = new ArrayList<Olut>(); 
        for (Olut olut : this) { 
            loytyneet.add(olut);  
        } 
        return loytyneet; 
    }
    
    
    /**
     * Testiohjelma oluille
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Oluet oluet = new Oluet();

        Olut lappari = new Olut(); 
        Olut koff = new Olut();
        lappari.rekisteroi();
        lappari.taytaOlut();
        koff.rekisteroi();
        koff.taytaOlut();

        try {
            oluet.lisaa(lappari);
            oluet.lisaa(koff);

            System.out.println("============= Oluet testi =================");

            for (int i = 0; i < oluet.getLkm(); i++) {
                Olut olut = oluet.anna(i);
                System.out.println("Olut nro: " + (i));
                olut.tulosta(System.out);
            }

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    }


}
