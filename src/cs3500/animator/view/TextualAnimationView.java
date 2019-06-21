package cs3500.animator.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.model.KeyFrame;
import cs3500.model.Shape;

/**
 * Represents a textual view for the animation for the shapes. All of the shapes and their commands
 * are displayed at once in a way where user can scroll through.
 */
public class TextualAnimationView implements IView {

  private Appendable out;

  public TextualAnimationView() {
    this.out = System.out;
  }

  /**
   * Displays the text view by appending all of the shapes and their commands to the output.
   * @param frames list of frames.
   * @param shapes list of shapes.
   * @param canvas four bounds on the animation frame.
   */
  public void displayTextualView(List<KeyFrame> frames, List<Shape> shapes,
                                 int[] canvas) {
    ArrayList<Shape> usedShapes = new ArrayList<Shape>();
    tryAppend(out, "canvas " + canvas[0] + " " + canvas[1] + " " + canvas[2]
            + " " + canvas[3] + "\n");
    for (Shape s : shapes) {
      if (!usedShapes.contains(s)) {
        tryAppend(out, this.printShapeCommands(s, frames) + "\n");
        usedShapes.add(s);
      }
    }
  }

  @Override
  public void displaySVG(List<KeyFrame> frames, List<Shape> shapes, int[] canvas) {
    throw new UnsupportedOperationException("displaySVG() not supported for Textual View");
  }

  @Override
  public void displayVisual(List<Shape> shapes) {
    throw new UnsupportedOperationException("displayVisual() not supported for Textual View");
  }

  @Override
  public Appendable getOut() {
    return this.out;
  }

  @Override
  public int getSpeed() {
    return 1000;
  }

  @Override
  public String[] getAddShapeFields() {
    throw new UnsupportedOperationException("Can't add shape in textual view.");
  }

  @Override
  public String[] getAddKeyFrameFields() {
    throw new UnsupportedOperationException("Can't add keyframe in textual view.");

  }

  @Override
  public String getDeleteShapeField() {
    throw new UnsupportedOperationException("Can't delete shape in textual view.");

  }

  @Override
  public String[] getDeleteKeyFrameFields() {
    throw new UnsupportedOperationException("Can't delete keyframe in textual view.");

  }

  /**
   * Returns the motions in string form for the shape.
   * @param s      the shape for which the commands should be printed
   * @param frames the list of frames
   * @return the next string representing the commands for the given shape
   */
  private String printShapeCommands(Shape s, List<KeyFrame> frames) {
    String toReturn = "shape " + s.getName() + "\n";
    for (int i = 0; i < frames.size() - 1; i++) {
      if (frames.get(i).getName().equals(s.getName())
              && frames.get(i + 1).getName().equals(s.getName())) {
        toReturn += printSingleMotion(frames.get(i), frames.get(i + 1)) + "\n";
      }
    }
    return toReturn;
  }

  public String printSingleMotion(KeyFrame first, KeyFrame second) {
    return "motion " + first.getName() + " " + first.getTime() + " " + first.getX()
            + " " + first.getY() + " " + first.getW() + " " + first.getH() + " "
            + first.getR() + " " + first.getG() + " " + first.getB() + "       " + second.getTime()
            + " " + second.getX() + " " + second.getY() + " " + second.getW() + " " + second.getH()
            + " " + second.getR() + " " + second.getG() + " " + second.getB();
  }

  @Override
  public void setSpeed(int num) {
    // Do nothing.
  }

  @Override
  public void setOutput(Appendable output) {
    this.out = output;
  }

  /**
   * Try to append the String to the appendable.
   * @param s1 the Appendable
   * @param s2 the string
   */
  private void tryAppend(Appendable s1, String s2) {
    try {
      s1.append(s2);
    } catch (IOException e) {
      e.getMessage();
    }
  }
}