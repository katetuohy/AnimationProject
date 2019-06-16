package cs3500.model;

import java.awt.Color;
import java.awt.Graphics;

public class Polygon extends AShape {

  private final int numSides;

  public Polygon(String name) {
    super(name, 100, 100);
    this.numSides = 4;
  }

  /**
   * Construct a polygon with name, number of sides.
   * @param name the name of the polygon.
   * @param numSides the number of sides of the polygon.
   */
  public Polygon(String name, int numSides) {
    super(name);
    if (numSides < 3) {
      throw new IllegalArgumentException("Number of sides can't be less than 3");
    }
    this.numSides = numSides;
  }

  /**
   * Construct a polygon with name, number of sides, width, height.
   * @param name the name of the polygon.
   * @param numSides the number of sides of the polygon.
   * @param width the width of the polygon.
   * @param height the height of the polygon.
   */
  public Polygon(String name, int numSides, int width, int height) {
    super(name, width, height);
    if (numSides < 3) {
      throw new IllegalArgumentException("Number of sides can't be less than 3");
    }
    this.numSides = numSides;
  }

  /**
   * Construct a polygon with name, number of sides, color, width, height, and showing.
   * @param name the name of the polygon.
   * @param numSides the number of sides of the polygon.
   * @param c the color of the polygon.
   * @param width the width of the polygon.
   * @param height the height of the polygon.
   * @param pos the position of the polygon.
   * @param show whether or not the polygon is visible.
   */
  public Polygon(String name, int numSides, Color c, int width, int height,
                 Position2D pos, boolean show) {
    super(name, c, width, height, pos, show);
    if (numSides < 3) {
      throw new IllegalArgumentException("Number of sides can't be less than 3");
    }
    this.numSides = numSides;
  }

  @Override
  public void drawShape(Graphics g) {
    g.fillRect((int) this.getPosition().getX(), (int) this.getPosition().getY(),
            this.getWidth(), this.getHeight());
  }

  @Override
  public String getPositionXML(int startTime, int endTime, Position2D from, Position2D to) {
    return "<animate attributeType=\"xml\" begin=\"" + startTime
            + "ms\" dur=\"" + (endTime - startTime)
            + "ms\" attributeName=\"x\" from=\"" + from.getX() + "\" to=\"" + to.getX()
            + "\"" + " />\n"
            + "<animate attributeType=\"xml\" begin=\"" + startTime
            + "ms\" dur=\"" + (endTime - startTime) + "ms\" "
            + "attributeName=\"y\" from=\"" + from.getY() + "\" to=\"" + to.getY() + "\" />\n";
  }

  /**
   * Returns the number of sides that this shape has.
   * @return number of sides.
   */
  public int getNumSides() {
    return this.numSides;
  }

  @Override
  public String getXML() {
    return "<rect id=\"" + name + "\" x=\"" + pos.getX() + "\" y=\"" + pos.getY()
            + "\" width=\"" + width + "\" height=\"" + height + "\" "
            + "fill=\"rgb(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue()
            + ")\" visibility=\"visible\" >\n";
  }

  @Override
  public String getEndXML() {
    return "</rect>\n";
  }

  @Override
  public String animateWidthXML(int width1, int width2) {
    return "attributeName=\"width\" from=\"" + width1 + "\" to=\"" + width2 + "\"\n";
  }

  @Override
  public String animateHeightXML(int height1, int height2) {
    return "attributeName=\"height\" from=\"" + height1 + "\" to=\"" + height2 + "\"\n";
  }
}
