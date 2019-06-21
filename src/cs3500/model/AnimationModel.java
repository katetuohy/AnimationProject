package cs3500.model;

import java.util.List;

/**
 * The interface that represents the model for an animation.
 */
public interface AnimationModel {

  /**
   * Initializes the Frames and ensures everything's in order
   */
  void setAnimation();

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
   * Setter for the time field.
   * @param currTime the current time.
   */
  void setTime(int currTime);

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
   * Gives the list of frames for the animation.
   * @return the frames.
   */
  List<KeyFrame> getFrames();

  /**
   * Add Shape to the list of shapes.
   * @param s the shape to add
   */
  void addShape(Shape s);

  /**
   * Adds the KeyFrame to the list of Key Frames.
   * @param k the Key frame to add
   */
  void addFrame(KeyFrame k);

  /**
   * Set the canvas dimensions of the model.
   * @param x      The X position for the center of the canvas
   * @param y      The Y position for the center of the canvas
   * @param width  The Width of the canvas
   * @param height The Height of the canvas
   */
  void setCanvas(int x, int y, int width, int height);

  /**
   * Inserts the given key into the list of frames at the appropriate location.
   * @param key the frame to insert.
   */
  void insertFrame(KeyFrame key);

  /**
   * Removes the given frame from the list of frames.
   * @param key the frame to remove
   */
  void removeFrame(KeyFrame key);
}
