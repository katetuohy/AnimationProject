package cs3500.model;

import java.awt.Color;

/**
 * Represents an oval shape.
 */
public class Oval extends AShape {

  private int xRadius = this.width / 2;
  private int yRadius = this.height / 2;

  /**
   * Construct an oval with name.
   * @param name the name of the Oval.
   */
  public Oval(String name) {
    super(name);
  }

  /**
   * Construct an oval with name, width, height.
   * @param name the name of the oval.
   * @param width the width of the oval.
   * @param height the height of the oval.
   */
  public Oval(String name, int width, int height) {
    super(name, width, height);
  }

  /**
   * Construct an oval with name, color.
   * @param name  the name of the oval
   * @param c     the color of the oval
   */
  public Oval(String name, Color c) {
    super(name, c);
  }

  /**
   * Constructs an oval with given height, width, and position.
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
   * @return the xRadius
   */
  private int getXRadius() {
    return this.xRadius;
  }

  /**
   * Gives the radius of the oval vertically.
   * @return the yRadius
   */
  private int getYRadius() {
    return this.yRadius;
  }
}
