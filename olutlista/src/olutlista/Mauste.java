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
 * @author tonip
 * @version 9.7.2020
 *
 */
public class Mauste {
    private String mauste;
    private int mallasNro;
    private int humalaNro;
    private int olutNro;
    
    private int tunnusNro;
    
    private static int seuraavaNro = 1;

    /**
     * Mausteen alustus
     */
    public Mauste() {
        
    }
    
    /**
     * Tietyn oluen mausteiden alustus
     * @param mallasNro maltaan viitenumero
     * @param humalaNro humalan viitenumero
     * @param olutNro oluen viitenumero
     */
    public Mauste(int mallasNro, int humalaNro, int olutNro) {
        this.mallasNro = mallasNro;
        this.humalaNro = humalaNro;
        this.olutNro = olutNro;
    }
    
    /**
     * @param nro1 viite maltaaseen
     * @param nro2 viite humalaan
     * @param nro3 viite olueen, jonka mausteet
     */
    public void taytaMauste(int nro1, int nro2, int nro3) {
        mallasNro = nro1;
        humalaNro = nro2;
        olutNro = nro3;
    }
    /**
     * Tulostetaan mausteiden tiedot
     * @param out tietovirta, johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(mauste);
    }
    
    /**
     * Tulostetaan oluen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    /**
     * antaa mausteelle uuden tunnusnumeron
     * @return mausteen tunnusnro
     * @example
     * <pre name="test">
     * Mauste bisse = new Mauste();
     * bisse.getTunnusNro() === 0;
     * bisse.rekisteroi();
     * Mauste bisse2 = new Mauste();
     * bisse2.rekisteroi();
     * int n1 = bisse.getTunnusNro();
     * int n2 = bisse2.getTunnusNro();
     * n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    
    /**
     * palauttaa mausteen id:n
     * @return  mausteen id
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
     * @return palauttaa maltaan id:n
     */
    public int getMallasNro() {
        return mallasNro;
    }
    
    /**
     * @return palauttaa humalan id:n
     */
    public int getHumalaNro() {
        return humalaNro;
    }
    
    /**
     * Testiohjelma mausteille
     * @param args ei käytössä
     */
    public static void main (String[] args) {
        Mauste mau = new Mauste();
        mau.taytaMauste(1,1,1);
        mau.tulosta(System.out);
    }

}
