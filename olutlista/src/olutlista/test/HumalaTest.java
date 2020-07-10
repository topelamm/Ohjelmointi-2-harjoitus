package olutlista.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import olutlista.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.07.09 17:57:27 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class HumalaTest {



  // Generated by ComTest BEGIN
  /** testRekisteroi72 */
  @Test
  public void testRekisteroi72() {    // Humala: 72
    Humala saaz = new Humala(); 
    assertEquals("From: Humala line: 74", 0, saaz.getTunnusNro()); 
    saaz.rekisteroi(); 
    Humala amarillo = new Humala(); 
    amarillo.rekisteroi(); 
    int n1 = saaz.getTunnusNro(); 
    int n2 = amarillo.getTunnusNro(); 
    assertEquals("From: Humala line: 80", n2-1, n1); 
  } // Generated by ComTest END
}