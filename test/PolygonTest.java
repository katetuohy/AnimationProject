import org.junit.Test;

import model.AShape;
import model.Command;
import model.Polygon;
import model.Position2D;
import java.awt.Color;

import static org.junit.Assert.assertEquals;

/**
 * Test the {@link Polygon} Class.
 */
public class PolygonTest {

  /**
   * Test the constructors initialize variables properly and getters
   * return expected values.
   */
  @Test
  public void testFirstConstructor1() {
    Polygon square = new Polygon("square");
    assertEquals(square.getNumSides(), 4);
    assertEquals(square.getHeight(), 100);
    assertEquals(square.getWidth(), 100);
    assertEquals(square.getName(), "square");
  }

  @Test
  public void testSecondConstructor() {
    Polygon pent = new Polygon("pent", 5);
    assertEquals(pent.getNumSides(), 5);
    assertEquals(pent.getHeight(), 100);
    assertEquals(pent.getWidth(), 100);
    assertEquals(pent.getName(), "pent");
  }

  @Test
  public void testThirdConstructor() {
    Polygon oct = new Polygon("oct", 8, 50, 75);
    assertEquals(oct.getNumSides(), 8);
    assertEquals(oct.getHeight(), 75);
    assertEquals(oct.getWidth(), 50);
    assertEquals(oct.getName(), "oct");
  }

  @Test
  public void testFourthConstructor() {
    Polygon six = new Polygon("six", 6, Color.MAGENTA,
            30, 60, new Position2D(40, 40), true);
    assertEquals(six.getNumSides(), 6);
    assertEquals(six.getHeight(), 60);
    assertEquals(six.getWidth(), 30);
    assertEquals(six.getName(), "six");
    assertEquals(six.getPosition(), new Position2D(40, 40));
    assertEquals(six.getColor(), Color.MAGENTA);
  }

  /**
   * Test invalid inputs throw illegal argument exceptions in the constructors.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2NegativeNumSides() {
    Polygon pent = new Polygon("pent", -6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2NumberLessThan3() {
    Polygon pent = new Polygon("pent", 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3NegativeNumSides() {
    Polygon oct = new Polygon("oct", -4, 50, 75);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3NegativeWidth() {
    Polygon oct = new Polygon("oct", 5, -67, 75);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3ZeroHeight() {
    Polygon oct = new Polygon("oct", 8, 90, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4NegativeNumSides() {
    Polygon six = new Polygon("six", -3, Color.MAGENTA,
            30, 60, new Position2D(40, 40), true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4Error2() {
    Polygon six = new Polygon("six", 6, Color.MAGENTA,
            0, 60, new Position2D(40, 40), true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4Error3() {
    Polygon six = new Polygon("six", 6, Color.MAGENTA,
            40, -3, new Position2D(40, 40), true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4Error4() {
    Polygon six = new Polygon("six", 1, Color.MAGENTA,
            40, 80, new Position2D(40, 40), true);
  }

  @Test
  public void testSetColorBlackToBlueEndTime() {
    AShape p = new Polygon("poly");
    Command c = new Command(p, 0, 5, Color.BLUE);
    assertEquals(Color.BLACK, p.getColor());
    p.setColor(5, 0, 5, c);
    assertEquals(p.getColor(), Color.BLUE);
  }

  @Test
  public void testSetColorBlackToCyanEndTime() {
    AShape p = new Polygon("poly");
    Command c = new Command(p, 0, 5, Color.CYAN);
    assertEquals(Color.BLACK, p.getColor());
    p.setColor(5, 0, 5, c);
    assertEquals(p.getColor(), Color.CYAN);
  }

  @Test
  public void testSetColorMidTime() {
    AShape p = new Polygon("poly");
    Command c = new Command(p, 0, 5, Color.BLUE);
    assertEquals(Color.BLACK, p.getColor());
    p.setColor(1, 0, 5, c);
    assertEquals(p.getColor(), new Color(0, 0, 51));
  }

  /**
   * Test setColor, setPosition, and setSize at different time intervals.
   */

  @Test
  public void testSetColorMidTime2() {
    AShape p = new Polygon("poly");
    Command c = new Command(p, 0, 5, Color.YELLOW);
    assertEquals(Color.BLACK, p.getColor());
    p.setColor(2, 0, 5, c);
    assertEquals(p.getColor(), new Color(102, 102, 0));
  }

  @Test
  public void testSetPosition() {
    AShape p = new Polygon("poly");
    Command c = new Command(p, 0, 5, new Position2D(100, 100));
    assertEquals(new Position2D(0,0), p.getPosition());
    p.setPosition(1, 0, 5, c);
    assertEquals(new Position2D(20, 20), p.getPosition());
  }

  @Test
  public void testSetSize() {
    AShape p = new Polygon("poly");
    Command c = new Command(p, 0, 5, 50, 50);
    assertEquals(100, p.getWidth());
    assertEquals(100, p.getHeight());
    p.setSize(1, 0, 5, c);
    assertEquals(90, p.getWidth());
    assertEquals(90, p.getHeight());
  }
}
