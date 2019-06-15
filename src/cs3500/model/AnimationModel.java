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
   */
  void setAnimationMap();

  /**
   * Creates new Commands in between existing ones when there is a break in time.
   * @param cmds the existing commands
   * @return new list of commands
   */
  List<Command> fillInTimeGaps(List<Command> cmds);

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
   * gets the canvas size and start point as an int[].
   * @return the canvas array.
   */
  int[] getCanvas();

  /**
   * gets the canvas size and start point as a string.
   * @return the canvas string
   */
  String getCanvasString();

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
   * Ensure that motions for a particular object are not overlapping in time frames.
   */
  void validateMotionsNotOverlapping();

  /**
   * Get the list of shapes for the animation.
   *
   * @return list of shapes
   */
  List<Shape> getShapes();

  /**
   * Get the list of motions for the animation.
   *
   * @return list of motions
   */
  List<Command> getMotions();

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

  /**
   * Set the canvas dimensions of the model.
   * @param x
   * @param y
   * @param width
   * @param height
   */
  void setCanvas(int x, int y, int width, int height);
}
