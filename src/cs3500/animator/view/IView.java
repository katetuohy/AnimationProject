package cs3500.animator.view;


import java.util.LinkedHashMap;
import java.util.List;

import cs3500.model.Shape;
import cs3500.model.Command;

public interface IView {

  void setOutput(Appendable output);

  void setSpeed(int num);

  void displayTextualView(LinkedHashMap<Command, Shape> commands, String canvas);

  void displaySVG(List<Command> motions);

  void displayVisual(List<Shape> shapes);

  Appendable getOut();

}
