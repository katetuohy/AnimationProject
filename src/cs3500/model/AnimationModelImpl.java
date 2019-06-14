package cs3500.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import cs3500.animator.util.AnimationBuilder;
/**
 * TODO:
 * - Builder
 *  - setBounds()
 *  - addKeyFrame()
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

  /**
   * Construct an animation model at time = 0.
   */
  public AnimationModelImpl() {
    this.time = 0;
    this.commands = new LinkedHashMap<Command, Shape>();
    this.motions = new ArrayList<Command>();
    this.shapes = new ArrayList<Shape>();
  }

  /**
   * Generates the LinkedHashMap of all the commands mapped to the shapes they act upon. Throws an
   * IllegalArgumentException when null arguments are passed.
   *
   * @param cmds The commands for the animation.
   * @param s    The list of shapes in the animation.
   */
  @Override
  public void setAnimationMap(ArrayList<Command> cmds, ArrayList<Shape> s) {
    if (cmds != null && s != null) {
      LinkedHashMap<Command, Shape> map = new LinkedHashMap<Command, Shape>();
      for (int i = 0; i < cmds.size(); i++) {
        String name = cmds.get(i).getShapeName();
        for (int j = 0; j < s.size(); j++) {
          if (s.get(j).getName().equals(name)) {
            map.put(cmds.get(i), s.get(j));
          }
        }
      }
      this.commands = map;
      this.validateCommands();
    } else {
      throw new IllegalArgumentException("Inputs must not be null!");
    }
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

  public ArrayList<Command> getMotions() {
    return this.motions;
  }

  public ArrayList<Shape> getShapes() {
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
  public void moveShapes() {
    for (Command c : commands.keySet()) {
      if (this.time >= c.getStartTime() && this.time <= c.getEndTime()) {
        int startTime = c.getStartTime();
        int endTime = c.getEndTime();
        commands.get(c).setPosition(this.time, startTime, endTime, c);
        commands.get(c).setSize(this.time, startTime, endTime, c);
        commands.get(c).setColor(this.time, startTime, endTime, c);

      }
    }
  }

  @Override
  public void validateCommands() {
    for (Command c1 : commands.keySet()) {
      for (Command c2 : commands.keySet()) {
        if (!c1.equals(c2) && c1.getShapeName().equals(c2.getShapeName()) && overlapping(c1, c2)) {
          throw new IllegalArgumentException("Command times for "
                  + c1.getShapeName() + " are overlapping!");
        }
      }
    }
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

  /**
   * Determines whether the times are overlapping for the two Commands.
   * @param c1 The first command
   * @param c2 the second command
   * @return true if they are overlapping
   */
  private boolean overlapping(Command c1, Command c2) {
    System.out.println(c1.getStartTime());
    System.out.println(c2.getStartTime());
    System.out.println(c1.getEndTime());
    System.out.println(c2.getEndTime());
    return ((c1.getStartTime() <= c2.getStartTime() && c1.getEndTime() >= c2.getStartTime())
            || (c2.getStartTime() <= c1.getStartTime() && c2.getEndTime() >= c1.getEndTime()));
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder implements AnimationBuilder<AnimationModelImpl> {
    AnimationModelImpl model;

    @Override
    public AnimationModelImpl build() {
      this.model = new AnimationModelImpl();
      return this.model;
    }

    @Override
    public AnimationBuilder<AnimationModelImpl> setBounds(int x, int y, int width, int height) {
      /**
       * TODO:
       * need to decide what to do here. We can add fields to the model. I also added fields to the
       * view since we'll probably need them. I'm guessing x and y are used for the Visual view.
       */
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModelImpl> declareShape(String name, String type) {

      model.getShapes().add(new ShapeFactory().getShape(name, type));
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModelImpl> addMotion(String name, int t1, int x1, int y1,
                                                          int w1, int h1, int r1, int g1, int b1,
                                                          int t2, int x2, int y2, int w2, int h2,
                                                          int r2, int g2, int b2) {
      Shape shape = null;
      for (Shape s : model.getShapes()) {
        if (s.getName().equals(name)){
          shape = s;
        }
      }
      model.getMotions().add(new Command(shape, t1, new Position2D(x1, y1), w1, h1,
              new Color(r1, g1, b1), t2,  new Position2D(x2, y2), w2, h2, new Color(r2, g2, b2)));
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModelImpl> addKeyframe(String name, int t, int x, int y, int w,
                                                            int h, int r, int g, int b) {
      return null;
    }
  }
}