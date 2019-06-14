package cs3500.animator.view;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import cs3500.model.Command;
import cs3500.model.Shape;

public class SVGAnimationView implements IView {
  private Appendable out;
  private int width;
  private int height;
  private int speed;

  public SVGAnimationView(Appendable out) {
    this.out = out;
  }

  public SVGAnimationView() {
    this.out = System.out;
    this.speed = 1;
  }

  public void displaySVG(List<Command> motions) {
    String setWidthAndHeightXML = String.format("<!--the overall svg width is %d and height is %d."
            + " By default anything\n" +
            "drawn between (0,0) and (width,height) will be visible -->\n" +
            "<svg width=\"%d\" height=\"%d\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">", width, height, width, height);
      Shape current = motions.get(0).getShape();
      String shapeXML = "";
      for (int i = 0; i < motions.size(); i++) {
        shapeXML += motions.get(i).getShape().getXML();
        while(motions.get(i).getShapeName().equals(current.getName())) {
          shapeXML += motions.get(i).getXML();
          i++;
        }
        i--;
        shapeXML += motions.get(i).getShape().getEndXML();
        current = motions.get(i).getShape();
      }

      tryAppend(out, setWidthAndHeightXML + shapeXML + "</svg>");
  }

  @Override
  public void display(List<Shape> Shapes) {
    throw new UnsupportedOperationException("display() method not supported for SVGAnimationView.");
  }

  @Override
  public void setOutput(Appendable output) {
    this.out = out;
  }

  @Override
  public Appendable getOut() {
    return this.out;
  }

  @Override
  public void setOutput(Appendable output) {
    this.out = out;
  }

  @Override
  public void setSpeed(int num) {
    this.speed = speed;
  }

  @Override
  public void displayTextualView(LinkedHashMap<Command, Shape> commands) {
    throw new UnsupportedOperationException("displayTextualView() method not supported for SVGAnimationView.");
  }

  // Try to append s2 to Appendable s1
  private void tryAppend(Appendable s1, String s2) {
    try {
      s1.append(s2);
    } catch (IOException e) {
      e.getMessage();
    }
  }
}
