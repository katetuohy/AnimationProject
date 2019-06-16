import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.model.AnimationModel;
import cs3500.model.AnimationModelImpl;
import cs3500.model.Command;
import cs3500.model.Oval;
import cs3500.model.Polygon;
import cs3500.model.Shape;

import static org.junit.Assert.assertEquals;
/**
 * TODO: - Add test for empty animation - DONE: test for teleportation - ADD tests for
 * FixRemaining... - DONE: test shapes are there once added
 */

/**
 * Test the {@link AnimationModel} class.
 */
public class AnimationModelImplTest {

  Shape s1;
  Shape s2;
  Shape s3;
  Shape s4;
  ArrayList<Shape> shapes;
  Command c1;
  Command c2;
  Command c3;
  Command c4;
  Command c5;
  ArrayList<Command> cmds;
  ArrayList<Command> cmdsAfterFill;

  /**
   * Initialize all test variables.
   */
  public void initTestVariables() {
    s1 = new Polygon("s1");
    s2 = new Polygon("s2", 5);
    s3 = new Polygon("s3", 6, 50, 100);
    s4 = new Oval("s4", Color.BLUE);
    shapes = new ArrayList<Shape>(Arrays.asList(s1, s2, s3, s4));

    c1 = new Command(s1, 0, 10);
    c2 = new Command(s2, 0, 10);
    c3 = new Command(s3, 0, 10);
    c4 = new Command(s4, 0, 5);
    c5 = new Command(s4, 5, 10);
    cmds = new ArrayList<Command>(Arrays.asList(c1, c2, c3, c4, c5));
  }

  public void initTestVariablesOverlappingCommands() {
    s1 = new Polygon("s1");
    s2 = new Polygon("s2", 5);
    s3 = new Polygon("s3", 6, 50, 100);
    s4 = new Oval("s4", Color.BLUE);
    shapes = new ArrayList<Shape>(Arrays.asList(s1, s2, s3, s4));

    c1 = new Command(s1, 0, 10);
    c2 = new Command(s1, 5, 15);

    cmds = new ArrayList<Command>(Arrays.asList(c1, c2));
  }

  public void initTestVariablesTeleportation() {
    s1 = new Polygon("s1");
    s2 = new Polygon("s2", 5);
    s3 = new Polygon("s3", 6, 50, 100);
    s4 = new Oval("s4", Color.BLUE);
    shapes = new ArrayList<Shape>(Arrays.asList(s1, s2, s3, s4));

    c1 = new Command(s1, 0, 5);
    c2 = new Command(s1, 6, 10);
    c3 = new Command(s2, 0, 2);
    c4 = new Command(s2, 6, 30);

    cmds = new ArrayList<Command>(Arrays.asList(c1, c2, c3, c4));
    cmdsAfterFill = new ArrayList<Command>(Arrays.asList(c1,
            new Command(s1, 5, 6), c2,
            new Command(s1, 10, 30), c3,
            new Command(s2, 2, 6), c4));
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
    m.addShape(s1);
    m.addMotion(c1);
    assertEquals(s1, m.getShapes().get(0));
    assertEquals(c1, m.getMotions().get(0));
  }

  /**
   * Test setAnimationMap function properly initializes the hashmap of commands and shapes.
   */
  @Test
  public void testSetAnimationMap() {
    initTestVariables();
    AnimationModel m = new AnimationModelImpl();
    m.addShape(s1);
    m.addShape(s2);
    m.addShape(s3);
    m.addShape(s4);
    m.addMotion(c1);
    m.addMotion(c2);
    m.addMotion(c3);
    m.addMotion(c4);
    m.addMotion(c5);
    assertEquals(5, m.getMotions().size());
    assertEquals(4, m.getShapes().size());
    m.setAnimationMap();
    assertEquals(m.getMap().size(), 5);
    assertEquals(m.getMap().get(c1), s1);
  }

  /**
   * Test setAnimationMap function properly initializes the hashmap of commands and shapes.
   */
  @Test
  public void testFillInTimeGapsTeleportation() {
    initTestVariablesTeleportation();
    AnimationModel m = new AnimationModelImpl();
    m.addShape(s1); ////////////
    m.addShape(s2);
    m.addShape(s3);
    m.addShape(s4);
    m.addMotion(c1);
    m.addMotion(c2);
    m.addMotion(c3);
    m.addMotion(c4);
    assertEquals(4, m.getMotions().size());
    assertEquals(4, m.getShapes().size());
    m.fixRemainingTimeGaps();

    for (int i = 0; i < cmdsAfterFill.size(); i++) {
      assertEquals(cmdsAfterFill.get(i).getShapeName(), m.getMotions().get(i).getShapeName());
      assertEquals(cmdsAfterFill.get(i).getStartTime(), m.getMotions().get(i).getStartTime());
      assertEquals(cmdsAfterFill.get(i).getEndTime(), m.getMotions().get(i).getEndTime());
    }
  }

  /**
   * Test setAnimationMap function throws illegalStateException when passed null inputs.
   */
  @Test(expected = IllegalStateException.class)
  public void testSetAnimationMapNullInputs() {
    initTestVariables();
    AnimationModel m = new AnimationModelImpl();
    m.setAnimationMap();
  }

  /**
   * Test that printCommands method works as expected.
   */
  @Test
  public void testPrintCommands() {
    initTestVariables();
    AnimationModel m = new AnimationModelImpl();
    m.addShape(s1);
    m.addShape(s2);
    m.addShape(s3);
    m.addShape(s4);
    m.addMotion(c1);
    m.addMotion(c2);
    m.addMotion(c3);
    m.addMotion(c4);
    m.setAnimationMap();
    assertEquals(m.printCommands(), "shape s1\n"
            + "motion s1 0 0.0 0.0 100 100 0 0 0       10 0.0 0.0 100 100 0 0 0\n"
            + "\n" + "shape s2\n"
            + "motion s2 0 0.0 0.0 100 100 0 0 0       10 0.0 0.0 100 100 0 0 0\n"
            + "\n" + "shape s3\n"
            + "motion s3 0 0.0 0.0 50 100 0 0 0       10 0.0 0.0 50 100 0 0 0\n"
            + "\n" + "shape s4\n"
            + "motion s4 0 0.0 0.0 100 100 0 0 255       5 0.0 0.0 100 100 0 0 255\n"
            + "motion s4 5 0.0 0.0 100 100 0 0 255       10 0.0 0.0 100 100 0 0 255\n\n");
  }

  /**
   * Test that moveShapes function will not change the shapes when time = 0.
   */
  @Test
  public void testMoveShapesTimeZero() {
    initTestVariables();
    AnimationModel m = new AnimationModelImpl();
    m.addShape(s1);
    m.addShape(s2);
    m.addShape(s3);
    m.addShape(s4);
    m.addMotion(c1);
    m.addMotion(c2);
    m.addMotion(c3);
    m.addMotion(c4);
    m.setAnimationMap();
    m.moveShapes();
    for (int i = 0; i < cmds.size(); i++) {
      assertEquals(cmds.get(i).getShapeName(), m.getMotions().get(i).getShapeName());
      assertEquals(cmds.get(i).getStartTime(), m.getMotions().get(i).getStartTime());
      assertEquals(cmds.get(i).getEndTime(), m.getMotions().get(i).getEndTime());
    }

  }

  /**
   * Test validateCommands class checks for overlapping commands.
   */
  @Test
  public void testValidateCommands() {
    initTestVariables();
    AnimationModel m = new AnimationModelImpl();
    m.addShape(s1);
    m.addShape(s2);
    m.addShape(s3);
    m.addShape(s4);
    m.addMotion(c1);
    m.addMotion(c2);
    m.addMotion(c3);
    m.addMotion(c4);
    m.addMotion(c5);
    m.setAnimationMap();
    assertEquals(m.printCommands(), "shape s1\n"
            + "motion s1 0 0.0 0.0 100 100 0 0 0       10 0.0 0.0 100 100 0 0 0\n"
            + "\n" + "shape s2\n"
            + "motion s2 0 0.0 0.0 100 100 0 0 0       10 0.0 0.0 100 100 0 0 0\n"
            + "\n" + "shape s3\n"
            + "motion s3 0 0.0 0.0 50 100 0 0 0       10 0.0 0.0 50 100 0 0 0\n"
            + "\n" + "shape s4\n"
            + "motion s4 0 0.0 0.0 100 100 0 0 255       5 0.0 0.0 100 100 0 0 255\n"
            + "motion s4 5 0.0 0.0 100 100 0 0 255       10 0.0 0.0 100 100 0 0 255\n"
            + "\n"
    );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValidateCommands2() {
    initTestVariablesOverlappingCommands();
    AnimationModel m = new AnimationModelImpl();
    m.addShape(s1);
    m.addMotion(c1);
    m.addMotion(c2);
    m.setAnimationMap();
  }

  @Test
  public void testCanvas() {
    initTestVariables();
    int[] canvas = {1, 2, 3, 4};
    AnimationModel m = new AnimationModelImpl();
    m.setCanvas(1, 2, 3, 4);
    assertEquals(canvas[0], m.getCanvas()[0]);
    assertEquals(canvas[3], m.getCanvas()[3]);
  }

  @Test
  public void testMoveShapes() {
    initTestVariables();
    initTestVariables();
    AnimationModel m = new AnimationModelImpl();
    m.addShape(new Polygon("rect1", 4, 50, 25));
    m.addShape(new Oval("ellipse", 20, 60));
    m.addMotion(new Command(m.getShapes().get(0), 0, 10, 100, 75));
    m.addMotion(new Command(m.getShapes().get(1), 0, 10, 100, 100));
    m.setAnimationMap();
    m.setTime(5);
    List<Shape> shapes = m.moveShapes();
    assertEquals(shapes.get(0).getWidth(), 75);
    assertEquals(shapes.get(0).getHeight(), 50);
    assertEquals(shapes.get(1).getWidth(), 60);
    assertEquals(shapes.get(1).getHeight(), 80);
  }
}
