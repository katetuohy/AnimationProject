package cs3500.animator.view;


import java.lang.reflect.Array;
import java.util.LinkedHashMap;
import java.util.List;

import cs3500.model.Shape;
import cs3500.model.Command;

public interface IView {

  /**
   * Set the view's output type. Eg. file, System.out, StringBuilder.
   * @param output the view's output.
   */
  void setOutput(Appendable output);

  /**
   * Sets the speed of the animation.
   * @param num speed
   */
  void setSpeed(int num);

  /**
   * Get the SVG for the animation in the XML format.
   * @param motions list of motions.
   * @param canvas four bounds on the animation frame.
   */
  void displaySVG(List<Command> motions, int[] canvas);

  /**
   * Get the textual representation of the animation.
   * @param commands list of motions.
   * @param canvas four bounds on the animation frame.
   */
  void displayTextualView(LinkedHashMap<Command, Shape> commands, int[] canvas);

  /**
   * Visually display the animation using Swing.
   * @param shapes list of shapes.
   */
  void displayVisual(List<Shape> shapes);

  /**
   * Get the output of the view.
   * @return the appendable output.
   */
  Appendable getOut();

}
