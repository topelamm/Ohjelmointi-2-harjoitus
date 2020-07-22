package olutlista.test;
// Generated by ComTest BEGIN
import olutlista.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.07.09 17:57:21 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class MaltaatTest {



  // Generated by ComTest BEGIN
  /** testIterator71 */
  @Test
  public void testIterator71() {    // Maltaat: 71
    Maltaat maltaat = new Maltaat(); 
    Mallas ohra = new Mallas(2); maltaat.lisaa(ohra); 
    Mallas ohra2 = new Mallas(1); maltaat.lisaa(ohra2); 
    Mallas ohra3 = new Mallas(2); maltaat.lisaa(ohra3); 
    Mallas ohra4 = new Mallas(1); maltaat.lisaa(ohra4); 
    Mallas ohra5 = new Mallas(2); maltaat.lisaa(ohra5); 
    Iterator<Mallas> i2=maltaat.iterator(); 
    assertEquals("From: Maltaat line: 84", ohra, i2.next()); 
    assertEquals("From: Maltaat line: 85", ohra2, i2.next()); 
    assertEquals("From: Maltaat line: 86", ohra3, i2.next()); 
    assertEquals("From: Maltaat line: 87", ohra4, i2.next()); 
    assertEquals("From: Maltaat line: 88", ohra5, i2.next()); 
    try {
    assertEquals("From: Maltaat line: 89", ohra4, i2.next()); 
    fail("Maltaat: 89 Did not throw NoSuchElementException");
    } catch(NoSuchElementException _e_){ _e_.getMessage(); }
    int n = 0; 
    int jnrot[] = { 2,1,2,1,2} ; 
    for ( Mallas mal:maltaat ) {
    assertEquals("From: Maltaat line: 95", jnrot[n], mal.getOlutNro()); n++; 
    }
    assertEquals("From: Maltaat line: 98", 5, n); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaMaltaat112 */
  @Test
  public void testAnnaMaltaat112() {    // Maltaat: 112
    Maltaat maltaat = new Maltaat(); 
    Mallas ohra = new Mallas(2); maltaat.lisaa(ohra); 
    Mallas ohra2 = new Mallas(1); maltaat.lisaa(ohra2); 
    Mallas ohra3 = new Mallas(2); maltaat.lisaa(ohra3); 
    Mallas ohra4 = new Mallas(1); maltaat.lisaa(ohra4); 
    Mallas ohra5 = new Mallas(2); maltaat.lisaa(ohra5); 
    List<Mallas> loytyneet; 
    loytyneet = maltaat.annaMaltaat(3); 
    assertEquals("From: Maltaat line: 124", 0, loytyneet.size()); 
    loytyneet = maltaat.annaMaltaat(1); 
    assertEquals("From: Maltaat line: 126", 2, loytyneet.size()); 
    assertEquals("From: Maltaat line: 127", true, loytyneet.get(0) == ohra2); 
    assertEquals("From: Maltaat line: 128", true, loytyneet.get(1) == ohra4); 
  } // Generated by ComTest END
}