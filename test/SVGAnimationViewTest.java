import org.junit.Test;

import cs3500.animator.view.SVGAnimationView;
import cs3500.model.AnimationModel;
import cs3500.model.AnimationModelImpl;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    s1 = new Polygon("s1", 4);
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
  public void testBasicXMLOneShapeOneCommand() {
    initializeTestVariables();
    IView v = new ViewFactory().getView("svg");
    v.setOutput(new StringBuilder());
    ArrayList<Command> cmds = new ArrayList<Command>();
    cmds.add(c1);
    v.displaySVG(cmds, canvas);
    assertEquals("<!--the overall svg width is 360 and height is 360. By default anything\n"
                    + "drawn between (200,70) and (width,height) will be visible -->\n" +
            "<svg width=\"560\" height=\"430\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"s1\" x=\"0.0\" y=\"0.0\" width=\"100\" height=\"100\" fill=\"rgb(0,0,0)\""
            + " visibility=\"visible\" >\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attributeName=\"x\" "
            + "from=\"0.0\" to=\"0.0\" />\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attributeName=\"y\" "
            + "from=\"0.0\" to=\"0.0\" />\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attributeName=\"fill\" "
            + "from=\"rgb(0,0,0)\" to=\"rgb(0,0,0)\" />\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attributeName=\"width\" "
            + "from=\"100\" to=\"100\"\n" + " />\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attributeName=\"height\" "
            + "from=\"100\" to=\"100\"\n" + " />\n" + "</rect>\n" + "</svg>",
            v.getOut().toString());
  }

  @Test
  public void testBasicXMLOneShapeOneCommand_OutputFile() {
    initializeTestVariables();
    IView v = new ViewFactory().getView("svg");
    FileWriter file = null;
    try {
      file = new FileWriter("testBasicXMLOneShapeOneCommand.svg");
    } catch (IOException e) {
      e.printStackTrace();
    }
    v.setOutput(file);
    ArrayList<Command> cmds = new ArrayList<Command>();
    cmds.add(c1);
    v.displaySVG(cmds, canvas);
    try {
      file.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Test two basic shapes with default (do nothing) commands.
   */
  @Test
  public void testBasicXMLFourCommandsTwoShapes() {
    initializeTestVariables();
    IView v = new SVGAnimationView();
    v.setSpeed(4);
    FileWriter out = null;
    try {
      out = new FileWriter("FourCommandsTwoShapes.svg");
    } catch (IOException e) {
      e.printStackTrace();
    }
      v.setOutput(out);
    v.displaySVG(new ArrayList<Command>(Arrays.asList(c1, c2, c3, c4)), canvas);
    try {
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testSVGToh3() {
    ViewFactory factory = new ViewFactory();
    IView v = factory.getView("svg");
    v.setSpeed(4);
    AnimationBuilder<AnimationModelImpl> builder = AnimationModelImpl.builder();
    Readable rn = null;
    try {
      rn = new FileReader("C:\\Users\\kr2e1\\GitHub\\AnimationProject\\src\\toh-3.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    AnimationModel model = AnimationReader.parseFile(rn, builder);
    model.setAnimationMap();
    FileWriter out = null;
    try {
      out = new FileWriter("test-toh-3.svg");
      v.setOutput(out);
    } catch (IOException e) {
      e.printStackTrace();
    }
    v.displaySVG(model.getMotions(), model.getCanvas());
    try {
      out.flush();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    //assertEquals("", v.getOut());
  }

  @Test
  public void testSVGBuilding() {
    ViewFactory factory = new ViewFactory();
    IView v = factory.getView("svg");
    v.setSpeed(2);
    AnimationBuilder<AnimationModelImpl> builder = AnimationModelImpl.builder();
    Readable rn = null;
    try {
      rn = new FileReader("C:\\Users\\kr2e1\\GitHub\\AnimationProject\\src\\buildings.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    AnimationModel model = AnimationReader.parseFile(rn, builder);
    model.setAnimationMap();
    FileWriter out = null;
    try {
      out = new FileWriter("testSVGBuildings.svg");
      v.setOutput(out);
    } catch (IOException e) {
      e.printStackTrace();
    }
    v.displaySVG(model.getMotions(), model.getCanvas());
    try {
      out.flush();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testSVGToh8() {
    ViewFactory factory = new ViewFactory();
    IView v = factory.getView("svg");
    v.setSpeed(20);
    AnimationBuilder<AnimationModelImpl> builder = AnimationModelImpl.builder();
    Readable rn = null;
    try {
      rn = new FileReader(
              "C:\\Users\\kr2e1\\GitHub\\AnimationProject\\src\\toh-8.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    AnimationModel model = AnimationReader.parseFile(rn, builder);
    model.setAnimationMap();
    FileWriter out = null;
    try {
      out = new FileWriter("toh-at-20.svg");
      v.setOutput(out);
    } catch (IOException e) {
      e.printStackTrace();
    }
    v.displaySVG(model.getMotions(), model.getCanvas());
    try {
      out.flush();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
