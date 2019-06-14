package cs3500.animator.view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import cs3500.model.AShape;
import cs3500.model.Command;
import cs3500.model.Shape;

public class SVGAnimationView implements IView {
  Appendable out;
  int speed;

  @Override
  public void displayTextualView(LinkedHashMap<Command, Shape> commands) {
    throw new UnsupportedOperationException("Only for the textual view.");
  }

  @Override
  public void display(List<Shape> shapes) {

  }

  @Override
  public void setShapesAndMotions(List<Shape> shapes, List<Command> motions) {

  }

  @Override
  public void setOutput(Appendable output) {
    this.out = output;
  }

  @Override
  public void setSpeed(int num) {
    this.speed = speed;
  }
}
