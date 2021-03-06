package olutlista.test;
// Generated by ComTest BEGIN
import java.io.File;
import olutlista.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.08.17 09:51:19 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class HumalatTest {



  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta57 
   * @throws SailoException when error
   */
  @Test
  public void testLueTiedostosta57() throws SailoException {    // Humalat: 57
    Oluet oluet = new Oluet(); 
    Olut lappari = new Olut(); 
    Olut koff = new Olut(); 
    lappari.taytaOlut(); 
    koff.taytaOlut(); 
    String hakemisto = "testioluet"; 
    String tiedNimi = hakemisto + "/nimet"; 
    File ftied = new File(tiedNimi+ ".dat"); 
    ftied.delete(); 
    oluet.lisaa(lappari); 
    oluet.lisaa(koff); 
    oluet.talleta(); 
    oluet = new Oluet(); 
    oluet.lueTiedostosta(); 
    Iterator<Olut> i = oluet.iterator(); 
    assertEquals("From: Humalat line: 76", true, i.hasNext()); 
    oluet.lisaa(koff); 
    oluet.talleta(); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testIterator183 */
  @Test
  public void testIterator183() {    // Humalat: 183
    Humalat humalat = new Humalat(); 
    Humala saaz = new Humala(2); humalat.lisaa(saaz); 
    Humala saaz2 = new Humala(1); humalat.lisaa(saaz2); 
    Humala saaz3 = new Humala(2); humalat.lisaa(saaz3); 
    Humala saaz4 = new Humala(1); humalat.lisaa(saaz4); 
    Humala saaz5 = new Humala(2); humalat.lisaa(saaz5); 
    Iterator<Humala> i2=humalat.iterator(); 
    assertEquals("From: Humalat line: 196", saaz, i2.next()); 
    assertEquals("From: Humalat line: 197", saaz2, i2.next()); 
    assertEquals("From: Humalat line: 198", saaz3, i2.next()); 
    assertEquals("From: Humalat line: 199", saaz4, i2.next()); 
    assertEquals("From: Humalat line: 200", saaz5, i2.next()); 
    try {
    assertEquals("From: Humalat line: 201", saaz4, i2.next()); 
    fail("Humalat: 201 Did not throw NoSuchElementException");
    } catch(NoSuchElementException _e_){ _e_.getMessage(); }
    int n = 0; 
    int jnrot[] = { 2,1,2,1,2} ; 
    for ( Humala hum:humalat ) {
    assertEquals("From: Humalat line: 207", jnrot[n], hum.getOlutNro()); n++; 
    }
    assertEquals("From: Humalat line: 210", 5, n); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaHumalat224 */
  @Test
  public void testAnnaHumalat224() {    // Humalat: 224
    Humalat humalat = new Humalat(); 
    Humala saaz = new Humala(2); humalat.lisaa(saaz); 
    Humala saaz2 = new Humala(1); humalat.lisaa(saaz2); 
    Humala saaz3 = new Humala(2); humalat.lisaa(saaz3); 
    Humala saaz4 = new Humala(1); humalat.lisaa(saaz4); 
    Humala saaz5 = new Humala(2); humalat.lisaa(saaz5); 
  } // Generated by ComTest END
}