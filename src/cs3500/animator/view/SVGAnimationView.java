package cs3500.animator.view;

import java.util.LinkedHashMap;
import java.util.List;

import cs3500.model.Command;
import cs3500.model.Shape;

public class SVGAnimationView implements IView {
  Appendable out;
  private int width;
  private int height;

  public SVGAnimationView(Appendable out) {
    this.out = out;
  }

  public SVGAnimationView() {
    this.out = System.out;
  }

  public void displaySVG(List<Command> motions) {
    String setWidthAndHeightXML = String.format("<!--the overall svg width is %d and height is %d. By default anything\n" +
            "drawn between (0,0) and (width,height) will be visible -->\n" +
            "<svg width=\"%d\" height=\"%d\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">", width, height, width, height);
      Shape current = motions.get(0).getShape();
      String shapeXML = "";
      for (int i = 0; i < motions.size(); i++) {
        shapeXML += motions.get(i).getShape().getXML();
        while(motions.get(i).getShapeName().equals(current.getName())) {
          shapeXML += ""; // Empty... change
          i++;
        }
        i--;
        shapeXML += motions.get(i).getShape().getEndXML();
        current = motions.get(i).getShape();
      }
  }

  @Override
  public void setOutput(String output) {

  }

  @Override
  public void setSpeed(int num) {

  }

  private String addShapeXML(Shape s) {
   return s.getXML();
  }

  /**
   * <!--A purple rectangle named P with lower left corner (200,200), width 50 and height 100 -->
   * <rect id="P" x="200" y="200" width="50" height="100" fill="rgb(128,0,128)" visibility="visible" >
   *     <!-- starting at time=1s, move the rectangle horizontally from x=200 to x=300 in 4 seconds -->
   *     <!-- fill=freeze keeps it there after the animation ends -->
   *     <animate attributeType="xml" begin="1000ms" dur="4000ms" attributeName="x" from="200" to="300" fill="freeze" />
   *
   *     <!--add more animations here for this rectangle using animate tags -->
   * </rect>
   */
}
