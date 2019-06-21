package cs3500.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import cs3500.animator.view.EditorView;
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
public class Controller implements IController, ActionListener {
  Timer timer;
  int speed;
  private int tick = -1;
  AnimationModel model;
  IView view;

  /**
   * Constructs the controller and calls the display methods on the different views given the model
   * and their type.
   *
   * @param model the animation model
   * @param view  the animation view
   */
  public Controller(AnimationModel model, IView view) {
    this.model = model;
    this.view = view;
    this.speed = view.getSpeed();

    if (view instanceof TextualAnimationView)

      if (view instanceof SVGAnimationView) {
        view.displaySVG(model.getMotions(), model.getCanvas());
      }

    if (view instanceof TextualAnimationView) {
      view.displayTextualView(model.getFrames(), model.getShapes(), model.getCanvas());
    }
    if (view instanceof SVGAnimationView) {
      view.displaySVG(model.getFrames(), model.getShapes(), model.getCanvas());
    }
    // Visual animation.
    if (view instanceof VisualAnimationView) {
      timer = new Timer(speed, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          model.setTime(tick++);
          List<Shape> shapesToRender = model.moveShapes();
          view.displayVisual(shapesToRender);
        }
      });
    }

    // Editor visual animation.
    if (view instanceof EditorView) {
      timer = new Timer(speed, this);
    }
  }
    @Override
    public void playAnimation() {
      timer.start();
    }

    public void replay() {
      timer.restart();
    }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch(e.getActionCommand()) {
      model.setTime(tick++);
      List<Shape> shapesToRender = model.moveShapes();
      view.displayVisual(shapesToRender);
      case "Delete KeyFrame":
        String[] deleteKeyFrameFields = view.getDeleteKeyFrameFields();
        model.deleteKeyFrame();
        break;
      case "Add KeyFrame":
        String[] addKeyFrameFields = view.getAddKeyFrameFields();
        model.insertKeyFrame();
        break;
      case "Delete Shape":
        String deleteShapeFields = view.getDeleteShapeField();
        model.deleteShape();
        break;
      case "Add Shape":
        String[] addShapeFields = view.getAddShapeFields();
        model.addShape(addShapeFields[0]);
        break;
      case "Replay":
        replay();
        break;
      default:
    }
  }
}