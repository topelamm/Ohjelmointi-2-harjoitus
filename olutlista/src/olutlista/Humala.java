package olutlista;

import java.io.OutputStream;
import java.io.PrintStream;

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
public class Humala {
    private String  humala;
    private int     olutNro;
    private int     tunnusNro;
    
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
        out.println(humala);
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
     * Testiohjelma humalille
     * @param args ei käytössä
     */
    public static void main (String[] args) {
        Humala hum = new Humala();
        hum.taytaHumala(1);
        hum.tulosta(System.out);
    }

}
