package olutlista.test;
// Generated by ComTest BEGIN
import olutlista.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.07.09 17:57:34 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class HumalatTest {



  // Generated by ComTest BEGIN
  /** testIterator69 */
  @Test
  public void testIterator69() {    // Humalat: 69
    Humalat humalat = new Humalat(); 
    Humala saaz = new Humala(2); humalat.lisaa(saaz); 
    Humala saaz2 = new Humala(1); humalat.lisaa(saaz2); 
    Humala saaz3 = new Humala(2); humalat.lisaa(saaz3); 
    Humala saaz4 = new Humala(1); humalat.lisaa(saaz4); 
    Humala saaz5 = new Humala(2); humalat.lisaa(saaz5); 
    Iterator<Humala> i2=humalat.iterator(); 
    assertEquals("From: Humalat line: 82", saaz, i2.next()); 
    assertEquals("From: Humalat line: 83", saaz2, i2.next()); 
    assertEquals("From: Humalat line: 84", saaz3, i2.next()); 
    assertEquals("From: Humalat line: 85", saaz4, i2.next()); 
    assertEquals("From: Humalat line: 86", saaz5, i2.next()); 
    try {
    assertEquals("From: Humalat line: 87", saaz4, i2.next()); 
    fail("Humalat: 87 Did not throw NoSuchElementException");
    } catch(NoSuchElementException _e_){ _e_.getMessage(); }
    int n = 0; 
    int jnrot[] = { 2,1,2,1,2} ; 
    for ( Humala hum:humalat ) {
    assertEquals("From: Humalat line: 93", jnrot[n], hum.getOlutNro()); n++; 
    }
    assertEquals("From: Humalat line: 96", 5, n); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaHumalat110 */
  @Test
  public void testAnnaHumalat110() {    // Humalat: 110
    Humalat humalat = new Humalat(); 
    Humala saaz = new Humala(2); humalat.lisaa(saaz); 
    Humala saaz2 = new Humala(1); humalat.lisaa(saaz2); 
    Humala saaz3 = new Humala(2); humalat.lisaa(saaz3); 
    Humala saaz4 = new Humala(1); humalat.lisaa(saaz4); 
    Humala saaz5 = new Humala(2); humalat.lisaa(saaz5); 
    List<Humala> loytyneet; 
    loytyneet = humalat.annaHumalat(3); 
    assertEquals("From: Humalat line: 122", 0, loytyneet.size()); 
    loytyneet = humalat.annaHumalat(1); 
    assertEquals("From: Humalat line: 124", 2, loytyneet.size()); 
    assertEquals("From: Humalat line: 125", true, loytyneet.get(0) == saaz2); 
    assertEquals("From: Humalat line: 126", true, loytyneet.get(1) == saaz4); 
  } // Generated by ComTest END
}