package olutlista;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 *  tietää humalan kentät (nimi)
 *
 *  osaa tarkistaa tietyn kentän oikeellisuuden (syntaksin)
 *
 *  osaa muuttaa |saaz|- merkkijonon oluen tiedoiksi
 *
 *  osaa antaa merkkijonona i:n kentän tiedot
 *
 *  osaa laittaa merkkijonon i:neksi kentäksi
 *  
 * @author tonip
 * @version 8.7.2020
 *
 */
public class Humala implements Cloneable, Tietue {
    private int     tunnusNro;
    private int     olutNro;
   
    private String  humala;
    private static int seuraavaNro = 1;
    
    /**
     * Maltaan alustus
     */
    public Humala() {
        
    }
    
    /**
     * Tietyn oluen humalan alustus
     * @param olutNro oluen viitenumero
     */
    public Humala(int olutNro) {
        this.olutNro = olutNro;
    }

    /**
     * Apumetodi testiarvoille
     * @param nro viite olueen, jonka humala kyseessä
     */
    public void taytaHumala(int nro) {
        olutNro= nro;
        humala = "saaz";
    }
    
    /**
     * Tulostetaan humalan tiedot
     * @param out tietovirta, johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(tunnusNro + "humala:" + humala );
    }
    
    /**
     * Tulostetaan oluen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    /**
     * antaa humalalle uuden tunnusnumeron
     * @return humalan tunnusnro
     * @example
     * <pre name="test">
     * Humala saaz = new Humala();
     * saaz.getTunnusNro() === 0;
     * saaz.rekisteroi();
     * Humala amarillo = new Humala();
     * amarillo.rekisteroi();
     * int n1 = saaz.getTunnusNro();
     * int n2 = amarillo.getTunnusNro();
     * n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    
    /**
     * palauttaa humalan id:n
     * @return  humalan id
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
    
    /**
     * palauttaa oluen id:n
     * @return oluen id
     */
    public int getOlutNro() {
        return olutNro;
    }
    
    /**
     * Asettaa tunnusnumeron ja samalla varmistaa että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin.
     * @param nr asetettava tunnusnumero
     */
    private void setTunnusNro(int nr) {
        tunnusNro = nr;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;
    }
    
    private void setOlutNro(int nr) {
        olutNro = nr;
    }
    
    /**
     * Jäsenen tiedot merkkijonona tiedostoon tallennettavaksi
     * @example
     * <pre name="test">
     * Olut olut = new Olut();
     * olut.parse(" 2 | Lapin Kulta | Lager");
     * olut.toString().startsWith("2|Lapin Kulta|Lager|") ===true;
     * </pre>
     *
     */
    @Override
    public String toString() {
        return "" +
                getTunnusNro()+ "|"+
                humala;
    }

    /**
     * Selvittää oluen tiedot tolpilla erotelluista merkkijonoista
     * @param rivi mistä oluen tiedot otetaan
     * 
     * @example
     * <pre name="test">
     * Olut olut = new Olut();
     * olut.parse(" 2 | Lapin Kulta | Lager");
     * olut.getTunnusNro() === 2;
     * olut.toString().startsWith("2|Lapin Kulta|Lager|") === true;
     * 
     * olut.rekisteroi();
     * int n = olut.getTunnusNro();
     * olut.parse (""+(n+20));
     * olut.rekisteroi();
     * olut.getTunnusNro()=== n+20+1;
     * 
     * </pre>
     */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        humala = Mjonot.erota(sb, '|', humala);
    }

    @Override
    public boolean equals(Object olut) {
        if ( olut == null) return false;
        return this.toString().contentEquals(olut.toString());
    }

    @Override
    public int hashCode(){
        return tunnusNro;
    }
    
    /**
     * kenttien määrä
     * @return kenttien määrä
     */
    @Override
    public int getKenttia() {
        return 3;
    }

    /**
     * ensimmäinen näytettävä kenttä
     * @return ekan kentän
     */
    @Override
    public int ekaKentta() {
        return 2;
    }

    /**
     * palautetaan kenttää vastaava kysymys
     * @param k monesko kenttä
     * @return kenttää vastaava kysymys
     */
    @Override
    public String getKysymys(int k) {
        switch (k) {
        case 0: return "Tunnus nro";
        case 1: return "Olut nro";
        case 2: return "Humala";
        default: return "Kaljaa!";
        }
    }

    /**
     * sisältö merkkijonona
     * @param k monesko kenttä
     * @return kentän sisältö
     */
    @Override
    public String anna(int k) {
        switch (k) {
        case 0: return "" + tunnusNro;
        case 1: return "" + olutNro;
        case 2: return "" + humala;
        default: return "Kaljaa!";
        }
    }

    /**
     * @param k mitä kenttää katsotaan
     * @param jono millainen merkkijono
     * @return palauttaa k:n kenttää vastaavan merkkijonon
     * @example
         * <pre name="test">
         *   Olut olut = new Olut();
         *   olut.aseta(1,"Lapin Kulta") === null;
         *   olut.aseta(2,"Lager") === null; 
         * </pre>
     */
    @Override
    public String aseta (int k, String jono) {
        String tjono = jono.trim();
        StringBuilder sb = new StringBuilder(tjono);
        switch (k) {
        case 0:
            setTunnusNro(Mjonot.erota(sb, '§', getTunnusNro()));
            return null;
        case 1:
            setOlutNro(Mjonot.erota(sb, '§', getOlutNro()));
            return null;
        case 2:
            try {
                humala = Mjonot.erotaEx(sb, '§', humala);
            } catch ( NumberFormatException ex ) {
                return "Humala ei saa olla tyhjä ";
            }
            return null;
      

        default:
            
            return "Kaljaa!";    
        }
    }
    
    /**
     * Tehdään identtinen klooni huomalasta
     * @return Object kloonattu humala
     * @example
     * 
     * <pre name="test">
         * #THROWS CloneNotSupportedException 
         *   Humala hum = new Humala();
         *   hum.parse("   2   |  1  |   Saaz ");
         *   Object kopio = hum.clone();
         *   kopio.toString() === hum.toString();
         *   hum.parse("   1   |  2  |   Uute ");
         *   kopio.toString().equals(hum.toString()) === false;
         *   
         * </pre>
     */
    @Override
    public Humala clone() throws CloneNotSupportedException { 
        return (Humala)super.clone();
    }
    
    /**
     * Testiohjelma humalille
     * @param args ei käytössä
     */
    public static void main (String[] args) {
        Humala hum = new Humala();
        hum.taytaHumala(1);
        hum.tulosta(System.out);
    }

}
