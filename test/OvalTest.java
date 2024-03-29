import org.junit.Test;

import java.awt.Color;

import cs3500.model.KeyFrame;
import cs3500.model.Oval;
import cs3500.model.Polygon;
import cs3500.model.Position2D;
import cs3500.model.Shape;

import static org.junit.Assert.assertEquals;

/**
 * Test the {@link Oval} Class.
 */
public class OvalTest {

  @Test
  public void testSetColorBlackToBlueEndTime() {
    Shape o = new Oval("oval");
    KeyFrame k1 = new KeyFrame("oval", 0, 100, 100, 20, 20, 0,
            0, 0);
    KeyFrame k2 = new KeyFrame("oval", 5, 50, 50, 50, 50, 0,
            0, 255);
    assertEquals(Color.BLACK, o.getColor());
    o.setColor(5, 0, 5, k1, k2);
    assertEquals(o.getColor(), Color.BLUE);
  }

  @Test
  public void testSetColorBlackToCyanEndTime() {
    Shape o = new Polygon("oval");
    KeyFrame k1 = new KeyFrame("oval", 0, 100, 100, 20, 20, 0,
            0, 0);
    KeyFrame k2 = new KeyFrame("oval", 5, 50, 50, 50, 50, 0,
            255, 255);
    assertEquals(Color.BLACK, o.getColor());
    o.setColor(5, 0, 5, k1, k2);
    assertEquals(o.getColor(), Color.CYAN);
  }

  @Test
  public void testSetColorMidTime() {
    Shape o = new Oval("oval");
    KeyFrame k1 = new KeyFrame("oval", 0, 100, 100, 20, 20, 0,
            0, 0);
    KeyFrame k2 = new KeyFrame("oval", 5, 50, 50, 50, 50, 0,
            0, 255);
    assertEquals(Color.BLACK, o.getColor());
    o.setColor(1, 0, 5, k1, k2);
    assertEquals(o.getColor(), new Color(0, 0, 51));
  }

  /**
   * Test setColor, setPosition, and setSize at different time intervals.
   */

  @Test
  public void testSetColorMidTime2() {
    Shape o = new Oval("oval");
    KeyFrame k1 = new KeyFrame("oval", 0, 100, 100, 20, 20, 0,
            0, 0);
    KeyFrame k2 = new KeyFrame("oval", 5, 50, 50, 50, 50, 255,
            255, 0);
    assertEquals(Color.BLACK, o.getColor());
    o.setColor(2, 0, 5, k1, k2);
    assertEquals(o.getColor(), new Color(102, 102, 0));
  }

  @Test
  public void testSetPosition() {
    Shape o = new Oval("oval");
    KeyFrame k1 = new KeyFrame("oval", 0, 0, 0, 20, 20, 0,
            0, 0);
    KeyFrame k2 = new KeyFrame("oval", 5, 100, 100, 50, 50, 0,
            0, 255);
    assertEquals(new Position2D(0, 0), o.getPosition());
    o.setPosition(1, 0, 5, k1, k2);
    assertEquals(new Position2D(20, 20), o.getPosition());
  }

  @Test
  public void testSetSize() {
    Shape o = new Oval("oval");
    KeyFrame k1 = new KeyFrame("oval", 0, 100, 100, 20, 20, 0,
            0, 0);
    KeyFrame k2 = new KeyFrame("oval", 5, 50, 50, 0, 0, 0,
            0, 255);
    assertEquals(100, o.getWidth());
    assertEquals(100, o.getHeight());
    o.setSize(1, 0, 5, k1, k2);
    assertEquals(90, o.getWidth());
    assertEquals(90, o.getHeight());
  }

}
