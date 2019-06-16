package cs3500.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import cs3500.animator.view.IView;
import cs3500.animator.view.SVGAnimationView;
import cs3500.animator.view.TextualAnimationView;
import cs3500.animator.view.VisualAnimationView;
import cs3500.model.Shape;
import cs3500.model.AnimationModel;

/**
 * The controller for the animation, acting in between the model and the view to control the
 * animation.
 */
public class Controller implements IController {
  Timer timer;
  int speed;
  private int tick = -1;
  AnimationModel model;
  IView view;

  /**
   * Constructs the controller and calls the display methods on the different views given the
   * model and their type.
   * @param model the animation model
   * @param view  the animation view
   */
  public Controller(AnimationModel model, IView view) {
    this.model = model;
    this.view = view;
    this.speed = view.getSpeed();

    if (view instanceof TextualAnimationView) {
      view.displayTextualView(model.getMap(), model.getCanvas());
    }

    if (view instanceof SVGAnimationView) {
      view.displaySVG(model.getMotions(), model.getCanvas());
    }

    // Visual animation.
    if (view instanceof VisualAnimationView) {
      timer = new Timer(500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          model.setTime(tick++);
          List<Shape> shapesToRender = model.moveShapes();
          view.displayVisual(shapesToRender);
        }
      });
    }

  }

  @Override
  public void playAnimation() {
    timer.start();
  }
}