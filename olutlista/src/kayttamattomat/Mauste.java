package kayttamattomat;

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
 * @author tonip
 * @version 9.7.2020
 *
 */
public class Mauste {
    private String mauste;
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
    
     * @param humalaNro humalan viitenumero
     * @param olutNro oluen viitenumero
     */
    public Mauste(int humalaNro, int olutNro) {
        this.humalaNro = humalaNro;
        this.olutNro = olutNro;
    }
    
    /**
    
     * @param nro2 viite humalaan
     * @param nro3 viite olueen, jonka mausteet
     */
    public void taytaMauste(int nro2, int nro3) {
    
        humalaNro = nro2;
        olutNro = nro3;
    }
    /**
     * Tulostetaan mausteiden tiedot
     * @param out tietovirta, johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        this.mauste = this.tunnusNro + " " + this.olutNro +" " + this.humalaNro + " ";
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
     * @return palauttaa humalan id:n
     */
    public int getHumalaNro() {
        return humalaNro;
    }
    
    /**
     * @param oid oluen id
     */
    public void setOlutNro(int oid) {
        this.olutNro = oid;
    }
    
    
    /**
     * @param hid humalan id
     */
    public void setHumalaNro(int hid) {
        this.humalaNro = hid;
    }
    
    /**
     * @param jid järjestysid
     */
    public void setTunnusNro(int jid) {
        this.tunnusNro= jid;
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
        setOlutNro(Mjonot.erota(sb, '|', getOlutNro()));
        setHumalaNro(Mjonot.erota(sb, '|', getHumalaNro()));
       
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
                getOlutNro() + "|"+
                getHumalaNro();
                
    }
    
    /**
     * Testiohjelma mausteille
     * @param args ei käytössä
     */
    public static void main (String[] args) {
        Mauste mau = new Mauste();
        mau.rekisteroi();
        mau.taytaMauste(1,1);
        mau.tulosta(System.out);
    }

}
