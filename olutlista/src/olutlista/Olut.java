package olutlista;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 *  tietää oluen kentät (nimi,vahvuus tyyli, arvio, jne)
 *
 *  osaa tarkistaa tietyn kentän oikeellisuuden (syntaksin)
 *
 *  osaa muuttaa |Lapin Kulta|- merkkijonon oluen tiedoiksi
 *
 *  osaa antaa merkkijonona i:n kentän tiedot
 *
 *  osaa laittaa merkkijonon i:neksi kentäksi
 * @author tonip
 * @version 8.7.2020
 *
 */
public class Olut {
    private int         tunnusNro;
    private String      nimi            ="";
    private String      tyyli           ="";
    private double      vahvuus         = 0;
    private double      arvosana        = 0;
    private String      resepti         = "";
    private String      huomioita       = "";

    private static int  seuraavaNro    = 1;



/**
 * @return oluen nimi
 * @example
 * <pre name="test">
 * Olut lappari = new Olut();
 * lappari.vastaaLapinKulta();
 * lappari.getNimi() =R= "Lapin Kulta";
 * </pre>
 */
public String getNimi() {
    return nimi;
    }

/**
 * testiarvot oluelle
 */
public void taytaOlut() {
    nimi = "Lapin Kulta";
    tyyli = "Lager";
    vahvuus = 4.5;
    arvosana = 9;
    resepti = "teollista kuraa";
    huomioita = "älä osta enää";
    
}
/**
 * Tulostetaan oluen tiedot
 * @param out tietovirta johon tulostetaan
 */
public void tulosta(PrintStream out) {
    out.println(String.format("%01d", tunnusNro));
    out.println(nimi);
    out.println(tyyli);
    out.println(vahvuus);
    out.println(arvosana);
    out.println(resepti);
    out.println(huomioita);
    out.println(" ");
}

/**
 * Tulostetaan oluen tiedot
 * @param os tietovirta johon tulostetaan
 */
public void tulosta(OutputStream os) {
    tulosta(new PrintStream(os));
}

/**
 * oluelle seuraava rekisterinumero
 * @return oluen rekisterinumero
 * @example
 * <pre name="test">
 * Olut koff = new Olut();
 * koff.getTunnusNro() === 0;
 * koff.rekisteroi();
 * Olut karhu = new Olut();
 * karhu.rekisteroi();
 * int n1 = koff.getTunnusNro();
 * int n2 = karhu.getTunnusNro();
 * n1 === n2-1;
 * </pre>
 */

public int rekisteroi() {
    tunnusNro = seuraavaNro;
    seuraavaNro++;
    return tunnusNro;
}
/**
 * Palauttaa jäsenen tunnusnumeron.
 * @return jäsenen tunnusnumero
 */
public int getTunnusNro() {
    return tunnusNro;
}

/**
 * testi oluelle
 * @param args ei käytössä
 */
public static void main(String args[]) {
    Olut lappari = new Olut();
    lappari.rekisteroi();
    lappari.taytaOlut();
    lappari.tulosta(System.out);
    
    Olut koff = new Olut();
    koff.rekisteroi();
    koff.taytaOlut();
    koff.tulosta(System.out);
}


}