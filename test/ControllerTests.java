import java.util.ArrayList;
import java.util.Arrays;

import cs3500.animator.view.EditorView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGAnimationView;
import cs3500.animator.view.TextualAnimationView;
import cs3500.animator.view.VisualAnimationView;
import cs3500.model.AnimationModel;
import cs3500.model.AnimationModelImpl;
import cs3500.model.KeyFrame;
import cs3500.model.Polygon;
import cs3500.model.Shape;

/**
 * ToDo:
 * Test Action Performed with each different command (Delete Keyframe, Add Keyframe, delete shape,
 * add shape, replay, increase speed, decrease speed, and timer listener)
 *
 */

/**
 * Tests the controller class.
 */
public class ControllerTests {

  TextualAnimationView text;
  VisualAnimationView vis;
  SVGAnimationViewTest svg;
  EditorView ed;
  AnimationModel model;
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
  ArrayList<KeyFrame> frames;

  public void initTestVariables() {
    IView text = new TextualAnimationView();
    IView vis = new VisualAnimationView();
    IView svg = new SVGAnimationView();
    IView ed = new EditorView();
    AnimationModel model = new AnimationModelImpl();
    s1 = new Polygon("s1", 4);
    s2 = new Polygon("s2", 4);
    s3 = new Polygon("s3", 4);
    shapes = new ArrayList<Shape>(Arrays.asList(s1, s2, s3));
    for (Shape s : shapes) {
      model.addShape(s);
    }

    k1 = new KeyFrame("s1", 0, 5, 5, 10, 10, 255, 0, 0);
    k2 = new KeyFrame("s1", 5, 55, 55, 10, 10, 0, 255, 255);
    k3 = new KeyFrame("s2", 0, 50, 30, 15, 10, 255, 255, 0);
    k4 = new KeyFrame("s2", 5, 10, 80, 15, 15, 255, 255, 0);
    k5 = new KeyFrame("s3", 1, 10, 30, 30, 20, 0, 0, 255);
    k6 = new KeyFrame("s3", 6, 10, 30, 30, 20, 0, 255, 255);
    k7 = new KeyFrame("s3", 10, 50, 50, 10, 20, 255, 0, 255);
    frames = new ArrayList<KeyFrame>(Arrays.asList(k1, k2, k3, k4, k5, k6, k7));
    for (KeyFrame key : frames) {
      model.addFrame(key);
    }

    canvas = new int[4];
    canvas[0] = 200;
    canvas[1] = 70;
    canvas[2] = 360;
    canvas[3] = 360;
  }



}
