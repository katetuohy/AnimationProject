package cs3500.model;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents an oval/ellipse shape.
 */
public class Oval extends AShape {

  private int xRadius = this.width / 2;
  private int yRadius = this.height / 2;

  /**
   * Construct an oval with name.
   *
   * @param name the name of the Oval.
   */
  public Oval(String name) {
    super(name);
  }

  /**
   * Construct an oval with name, width, height.
   *
   * @param name   the name of the oval.
   * @param width  the width of the oval.
   * @param height the height of the oval.
   */
  public Oval(String name, int width, int height) {
    super(name, width, height);
  }

  /**
   * Construct an oval with name, color.
   *
   * @param name the name of the oval
   * @param c    the color of the oval
   */
  public Oval(String name, Color c) {
    super(name, c);
  }

  /**
   * Constructs an oval with given height, width, and position.
   *
   * @param name   the name of the oval
   * @param c      the color of the oval
   * @param width  the width of the oval
   * @param height the height of the oval
   * @param pos    the position of the oval
   */
  public Oval(String name, Color c, int width, int height, Position2D pos, boolean show) {
    super(name, c, width, height, pos, show);
  }

  /**
   * Gives the radius of the oval across.
   *
   * @return the xRadius
   */
  private int getXRadius() {
    return this.xRadius;
  }

  /**
   * Gives the radius of the oval vertically.
   *
   * @return the yRadius
   */
  private int getYRadius() {
    return this.yRadius;
  }

  @Override
  public void drawShape(Graphics g) {
    g.fillOval((int) this.getPosition().getX(), (int) this.getPosition().getY(),
            this.getWidth(), this.getHeight());
  }

  @Override
  public String getPositionXML(int startTime, int endTime, Position2D from, Position2D to) {
    return "<animate attributeType=\"xml\" begin=\"" + startTime
            + "ms\" dur=\"" + (endTime - startTime)
            + "ms\" attributeName=\"cx\" from=\"" + from.getX() + "\" to=\"" + to.getX()
            + "\"" + " />\n"
            + "<animate attributeType=\"xml\" begin=\"" + startTime
            + "ms\" dur=\"" + (endTime - startTime) + "ms\" "
            + "attributeName=\"cy\" from=\"" + from.getY() + "\" to=\"" + to.getY() + "\" />\n";
  }

  @Override
  public String getXML() {
    return "<ellipse id=\"" + name + "\" cx=\"" + pos.getX() + "\" cy=\"" + pos.getY() + "\" rx=\""
            + xRadius + "\" ry=\"" + yRadius
            + "\" fill=\"rgb(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue()
            + ")\" visibility=\"visible\" >\n";
  }

  @Override
  public String getEndXML() {
    return "</ellipse>\n";
  }

  @Override
  public String animateWidthXML(int width1, int width2) {
    return "attributeName=\"rx\" from=\"" + (width1 / 2) + "\" to=\"" + (width2 / 2) + "\"\n";
  }

  @Override
  public String animateHeightXML(int height1, int height2) {
    return "attributeName=\"ry\" from=\"" + (height1 / 2) + "\" to=\"" + (height2 / 2) + "\"\n";
  }
}
