import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.model.AnimationModel;
import cs3500.model.AnimationModelImpl;
import cs3500.model.KeyFrame;
import cs3500.model.Oval;
import cs3500.model.Polygon;
import cs3500.model.Shape;

import static org.junit.Assert.assertEquals;

/**
 * Test the {@link AnimationModel} class.
 */
public class AnimationModelImplTest {

  Shape s1;
  Shape s2;
  Shape s3;
  ArrayList<Shape> shapes;
  KeyFrame k1;
  KeyFrame k2;
  KeyFrame k3;
  KeyFrame k4;
  KeyFrame k5;
  KeyFrame k6;
  KeyFrame k7;
  int[] canvas;
  ArrayList<KeyFrame> motions;

  /**
   * Initialize all test variables.
   */
  private void initTestVariables() {
    s1 = new Polygon("s1", 4);
    s2 = new Polygon("s2", 4);
    s3 = new Polygon("s3", 4);
    shapes = new ArrayList<Shape>(Arrays.asList(s1, s2, s3));

    k1 = new KeyFrame("s1", 0, 5, 5, 10, 10, 255, 0, 0);
    k2 = new KeyFrame("s1", 5, 55, 55, 10, 10, 0, 255, 255);
    k3 = new KeyFrame("s2", 0, 50, 30, 15, 10, 255, 255, 0);
    k4 = new KeyFrame("s2", 5, 10, 80, 15, 15, 255, 255, 0);
    k5 = new KeyFrame("s3", 1, 10, 30, 30, 20, 0, 0, 255);
    k6 = new KeyFrame("s3", 6, 10, 30, 30, 20, 0, 255, 255);
    k7 = new KeyFrame("s3", 10, 50, 50, 10, 20, 255, 0, 255);
    motions = new ArrayList<KeyFrame>(Arrays.asList(k1, k2, k3, k4, k5, k6, k7));

    canvas = new int[4];
    canvas[0] = 200;
    canvas[1] = 70;
    canvas[2] = 360;
    canvas[3] = 360;
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
    m.addFrame(k1);
    m.addFrame(k2);
    assertEquals(s1, m.getShapes().get(0));
    assertEquals(k1, m.getFrames().get(0));
  }

  /**
   * Test setAnimationMap function properly initializes the hashmap of commands and shapes.
   */
  @Test
  public void testAddFramesAndAddShapes() {
    initTestVariables();
    AnimationModel m = new AnimationModelImpl();
    m.addShape(s1);
    m.addShape(s2);
    m.addShape(s3);
    m.addFrame(k1);
    m.addFrame(k2);
    m.addFrame(k3);
    m.addFrame(k4);
    m.addFrame(k5);
    m.addFrame(k6);
    m.addFrame(k7);
    assertEquals(7, m.getFrames().size());
    assertEquals(3, m.getShapes().size());
    m.setAnimation();
    assertEquals(m.getFrames().get(1), k2);
    assertEquals(m.getShapes().get(6), k7);
  }

  /**
   * Test setAnimationMap function throws illegalStateException when passed null inputs.
   */
  @Test(expected = IllegalStateException.class)
  public void testSetAnimationMapNullInputs() {
    initTestVariables();
    AnimationModel m = new AnimationModelImpl();
    m.setAnimation();
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
    m.addFrame(k1);
    m.addFrame(k2);
    m.addFrame(k3);
    m.addFrame(k4);
    m.addFrame(k5);
    m.addFrame(k6);
    m.addFrame(k7);
    m.setAnimation();
    m.moveShapes();
    for (int i = 0; i < motions.size(); i++) {
      assertEquals(motions.get(i).getName(), m.getFrames().get(i).getName());
      assertEquals(motions.get(i).getTime(), m.getFrames().get(i).getTime());
      assertEquals(motions.get(i).getX(), m.getFrames().get(i).getX());
      assertEquals(motions.get(i).getY(), m.getFrames().get(i).getY());
      assertEquals(motions.get(i).getW(), m.getFrames().get(i).getW());
      assertEquals(motions.get(i).getH(), m.getFrames().get(i).getH());
      assertEquals(motions.get(i).getR(), m.getFrames().get(i).getR());
      assertEquals(motions.get(i).getG(), m.getFrames().get(i).getG());
      assertEquals(motions.get(i).getB(), m.getFrames().get(i).getB());
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
