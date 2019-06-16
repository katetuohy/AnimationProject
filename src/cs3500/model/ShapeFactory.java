package cs3500.model;

/**
 * Creates a shape based on the given string type.
 */
public class ShapeFactory {

  /**
   * Returns a new shape with the given name based on the given type.
   * @param name the name of the shape
   * @param type the type of shape
   * @return a new shape
   */
    public Shape getShape(String name, String type) {
      if (type == null) { //maybe throw null exception here
        return null;
      }
      if (type.equalsIgnoreCase("rectangle")) {
        return new Polygon(name, 4);
      }
      if (type.equalsIgnoreCase("ellipse")) {
        return new Oval(name);
      }
      return null;
    }
  }
