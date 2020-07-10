package olutlista;

 import java.util.*;


    /**
     * osaa lisätä ja poistaa humalan
     *
     * lukee ja kirjoittaa humalat tiedostoon |
     *
     * osaa etsiä ja lajitella
     * @author tonip
     * @version 9.7.2020
     *
     */
    public class Mausteet implements Iterable<Mauste> {
        
        private String tiedostonNimi = "";
        
        private final Collection<Mauste> alkiot = new ArrayList<Mauste>();
        
        /**
         * Alustus
         */
        public Mausteet() {
            
        }
        
        /**
         * Lisää uuden mausteen tietorakenteesen
         * @param mau lisättävä mauste
         */
        public void lisaa(Mauste mau) {
            alkiot.add(mau);
        }
        
        /**
         * Mausteiden lukeminen tiedostosta
         * @param hakemisto tiedoston hakemisto
         * @throws SailoException poikkeus lukemisen epäonnistuessa
         */
        public void lueTiedostosta(String hakemisto) throws SailoException{
            tiedostonNimi = hakemisto + ".mau";
            throw new SailoException("Ei osata vielä lukea tiedostoa" + tiedostonNimi);
        }
        
        /**
         * Tallentaa mausteet tiedostoon
         * @throws SailoException tallennuksen epäonnistuessa
         */
        public void tallenna() throws SailoException {
            throw new SailoException ("Ei osata vielä tallentaa tiedostoa" + tiedostonNimi);
        }
        
        /**
         * Palauttaa mausteiden lukumäärän
         * @return mausteiden määrän
         */
        public int getLkm() {
            return alkiot.size();
        }
        
        
        /**
         * Iteraattori kaikkien mausteiden läpikäymiseen
         * @return mausteiteraattori
         * 
         * @example
         * <pre name="test">
         * #PACKAGEIMPORT
         * #import java.util.*;
         * 
         *  Mausteet mausteet = new Mausteet();
         *   
         *  Mauste bisse = new Mauste(2,2,2); mausteet.lisaa(bisse);
         *  Mauste bisse2 = new Mauste(1,1,1); mausteet.lisaa(bisse2);
         *  Mauste bisse3 = new Mauste(2,2,2); mausteet.lisaa(bisse3);
         *  Mauste bisse4 = new Mauste(1,1,1); mausteet.lisaa(bisse4);
         *  Mauste bisse5 = new Mauste(2,2,2); mausteet.lisaa(bisse5);
         * 
         *  Iterator<Mauste> i2=mausteet.iterator();
         *  i2.next() === bisse;
         *  i2.next() === bisse2;
         *  i2.next() === bisse3;
         *  i2.next() === bisse4;
         *  i2.next() === bisse5;
         *  i2.next() === bisse4;  #THROWS NoSuchElementException  
         *  
         *  int n = 0;
         *  int jnrot[] = {2,1,2,1,2};
         *  
         *  for ( Mauste mau : mausteet ) { 
         *    mau.getOlutNro() === jnrot[n]; n++;  
         *  }
         *  
         *  n === 5;
         *  
         * </pre>
         */
        @Override
        public Iterator<Mauste> iterator(){
            return alkiot.iterator();
        }
        
        /**
         * Haetaan kaikki oluen mausteet
         * @param tunnusnro oluen numero jolle mausteet haetaan
         * @return viiteet löydettyihin mausteisiin
         * @example
         * <pre name="test">
        
         * 
         * Mausteet mausteet = new Mausteet();
         *  Mauste bisse = new Mauste(2,2,2); mausteet.lisaa(bisse);
         *  Mauste bisse2 = new Mauste(1,1,1); mausteet.lisaa(bisse2);
         *  Mauste bisse3 = new Mauste(2,2,2); mausteet.lisaa(bisse3);
         *  Mauste bisse4 = new Mauste(1,1,1); mausteet.lisaa(bisse4);
         *  Mauste bisse5 = new Mauste(2,2,2); mausteet.lisaa(bisse5);
         *  
         *  List<Mauste> loytyneet;
         *  loytyneet = mausteet.annaMausteet(3);
         *  loytyneet.size() === 0;
         *  loytyneet = mausteet.annaMausteet(1);
         *  loytyneet.size() === 2;
         *  loytyneet.get(0) == bisse2 === true;
         *  loytyneet.get(1) == bisse4 === true;
         *  
         * </pre>
         */
        public List<Mauste> annaMausteet (int tunnusnro){
            List<Mauste> loydetyt = new ArrayList<Mauste>();
            for (Mauste mau : alkiot)
                if (mau.getOlutNro() == tunnusnro) loydetyt.add(mau);
            return loydetyt;
        }
        
        /**
         * testiohjelma
         * @param args ei käytössä
         */
        public static void main(String[] args) {
            Mausteet mausteet = new Mausteet();
            Mauste bisse = new Mauste();
            bisse.taytaMauste(2,2,2);
            Mauste bisse2 = new Mauste();
            bisse2.taytaMauste(1,1,1);
            Mauste bisse3 = new Mauste();
            bisse3.taytaMauste(2,2,2);
            Mauste bisse4 = new Mauste();
            bisse4.taytaMauste(2,2,2);
            
            mausteet.lisaa(bisse);
            mausteet.lisaa(bisse2);
            mausteet.lisaa(bisse3);
            mausteet.lisaa(bisse2);
            mausteet.lisaa(bisse4);
            
            System.out.println("========= Mausteet testi ================");
            
            List<Mauste> mausteet2 = mausteet.annaMausteet(2);
            
            for(Mauste mau : mausteet2) {
                System.out.print(mau.getTunnusNro()  +" ");
                mau.tulosta(System.out);
                System.out.println(" ");
            }
            
        }
        
    }
