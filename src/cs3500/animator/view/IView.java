package cs3500.animator.view;


import java.util.List;

import cs3500.model.AShape;
import cs3500.model.Command;

public interface IView {

  void render(List<AShape> Shapes);

  void display();

  void setShapesAndMotions(List<AShape> shapes, List<Command> motions);

  void setInput(String input);

  void setOutput(String output);

  void setSpeed(int num);

}
