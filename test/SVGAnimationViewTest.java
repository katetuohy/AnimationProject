import org.junit.Test;

import cs3500.animator.view.SVGAnimationView;
import cs3500.model.AnimationModel;
import cs3500.model.AnimationModelImpl;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewFactory;
import cs3500.model.Command;
import cs3500.model.Polygon;
import cs3500.model.Position2D;
import cs3500.model.Shape;

import static junit.framework.TestCase.assertEquals;

public class SVGAnimationViewTest {
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
  Command c6;
  ArrayList<Command> cmds;
  int[] canvas;

  private void initializeTestVariables() {
    s1 = new Polygon("s1",4);
    s2 = new Polygon("s2", 4);
    s3 = new Polygon("s3", 4);
    s4 = new Polygon("s4", 4);
    shapes = new ArrayList<Shape>(Arrays.asList(s1, s2, s3, s4));

    c1 = new Command(s1, 0, 5);
    c2 = new Command(s1, 5, 10);
    c3 = new Command(s2, 0, 5, new Color(255, 0, 0));
    c4 = new Command(s2, 5, 10, new Position2D(50, 50));
    c5 = new Command(s3, 0, 10, 30, 30);
    c6 = new Command(s4, 0, 10);
    cmds = new ArrayList<Command>(Arrays.asList(c1, c2, c3, c4, c5, c6));

    canvas = new int[4];
    canvas[0] = 200;
    canvas[1] = 70;
    canvas[2] = 360;
    canvas[3] = 360;
  }

  @Test
  public void testSVGConstructor() {
    IView v = new ViewFactory().getView("svg");
    v.setOutput(System.out);
    v.setSpeed(4);
    assertEquals(v.getOut(), System.out);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testBasicXMLNoShapesError() {
    initializeTestVariables();
    IView v = new ViewFactory().getView("svg");
    v.displaySVG(new ArrayList<Command>(), canvas);
  }

  @Test
  public void testBasicXMLOneShape() {
    initializeTestVariables();
    IView v = new ViewFactory().getView("svg");
    ArrayList<Command> cmds = new ArrayList<Command>();
    cmds.add(c1);
    v.displaySVG(cmds, canvas);
  }

  /**
   * Test two basic shapes with default (do nothing) commands.
   */
  @Test
  public void testBasicXMLTwoBasicShape() {
    initializeTestVariables();
    IView v = new SVGAnimationView();
    v.setOutput(new StringBuilder());
    v.displaySVG(new ArrayList<Command>(Arrays.asList(c1, c2)), canvas);
    assertEquals("", v.getOut());
  }

  @Test
  public void testSVGSimple() {
    initializeTestVariables();
    ViewFactory factory = new ViewFactory();
    IView v = factory.getView("svg");
    AnimationBuilder<AnimationModelImpl> builder = AnimationModelImpl.builder();
    Readable rn = null;
    try {
      rn = new FileReader("C:\\Users\\kr2e1\\GitHub\\AnimationProject\\src\\smalldemo.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    v.setOutput(new StringBuilder());
    AnimationModel model = AnimationReader.parseFile(rn, builder);
    int[] can = model.getCanvas();
    assertEquals(can.length, 4);
    v.displaySVG(model.getMotions(), can);
    assertEquals("", v.getOut());
  }
}
