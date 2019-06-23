package cs3500.model;

import java.awt.Color;

/**
 * A KeyFrame is an instance of a Shape's state at the time.
 */
public class KeyFrame {

  private final int time;
  private final String shapeName;
  private int x;
  private int y;
  private int w;
  private int h;
  private int r;
  private int g;
  private int b;

  /**
   * Creates a Keyframe with the given parameters.
   * @param shapeName the name of the shape that this keyframe applies to
   * @param time the time for the keyframe
   * @param x the x value of the shape at the time
   * @param y the y value of the shape
   * @param w the width of the shape
   * @param h the height of the shape
   * @param r the r value for the color of the shape
   * @param g the g value for the color of the shape
   * @param b the b value for the color of the shape
   */
  public KeyFrame(String shapeName, int time, int x, int y, int w,
                  int h, int r, int g, int b) {
    this.time = time;
    this.shapeName = shapeName;
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Returns the time that the keyframe occurs at.
   * @return the time
   */
  public int getTime() {
    return this.time;
  }

  /**
   * Returns the name of the shape that the frame applies to.
   * @return the name
   */
  public String getName() {
    return this.shapeName;
  }

  /**
   * Gets the x-value for the shapes location.
   * @return the x value
   */
  public int getX() {
    return this.x;
  }

  /**
   * Gets the y value for the shape's location
   * @return the y-coord
   */
  public int getY() {
    return this.y;
  }

  /**
   * Gets the width of the shape for the time
   * @return the width
   */
  public int getW() {
    return this.w;
  }

  /**
   * Gets the height of the shape for the time.
   * @return the height
   */
  public int getH() {
    return this.h;
  }

  /**
   * Gets the red value for the shape at the time.
   * @return the r value
   */
  public int getR() {
    return this.r;
  }

  /**
   * Gets the g value for the shape at the time.
   * @return the g value
   */
  public int getG() {
    return this.g;
  }

  /**
   * Gets the b value for the shape at the time.
   * @return the b value
   */
  public int getB() {
    return this.b;
  }

  /**
   * Returns the color of the shape at the key frame time.
   * @return the color
   */
  public Color getColor() {
    return new Color(r, g, b);
  }

  /**
   * Returns true if this keyframe is the same as the given one.
   * @param other the other keyframe to compare this to
   * @return true if they are equal
   */
  public boolean equals(KeyFrame other) {
    return other.getName().equals(shapeName) && other.getTime() == time && other.getX() == x
            && other.getY() == y && other.getW() == w && other.getH() == h && other.getR() == r
            && other.getG() == g && other.getB() == b;
  }

  /**
   * Returns the XML for this keyframe to the next one
   * @param s     the shape that the keyframe applies to
   * @param speed the speed of the animation
   * @param next  the next keyframe that this one is changing state to
   * @return the svg string
   */
  public String getXML(Shape s, int speed, KeyFrame next) {
    int newStartTime = this.time * (1000 / speed);
    int newEndTime = next.getTime() * (1000 / speed);
    return s.getPositionXML(newStartTime, newEndTime, new Position2D(this.x,
            + this.y), new Position2D(next.getX(), next.getY()))
            + "<animate attributeType=\"xml\" begin=\"" + newStartTime
            + "ms\" dur=\"" + (newEndTime - newStartTime) + "ms\" "
            + "attributeName=\"fill\" from=\""
            + "rgb(" + this.r + "," + this.g + "," + this.b + ")\""
            + " to=\"" + "rgb(" + next.getR() + "," + next.getG() + "," + next.getB()
            + ")\"" + " />\n"
            + "<animate attributeType=\"xml\" begin=\"" + newStartTime
            + "ms\" dur=\"" + (newEndTime - newStartTime) + "ms\" "
            + s.animateWidthXML(this.w, next.getW()) + " />\n"
            + "<animate attributeType=\"xml\" begin=\"" + newStartTime
            + "ms\" dur=\"" + (newEndTime - newStartTime) + "ms\" "
            + s.animateHeightXML(this.h, next.getH()) + " />\n";
  }
}
