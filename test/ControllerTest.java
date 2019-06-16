import org.junit.Test;
import org.junit.runners.model.TestTimedOutException;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import cs3500.animator.view.IView;
import cs3500.animator.view.SVGAnimationView;
import cs3500.animator.view.TextualAnimationView;
import cs3500.animator.view.VisualAnimationView;
import cs3500.controller.Controller;
import cs3500.controller.IController;
import cs3500.model.AnimationModel;
import cs3500.model.AnimationModelImpl;
import cs3500.model.Command;
import cs3500.model.Oval;
import cs3500.model.Polygon;
import cs3500.model.Shape;

import static org.junit.Assert.assertEquals;

/**
 * Test the {@link Controller} Class.
 */
public class ControllerTest {

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
  AnimationModel m;

  /**
   * Initialize all test variables.
   */
  public void initTestVariables() {
    s1 = new Polygon("s1");
    s2 = new Polygon("s2", 5);
    s3 = new Polygon("s3", 6, 50, 100);
    s4 = new Oval("s4", Color.BLUE);
    shapes = new ArrayList<Shape>(Arrays.asList(s1, s2, s3, s4));
    AnimationModel m = new AnimationModelImpl();
    for (Shape s : shapes) {
      m.addShape(s);
    }

    c1 = new Command(s1, 0, 10);
    c2 = new Command(s2, 0, 10);
    c3 = new Command(s3, 0, 10);
    c4 = new Command(s4, 0, 5);
    c5 = new Command(s4, 5, 10);
    cmds = new ArrayList<Command>(Arrays.asList(c1, c2, c3, c4, c5));
    for (Command c : cmds) {
      m.addMotion(c);
    }
    m.setAnimationMap();
  }

/*  @Test
  public void testController() {
    initTestVariables();
    IView v = new TextualAnimationView();
    AnimationModel m = new AnimationModelImpl();
    IController c = new Controller(m, v);
    v.setOutput(new StringBuilder());
    assertEquals("shape s1\n"
            + "motion s1 0 0.0 0.0 100 100 0 0 0       10 0.0 0.0 100 100 0 0 0\n"
            + "\n" + "shape s2\n"
            + "motion s2 0 0.0 0.0 100 100 0 0 0       10 0.0 0.0 100 100 0 0 0\n"
            + "\n" + "shape s3\n"
            + "motion s3 0 0.0 0.0 50 100 0 0 0       10 0.0 0.0 50 100 0 0 0\n"
            + "\n" + "shape s4\n"
            + "motion s4 0 0.0 0.0 100 100 0 0 255       5 0.0 0.0 100 100 0 0 255\n"
            + "motion s4 5 0.0 0.0 100 100 0 0 255       10 0.0 0.0 100 100 0 0 255\n\n"
            , v.getOut().toString());
  }*/
}
