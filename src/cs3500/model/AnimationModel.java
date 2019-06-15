package cs3500.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.print.Doc;

import cs3500.animator.util.AnimationBuilder;

/**
 * Represents the model for an animation.
 */
public interface AnimationModel {

  /**
   * Initialize the commands HashMap.
   *
   * @param cmds The commands for the animation.
   * @param s    The list of shapes in the animation.
   */
  void setAnimationMap(ArrayList<Command> cmds, ArrayList<Shape> s);

  /**
   * Creates new Commands in between existing ones when there is a break in time.
   * @param cmds the existing commands
   * @return new list of commands
   */
  ArrayList<Command> fillIn(ArrayList<Command> cmds);

  /**
   * Return the list of commands sorted by shape for the entire animation.
   *
   * @return The list of commands in string format.
   */
  String printCommands();

  /**
   * Update the states of all the shapes in the animation.
   */
  List<Shape> moveShapes();

  /**
   * Getter for the time field.
   *
   * @return the current time.
   */
  int getTime();

  /**
   * gets the canvas size and location.
   * @return the canvas string
   */
  String getCanvas();

  /**
   * Setter for the time field.
   *
   * @param currTime the current time.
   */
  void setTime(int currTime);

  /**
   * Getter for the HashMap commands field.
   *
   * @return the commands HashMap.
   */
  LinkedHashMap<Command, Shape> getMap();

  /**
   * Ensure that commands for a particular object are not overlapping in time frames.
   */
  void validateCommands();

  /**
   * Get the list of shapes for the animation.
   *
   * @return list of shapes
   */
  ArrayList<Shape> getShapes();

  /**
   * Get the list of motions for the animation.
   *
   * @return list of motions
   */
  ArrayList<Command> getMotions();

  /**
   * Get the maximum width of the frame based on the shapes' positions.
   * @return width of the frame.
   */
  int getMaxWidth();

  /**
   * Get the maximum height of the frame based on the shapes' positions and dimensions.
   * @return height of the frame.
   */
  int getMaxHeight();

  /**
   * Add Shape to the list of shapes.
   */
  void addShape(Shape s);

  /**
   * Add Command to the list of commands.
   */
  void addMotion(Command c);
}
