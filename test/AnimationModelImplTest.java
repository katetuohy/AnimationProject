import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import model.AShape;
import model.AnimationModel;
import model.AnimationModelImpl;
import model.Command;
import model.Oval;
import model.Polygon;

import static org.junit.Assert.assertEquals;

/**
 * Test the {@link AnimationModel} class.
 */
public class AnimationModelImplTest {

  AShape s1;
  AShape s2;
  AShape s3;
  AShape s4;
  ArrayList<AShape> shapes;
  Command c1;
  Command c2;
  Command c3;
  Command c4;
  Command c5;
  ArrayList<Command> cmds;
  ArrayList<Command> cmds2;
  ArrayList<Command> cmds3;

  /**
   * Initialize all test variables.
   */
  public void initTestVariables() {
    s1 = new Polygon("s1");
    s2 = new Polygon("s2", 5);
    s3 = new Polygon("s3", 6, 50, 100);
    s4 = new Oval("s4", Color.BLUE);
    shapes = new ArrayList<AShape>(Arrays.asList(s1, s2, s3, s4));

    c1 = new Command(s1, 0, 10);
    c2 = new Command(s2, 0, 10);
    c3 = new Command(s3, 0, 10);
    c4 = new Command(s4, 0, 10);
    c5 = new Command(s4, 0, 10);
    cmds = new ArrayList<Command>(Arrays.asList(c1, c2, c3, c4, c5));
    cmds2 = new ArrayList<Command>(Arrays.asList(c1, c2, c3, c4));
    cmds3 = new ArrayList<Command>(Arrays.asList(c4, c5));
  }

  /**
   * Test the default constructor. Test that it initializes fields correctly.
   */
  @Test
  public void testConstructor() {
    initTestVariables();
    AnimationModel m = new AnimationModelImpl();
    assertEquals(m.getTime(), 0);
    m.setTime(10);
    assertEquals(m.getTime(), 10);
  }

  /**
   * Test setAnimationMap function properly initializes the hashmap of commands and shapes.
   */
  @Test
  public void testSetAnimationMap() {
    initTestVariables();
    AnimationModel m = new AnimationModelImpl();
    m.setAnimationMap(this.cmds, this.shapes);
    assertEquals(m.getMap().size(), 5);
    assertEquals(m.getMap().get(c1), s1);
  }

  /**
   * Test setAnimationMap function throws illegalArgumentException when passed null inputs.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetAnimationMapNullInputs() {
    initTestVariables();
    AnimationModel m = new AnimationModelImpl();
    m.setAnimationMap(null, null);
  }

  /**
   * Test that printCommands method works as expected.
   */
  @Test
  public void testPrintCommands() {
    initTestVariables();
    AnimationModel m = new AnimationModelImpl();
    m.setAnimationMap(cmds, shapes);
    assertEquals(m.printCommands(), "shape s1\n"
            + "motion s1 0 0.0 0.0 100 100 0 0 0       10 0.0 0.0 100 100 0 0 0\n"
            + "\n" + "shape s2\n"
            + "motion s2 0 0.0 0.0 100 100 0 0 0       10 0.0 0.0 100 100 0 0 0\n"
            + "\n" + "shape s3\n"
            + "motion s3 0 0.0 0.0 50 100 0 0 0       10 0.0 0.0 50 100 0 0 0\n"
            + "\n" + "shape s4\n"
            + "motion s4 0 0.0 0.0 100 100 0 0 255       10 0.0 0.0 100 100 0 0 255\n"
            + "motion s4 0 0.0 0.0 100 100 0 0 255       10 0.0 0.0 100 100 0 0 255\n\n");
  }

  /**
   * Test that moveShapes function will not change the shapes when time = 0.
   */
  @Test
  public void testMoveShapesTimeZero() {
    initTestVariables();
    AnimationModel m = new AnimationModelImpl();
    m.setAnimationMap(cmds,shapes);
    ArrayList<Command> cmds1 = cmds;
    ArrayList<AShape> shapes1 = shapes;
    m.moveShapes();
    assertEquals(cmds, cmds1);
    assertEquals(shapes, shapes1);
  }

  /**
   * Test validateCommands class checks for overlapping commands.
   */

  @Test
  public void testValidateCommands() {
    initTestVariables();
    AnimationModel m = new AnimationModelImpl();
    m.setAnimationMap(cmds2, shapes);
    m.validateCommands();
    assertEquals(m.printCommands(), "shape s1\n"
            + "motion s1 0 0.0 0.0 100 100 0 0 0       10 0.0 0.0 100 100 0 0 0\n"
            + "\n" + "shape s2\n"
            + "motion s2 0 0.0 0.0 100 100 0 0 0       10 0.0 0.0 100 100 0 0 0\n"
            + "\n" + "shape s3\n"
            + "motion s3 0 0.0 0.0 50 100 0 0 0       10 0.0 0.0 50 100 0 0 0\n"
            + "\n" + "shape s4\n"
            + "motion s4 0 0.0 0.0 100 100 0 0 255       10 0.0 0.0 100 100 0 0 255\n\n");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValidateCommands2() {
    initTestVariables();
    AnimationModel m = new AnimationModelImpl();
    m.setAnimationMap(cmds3, shapes);
    m.validateCommands();
  }
}
