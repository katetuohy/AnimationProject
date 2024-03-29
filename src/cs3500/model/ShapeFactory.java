package cs3500.model;

import java.awt.Color;

/**
 * Creates a shape based on the given string type.
 */
public class ShapeFactory {

  /**
   * Returns a new shape with the given name based on the given type.
   *
   * @param name the name of the shape
   * @param type the type of shape
   * @return a new shape
   */
  public Shape getShape(String name, String type) {
    if (type == null) { //maybe throw null exception here
      return null;
    }
    if (type.equalsIgnoreCase("rectangle") || type.equalsIgnoreCase("rect")) {
      return new Polygon(name, 4);
    }
    if (type.equalsIgnoreCase("ellipse") || type.equalsIgnoreCase("oval")) {
      return new Oval(name);
    }
    return null;
  }

  /**
   * Returns a new shape of the given type and all inputs possible.
   *
   * @param name   the name of the shape.
   * @param type   the type of the shape.
   * @param width  the width of the shape.
   * @param height the height of the shape.
   * @param pos    the position of the shape.
   * @param c      the color of the shape.
   * @return a new shape.
   */
  public Shape getShapeFull(String name, String type, int width,
                            int height, Position2D pos, Color c) {
    if (type == null) {
      return null;
    }
    if (type.equalsIgnoreCase("rectangle") || type.equalsIgnoreCase("rect")) {
      return new Polygon(name, 4, c, width, height, pos, true);
    }
    if (type.equalsIgnoreCase("ellipse") || type.equalsIgnoreCase("oval")) {
      return new Oval(name, c, width, height, pos, true);
    }
    return null;
  }
}
