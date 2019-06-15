package cs3500.model;

/**
 * Shape factory.
 */
public class ShapeFactory {
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
