package olutlista.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import olutlista.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.07.23 10:55:48 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class OlutTest {



  // Generated by ComTest BEGIN
  /** testGetNimi36 */
  @Test
  public void testGetNimi36() {    // Olut: 36
    Olut lappari = new Olut(); 
    lappari.taytaOlut(); 
    { String _l_=lappari.getNimi(),_r_="Lapin Kulta"; if ( !_l_.matches(_r_) ) fail("From: Olut line: 39" + " does not match: ["+ _l_ + "] != [" + _r_ + "]");}; 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testRekisteroi85 */
  @Test
  public void testRekisteroi85() {    // Olut: 85
    Olut koff = new Olut(); 
    assertEquals("From: Olut line: 87", 0, koff.getTunnusNro()); 
    koff.rekisteroi(); 
    Olut karhu = new Olut(); 
    karhu.rekisteroi(); 
    int n1 = koff.getTunnusNro(); 
    int n2 = karhu.getTunnusNro(); 
    assertEquals("From: Olut line: 93", n2-1, n1); 
  } // Generated by ComTest END
}