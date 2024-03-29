import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewFactory;
import cs3500.model.AnimationModel;
import cs3500.model.AnimationModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests the textual view for the animation.
 */
public class TextualViewTest {

  @Test
  public void testTextSimple() {
    ViewFactory factory = new ViewFactory();
    IView v = factory.getView("text");
    AnimationBuilder<AnimationModelImpl> builder = AnimationModelImpl.builder();
    Readable rn = null;
    try {
      rn = new FileReader("C:\\Users\\kr2e1\\GitHub\\AnimationProject\\src\\toh-3.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    AnimationModel model = AnimationReader.parseFile(rn, builder);
    v.setOutput(new StringBuilder());
    v.displayTextualView(model.getFrames(), model.getShapes(), model.getCanvas());
    assertEquals("canvas 145 50 410 220\n"
                    + "shape disk1\n" + "motion disk1 1 190 180 20 30 0 49 90       25 190 180 20 "
                    + "30 0 49 90\n" +
                    "motion disk1 25 190 180 20 30 0 49 90       35 190 50 20 30 0 49 90\n" +
                    "motion disk1 35 190 50 20 30 0 49 90       36 190 50 20 30 0 49 90\n" +
                    "motion disk1 36 190 50 20 30 0 49 90       46 490 50 20 30 0 49 90\n"
                    + "motion disk1 46 490 50 20 30 0 49 90       47 490 50 20 30 0 49 "
                    + "90\n"
                    + "motion disk1 47 490 50 20 30 0 49 90       57 490 240 20 30 0 49 "
                    + "90\n"
                    + "motion disk1 57 490 240 20 30 0 49 90       89 490 240 20 30 0 49 9"
                    + "0\n"
                    + "motion disk1 89 490 240 20 30 0 49 90       99 490 50 20 30 0 49 9"
                    + "0\n"
                    + "motion disk1 99 490 50 20 30 0 49 90       100 490 50 20 30 0 49 "
                    + "90\n"
                    + "motion disk1 100 490 50 20 30 0 49 90       110 340 50 20 30 0 49 "
                    + "90\n"
                    + "motion disk1 110 340 50 20 30 0 49 90       111 340 50 20 30 0 49 9"
                    + "0\n"
                    + "motion disk1 111 340 50 20 30 0 49 90       121 340 210 20 30 0 49 "
                    + "90\n"
                    + "motion disk1 121 340 210 20 30 0 49 90       153 340 210 20 30 0 4"
                    + "9 90\n"
                    + "motion disk1 153 340 210 20 30 0 49 90       163 340 50 20 30 0 49 "
                    + "90\n"
                    + "motion disk1 163 340 50 20 30 0 49 90       164 340 50 20 30 0 49 "
                    + "90\n"
                    + "motion disk1 164 340 50 20 30 0 49 90       174 190 50 20 30 0 49 9"
                    + "0\n"
                    + "motion disk1 174 190 50 20 30 0 49 90       175 190 50 20 30 0 49 "
                    + "90\n"
                    + "motion disk1 175 190 50 20 30 0 49 90       185 190 240 20 30 0 49 "
                    + "90\n"
                    + "motion disk1 185 190 240 20 30 0 49 90       217 190 240 20 30 0 49"
                    + " 90\n"
                    + "motion disk1 217 190 240 20 30 0 49 90       227 190 50 20 30 0 4"
                    + "9 90\n"
                    + "motion disk1 227 190 50 20 30 0 49 90       228 190 50 20 30 0 49 "
                    + "90\n"
                    + "motion disk1 228 190 50 20 30 0 49 90       238 490 50 20 30 0 49"
                    + " 90\n"
                    + "motion disk1 238 490 50 20 30 0 49 90       239 490 50 20 30 0 4"
                    + "9 90\n"
                    + "motion disk1 239 490 50 20 30 0 49 90       249 490 180 20 30 0 4"
                    + "9 90\n"
                    + "motion disk1 249 490 180 20 30 0 49 90       257 490 180 20 30 0 2"
                    + "55 0\n"
                    + "motion disk1 257 490 180 20 30 0 255 0       302 490 180 20 30 0 2"
                    + "55 0\n"
                    + "\n" + "shape disk2\n"
                    + "motion disk2 1 167 210 65 30 6 247 41       57 167 210 65 30 6 24"
                    + "7 41\n"
                    + "motion disk2 57 167 210 65 30 6 247 41       67 167 50 65 30 6 24"
                    + "7 41\n"
                    + "motion disk2 67 167 50 65 30 6 247 41       68 167 50 65 30 6 24"
                    + "7 41\n"
                    + "motion disk2 68 167 50 65 30 6 247 41       78 317 50 65 30 6 24"
                    + "7 41\n"
                    + "motion disk2 78 317 50 65 30 6 247 41       79 317 50 65 30 6 24"
                    + "7 41\n"
                    + "motion disk2 79 317 50 65 30 6 247 41       89 317 240 65 30 6 24"
                    + "7 41\n"
                    + "motion disk2 89 317 240 65 30 6 247 41       185 317 240 65 30 6 2"
                    + "47 41\n"
                    + "motion disk2 185 317 240 65 30 6 247 41       195 317 50 65 30 6 2"
                    + "47 41\n"
                    + "motion disk2 195 317 50 65 30 6 247 41       196 317 50 65 30 6 2"
                    + "47 41\n"
                    + "motion disk2 196 317 50 65 30 6 247 41       206 467 50 65 30 6 24"
                    + "7 41\n"
                    + "motion disk2 206 467 50 65 30 6 247 41       207 467 50 65 30 6 24"
                    + "7 41\n"
                    + "motion disk2 207 467 50 65 30 6 247 41       217 467 210 65 30 6 2"
                    + "47 41\n"
                    + "motion disk2 217 467 210 65 30 6 247 41       225 467 210 65 30 0 2"
                    + "55 0\n"
                    + "motion disk2 225 467 210 65 30 0 255 0       302 467 210 65 30 0 25"
                    + "5 0\n"
                    + "\n" + "shape disk3\n"
                    + "motion disk3 1 145 240 110 30 11 45 175       121 145 240 110 30 1"
                    + "1 45 175\n"
                    + "motion disk3 121 145 240 110 30 11 45 175       131 145 50 110 3"
                    + "0 11 45 175\n"
                    + "motion disk3 131 145 50 110 30 11 45 175       132 145 50 110 30 1"
                    + "1 45 175\n"
                    + "motion disk3 132 145 50 110 30 11 45 175       142 445 50 110 30 1"
                    + "1 45 175\n"
                    + "motion disk3 142 445 50 110 30 11 45 175       143 445 50 110 30 1"
                    + "1 45 175\n"
                    + "motion disk3 143 445 50 110 30 11 45 175       153 445 240 110 30 1"
                    + "1 45 175\n"
                    + "motion disk3 153 445 240 110 30 11 45 175       161 445 240 110 3"
                    + "0 0 255 0\nmotion disk3 161 445 240 110 30 0 255 0       302 445 240 110 3"
                    + "0 0 255 0\n\n",
            v.getOut().toString());
  }

  @Test
  public void testTextOutputFile() throws IOException {
    ViewFactory factory = new ViewFactory();
    IView v = factory.getView("text");
    AnimationBuilder<AnimationModelImpl> builder = AnimationModelImpl.builder();
    Readable rn = null;
    try {
      rn = new FileReader("C:\\Users\\kr2e1\\GitHub\\AnimationProject\\src\\toh-3.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    FileWriter out = null;
    try {
      out = new FileWriter("text-transcript.txt");
      v.setOutput(out);
    } catch (IOException e) {
      e.printStackTrace();
    }
    AnimationModel model = AnimationReader.parseFile(rn, builder);
    v.displayTextualView(model.getFrames(), model.getShapes(), model.getCanvas());
    out.close();
    String result = "";
    File file = new File("text-transcript.txt");
    Scanner sc = new Scanner(file);
    while (sc.hasNextLine()) {
      result += (sc.nextLine());
    }
    assertEquals("canvas 145 50 410 220shape disk1motion disk1 1 190 180 20 30 0 49 90"
            + "       25 190 180 20 30 0 49 90motion disk1 25 190 180 20 30 0 49 90"
            + "       35 190 50 20 30 0 49 90motion disk1 35 190 50 20 30 0 49 90"
            + "       36 190 50 20 30 0 49 90motion disk1 36 190 50 20 30 0 49 90"
            + "       46 490 50 20 30 0 49 90motion disk1 46 490 50 20 30 0 49 90"
            + "       47 490 50 20 30 0 49 90motion disk1 47 490 50 20 30 0 49 90"
            + "       57 490 240 20 30 0 49 90motion disk1 57 490 240 20 30 0 49 90"
            + "       89 490 240 20 30 0 49 90motion disk1 89 490 240 20 30 0 49 90"
            + "       99 490 50 20 30 0 49 90motion disk1 99 490 50 20 30 0 49 90"
            + "       100 490 50 20 30 0 49 90motion disk1 100 490 50 20 30 0 49 90"
            + "       110 340 50 20 30 0 49 90motion disk1 110 340 50 20 30 0 49 90"
            + "       111 340 50 20 30 0 49 90motion disk1 111 340 50 20 30 0 49 90"
            + "       121 340 210 20 30 0 49 90motion disk1 121 340 210 20 30 0 49 90"
            + "       153 340 210 20 30 0 49 90motion disk1 153 340 210 20 30 0 49 90"
            + "       163 340 50 20 30 0 49 90motion disk1 163 340 50 20 30 0 49 90"
            + "       164 340 50 20 30 0 49 90motion disk1 164 340 50 20 30 0 49 90"
            + "       174 190 50 20 30 0 49 90motion disk1 174 190 50 20 30 0 49 90"
            + "       175 190 50 20 30 0 49 90motion disk1 175 190 50 20 30 0 49 90"
            + "       185 190 240 20 30 0 49 90motion disk1 185 190 240 20 30 0 49 90"
            + "       217 190 240 20 30 0 49 90motion disk1 217 190 240 20 30 0 49 90"
            + "       227 190 50 20 30 0 49 90motion disk1 227 190 50 20 30 0 49 90"
            + "       228 190 50 20 30 0 49 90motion disk1 228 190 50 20 30 0 49 90"
            + "       238 490 50 20 30 0 49 90motion disk1 238 490 50 20 30 0 49 90"
            + "       239 490 50 20 30 0 49 90motion disk1 239 490 50 20 30 0 49 90"
            + "       249 490 180 20 30 0 49 90motion disk1 249 490 180 20 30 0 49 90"
            + "       257 490 180 20 30 0 255 0motion disk1 257 490 180 20 30 0 255 0"
            + "       302 490 180 20 30 0 255 0shape disk2motion disk2 1 167 210 65 30 6 247 41"
            + "       57 167 210 65 30 6 247 41motion disk2 57 167 210 65 30 6 247 41"
            + "       67 167 50 65 30 6 247 41motion disk2 67 167 50 65 30 6 247 41"
            + "       68 167 50 65 30 6 247 41motion disk2 68 167 50 65 30 6 247 41"
            + "       78 317 50 65 30 6 247 41motion disk2 78 317 50 65 30 6 247 41"
            + "       79 317 50 65 30 6 247 41motion disk2 79 317 50 65 30 6 247 41"
            + "       89 317 240 65 30 6 247 41motion disk2 89 317 240 65 30 6 247 41"
            + "       185 317 240 65 30 6 247 41motion disk2 185 317 240 65 30 6 247 41"
            + "       195 317 50 65 30 6 247 41motion disk2 195 317 50 65 30 6 247 41"
            + "       196 317 50 65 30 6 247 41motion disk2 196 317 50 65 30 6 247 41"
            + "       206 467 50 65 30 6 247 41motion disk2 206 467 50 65 30 6 247 41"
            + "       207 467 50 65 30 6 247 41motion disk2 207 467 50 65 30 6 247 41"
            + "       217 467 210 65 30 6 247 41motion disk2 217 467 210 65 30 6 247 41"
            + "       225 467 210 65 30 0 255 0motion disk2 225 467 210 65 30 0 255 0"
            + "       302 467 210 65 30 0 255 0shape disk3motion disk3 1 145 240 110 30 11 45 175"
            + "       121 145 240 110 30 11 45 175motion disk3 121 145 240 110 30 11 45 175"
            + "       131 145 50 110 30 11 45 175motion disk3 131 145 50 110 30 11 45 175"
            + "       132 145 50 110 30 11 45 175motion disk3 132 145 50 110 30 11 45 175"
            + "       142 445 50 110 30 11 45 175motion disk3 142 445 50 110 30 11 45 175"
            + "       143 445 50 110 30 11 45 175motion disk3 143 445 50 110 30 11 45 175"
            + "       153 445 240 110 30 11 45 175motion disk3 153 445 240 110 30 11 45 175"
            + "       161 445 240 110 30 0 255 0motion disk3 161 445 240 110 30 0 255 0"
            + "       302 445 240 110 30 0 255 0", result);
  }

  /**
   * Test that the Hanoi also produces the correct text view.
   */
  public void testTextHanoi() throws IOException {
    ViewFactory factory = new ViewFactory();
    IView v = factory.getView("text");
    AnimationBuilder<AnimationModelImpl> builder = AnimationModelImpl.builder();
    Readable rn = null;
    try {
      rn = new FileReader("C:\\Users\\kr2e1\\GitHub\\AnimationProject\\src\\hanoi.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    AnimationModel model = AnimationReader.parseFile(rn, builder);
    v.setOutput(new FileWriter("testTextHanoi.txt"));
    v.displayTextualView(model.getFrames(), model.getShapes(), model.getCanvas());
  }

  @Test
  public void testBuildingOutputFile() throws IOException {
    ViewFactory factory = new ViewFactory();
    IView v = factory.getView("text");
    AnimationBuilder<AnimationModelImpl> builder = AnimationModelImpl.builder();
    Readable rn = null;
    try {
      rn = new FileReader("C:\\Users\\kr2e1\\GitHub" +
              "\\AnimationProject\\src\\buildings.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    FileWriter out = null;
    try {
      out = new FileWriter("testBuildingOutputFile.txt");
      v.setOutput(out);
    } catch (IOException e) {
      e.printStackTrace();
    }
    AnimationModel model = AnimationReader.parseFile(rn, builder);
    v.displayTextualView(model.getFrames(), model.getShapes(), model.getCanvas());
    try {
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    String result = "";
    File file = new File("testBuildingOutputFile.txt");
    Scanner sc = new Scanner(file);
    result += (sc.nextLine()) + "\n";
    assertEquals("canvas 0 0 800 800\n", result);
  }
}