package cs3500.animator.view;

import java.io.IOException;
import java.util.List;

import cs3500.model.KeyFrame;
import cs3500.model.Shape;

/**
 * Represents an Animation View that provides the view as a svg file in xml format.
 * This view does not support:
 *  - displayVisual()
 *  - displayTextualView()
 */
public class SVGAnimationView implements IView {
  private Appendable out;
  private int speed;

  public SVGAnimationView() {
    this.out = System.out;
    this.speed = 1;
  }

  @Override
  public void displaySVG(List<KeyFrame> frames, List<Shape> shapes, int[] canvas) {
    String setWidthAndHeightXML =
            String.format("<!--the overall svg width is %d and height is %d."
                    + " By default anything\n"
                    + "drawn between (%d,%d) and (width,height) will be visible -->\n"
                    + "<svg width=\"%d\" height=\"%d\" version=\"1.1\"\n"
                    + "     xmlns=\"http://www.w3.org/2000/svg\">\n",
            canvas[2], canvas[3], canvas[0], canvas[1], canvas[0] + canvas[2],
            canvas[1] + canvas[3]);
    Shape currentShape = shapes.get(0);
    KeyFrame first;
    KeyFrame second;
    String shapeXML = "";
    for (int i = 0; i < frames.size() - 1; i++) {
      first = frames.get(i);
      second = frames.get(i + 1);
      // If the current motion is for the current shape, add the <animate> tag.
      // if i = 0 or if the current motion is the first for the current
      // shape, add the header XML:
      if ((i == 0 || !first.getName().equals(frames.get(i - 1).getName()))
              && first.getName().equals(second.getName())) {
        for (Shape s : shapes) {
          if (first.getName().equals(s.getName())) {
            currentShape = s;
          }
        }
        shapeXML += currentShape.getXML();
      }
      if (first.getName().equals(currentShape.getName())
              && second.getName().equals(currentShape.getName())) {
        shapeXML += first.getXML(currentShape, this.speed, second);
      }
      // If i = last index OR if the current motion is the LAST
      // for the current shape, add the end tag XML.
      if (!first.getName().equals(second.getName())) {
        for (Shape s : shapes) {
          if (first.getName().equals(s.getName())) {
            currentShape = s;
          }
        }
        shapeXML += currentShape.getEndXML();
      }
    }
    tryAppend(out, setWidthAndHeightXML + shapeXML + "</svg>");
  }

  @Override
  public void setOutput(Appendable output) {
    this.out = output;
  }

  @Override
  public Appendable getOut() {
    return this.out;
  }

  @Override
  public int getSpeed() {
    return speed;
  }

  @Override
  public String[] getAddShapeFields() {
    throw new UnsupportedOperationException("Can't add shape in SVG view.");
  }

  @Override
  public String[] getAddKeyFrameFields() {
    throw new UnsupportedOperationException("Can't add keyframe fields in SVG view.");
  }

  @Override
  public String getDeleteShapeField() {
    throw new UnsupportedOperationException("Can't delete shape in SVG view.");
  }

  @Override
  public String[] getDeleteKeyFrameFields() {
    throw new UnsupportedOperationException("Can't edit keyframe in SVG view.");
  }

  @Override
  public void setSpeed(int num) {
    this.speed = num;
  }

  /**
   * Try to append s2 to Appendable s1 or catch an IOException.
   * @param s1 The Appendable to attach the string to
   * @param s2 The string to attach to the appendable
   */
  private void tryAppend(Appendable s1, String s2) {
    try {
      s1.append(s2);
    } catch (IOException e) {
      e.getMessage();
    }
  }

  @Override
  public void displayTextualView(List<KeyFrame> frames, List<Shape> shape, int[] canvas) {
    throw new UnsupportedOperationException("displayTextualView() not supported for SVG View");
  }

  @Override
  public void displayVisual(List<Shape> shapes) {
    throw new UnsupportedOperationException("displayVisual() not supported for SVG View");
  }
}
