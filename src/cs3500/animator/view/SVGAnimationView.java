package cs3500.animator.view;

import java.util.LinkedHashMap;
import java.util.List;

import cs3500.model.AShape;
import cs3500.model.Command;
import cs3500.model.Shape;

public class SVGAnimationView implements IView {

  private Appendable out;

  public SVGAnimationView() {
    this.out = System.out;
  }

  @Override
  public void display(List<Shape> Shapes) {

  }

  @Override
  public void setOutput(Appendable output) {
    this.out = output;
  }

  @Override
  public void setSpeed(int num) {

  }

  @Override
  public void displayTextualView(LinkedHashMap<Command, Shape> commands) {
    throw new IllegalArgumentException("Can't print text for SVG");
  }
}
