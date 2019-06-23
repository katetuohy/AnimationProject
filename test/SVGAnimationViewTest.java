import org.junit.Test;

import cs3500.animator.view.SVGAnimationView;
import cs3500.model.AnimationModel;
import cs3500.model.AnimationModelImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewFactory;
import cs3500.model.KeyFrame;
import cs3500.model.Polygon;
import cs3500.model.Shape;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests the SVG Animation View.
 */
public class SVGAnimationViewTest {

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
  ArrayList<KeyFrame> frames;
  int[] canvas;

  private void initializeTestVariables() {
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
    frames = new ArrayList<KeyFrame>(Arrays.asList(k1, k2, k3, k4, k5, k6, k7));

    canvas = new int[4];
    canvas[0] = 200;
    canvas[1] = 70;
    canvas[2] = 360;
    canvas[3] = 360;
  }

  /**
   * Test the view constructors are initialized properly. Check changing speed, adding shapes,
   * .
   */
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
    v.displaySVG(new ArrayList<KeyFrame>(),new ArrayList<Shape>(), canvas);
  }

  @Test
  public void testBasicXMLOneShapeOneCommand() {
    initializeTestVariables();
    IView v = new ViewFactory().getView("svg");
    v.setOutput(new StringBuilder());
    ArrayList<KeyFrame> frames = new ArrayList<KeyFrame>(Arrays.asList(k1, k2));
    v.displaySVG(frames, new ArrayList<Shape>(Arrays.asList(s1)), canvas);
    assertEquals("<!--the overall svg width is 360 and height is 360. By default anything "
                    + "\ndrawn between (200,70) and (width,height) will be visible -->\n" +
                    "<svg width=\"560\" height=\"430\" version=\"1.1\"\n" +
                    "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
                    "<rect id=\"s1\" x=\"0.0\" y=\"0.0\" width=\"100\" height=\"100\" fill=\"rgb"
                    + "(0,0,0)\" visibility=\"visible\" >\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attributeName"
                    + "=\"x\" from=\"5.0\" to=\"55.0\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attributeName"
                    + "=\"y\" from=\"5.0\" to=\"55.0\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attributeName=\"f"
                    + "ill\" from=\"rgb(255,0,0)\" to=\"rgb(0,255,255)\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attributeName=\"w"
                    + "idth\" from=\"10\" to=\"10\"\n" +
                    " />\n" + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attri"
                    + "buteName=\"height\" from=\"10\" to=\"10\"\n />\n" +
                    "</rect>\n</svg>",
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
    ArrayList<KeyFrame> frames = new ArrayList<KeyFrame>(Arrays.asList(k1, k2));
    v.displaySVG(frames, new ArrayList<Shape>(Arrays.asList(s1)), canvas);
    try {
      file.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    String result = "";
    File f = new File("testBasicXMLOneShapeOneCommand.svg");
    Scanner sc = null;
    try {
      sc = new Scanner(f);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    while (sc.hasNextLine()) {
      result += (sc.nextLine());
    }
    assertEquals("<!--the overall svg width is 360 and height is 360. By default anythin"
            + "g drawn between (200,70) and (width,height) will be visible --><svg width=\"560\" h"
            + "eight=\"430\" version=\"1.1\"     xmlns=\"http://www.w3.org/2000/svg\"><rect id"
            + "=\"s1\" x=\"0.0\" y=\"0.0\" width=\"100\" height=\"100\" fill=\"rgb(0,0,0)\" visibi"
            + "lity=\"visible\" ><animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attri"
            + "buteName=\"x\" from=\"5.0\" to=\"55.0\" /><animate attributeType=\"xml\" begin=\"0m"
            + "s\" dur=\"5000ms\" attributeName=\"y\" from=\"5.0\" to=\"55.0\" /><animate attribu"
            + "teType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attributeName=\"fill\" from=\"rgb(255,"
            + "0,0)\" to=\"rgb(0,255,255)\" /><animate attributeType=\"xml\" begin=\"0ms\" dur=\"5"
            + "000ms\" attributeName=\"width\" from=\"10\" to=\"10\" /><animate attributeType=\"x"
            + "ml\" begin=\"0ms\" dur=\"5000ms\" attributeName=\"height\" from=\"10\" to=\"10\" /"
            + "></rect></svg>", result);
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
    v.displaySVG(new ArrayList<KeyFrame>(Arrays.asList(k1, k2, k3, k4)),
            new ArrayList<Shape>(Arrays.asList(s1, s2)), canvas);
    try {
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    String result = "";
    File f = new File("FourCommandsTwoShapes.svg");
    Scanner sc = null;
    try {
      sc = new Scanner(f);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    while (sc.hasNextLine()) {
      result += (sc.nextLine());
    }
    assertEquals("<!--the overall svg width is 360 and height is 360. By default anythin"
            + "g drawn between (200,70) and (width,height) will be visible --><svg width=\"560\" h"
            + "eight=\"430\" version=\"1.1\"     xmlns=\"http://www.w3.org/2000/svg\"><rect id=\"s"
            + "1\" x=\"0.0\" y=\"0.0\" width=\"100\" height=\"100\" fill=\"rgb(0,0,0)\" visibility"
            + "=\"visible\" ><animate attributeType=\"xml\" begin=\"0ms\" dur=\"1250ms\" attribute"
            + "Name=\"x\" from=\"5.0\" to=\"55.0\" /><animate attributeType=\"xml\" begin=\"0m"
            + "s\" dur=\"1250ms\" attributeName=\"y\" from=\"5.0\" to=\"55.0\" /><animate attribu"
            + "teType=\"xml\" begin=\"0ms\" dur=\"1250ms\" attributeName=\"fill\" from=\"rgb(255,"
            + "0,0)\" to=\"rgb(0,255,255)\" /><animate attributeType=\"xml\" begin=\"0ms\" dur=\"1"
            + "250ms\" attributeName=\"width\" from=\"10\" to=\"10\" /><animate attributeType=\"x"
            + "ml\" begin=\"0ms\" dur=\"1250ms\" attributeName=\"height\" from=\"10\" to=\"10\" /"
            + "></rect><rect id=\"s2\" x=\"0.0\" y=\"0.0\" width=\"100\" height=\"100\" fill=\"r"
            + "gb(0,0,0)\" visibility=\"visible\" ><animate attributeType=\"xml\" begin=\"0ms\" d"
            + "ur=\"1250ms\" attributeName=\"x\" from=\"50.0\" to=\"10.0\" /><animate attributeTy"
            + "pe=\"xml\" begin=\"0ms\" dur=\"1250ms\" attributeName=\"y\" from=\"30.0\" to=\"80"
            + ".0\" /><animate attributeType=\"xml\" begin=\"0ms\" dur=\"1250ms\" attributeName"
            + "=\"fill\" from=\"rgb(255,255,0)\" to=\"rgb(255,255,0)\" /><animate attributeType"
            + "=\"xml\" begin=\"0ms\" dur=\"1250ms\" attributeName=\"width\" from=\"15\" to=\"1"
            + "5\" /><animate attributeType=\"xml\" begin=\"0ms\" dur=\"1250ms\" attributeName"
            + "=\"height\" from=\"10\" to=\"15\" /></rect></svg>", result);
  }

  /**
   * Tests to ensure that the toh-3 file is read in correctly and is correctly formatted to the
   * new file, then closes properly.
   */
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
    FileWriter out = null;
    try {
      out = new FileWriter("test-toh-3.svg");
      v.setOutput(out);
    } catch (IOException e) {
      e.printStackTrace();
    }
    v.displaySVG(model.getFrames(), model.getShapes(), model.getCanvas());
    try {
      out.flush();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    String result = "";
    File f = new File("test-toh-3.svg");
    Scanner sc = null;
    try {
      sc = new Scanner(f);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    while (sc.hasNextLine()) {
      result += (sc.nextLine());
    }
    assertEquals(v.getSpeed(), 4);
  }

  /**
   * Tests to make sure that the same as above is done but with a different input file.
   */
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
    FileWriter out = null;
    try {
      out = new FileWriter("testSVGBuildings.svg");
      v.setOutput(out);
    } catch (IOException e) {
      e.printStackTrace();
    }
    v.displaySVG(model.getFrames(), model.getShapes(), model.getCanvas());
    try {
      out.flush();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals(v.getSpeed(), 2);
  }

  /**
   * Tests that the toh-8 is handled correctly by the svg view and outputed properly to the file.
   */
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
    FileWriter out = null;
    try {
      out = new FileWriter("toh-at-20.svg");
      v.setOutput(out);
    } catch (IOException e) {
      e.printStackTrace();
    }
    v.displaySVG(model.getFrames(), model.getShapes(), model.getCanvas());
    try {
      out.flush();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals(v.getSpeed(), 20);
  }
}
