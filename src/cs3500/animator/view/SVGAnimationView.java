package cs3500.animator.view;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import cs3500.model.Command;
import cs3500.model.Shape;

/**
 * The view that outputs the animation in SVG form.
 */
public class SVGAnimationView implements IView {
  private Appendable out;
  private int speed;

  public SVGAnimationView() {
    this.out = System.out;
    this.speed = 1;
  }

  @Override
  public void displaySVG(List<Command> motions, int[] canvas) {
    String setWidthAndHeightXML = String.format("<!--the overall svg width is %d and height is %d."
                    + " By default anything\n"
                    + "drawn between (%d,%d) and (width,height) will be visible -->\n"
                    + "<svg width=\"%d\" height=\"%d\" version=\"1.1\"\n"
                    + "     xmlns=\"http://www.w3.org/2000/svg\">\n",
            canvas[2], canvas[3], canvas[0], canvas[1], canvas[0] + canvas[2],
            canvas[1] + canvas[3]);
    Shape currentShape = motions.get(0).getShape();
    Command currentMotion;
    String shapeXML = "";
    for (int i = 0; i < motions.size(); i++) {
      currentMotion = motions.get(i);
      // if i = 0 or if the current motion is the first for the current
      // shape, add the header XML:
      if (i == 0 || !currentMotion.getShapeName().equals(motions.get(i - 1).getShapeName())) {
        currentShape = currentMotion.getShape();
        shapeXML += currentShape.getXML();
      }
      // If the current motion is for the current shape, add the <animate> tag.
      if (currentMotion.getShapeName().equals(currentShape.getName())) {
        shapeXML += currentMotion.getXML(this.speed);
      }
      // If i = last index OR if the current motion is the LAST
      // for the current shape, add the end tag XML.
      if (i == motions.size() - 1 || !currentMotion.getShapeName()
              .equals(motions.get(i + 1).getShapeName())) {
        shapeXML += currentMotion.getShape().getEndXML();
        currentShape = currentMotion.getShape();
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
    return 1000;
  }

  @Override
  public void setSpeed(int num) {
    this.speed = num;
  }

  /**
   * Try to append s2 to Appendable s1.
   *
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
  public void displayTextualView(LinkedHashMap<Command, Shape> commands, int[] canvas) {
    throw new UnsupportedOperationException("displayTextualView() not supported for SVG View");
  }

  @Override
  public void displayVisual(List<Shape> shapes) {
    throw new UnsupportedOperationException("displayVisual() not supported for SVG View");
  }
}
