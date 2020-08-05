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
public class Olut implements Cloneable, Tietue, Comparable<Olut> {
    private int         tunnusNro;
    private String      nimi            ="";
    private String      tyyli           ="";
    private String      vahvuus         = "";
    private String      arvosana        = "";
    private String      mallas          ="";
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
    vahvuus = "4.5";
    arvosana = "9";
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
    StringBuilder sb = new StringBuilder("");
    String erotin = "";
    for (int k = 0; k < getKenttia(); k++) {
        sb.append(erotin);
        sb.append(anna(k));
        erotin = "|";
    }
    return sb.toString();
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
    for (int k = 0; k < getKenttia(); k++)
        aseta(k, Mjonot.erota(sb, '|'));
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
 * @return palauttaa oluen tyylin
 */
public String getTyyli() {
    return tyyli;
}

/**
 * @return palauttaa oluen arvion
 */
public String getArvio() {
    return arvosana;
}

/**
 * @return palauttaa oluen vahvuude
 */
public String getVahvuus() {
    return vahvuus;
}

/**
 * @return palauttaa oluen maltaan
 */
public String getMaltaat() {
    return mallas;
}

/**
 * @return palauttaa oluen reseptin
 */
public String getResepti() {
    return resepti;
}

/**
 * @return palauttaa huomioita oluesta
 */
public String getHuomioita() {
    return huomioita;
}
/**
 * @param s oluen nimi
 * @return virheilmoitus, null jos ok
 */
public String setNimi(String s) {
    nimi = s;
    return null;
}

/**
 * @param s oluen tyyli
 * @return virheilmoitus, null jos ok
 */
public String setTyyli(String s) {
    tyyli = s;
    return null;
}

/**
 * @param s oluen arvosana
 * @return virheilmoitus, null jos ok
 */
public String setArvio(String s) {
    if(!s.matches("^\\d{1,2}.\\d{1}$")) return "Arvio numeroina 0.0-10.0!";
    arvosana = s;
    return null;
}

/**
 * @param s oluen vahvuus
 * @return virheilmoitus, null jos ok
 */
public String setVahvuus(String s) {
   
    if(!s.matches("^\\d{1,2}.\\d{1}$")) return "Vahvuus esim 4.5!";
    vahvuus = s;
    return null;
    
    //||^\\d{1}.\\d{1}$
}

/**
 * @param s oluen maltaat
 * @return virheilmoitus, null jos ok
 */
public String setMaltaat(String s) {
    mallas = s;
    return null;
}

/**
 * @param s oluen resepti
 * @return virheilmoitus, null jos ok
 */
public String setResepti(String s) {
    resepti = s;
    return null;
}

/**
 * @param s huomioita oluesta
 * @return virheilmoitus, null jos ok
 */
public String setHuomioita(String s) {
    huomioita = s;
    return null;
}

@Override
public Olut clone() throws CloneNotSupportedException {
    Olut uusi = (Olut) super.clone();
    return uusi;
}

/**
 * kenttien määrä
 * @return kenttien määrä
 */
@Override
public int getKenttia() {
    return 8;
}

/**
 * ensimmäinen näytettävä kenttä
 * @return ekan kentän
 */
@Override
public int ekaKentta() {
    return 1;
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
    case 1: return "Nimi";
    case 2: return "Tyyli";
    case 3: return "Vahvuus";
    case 4: return "Arvosana";
    case 5: return "Mallas";
    case 6: return "Resepti";
    case 7: return "Huomioita";
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
    case 1: return nimi;
    case 2: return "" + tyyli;
    case 3: return "" + vahvuus;
    case 4: return "" + arvosana;
    case 5: return "" + mallas;
    case 6: return "" + resepti;
    case 7: return "" + huomioita;
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
        try {
            nimi = Mjonot.erotaEx(sb, '§', nimi);
        } catch ( NumberFormatException ex ) {
            return "Nimi ei saa olla tyhjä ";
        }
        return null;
    case 2:
        tyyli = tjono;
        return null;
    case 3:
       try {
        double vahvuus7 = Double.parseDouble(tjono);
        vahvuus = ""+ vahvuus7;
       }catch (NumberFormatException ex) {
           return "Vain numeroja! " ;
       }
        return null;
    case 4:
        try {
            double vahvuus7 = Double.parseDouble(tjono);
            arvosana = ""+ vahvuus7;
           }catch (NumberFormatException ex) {
               return "Vain numeroja! " ;
           }
        return null;
    case 5:
        mallas = tjono;
        return null;
    case 6:
        resepti = tjono;
        return null;
    case 7:
        huomioita = tjono;
        return null;
    default:
        
        return "Kaljaa!";    
    }
}

@Override
public int compareTo(Olut olut) {
    return nimi.compareTo(olut.nimi);
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