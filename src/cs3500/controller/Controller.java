package cs3500.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import cs3500.animator.view.IView;
import cs3500.model.AShape;
import cs3500.model.AnimationModel;

public class Controller implements IController {
  Timer timer;
  int counter;
  private int tick = 1;
  AnimationModel model;
  IView view;

  public Controller(AnimationModel model, IView view){
    this.model = model;
    this.view = view;
    timer = new Timer(1500, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        model.setTime(tick++);
        List<Shape> shapesToRender = model.moveShapes();
        view.display(shapesToRender);
      }
    });
  }

  @Override
  public void run() {
    timer.start();
  }
}