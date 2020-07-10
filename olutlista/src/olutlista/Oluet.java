package olutlista;

/**
 * osaa lisätä ja poistaa oluen
 * lukee ja kirjoittaa oluet tiedostoon |
 * osaa etsiä ja lajitella
 * 
 * @author tonip
 * @version 8.7.2020
 *
 */
public class Oluet {
    private static final int MAX_JASENIA   = 8;
    private int              lkm           = 0;
    private String           tiedostonNimi = "";
    private Olut             alkiot[]      = new Olut[MAX_JASENIA];
    
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
     * oluet.lisaa(lappari);  #THROWS SailoException
     * </pre>
     */
    public void lisaa(Olut olut) throws SailoException {
        if (lkm >= alkiot.length) throw new SailoException("Liikaa alkioita");
        alkiot[lkm] = olut;
        lkm++;
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
     * Keskeneräinen. Pitäisi lukean oluet tiedostosta
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        tiedostonNimi = hakemisto + "/nimet.dat";
        throw new SailoException("Ei osata vielä lukea tiedostoa " + tiedostonNimi);
    }

    /**
     * Tallentaa oluet tiedostoon.  Kesken.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta() throws SailoException {
        throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedostonNimi);
    }

    /**
     * Palauttaa oluiden lukumäärän
     * @return oluiden lukumäärä
     */
    public int getLkm() {
        return lkm;
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
