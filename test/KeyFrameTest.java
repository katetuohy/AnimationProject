import org.junit.Test;

import java.awt.Color;

import cs3500.model.KeyFrame;
import cs3500.model.Polygon;
import cs3500.model.Shape;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Tests the Key Frame class.
 */
public class KeyFrameTest {

  @Test
  public void testConstructor() {
    KeyFrame key = new KeyFrame("rect1", 0, 50, 50, 20, 30, 0,
            255, 255);
    assertEquals("rect1", key.getName());
    assertEquals(0, key.getTime());
    assertEquals(50, key.getX());
    assertEquals(50, key.getY());
    assertEquals(20, key.getW());
    assertEquals(30, key.getH());
    assertEquals(0, key.getR());
    assertEquals(255, key.getG());
    assertEquals(255, key.getB());
  }

  @Test
  public void testGetColor() {
    KeyFrame key1 = new KeyFrame("rect1", 0, 50, 50, 20, 30, 0,
            255, 255);
    KeyFrame key2 = new KeyFrame("rect2", 0, 50, 50, 20, 30, 255,
            100, 30);
    KeyFrame key3 = new KeyFrame("rect3", 0, 50, 50, 20, 30, 140,
            20, 235);
    assertEquals(new Color(0, 255, 255), key1.getColor());
    assertEquals(new Color(255, 100, 30), key2.getColor());
    assertEquals(new Color(140, 20, 235), key3.getColor());
  }

  @Test
  public void testEquals() {
    KeyFrame key1 = new KeyFrame("rect1", 0, 50, 50, 20, 30, 0,
            255, 255);
    KeyFrame key2 = new KeyFrame("rect2", 0, 50, 50, 20, 30, 255,
            100, 30);
    KeyFrame key4 = new KeyFrame("rect1", 0, 50, 50, 20, 30, 0,
            255, 255);
    KeyFrame key5 = new KeyFrame("rect2", 0, 50, 50, 20, 30, 255,
            100, 30);
    assertTrue(key1.equals(key4));
    assertTrue(key5.equals(key2));
    assertFalse(key1.equals(key2));
  }

  @Test
  public void testGetXML() {
    KeyFrame key1 = new KeyFrame("rect1", 0, 50, 50, 20, 30, 0,
            255, 255);
    Shape s = new Polygon("rect1", 4, 50, 50);
    KeyFrame key2 = new KeyFrame("rect1", 5, 50, 50, 20, 30, 255,
            100, 30);
    assertEquals("<animate attributeType=\"xml\" begin=\"0ms\" dur=\"1250ms\" attributeNam"
            + "e=\"x\" from=\"50.0\" to=\"50.0\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"1250ms\" attributeName=\"y\" fro"
            + "m=\"50.0\" to=\"50.0\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"1250ms\" attributeName=\"fill\" f"
            + "rom=\"rgb(0,255,255)\" to=\"rgb(255,100,30)\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"1250ms\" attributeName=\"widt"
            + "h\" from=\"20\" to=\"20\"\n" + " />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"1250ms\" attributeName=\"heigh"
            + "t\" from=\"30\" to=\"30\"\n" +
            " />\n", key1.getXML(s, 4, key2));
  }

  @Test
  public void testGetXML2() {
    KeyFrame key1 = new KeyFrame("rect1", 7, 50, 50, 20, 30, 0,
            255, 255);
    Shape s = new Polygon("rect1", 4, 20, 30);
    KeyFrame key2 = new KeyFrame("rect1", 15, 90, 60, 50, 40, 255,
            100, 30);
    assertEquals("<animate attributeType=\"xml\" begin=\"7ms\" dur=\"8ms\" attributeName"
            + "=\"x\" from=\"50.0\" to=\"90.0\" />\n"
            + "<animate attributeType=\"xml\" begin=\"7ms\" dur=\"8ms\" attributeName=\"y\" from"
            + "=\"50.0\" to=\"60.0\" />\n"
            + "<animate attributeType=\"xml\" begin=\"7ms\" dur=\"8ms\" attributeName=\"fill\" from"
            + "=\"rgb(0,255,255)\" to=\"rgb(255,100,30)\" />\n" +
            "<animate attributeType=\"xml\" begin=\"7ms\" dur=\"8ms\" attributeName=\"width\" fro"
            + "m=\"20\" to=\"50\"\n" + " />\n"
            + "<animate attributeType=\"xml\" begin=\"7ms\" dur=\"8ms\" attributeName=\"height\" f"
            + "rom=\"30\" to=\"40\"\n" +
            " />\n", key1.getXML(s, 1000, key2));
  }
}
