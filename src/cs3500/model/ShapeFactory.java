package cs3500.model;

/**
 * Shape factory.
 */
public class ShapeFactory {
    public Shape getShape(String name, String type) {
      if (type == null) { //maybe throw null exception here
        return null;
      }
      if (type.equalsIgnoreCase("polygon")) {
        return new Polygon(name);
      }
      if (type.equalsIgnoreCase("oval")) {
        return new Oval(name);
      }
      return null;
    }
  }
