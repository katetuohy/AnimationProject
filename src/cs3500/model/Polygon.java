package cs3500.model;

import java.awt.Color;

/**
 * Represents a polygon with n sides.
 */
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

  /**
   * Returns the number of sides that this shape has.
   * @return number of sides.
   */
  public int getNumSides() {
    return this.numSides;
  }

  @Override
  public String getXML() {
    String.format("<rect id=\"%s\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" " +
                    "fill=\"rgb(128,0,128)\" visibility=\"visible\" >\n"
    , this.name, )
  }

  @Override
  public String getEndXML() {
    return "</rect>";
  }
}
