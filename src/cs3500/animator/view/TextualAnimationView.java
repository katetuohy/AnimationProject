package cs3500.animator.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import cs3500.model.Command;
import cs3500.model.Shape;

public class TextualAnimationView implements IView {

  private Appendable out;

  public TextualAnimationView() {
    this.out = System.out;
  }

  public void displayTextualView(LinkedHashMap<Command, Shape> commands, int[] canvas) {
    ArrayList<Shape> usedShapes = new ArrayList<Shape>();
    tryAppend(out, "canvas " + canvas[0] + " " + canvas[1] + " " + canvas[2]
            + " " + canvas[3] + "\n");
    for (Shape s : commands.values()) {
      if (!usedShapes.contains(s)) {
        tryAppend(out, this.printShapeCommands(s, commands) + "\n");
        usedShapes.add(s);
      }
    }
  }

  @Override
  public void displaySVG(List<Command> motions, int[] canvas) {
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
  public void setSpeed(int num) {
    // Do nothing.
  }

  private String printShapeCommands(Shape s, LinkedHashMap<Command, Shape> commands) {
    String toReturn = "shape " + s.getName() + "\n";
    for (Command c : commands.keySet()) {
      if (c.getShapeName().equals(s.getName())) {
        toReturn += c.printCommand() + "\n";
      }
    }
    return toReturn;
  }

  @Override
  public void setOutput(Appendable output) {
    this.out = output;
  }

  // Try to append s2 to readable s1
  private void tryAppend(Appendable s1, String s2) {
    try {
      s1.append(s2);
    } catch (IOException e) {
      e.getMessage();
    }
  }
}