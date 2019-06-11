package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.util.List;

import model.Command;
import model.AShape;

public interface IView {

  void render(List<AShape> Shapes);


  void setShapesAndMotions(List<AShape> shapes, List<Command> motions);

}
