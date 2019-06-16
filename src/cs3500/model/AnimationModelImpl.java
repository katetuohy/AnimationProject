package cs3500.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import cs3500.animator.util.AnimationBuilder;
/**
 * TODO:
 * - Builder
 *  - setBounds()
 *  - Can we decide if we're going to use List<> or ArrayList for everything?
 */

/**
 * Represents the model for an animation. The animation saves the current time and map of commands
 * to the shapes they act on. A method is used to update all of the animation shapes for a given
 * time.
 */
public final class AnimationModelImpl implements AnimationModel {

  int time;
  private ArrayList<Command> motions;
  private ArrayList<Shape> shapes;
  private LinkedHashMap<Command, Shape> commands;
  private int[] canvas;

  /**
   * Construct an animation model at time = 0.
   */
  public AnimationModelImpl() {
    this.time = 0;
    this.commands = new LinkedHashMap<Command, Shape>();
    this.motions = new ArrayList<Command>();
    this.shapes = new ArrayList<Shape>();
    this.canvas = new int[4];
    for (int i = 0; i < canvas.length; i++) {
      canvas[i] = 0;
    }
  }

  /**
   * Generates the LinkedHashMap of all the commands mapped to the shapes they act upon. Throws an
   * IllegalStateException when the motions or shapes lists are null.
   */
  @Override
  public void setAnimationMap() {
    if (motions != null && shapes != null && motions.size() > 0 && shapes.size() > 0) {
      // Sort the motions
      this.sortMotions();
      // Make sure motions have no overlapping time intervals.
      this.validateMotionsNotOverlapping();
      // Fill in time gaps in motions.
      this.fixRemainingTimeGaps();

      LinkedHashMap<Command, Shape> map = new LinkedHashMap<Command, Shape>();
      for (int i = 0; i < motions.size(); i++) {
        String name = motions.get(i).getShapeName();
        for (int j = 0; j < shapes.size(); j++) {
          if (shapes.get(j).getName().equals(name)) {
            map.put(motions.get(i), shapes.get(j));
          }
        }
      }
      this.commands = map;
    } else {
      throw new IllegalStateException("Motions or commands must not be null or empty!");
    }
  }

  // Sorts list of commands in same order of the given list of shapes.
  private void sortMotions() {
    ArrayList<Command> sorted = new ArrayList<Command>();
    for (Shape shape : shapes) {
      for (Command c : motions) {
        if (c.getShapeName().equals(shape.getName())) {
          sorted.add(c);
        }
      }
    }
    this.motions = sorted;
  }

  @Override
  public void fixRemainingTimeGaps() {
    int longestTime = 0;
    //Get end time of animation.
    for (Command c : motions) {
      if (c.getEndTime() > longestTime) {
        longestTime = c.getEndTime();
      }
    }
    ArrayList<Command> newCmds = new ArrayList<Command>();
    // Add command to beginning of shape's motion if there is none.
    if(motions.get(0).getStartTime() != 0) {
      newCmds.add(new Command(motions.get(0).getShape(), 0,
              motions.get(0).getStartTime()));
    }
    newCmds.add(motions.get(0));
    for (int i = 1; i < motions.size() - 1; i++) {
      Command current = motions.get(i);
      Command last = motions.get(i - 1);
      // If its a new shape, and doesn't start at zero.
      if(!current.getShapeName().equalsIgnoreCase(last.getShapeName())) {
        if (current.getStartTime() != 0) {
          newCmds.add(new Command(current.getShape(), 0,
                  current.getStartTime()));
        }
      }
      // If it's same shape, but there's a break in time.
      if (current.getShapeName().equalsIgnoreCase(last.getShapeName())
              && last.getEndTime() != current.getStartTime()) {
        newCmds.add(new Command(current.getShape(), last.getEndTime(),
                current.getStartTime()));
      }
      newCmds.add(motions.get(i));
      // If it's a different shape and
      if(!current.getShapeName().equalsIgnoreCase(motions.get(i + 1).getShapeName())) {
        if (current.getEndTime() != longestTime) {
          newCmds.add(new Command(current.getShape(), current.getEndTime(),
                  longestTime));
        }
      }
    }
    if(motions.size() > 1
            && !motions.get(motions.size() - 1).getShapeName().equals(motions.get(motions.size() - 2).
            getShapeName()) && motions.get(motions.size() - 1).getStartTime() != 0) {
      newCmds.add(new Command(motions.get(motions.size() - 1).getShape(), 0,
              motions.get(0).getStartTime()));
    }

    // If it's same shape, but there's a break in time.
    if (motions.size() > 1 &&
            motions.get(motions.size() - 1).getShapeName().equalsIgnoreCase(motions.get(motions.size() - 2).getShapeName())
            && motions.get(motions.size() - 2).getEndTime() != motions.get(motions.size() - 1).getStartTime()) {
      newCmds.add(new Command(motions.get(motions.size() - 1).getShape(), motions.get(motions.size() - 2).getEndTime(),
              motions.get(motions.size() - 1).getStartTime()));
    }

    newCmds.add(motions.get(motions.size() - 1));
    if(motions.get(motions.size() - 1).getEndTime() != longestTime) {
      newCmds.add(new Command(motions.get(motions.size() - 1).getShape(),
              motions.get(motions.size() - 1).getEndTime(), longestTime));
    }

    motions =  newCmds;
  }

  @Override
  public void validateMotionsNotOverlapping() {
    for (Command c1 : motions) {
      for (Command c2 : motions) {
        if (!c1.equals(c2) && c1.getShapeName().equals(c2.getShapeName()) && overlapping(c1, c2)) {
          throw new IllegalArgumentException("Command times for "
                  + c1.getShapeName() + " are overlapping!");
        }
      }
    }
  }

  @Override
  public String getCanvasString() {
    return "canvas " + canvas[0] + " " + canvas[1] + " " + canvas[2] + " " + canvas[3];
  }

  @Override
  public int[] getCanvas() {
    return this.canvas;
  }

  @Override
  public void setTime(int time) {
    this.time = time;
  }

  @Override
  public int getTime() {
    return this.time;
  }

  @Override
  public LinkedHashMap<Command, Shape> getMap() {
    return this.commands;
  }

  @Override
  public List<Command> getMotions() {
    return this.motions;
  }

  @Override
  public List<Shape> getShapes() {
    return this.shapes;
  }

  @Override
  public String printCommands() {
    String toReturn = "";
    ArrayList<Shape> usedShapes = new ArrayList<Shape>();
    for (Shape s : commands.values()) {
      if (!usedShapes.contains(s)) {
        toReturn += this.printShapeCommands(s) + "\n";
        usedShapes.add(s);
      }
    }
    return toReturn;
  }

  private String printShapeCommands(Shape s) {
    String toReturn = "shape " + s.getName() + "\n";
    for (Command c : commands.keySet()) {
      if (c.getShapeName().equals(s.getName())) {
        toReturn += c.printCommand() + "\n";
      }
    }
    return toReturn;
  }


  @Override
  public List<Shape> moveShapes() {
    List<Shape> shapesToRender = new ArrayList<Shape>();
    for (Command c : commands.keySet()) {
      if (this.time >= c.getStartTime() && this.time <= c.getEndTime()) {
        int startTime = c.getStartTime();
        int endTime = c.getEndTime();
        commands.get(c).setPosition(this.time, startTime, endTime, c);
        commands.get(c).setSize(this.time, startTime, endTime, c);
        commands.get(c).setColor(this.time, startTime, endTime, c);
        for (Shape s : shapes) {
          if (s.getName().equalsIgnoreCase(c.getShapeName())) {
            shapesToRender.add(s);
          }
        }
      }
    }
    return shapesToRender;
  }

  @Override
  public int getMaxWidth() {
    int maxWidth = 0;
    for (Command c: commands.keySet()) {
      if (c.getFrom().getX() > maxWidth) {
        maxWidth = (int)c.getFrom().getX() + c.getOldWidth();
      }
      if (c.getTo().getX() > maxWidth) {
        maxWidth = (int)c.getTo().getX() + c.getNewWidth();
      }
    }
    return maxWidth;
  }

  @Override
  public int getMaxHeight() {
    int maxHeight = 0;
    for (Command c: commands.keySet()) {
      if (c.getFrom().getY() > maxHeight) {
        maxHeight = (int)c.getFrom().getY() + c.getOldHeight();
      }
      if (c.getTo().getY() > maxHeight) {
        maxHeight = (int)c.getTo().getY() + c.getNewHeight();
      }
    }
    return maxHeight;
  }

  @Override
  public void addShape(Shape s) {
    this.shapes.add(s);
  }

  @Override
  public void addMotion(Command c) {
    this.motions.add(c);
  }

  @Override
  public void setCanvas(int x, int y, int width, int height) {
    this.canvas[0] = x;
    this.canvas[1] = y;
    this.canvas[2] = width;
    this.canvas[3] = height;
  }

  /**
   * Determines whether the times are overlapping for the two Commands. They are not overlapping
   * if the times are the same, it should be this way.
   * @param c1 The first command
   * @param c2 the second command
   * @return true if they are overlapping
   */
  private boolean overlapping(Command c1, Command c2) {
    return ((c1.getStartTime() < c2.getStartTime() && c1.getEndTime() > c2.getStartTime())
            || (c2.getStartTime() < c1.getStartTime() && c2.getEndTime() > c1.getEndTime()));
  }

  /**
   * Builder for Animation Model.
   * @return Builder object.
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Represents a builder pattern for an animation model. Animation model can be constructed by
   * adding shapes, motions, bounds.
   * Keyframes not supported in this implementation.
   */
  public static final class Builder implements AnimationBuilder<AnimationModelImpl> {
    AnimationModelImpl model;
    int[] canvas = {0, 0, 0, 0};
    ArrayList<Shape> shapes = new ArrayList<Shape>();
    ArrayList<Command> motions = new ArrayList<Command>();

    @Override
    public AnimationModelImpl build() {
      this.model = new AnimationModelImpl();
      model.setCanvas(canvas[0], canvas[1], canvas[2], canvas[3]);
      for (int i = 0; i < shapes.size(); i++) {
        model.addShape(shapes.get(i));
      }
      for (int i = 0; i < motions.size(); i++) {
        model.addMotion(motions.get(i));
      }
      return this.model;
    }

    @Override
    public AnimationBuilder<AnimationModelImpl> setBounds(int x, int y, int width, int height) {
      canvas[0] = x;
      canvas[1] = y;
      canvas[2] = width;
      canvas[3] = height;
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModelImpl> declareShape(String name, String type) {
      shapes.add(new ShapeFactory().getShape(name, type));
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModelImpl> addMotion(String name, int t1, int x1, int y1,
                                                          int w1, int h1, int r1, int g1, int b1,
                                                          int t2, int x2, int y2, int w2, int h2,
                                                          int r2, int g2, int b2) {
      Shape shape = null;
      for (Shape s : shapes) {
        if (s.getName().equals(name)){
          shape = s;
        }
      }
      motions.add(new Command(shape, t1, new Position2D(x1, y1), w1, h1,
              new Color(r1, g1, b1), t2,  new Position2D(x2, y2), w2, h2, new Color(r2, g2, b2)));
      return this;
    }

    @Override
    /**
     * Not supported in this model implementation.
     */
    public AnimationBuilder<AnimationModelImpl> addKeyframe(String name, int t, int x, int y, int w,
                                                            int h, int r, int g, int b) {
      throw new UnsupportedOperationException("addKeyframe() method not supported in this model.");
    }
  }
}