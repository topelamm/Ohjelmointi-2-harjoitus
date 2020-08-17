package olutlista;

import java.util.Collection;
import java.util.List;

import kayttamattomat.Mallas;
import kayttamattomat.Maltaat;
import kayttamattomat.Mauste;
import kayttamattomat.Mausteet;

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
    private       Oluet oluet = new Oluet();
    private       Humalat humalat = new Humalat();
    private final Maltaat maltaat = new Maltaat();
    private       Mausteet mausteet = new Mausteet();

    /**
     * palauttaa oluiden määrän
     * @return olutmäärä
     */
    public int getOluet(){
       return oluet.getLkm();
   }
    
    /**
     * Poistaa oluiden ja humalien tiedot olutlistalta
     * @param olut poistettava olut
     * @return poistettujen määrän
     */
    public int poista(Olut olut) {
        if ( olut == null ) return 0;
        int ret = oluet.poista(olut.getTunnusNro()); 
        humalat.poistaHumalat(olut.getTunnusNro()); 
        return ret; 
    }
    
    /**
     * Poistaa humalan
     * @param humala poistettava humala
     */
    public void poistaHumala(Humala humala) { 
        humalat.poista(humala); 
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
     * @throws SailoException ongelmissa
     */
    public void lisaa(Humala hum) throws SailoException { 
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
     * Korvaa oluen tai jos ei ole olemassa lisää uuden
     * @param olut lisättävä olut
     * @throws SailoException ongelmissa
     */
    public void korvaaTaiLisaa(Olut olut) throws SailoException{
        oluet.korvaaTaiLisaa(olut);
    }
    
    /**
     * Korvaa humalan tai jos ei ole olemassa lisää uuden
     * @param humala lisättävä humala
     * @throws SailoException ongelmissa
     */
    public void korvaaTaiLisaa(Humala humala) throws SailoException{
        humalat.korvaaTaiLisaa(humala);
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
     * Palauttaa oluiden viiteet
     * @param hakuehto hakuehto
     * @param k etsittävän indeksi
     * @return löytyneet oluet
     * @throws SailoException vikatilanteessa
     */
    public Collection<Olut> etsi(String hakuehto, int k) throws SailoException{
        return oluet.etsi(hakuehto, k);
    }
    
    /**
     * Haetaan oluen maltaat
     * @param olut olut, jolle maltaat haetaan
     * @return tietorakenne, jossa viitteet
     * @example
 
     */
    public List<Mallas> annaMaltaat(Olut olut){
        return maltaat.annaMaltaat(olut.getTunnusNro());
    }
    
    /**
     * Haetaan oluen humlat
     * @param olut olut, jolle humalat haetaan
     * @return tietorakenne, jossa viitteet
     * @example

     */
    //public Humala annaHumalat(Olut olut){
    //    return humalat.annaHumalat(olut.getTunnusNro());
   // }

    public List<Humala> annaHumalat(Olut olut) {
        return humalat.annaHumalat(olut.getTunnusNro());
    }

    
    /**
     * @param i humalaNro
     * @return alkiot
     */
    //public Humala annaHumalat(int i) {
    //    return humalat.annaHumalat(i);
    //}

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
        oluet = new Oluet();
        humalat = new Humalat();
       
        
        setTiedosto(nimi);
        oluet.lueTiedostosta();
        humalat.lueTiedostosta();
        
    }
    
    /**
     * @throws SailoException poikkeus
     */
    public void lueOluetTiedostosta() throws SailoException{
        oluet.lueTiedostosta();
    }
    
    /**
     * @throws SailoException poikkeus
     */
    public void lueHumalatTiedostosta() throws SailoException{
        humalat.lueTiedostosta();
    }
    
    
    /**
     * nimien asetus
     * @param nimi nimi
     */
    public void setTiedosto(String nimi) {
       
        String hakemistonNimi = "";
        if( !nimi.isEmpty() ) hakemistonNimi = nimi + "_";
        oluet.setTiedostonPerusNimi(hakemistonNimi + "nimet");
        humalat.setTiedostonPerusNimi(hakemistonNimi + "humalat");
        
    }
    
    /**
     * tallentaa olutlistan tiedostot tiedostoon
     * @throws SailoException poikkeus tallennuksen ongelmissa
     */
    public void tallenna() throws SailoException{
        String virhe = "";
        try {
            oluet.talleta();
        } catch (SailoException ex) {
            virhe = ex.getMessage();
        }

        try {
            humalat.tallenna();
        }catch (SailoException ex) {
            virhe += ex.getMessage();
        }
         try {                           
             mausteet.tallenna();         
        }catch (SailoException ex) {    
             virhe += ex.getMessage();
        }
        if(!"".equals(virhe)) throw new SailoException(virhe);
    }
        
    /**  }                               
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
            
            Mauste bisse = new Mauste(id1,id1); bisse.taytaMauste( id1, id1); olutlista.lisaa(bisse);
            
            System.out.println("======================= Olutlista testi ============================");
            
            for (int i = 0; i<olutlista.getOluet(); i++) {
                Olut olut = olutlista.annaOlut(i);
                System.out.println("Olut paikassa: " + i);
                olut.tulosta(System.out);
                List<Mallas> loytyneet = olutlista.annaMaltaat(olut);
                for (Mallas mallas : loytyneet)
                    mallas.tulosta(System.out);
                
             //   List<Humala> loytyneet2 = olutlista.annaHumalat(olut);
             //   for (Humala humala : loytyneet2)
             //       humala.tulosta(System.out);
             //       System.out.println(" ");
                    
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
