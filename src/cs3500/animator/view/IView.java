package cs3500.animator.view;


import java.util.List;

import cs3500.model.AShape;
import cs3500.model.Command;

public interface IView {

  void display(List<AShape> Shapes);

  void setOutput(String output);

  void setSpeed(int num);

}
