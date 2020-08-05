package olutlista.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import olutlista.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.08.05 10:14:50 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class OlutTest {



  // Generated by ComTest BEGIN
  /** testGetNimi39 */
  @Test
  public void testGetNimi39() {    // Olut: 39
    Olut lappari = new Olut(); 
    lappari.taytaOlut(); 
    { String _l_=lappari.getNimi(),_r_="Lapin Kulta"; if ( !_l_.matches(_r_) ) fail("From: Olut line: 42" + " does not match: ["+ _l_ + "] != [" + _r_ + "]");}; 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testRekisteroi90 */
  @Test
  public void testRekisteroi90() {    // Olut: 90
    Olut koff = new Olut(); 
    assertEquals("From: Olut line: 92", 0, koff.getTunnusNro()); 
    koff.rekisteroi(); 
    Olut karhu = new Olut(); 
    karhu.rekisteroi(); 
    int n1 = koff.getTunnusNro(); 
    int n2 = karhu.getTunnusNro(); 
    assertEquals("From: Olut line: 98", n2-1, n1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString128 */
  @Test
  public void testToString128() {    // Olut: 128
    Olut olut = new Olut(); 
    olut.parse(" 2 | Lapin Kulta | Lager"); 
    assertEquals("From: Olut line: 131", true, olut.toString().startsWith("2|Lapin Kulta|Lager|")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse152 */
  @Test
  public void testParse152() {    // Olut: 152
    Olut olut = new Olut(); 
    olut.parse(" 2 | Lapin Kulta | Lager"); 
    assertEquals("From: Olut line: 155", 2, olut.getTunnusNro()); 
    assertEquals("From: Olut line: 156", true, olut.toString().startsWith("2|Lapin Kulta|Lager|")); 
    olut.rekisteroi(); 
    int n = olut.getTunnusNro(); 
    olut.parse (""+(n+20)); 
    olut.rekisteroi(); 
    assertEquals("From: Olut line: 162", n+20+1, olut.getTunnusNro()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAseta362 */
  @Test
  public void testAseta362() {    // Olut: 362
    Olut olut = new Olut(); 
    assertEquals("From: Olut line: 364", null, olut.aseta(1,"Lapin Kulta")); 
    assertEquals("From: Olut line: 365", null, olut.aseta(2,"Lager")); 
  } // Generated by ComTest END
}