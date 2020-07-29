package olutlista;


import java.io.*;

import fi.jyu.mit.ohj2.Mjonot;

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
    private String      mallas          ="";
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
 * lappari.taytaOlut();
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
    mallas = "ohramallas";
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
    out.println(mallas);
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
 * Asettaa tunnusnumeron ja samalla varmistaa että
 * seuraava numero on aina suurempi kuin tähän mennessä suurin.
 * @param nr asetettava tunnusnumero
 */
private void setTunnusNro(int nr) {
    tunnusNro = nr;
    if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;
}

/**
 * Oluen tiedot merkkijonona tiedostoon tallennettavaksi
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
            nimi + "|"+
            tyyli + "|"+
            mallas + "|"+
            vahvuus + "|"+ 
            arvosana + "|"+ 
            resepti + "|"+ 
            huomioita; 
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
    nimi = Mjonot.erota(sb, '|', nimi);
    tyyli = Mjonot.erota(sb, '|', tyyli);
    mallas = Mjonot.erota(sb, '|', mallas);
    vahvuus = Mjonot.erota(sb, '|', vahvuus);
    arvosana = Mjonot.erota(sb, '|', arvosana);
    resepti = Mjonot.erota(sb, '|', resepti);
    huomioita = Mjonot.erota(sb, '|', huomioita);
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