package cs3500.model;

import java.awt.Color;

/**
 * Represents a geometrical shape.
 */
public abstract class AShape implements Shape {

  protected final String name;
  protected Color c;
  protected int width;
  protected int height;
  protected Position2D pos;
  protected boolean show;

  /**
   * Construct a shape with name.
   *
   * @param name the name of the shape.
   */
  public AShape(String name) {
    this.c = Color.BLACK;
    this.width = 100;
    this.height = 100;
    this.pos = new Position2D(0, 0);
    this.name = name;
    this.show = true;
  }

  /**
   * Construct a shape with name, width and height.
   *
   * @param name   the name of the shape.
   * @param width  the width of the shape.
   * @param height the height of the shape.
   */
  public AShape(String name, int width, int height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Height and width must be greater than 0");
    }
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name can't be null or nothing");
    }
    this.c = Color.BLACK;
    this.width = width;
    this.height = height;
    this.pos = new Position2D(0, 0);
    this.name = name;
    this.show = true;
  }

  /**
   * Construct a shape with name and color.
   *
   * @param name the name of the shape.
   * @param c    the color of the shape.
   */
  public AShape(String name, Color c) {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name can't be null or nothing");
    }
    this.c = c;
    this.width = 100;
    this.height = 100;
    this.pos = new Position2D(0, 0);
    this.name = name;
  }

  /**
   * Construct a shape with every parameter.
   *
   * @param name   the name of the shape.
   * @param c      the color of the shape.
   * @param width  the width of the shape.
   * @param height the height of the shape.
   * @param pos    the position of the shape.
   * @param show   whether the shape is showing or not.
   */
  public AShape(String name, Color c, int width, int height,
                Position2D pos, boolean show) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Height and width must be greater than 0");
    }
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name can't be null or nothing");
    }
    if (pos == null) {
      throw new IllegalArgumentException("Position can't be null.");
    }
    this.name = name;
    this.c = c;
    this.width = width;
    this.height = height;
    this.pos = pos;
  }

  @Override
  public boolean sameShape(AShape other) {
    return this.name.equals(other.getName()) && this.c == other.c && this.width == other.width
            && this.height == other.height && this.pos.equals(other.pos);
  }

  @Override
  public Color getColor() {
    return this.c;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Position2D getPosition() {
    Position2D temp = new Position2D(this.pos.getX(), this.pos.getY());
    return temp;
  }

  @Override
  public void setShow() {
    this.show = !this.show;
  }

  @Override
  public void setPosition(int currTime, int startTime, int endTime, KeyFrame first,
                          KeyFrame second) {
    Position2D from = new Position2D(first.getX(), first.getY());
    Position2D to = new Position2D(second.getX(), second.getY());
    int difference = Math.max(endTime - startTime, 1);
    double x_dist = (to.getX() - from.getX()) / difference;
    double y_dist = (to.getY() - from.getY()) / difference;
    double x_new = from.getX() + (x_dist * (currTime - startTime));
    double y_new = from.getY() + (y_dist * (currTime - startTime));
    this.pos = new Position2D(x_new, y_new);
  }

  @Override
  public void setColor(int currTime, int startTime, int endTime, KeyFrame first,
                       KeyFrame second) {
    Color original = first.getColor();
    Color color = second.getColor();
    int difference = Math.max(endTime - startTime, 1);
    int red_inc = (color.getRed() - original.getRed()) / difference;
    int blue_inc = (color.getBlue() - original.getBlue()) / difference;
    int green_inc = (color.getGreen() - original.getGreen()) / difference;
    int red_new = original.getRed() + (red_inc * (currTime - startTime));
    int blue_new = original.getBlue() + (blue_inc * (currTime - startTime));
    int green_new = original.getGreen() + (green_inc * (currTime - startTime));
    this.c = new Color(red_new, green_new, blue_new);
  }

  @Override
  public void setSize(int currTime, int startTime, int endTime, KeyFrame first,
                      KeyFrame second) {
    int startWidth = first.getW();
    int startHeight = first.getH();
    int endWidth = second.getW();
    int endHeight = second.getH();
    int difference = Math.max(endTime - startTime, 1);
    int width_inc = (endWidth - startWidth) / difference;
    int height_inc = (endHeight - startHeight) / difference;
    int width_new = startWidth + (width_inc * (currTime - startTime));
    int height_new = startHeight + (height_inc * (currTime - startTime));
    this.height = height_new;
    this.width = width_new;
  }
}