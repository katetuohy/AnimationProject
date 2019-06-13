package cs3500.controller;

import cs3500.animator.view.IView;
import cs3500.model.AnimationModel;

public class Controller {

  private AnimationModel model;
  private IView view;


  public Controller(AnimationModel model, IView view) {
    this.model = model;
    this.view = view;
    view.display();
  }

}
