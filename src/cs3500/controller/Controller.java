package cs3500.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import cs3500.animator.view.IView;
import cs3500.model.Shape;
import cs3500.model.AnimationModel;

public class Controller implements IController {
  Timer timer;
  private int tick = 1;
  AnimationModel model;
  IView view;

  public Controller(AnimationModel model, IView view){
    this.model = model;
    this.view = view;
    view.displayTextualView(model.getMap(), model.getCanvas());
    view.displaySVG(model.getMotions(), model.getCanvas());
    /**
     * TODO:
     * how to make this so that it only runs when we know its a visual view.
     */
    timer = new Timer(1500, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        model.setTime(tick++);
        List<Shape> shapesToRender = model.moveShapes();
        view.displayVisual(shapesToRender);
      }
    });
  }

  @Override
  public void playAnimation() {
    timer.start();
  }
}