package cs3500.model;

import java.awt.Color;

/**
 * A Command given for a shape to change state.
 */
public class Command {

  private final Shape shape;
  private final int startTime;
  private final Position2D to;
  private final int oldWidth;
  private final int oldHeight;
  private final Color oldColor;
  private final int endTime;
  private final Position2D from;
  private final int newWidth;
  private final int newHeight;
  private final Color color;


  public Command(Shape shape, int startTime, Position2D to, int oldWidth, int oldHeight,
                 Color oldColor, int endTime, Position2D from, int newWidth, int newHeight,
                 Color color) {
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null.");
    }
    if (startTime >= endTime) {
      throw new IllegalArgumentException("Start time must be strictly less than end time.");
    }
    if (color == null) {
      throw new IllegalArgumentException("Color should not be null.");
    }
    if(to == null || from == null) {
      throw new IllegalArgumentException("Position should not be null.");
    }
    this.shape = shape;
    this.startTime = startTime;
    this.to = to;
    this.oldWidth = oldWidth;
    this.oldHeight = oldHeight;
    this.oldColor = oldColor;
    this.endTime = endTime;
    this.from = from;
    this.newWidth = newWidth;
    this.newHeight = newHeight;
    this.color = color;
  }

  /**
   * The do nothing command, when nothing changes about the state of the shape.
   * @param shape     the shape to which this command applies to
   * @param startTime the starting time for the command
   * @param endTime   when the command ends
   */
  public Command(Shape shape, int startTime, int endTime) {
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null.");
    }
    if (startTime >= endTime) {
      throw new IllegalArgumentException("Start time must be strictly less than end time.");
    }
    this.shape = shape;
    this.oldColor = shape.getColor();
    this.endTime = endTime;
    this.startTime = startTime;
    this.from = new Position2D(0, 0);
    this.to = new Position2D(0, 0);
    this.oldWidth = shape.getWidth();
    this.oldHeight = shape.getHeight();
    this.newHeight = shape.getHeight();
    this.newWidth = shape.getWidth();
    this.color = shape.getColor();
  }

  /**
   * The constructor to change the color of the shape if nothing else is to be changed.
   * @param shape     the shape to which the command applies to
   * @param startTime the start time for the command
   * @param endTime   when the command ends
   * @param color     the new color for the shape
   */
  public Command(Shape shape, int startTime, int endTime, Color color) {
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null.");
    }
    if (startTime >= endTime) {
      throw new IllegalArgumentException("Start time must be strictly less than end time.");
    }
    if (color == null) {
      throw new IllegalArgumentException("Color should not be null.");
    }
    this.shape = shape;
    this.oldColor = shape.getColor();
    this.endTime = endTime;
    this.startTime = startTime;
    this.from = new Position2D(0, 0);
    this.to = new Position2D(0, 0);
    this.oldWidth = shape.getWidth();
    this.oldHeight = shape.getHeight();
    this.newHeight = shape.getHeight();
    this.newWidth = shape.getWidth();
    this.color = color;
  }

  /**
   * The Command constructor to set a new position if nothing else is to be changed.
   * @param shape      the shape to which the command applies to
   * @param startTime  the time tick when the command begins
   * @param endTime    the time tick when the command ends
   * @param pos        the new position for the shape
   */
  public Command(Shape shape, int startTime, int endTime, Position2D pos) {
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null.");
    }
    if (startTime >= endTime) {
      throw new IllegalArgumentException("Start time must be strictly less than end time.");
    }
    if (pos == null) {
      throw new IllegalArgumentException("Position should not be null.");
    }
    this.shape = shape;
    this.oldColor = shape.getColor();
    this.endTime = endTime;
    this.startTime = startTime;
    this.from = new Position2D(0, 0);
    this.to = pos;
    this.oldWidth = shape.getWidth();
    this.oldHeight = shape.getHeight();
    this.newHeight = shape.getHeight();
    this.newWidth = shape.getWidth();
    this.color = Color.BLACK;
  }

  /**
   * Constructor to change shape width and height.
   * @param shape     the shape of which the command applies to
   * @param startTime the time tick when the command begins
   * @param endTime   the time tick when the command ends
   * @param width     the new width of the shape
   * @param height    the new height of the shape
   */
  public Command(Shape shape, int startTime, int endTime, int width, int height) {
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null.");
    }
    if (startTime >= endTime) {
      throw new IllegalArgumentException("Start time must be strictly less than end time.");
    }
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Height and width must be greater than 0");
    }
    this.shape = shape;
    this.oldColor = shape.getColor();
    this.endTime = endTime;
    this.startTime = startTime;
    this.from = new Position2D(0, 0);
    this.to = shape.getPosition();
    this.oldWidth = shape.getWidth();
    this.oldHeight = shape.getHeight();
    this.newHeight = height;
    this.newWidth = width;
    this.color = Color.BLACK;
  }

  /**
   * Prints out the values for the command.
   * @return the string of values
   */
  public String printCommand() {
    return "motion " + this.shape.getName() + " " + this.startTime + " " + this.from.getX()
            + " " + this.from.getY() + " " + this.oldWidth + " " + this.oldHeight + " "
            + oldColor.getRed() + " " + this.oldColor.getGreen() + " " + this.oldColor.getBlue()
            + "       " + this.endTime + " " + this.to.getX() + " " + this.to.getY()
            + " " + this.newWidth + " " + this.newHeight + " " + this.color.getRed() + " "
            + this.color.getGreen() + " " + this.color.getBlue();
  }

  /**
   * Returns the Position of which the shape is moving from.
   * @return start position
   */
  public Position2D getFrom() {
    return from;
  }

  /**
   * Returns the Position of which the shape is moving to.
   * @return end position.
   */
  public Position2D getTo() {
    return to;
  }

  /**
   * Returns the name of the shape.
   * @return the name
   */
  public String getShapeName() {
    return this.shape.getName();
  }

  /**
   * Returns the time at which the command began.
   * @return the start time
   */
  public int getStartTime() {
    return this.startTime;
  }

  /**
   * Returns the time at which the command will end.
   * @return the end time
   */
  public int getEndTime() {
    return this.endTime;
  }

  /**
   * Return the starting width of the shape.
   * @return the old width
   */
  public int getOldWidth() {
    return this.oldWidth;
  }

  /**
   * Returns the starting height of the shape.
   * @return the old height
   */
  public int getOldHeight() {
    return this.oldHeight;
  }

  /**
   * Returns the end width of the shape.
   * @return the new width
   */
  public int getNewWidth() {
    return this.newWidth;
  }

  /**
   * Returns the end height of the shape.
   * @return the new height
   */
  public int getNewHeight() {
    return this.newHeight;
  }

  /**
   * Returns the starting color of the shape.
   * @return the old color
   */
  public Color getOldColor() {
    return this.oldColor;
  }

  /**
   * Returns the end color of the shape.
   * @return the new color
   */
  public Color getNewColor() {
    return this.color;
  }

  /**
   * Get the motion's shape.
   * @return a Shape.
   */
  public Shape getShape() {
    return this.shape;
  }

  public String getXML() {
    return "<animate attributeType=\"xml\" begin=\""+ startTime
            + "ms\" dur=\"" + (endTime - startTime)
            + "ms\" attributeName=\"cx\" from=\"" + from.getX() + "\" to=\"" + to.getX()
            + "\"" + " />\n"

            + "<animate attributeType=\"xml\" begin=\""+ startTime
            + "ms\" dur=\"" + (endTime - startTime) + "ms\""
            + "attributeName=\"y\" from=\"" + from.getY() + "\" to=\" + to.getY()\"\n"

            + "<animate attributeType=\"xml\" begin=\""+ startTime
            + "ms\" dur=\"" + (endTime - startTime) + "ms\""
            + "attributeName=\"color-interpolation\" from=\""
            + "rgb(" + oldColor.getRed() + "," + oldColor.getGreen() + "," + oldColor.getBlue()
            + ")\""
            + "\" to=\"" + "rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue()
            + ")\"" + " />\n"

            + "<animate attributeType=\"xml\" begin=\""+ startTime
            + "ms\" dur=\"" + (endTime - startTime) + "ms\""
            + this.shape.animateWidthXML(this.oldWidth, this.newWidth)

            + "<animate attributeType=\"xml\" begin=\"" + startTime
            + "ms\" dur=\"" + (endTime - startTime) + "ms\""
            + this.shape.animateHeightXML(this.oldHeight, this.newHeight);
  }
}
