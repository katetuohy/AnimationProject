package cs3500.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import cs3500.animator.util.AnimationBuilder;

/**
 * Represents the model for an animation. The animation saves the current time and map of commands
 * to the shapes they act on. A method is used to update all of the animation shapes for a given
 * time.
 */
public final class AnimationModelImpl implements AnimationModel {

  int time;

  private LinkedHashMap<Command, AShape> commands = new LinkedHashMap<Command,
          AShape>();

  /**
   * Construct an animation model at time = 0.
   */
  public AnimationModelImpl() {
    this.time = 0;
  }

  /**
   * Generates the LinkedHashMap of all the commands mapped to the shapes they act upon. Throws an
   * IllegalArgumentException when null arguments are passed.
   *
   * @param cmds The commands for the animation.
   * @param s    The list of shapes in the animation.
   */
  @Override
  public void setAnimationMap(ArrayList<Command> cmds, ArrayList<AShape> s) {
    if (cmds != null && s != null) {
      LinkedHashMap<Command, AShape> map = new LinkedHashMap<Command, AShape>();
      // map.put(command, shape);
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
  public LinkedHashMap<Command, AShape> getMap() {
    return this.commands;
  }

  @Override
  public String printCommands() {
    String toReturn = "";
    ArrayList<AShape> usedShapes = new ArrayList<AShape>();
    for (AShape s : commands.values()) {
      if (!usedShapes.contains(s)) {
        toReturn += this.printShapeCommands(s) + "\n";
        usedShapes.add(s);
      }
    }
    return toReturn;
  }

  private String printShapeCommands(AShape s) {
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

  public static final class Builder implements AnimationBuilder<AnimationModelImpl> {
    @Override
    public AnimationModelImpl build() {
      return null;
    }

    @Override
    public AnimationBuilder<AnimationModelImpl> setBounds(int x, int y, int width, int height) {
      return null;
    }

    @Override
    public AnimationBuilder<AnimationModelImpl> declareShape(String name, String type) {
      return null;
    }

    @Override
    public AnimationBuilder<AnimationModelImpl> addMotion(String name, int t1, int x1, int y1,
                                                          int w1, int h1, int r1, int g1, int b1,
                                                          int t2, int x2, int y2, int w2, int h2,
                                                          int r2, int g2, int b2) {
      return null;
    }

    @Override
    public AnimationBuilder<AnimationModelImpl> addKeyframe(String name, int t, int x, int y, int w,
                                                            int h, int r, int g, int b) {
      return null;
    }
    // FILL IN HERE
  }
}