package cs3500.animator.view;

import java.util.List;

import cs3500.model.KeyFrame;
import cs3500.model.Shape;

/**
 * Represents a view for an animation. Supports setting the output of the animation view eg. a text
 * file or svg file to render.
 */
public interface IView {

  /**
   * Set the view's output type as an Appendable. Eg. FileWriter, System.out, StringBuilder.
   * @param output the view's output.
   */
  void setOutput(Appendable output);

  /**
   * Sets the speed of the animation in ticks/second.
   * @param num speed
   */
  void setSpeed(int num);

  /**
   * Set the SVG for the animation in the XML format.
   * @param frames list of keyframes.
   * @param canvas four bounds on the animation frame.
   */
  void displaySVG(List<KeyFrame> frames, List<Shape> shapes, int[] canvas);

  /**
   * Set the textual representation of the animation.
   * @param frames list of frames.
   * @param shapes list of shapes
   * @param canvas four bounds on the animation frame.
   */
  void displayTextualView(List<KeyFrame> frames, List<Shape> shapes, int[] canvas);

  /**
   * Visually display the animation using Swing library.
   * @param shapes list of shapes.
   */
  void displayVisual(List<Shape> shapes);

  /**
   * Get the output of the view.
   * @return the appendable output.
   */
  Appendable getOut();

  /**
   * Gives the speed for the visual view.
   * @return the speed
   */
  int getSpeed();

  /**
   * Get the fields descibing the shape to add.
   * @return a list of fields.
   */
  String[] getAddShapeFields();

  /**
   * Get the fields descibing the keyframe to add.
   * @return a list of fields.
   */
  String[] getAddKeyFrameFields();

  /**
   * Get the name of the shape to delete.
   * @return the name of the shape.
   */
  String getDeleteShapeField();

  /**
   * Get the shape name and the time of the keyframe to delete.
   * @return a list of the shape name and time.
   */
  String[] getDeleteKeyFrameFields();

  /**
   * Set the message to output to the user. For the Visual view only.
   * @param message message to show user.
   */
  void setMessage(String message);
}
