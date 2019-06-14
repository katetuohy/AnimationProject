package cs3500.controller;

import java.io.FileWriter;
import java.io.IOException;

import cs3500.animator.view.IView;
import cs3500.model.AnimationModel;

public class Controller implements IController{

  private AnimationModel model;
  private IView view;
  private Appendable ap;

  public Controller(AnimationModel model, IView view) {
    this.model = model;
    this.view = view;
    try {
      this.ap = new FileWriter("output.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void playAnimation() {
    try {
      view.displayTextualView(model.getMap());
    } catch (UnsupportedOperationException e) {
      view.display(model.getShapes());
    }
  }
}
