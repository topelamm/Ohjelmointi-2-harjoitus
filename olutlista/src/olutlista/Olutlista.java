package olutlista;

import java.util.List;

/**
 * Huolehtii oluet ja olut luokkien yhteistyöstä(sekä vast. humala humalat ja mallas/maltaat
 *
 * lukee ja kirjoittaa olutlista tiedostoon pyytämällä avustajiltaan
 * @author tonip
 * @version 9.7.2020
 * 
 *
 */

public class Olutlista {
    private final Oluet oluet = new Oluet();
    private final Humalat humalat = new Humalat();
    private final Maltaat maltaat = new Maltaat();
    private final Mausteet mausteet = new Mausteet();

    /**
     * palauttaa oluiden määrän
     * @return olutmäärä
     */
    public int getOluet(){
       return oluet.getLkm();
   }
    
    /**
     * Poistaa oluista, maltaista ja humalista tietyn numeroisen
     * @param nro viitenumero poistettavaan
     * @return poistettujen määrän
     */
    public int poista(@SuppressWarnings("unused") int nro) {
        return 0;
    }
    
    /**
     * Lisää olutlistaan uuden oluen
     * @param olut lisättävä olut
     * @throws SailoException, jos ei voi tehdä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Olutlista olutlista = new Olutlista();
     * Olut lappari = new Olut();
     * Olut koff = new Olut();
     * lappari.rekisteroi();
     * koff.rekisteroi();
     * olutlista.getOluet() === 0;
     * olutlista.lisaa(lappari); olutlista.getOluet()=== 1;
     * olutlista.lisaa(koff); olutlista.getOluet() === 2;
     * olutlista.lisaa(lappari); olutlista.getOluet()=== 3;
     * olutlista.getOluet() === 3;
     * olutlista.annaOlut(0) === lappari;
     * olutlista.annaOlut(1) === koff;
     * olutlista.annaOlut(2) === lappari;
     * olutlista.annaOlut(3) === lappari; #THROWS IndexOutOfBoundsException
     * olutlista.lisaa(lappari); olutlista.getOluet() === 4;
     * olutlista.lisaa(lappari); olutlista.getOluet() === 5;
     * olutlista.lisaa(lappari); olutlista.getOluet() === 6;
     * olutlista.lisaa(lappari); olutlista.getOluet() === 7;
     * olutlista.lisaa(lappari); olutlista.getOluet() === 8;
     * olutlista.lisaa(lappari); #THROWS SailoException
     * 
     * </pre>
     */
    public void lisaa(Olut olut) throws SailoException{
        oluet.lisaa(olut);
    }
    
    /**
     * lisätään uusi mallas olutlistaan
     * @param mal lisättävä mallas
     */
    public void lisaa(Mallas mal) {
        maltaat.lisaa(mal);
    }
    
    /**
     * lisätään uusi humala olutlistaan
     * @param hum lisättävä humala
     */
    public void lisaa(Humala hum) {
        humalat.lisaa(hum);
    }
    
    /**
     * lisätään uudet mausteet olutlistaan
     * @param mau lisättävät mausteet
     */
    public void lisaa(Mauste mau) {
        mausteet.lisaa(mau);
    }
    
    /**
     * palauttaa i:n jäsenen
     * @param i monesko olut palautetaan
     * @return viite i:teen jäseneen
     * @throws IndexOutOfBoundsException jos i väärin
     */
    public Olut annaOlut(int i) throws IndexOutOfBoundsException {
        return oluet.anna(i);
    }
    
    /**
     * Haetaan oluen maltaat
     * @param olut olut, jolle maltaat haetaan
     * @return tietorakenne, jossa viitteet
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     * Olutlista olutlista = new Olutlista();
     * Olut lappari = new Olut();
     * Olut koff = new Olut();
     * Olut karhu = new Olut();
     * lappari.rekisteroi();
     * koff.rekisteroi();
     * karhu.rekisteroi();
     * int id1= lappari.getTunnusNro();
     * int id2 = koff.getTunnusNro();
     * Mallas ohra11 = new Mallas(id1); olutlista.lisaa(ohra11);
     * Mallas ohra12 = new Mallas(id1); olutlista.lisaa(ohra12);
     * Mallas ohra21 = new Mallas(id2); olutlista.lisaa(ohra21);
     * Mallas ohra22 = new Mallas(id2); olutlista.lisaa(ohra22);
     * Mallas ohra23 = new Mallas(id2); olutlista.lisaa(ohra23);
     * 
     * List<Mallas>loytyneet;
     * loytyneet = olutlista.annaMaltaat(karhu);
     * loytyneet.size() === 0;
     * loytyneet = olutlista.annaMaltaat(lappari);
     * loytyneet.size() === 2;
     * loytyneet.get(0) == ohra11 === true;
     * loytyneet.get(1) == ohra12 === true;
     * loytyneet = olutlista.annaMaltaat(koff);
     * loytyneet.size() === 3;
     * loytyneet.get(0) == ohra21 === true;
     * </pre>
     */
    public List<Mallas> annaMaltaat(Olut olut){
        return maltaat.annaMaltaat(olut.getTunnusNro());
    }
    
    /**
     * Haetaan oluen humlat
     * @param olut olut, jolle humalat haetaan
     * @return tietorakenne, jossa viitteet
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     * Olutlista olutlista = new Olutlista();
     * Olut lappari = new Olut();
     * Olut koff = new Olut();
     * Olut karhu = new Olut();
     * lappari.rekisteroi();
     * koff.rekisteroi();
     * karhu.rekisteroi();
     * int id1= lappari.getTunnusNro();
     * int id2 = koff.getTunnusNro();
     * Humala saaz11 = new Humala(id1); olutlista.lisaa(saaz11);
     * Humala saaz12 = new Humala(id1); olutlista.lisaa(saaz12);
     * Humala saaz21 = new Humala(id2); olutlista.lisaa(saaz21);
     * Humala saaz22 = new Humala(id2); olutlista.lisaa(saaz22);
     * Humala saaz23 = new Humala(id2); olutlista.lisaa(saaz23);
     * 
     * List<Humala>loytyneet;
     * loytyneet = olutlista.annaHumalat(karhu);
     * loytyneet.size() === 0;
     * loytyneet = olutlista.annaHumalat(lappari);
     * loytyneet.size() === 2;
     * loytyneet.get(0) == saaz11 === true;
     * loytyneet.get(1) == saaz12 === true;
     * loytyneet = olutlista.annaHumalat(koff);
     * loytyneet.size() === 3;
     * loytyneet.get(0) == saaz21 === true;
     * </pre>
     */
    public List<Humala> annaHumalat(Olut olut){
        return humalat.annaHumalat(olut.getTunnusNro());
    }
    
    
    /**
     * @param olut oluen mausteet
     * @return tietorakenne, jossa viitteet
     */
    public List<Mauste> annaMausteet(Olut olut){
        return mausteet.annaMausteet(olut.getTunnusNro());
    }
    
    
    /**
     * Lukee olutlistan tiedot tiedostosta
     * @param nimi hakemiseen käytettävä nimi
     * @throws SailoException poikkeus epäonnistuneessa lukemisessa
     */
    public void lueTiedostosta(String nimi) throws SailoException{
        oluet.lueTiedostosta(nimi);
        maltaat.lueTiedostosta(nimi);
        humalat.lueTiedostosta(nimi);
    }
    
    /**
     * tallentaa olutlistan tiedostot tiedostoon
     * @throws SailoException poikkeus tallennuksen ongelmissa
     */
    public void tallenna() throws SailoException{
        oluet.talleta();
        maltaat.tallenna();
        humalat.tallenna();
    }
    
    /**
     * testiohjelma
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Olutlista olutlista = new Olutlista();
        
        try {
            
            Olut lappari = new Olut();
            Olut koff = new Olut();
            
            lappari.rekisteroi();
            lappari.taytaOlut();
            koff.rekisteroi();
            koff.taytaOlut();
            
            olutlista.lisaa(lappari);
            olutlista.lisaa(koff);
            int id1 = lappari.getTunnusNro();
            int id2 = koff.getTunnusNro();
            
            Mallas ohra11 = new Mallas(id1); ohra11.taytaMallas(id1); olutlista.lisaa(ohra11);
            Mallas ohra12 = new Mallas(id1); ohra12.taytaMallas(id1); olutlista.lisaa(ohra12);
            Mallas ohra21 = new Mallas(id2); ohra21.taytaMallas(id2); olutlista.lisaa(ohra21);
            Mallas ohra22 = new Mallas(id2); ohra22.taytaMallas(id2); olutlista.lisaa(ohra22);
            Mallas ohra23 = new Mallas(id2); ohra23.taytaMallas(id2); olutlista.lisaa(ohra23);
            
            Humala saaz11 = new Humala(id1); saaz11.taytaHumala(id1); olutlista.lisaa(saaz11);
            Humala saaz12 = new Humala(id1); saaz12.taytaHumala(id1); olutlista.lisaa(saaz12);
            Humala saaz21 = new Humala(id2); saaz21.taytaHumala(id2); olutlista.lisaa(saaz21);
            Humala saaz22 = new Humala(id2); saaz22.taytaHumala(id2); olutlista.lisaa(saaz22);
            Humala saaz23 = new Humala(id2); saaz23.taytaHumala(id2); olutlista.lisaa(saaz23);
            
            Mauste bisse = new Mauste(id1,id1,id1); bisse.taytaMauste(id1, id1, id1); olutlista.lisaa(bisse);
            
            System.out.println("======================= Olutlista testi ============================");
            
            for (int i = 0; i<olutlista.getOluet(); i++) {
                Olut olut = olutlista.annaOlut(i);
                System.out.println("Olut paikassa: " + i);
                olut.tulosta(System.out);
                List<Mallas> loytyneet = olutlista.annaMaltaat(olut);
                for (Mallas mallas : loytyneet)
                    mallas.tulosta(System.out);
                
                List<Humala> loytyneet2 = olutlista.annaHumalat(olut);
                for (Humala humala : loytyneet2)
                    humala.tulosta(System.out);
                    System.out.println(" ");
                    
                List<Mauste> loytyneet3 = olutlista.annaMausteet(olut);
                for (Mauste mauste : loytyneet3)
                    mauste.tulosta(System.out);
                    System.out.println(" ");              
            }
        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
}