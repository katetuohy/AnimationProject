import org.junit.Test;

import java.awt.Color;

import model.AShape;
import model.Command;
import model.Oval;
import model.Polygon;
import model.Position2D;

import static org.junit.Assert.assertEquals;

/**
 * Test the {@link Command} Class.
 */
public class CommandTest {

  AShape s1 = new Polygon("s1");
  AShape s2 = new Polygon("s2", 5);
  AShape s3 = new Polygon("s3", 6, 50, 100);
  AShape s4 = new Oval("s4", Color.BLUE);

  Command c1 = new Command(s1, 0, 10, new Position2D(0,0),
          new Position2D(50, 100), 50, 50, Color.PINK);
  Command c2 = new Command(s2, 5, 9, new Position2D(200,200),
          new Position2D(20, 60), 50, 50, Color.ORANGE);
  Command c3 = new Command(s3, 3, 34, new Position2D(-10,1),
          new Position2D(5, 10), 80, 5, Color.CYAN);
  Command c4 = new Command(s4, 8, 26, new Position2D(-10,1),
          new Position2D(800, 800), 80, 5, Color.MAGENTA);


  @Test
  public void testFirstConstructor1() {
    assertEquals(c1.printCommand(), "motion s1 0 0.0 0.0 100 100 0 0 0       "
            + "10 50.0 100.0 50 50 255 175 175");
  }

  @Test
  public void testFirstConstructor2() {
    assertEquals(c2.printCommand(), "motion s2 5 200.0 200.0 100 100 0 0 0       "
            + "9 20.0 60.0 50 50 255 200 0");
  }

  @Test
  public void testFirstConstructor3() {
    assertEquals(c3.printCommand(), "motion s3 3 -10.0 1.0 50 100 0 0 0       "
            + "34 5.0 10.0 80 5 0 255 255");
  }

  @Test
  public void testFirstConstructor4() {
    assertEquals(c4.printCommand(), "motion s4 8 -10.0 1.0 100 100 0 0 255       "
            + "26 800.0 800.0 80 5 255 0 255");
  }

  @Test(expected = IllegalArgumentException.class)
  public void firstConstructorError1() {
    Command c = new Command(null, 0, 10, new Position2D(0,0),
            new Position2D(50, 100), 50, 50, Color.PINK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void firstConstructorError2() {
    Command c = new Command(s1, 4, 3, new Position2D(0,0),
            new Position2D(50, 100), 50, 50, Color.PINK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void firstConstructorError3() {
    Command c = new Command(s1, -3, 3, new Position2D(0,0),
            new Position2D(50, 100), 50, 50, Color.PINK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void firstConstructorError4() {
    Command c = new Command(s1, 0, 0, new Position2D(0,0),
            new Position2D(50, 100), 50, 50, Color.PINK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void firstConstructorError5() {
    Command c = new Command(s1, 0, 0, new Position2D(0,0),
            new Position2D(50, 100), 0, 50, Color.PINK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void firstConstructorError6() {
    Command c = new Command(s1, 0, 0, new Position2D(0,0),
            new Position2D(50, 100), 50, -50, Color.PINK);
  }

  @Test
  public void testSecondConstructor() {
    Command c = new Command(s1, 0, 10);
    assertEquals(c.printCommand(), "motion s1 0 0.0 0.0 100 100 0 0 0       "
            + "10 0.0 0.0 100 100 0 0 0");
  }

  @Test
  public void testSecondConstructor2() {
    Command c2 = new Command(s2, 0, 10);
    assertEquals(c2.printCommand(), "motion s2 0 0.0 0.0 100 100 0 0 0       "
            + "10 0.0 0.0 100 100 0 0 0");
  }

  @Test
  public void testSecondConstructor3() {
    Command c3 = new Command(s3, 0, 10);
    assertEquals(c3.printCommand(), "motion s3 0 0.0 0.0 50 100 0 0 0       "
            + "10 0.0 0.0 50 100 0 0 0");
  }

  @Test
  public void testSecondConstructor4() {
    Command c4 = new Command(s4, 0, 10);
    assertEquals(c4.printCommand(), "motion s4 0 0.0 0.0 100 100 0 0 255       "
            + "10 0.0 0.0 100 100 0 0 255");
  }

  @Test
  public void testThirdConstructor() {
    Command c = new Command(s1, 0, 10, Color.BLUE);
    assertEquals(c.printCommand(), "motion s1 0 0.0 0.0 100 100 0 0 0       "
            + "10 0.0 0.0 100 100 0 0 255");
  }

  @Test
  public void testThirdConstructor2() {
    Command cmd2 = new Command(s2, 0, 5, Color.CYAN);
    assertEquals(cmd2.printCommand(), "motion s2 0 0.0 0.0 100 100 0 0 0       "
            + "5 0.0 0.0 100 100 0 255 255");
  }

  @Test
  public void testThirdConstructor3() {
    Command c3 = new Command(s3, 3, 10, Color.MAGENTA);
    assertEquals(c3.printCommand(), "motion s3 3 0.0 0.0 50 100 0 0 0       "
            + "10 0.0 0.0 50 100 255 0 255");
  }

  @Test
  public void testThirdConstructor4() {
    Command c4 = new Command(s4, 3, 4, Color.YELLOW);
    assertEquals(c4.printCommand(), "motion s4 3 0.0 0.0 100 100 0 0 255       "
            + "4 0.0 0.0 100 100 255 255 0");
  }

  @Test
  public void testFourthConstructor() {
    Command c = new Command(s1, 0, 10, new Position2D(50, 3));
    assertEquals(c.printCommand(), "motion s1 0 0.0 0.0 100 100 0 0 0       "
            + "10 50.0 3.0 100 100 0 0 0");
  }

  @Test
  public void testFourthConstructor2() {
    Command cmd2 = new Command(s2, 0, 5, new Position2D(30, 20));
    assertEquals(cmd2.printCommand(), "motion s2 0 0.0 0.0 100 100 0 0 0       "
            + "5 30.0 20.0 100 100 0 0 0");
  }

  @Test
  public void testFourthConstructor3() {
    Command c3 = new Command(s3, 3, 10, new Position2D(- 25, -25));
    assertEquals(c3.printCommand(), "motion s3 3 0.0 0.0 50 100 0 0 0       "
            + "10 -25.0 -25.0 50 100 0 0 0");
  }

  @Test
  public void testFourthConstructor4() {
    Command c4 = new Command(s4, 3, 4, new Position2D(400, 300));
    assertEquals(c4.printCommand(), "motion s4 3 0.0 0.0 100 100 0 0 255       "
            + "4 400.0 300.0 100 100 0 0 0");
  }

  @Test
  public void testFifthConstructor() {
    Command c = new Command(s1, 0, 10, 99, 44);
    assertEquals(c.printCommand(), "motion s1 0 0.0 0.0 100 100 0 0 0       "
            + "10 0.0 0.0 99 44 0 0 0");
  }

  @Test
  public void testFifthConstructor2() {
    Command cmd2 = new Command(s2, 0, 5, 70, 70);
    assertEquals(cmd2.printCommand(), "motion s2 0 0.0 0.0 100 100 0 0 0       "
            + "5 0.0 0.0 70 70 0 0 0");
  }

  @Test
  public void testFifthConstructor3() {
    Command c3 = new Command(s3, 3, 10, 20, 50);
    assertEquals(c3.printCommand(), "motion s3 3 0.0 0.0 50 100 0 0 0       "
            + "10 0.0 0.0 20 50 0 0 0");
  }

  @Test
  public void testFifthConstructor4() {
    Command c4 = new Command(s4, 3, 4, 30, 40);
    assertEquals(c4.printCommand(), "motion s4 3 0.0 0.0 100 100 0 0 255       "
            + "4 0.0 0.0 30 40 0 0 0");
  }

  @Test
  public void testGetShape() {
    Command c = new Command(s1, 0, 10);
    assertEquals(c.getShapeName(), "s1");
    assertEquals(c2.getShapeName(), "s2");
    assertEquals(c3.getShapeName(), "s3");
    assertEquals(c4.getShapeName(), "s4");
  }

  @Test
  public void testGetFrom() {
    assertEquals(c1.getFrom(), new Position2D(0, 0));
    assertEquals(c2.getFrom(), new Position2D(200.0, 200.0));
    assertEquals(c3.getFrom(), new Position2D(-10.0, 1.0));
    assertEquals(c4.getFrom(), new Position2D(-10.0, 1.0));
  }

  @Test
  public void testGetTo() {
    assertEquals(c1.getTo(), new Position2D(50, 100));
    assertEquals(c2.getTo(), new Position2D(20, 60));
    assertEquals(c3.getTo(), new Position2D(5, 10));
    assertEquals(c4.getTo(), new Position2D(800, 800));
  }

  @Test
  public void testGetStartTime() {
    assertEquals(c1.getStartTime(), 0);
    assertEquals(c2.getStartTime(), 5);
    assertEquals(c3.getStartTime(), 3);
    assertEquals(c4.getStartTime(), 8);
  }

  @Test
  public void testGetEndTime() {
    assertEquals(c1.getEndTime(), 10);
    assertEquals(c2.getEndTime(), 9);
    assertEquals(c3.getEndTime(), 34);
    assertEquals(c4.getEndTime(), 26);
  }

  @Test
  public void testGetOldWidth() {
    assertEquals(c1.getOldWidth(), 100);
    assertEquals(c2.getOldWidth(), 100);
    assertEquals(c3.getOldWidth(), 50);
    assertEquals(c4.getOldWidth(), 100);
  }

  @Test
  public void testGetNewWidth() {
    assertEquals(c1.getNewWidth(), 50);
    assertEquals(c2.getNewWidth(), 50);
    assertEquals(c3.getNewWidth(), 80);
    assertEquals(c4.getNewWidth(), 80);
  }

  @Test
  public void testGetOldHeight() {
    assertEquals(c1.getOldHeight(), 100);
    assertEquals(c2.getOldHeight(), 100);
    assertEquals(c3.getOldHeight(), 100);
    assertEquals(c4.getOldHeight(), 100);
  }

  @Test
  public void testGetNewHeight() {
    assertEquals(c1.getNewHeight(), 50);
    assertEquals(c2.getNewHeight(), 50);
    assertEquals(c3.getNewHeight(), 5);
    assertEquals(c4.getNewHeight(), 5);
  }

  @Test
  public void testGetOldColor() {
    assertEquals(c1.getOldColor(), Color.BLACK);
    assertEquals(c2.getOldColor(), Color.BLACK);
    assertEquals(c3.getOldColor(), Color.BLACK);
    assertEquals(c4.getOldColor(), Color.BLUE);
  }

  @Test
  public void testGetNewColor() {
    assertEquals(c1.getNewColor(), Color.PINK);
    assertEquals(c2.getNewColor(), Color.ORANGE);
    assertEquals(c3.getNewColor(), Color.CYAN);
    assertEquals(c4.getNewColor(), Color.MAGENTA);
  }
}
