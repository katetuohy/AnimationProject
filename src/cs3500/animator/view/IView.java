package cs3500.animator.view;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import cs3500.model.AShape;
import cs3500.model.Command;
import cs3500.model.Shape;

public interface IView {

  /**
   * Display method for textual view.
   * @param commands
   */
  void displayTextualView(LinkedHashMap<Command, Shape> commands);

  void display(ArrayList<Shape> shapes);

  void setShapesAndMotions(List<Shape> shapes, List<Command> motions);

  void setOutput(Appendable output);

  void setSpeed(int num);

}
