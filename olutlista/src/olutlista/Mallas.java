package olutlista;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 *  tietää maltaan kentät (nimi)
 *
 *  osaa tarkistaa tietyn kentän oikeellisuuden (syntaksin)
 *
 *  osaa muuttaa |ohramallas|- merkkijonon oluen tiedoiksi
 *
 *  osaa antaa merkkijonona i:n kentän tiedot
 *
 *  osaa laittaa merkkijonon i:neksi kentäksi
 *  
 * @author tonip
 * @version 8.7.2020
 *
 */
public class Mallas {
    private String  mallas;
    private int     olutNro;
    private int     tunnusNro;
    
    private static int seuraavaNro = 1;
    
    /**
     * Maltaan alustus
     */
    public Mallas() {
        
    }
    
    /**
     * Tietyn oluen maltaan alustus
     * @param tunnusNro oluen viitenumero
     */
    public Mallas(int tunnusNro) {
        this.olutNro = tunnusNro;
    }

    /**
     * Apumetodi testiarvoille
     * @param nro viite olueen, jonka mallas kyseessä
     */
    public void taytaMallas(int nro) {
        olutNro= nro;
        mallas = "ohramallas";
    }
    
    /**
     * Tulostetaan maltaan tiedot
     * @param out tietovirta, johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(mallas);
    }
    
    /**
     * Tulostetaan oluen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    /**
     * antaa maltaalle uuden tunnusnumeron
     * @return maltaan tunnusnro
     * @example
     * <pre name="test">
     * Mallas ohra = new Mallas();
     * ohra.getOlutNro() === 0;
     * ohra.rekisteroi();
     * Mallas ohra2 = new Mallas();
     * ohra2.rekisteroi();
     * int n1 = ohra.getTunnusNro();
     * int n2 = ohra2.getTunnusNro();
     * n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    
    /**
     * palauttaa maltaan id:n
     * @return  maltaan id
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
     * Testiohjelma maltaille
     * @param args ei käytössä
     */
    public static void main (String[] args) {
        Mallas ohra = new Mallas();
        ohra.taytaMallas(1);
        ohra.tulosta(System.out);
    }

}
