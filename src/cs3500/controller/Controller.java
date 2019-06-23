package cs3500.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import cs3500.animator.view.EditorView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGAnimationView;
import cs3500.animator.view.TextualAnimationView;
import cs3500.animator.view.VisualAnimationView;
import cs3500.model.AnimationModel;
import cs3500.model.KeyFrame;
import cs3500.model.Position2D;
import cs3500.model.Shape;
import cs3500.model.ShapeFactory;

/**
 * The controller for the animation, acting in between the model and the view to control the
 * animation.
 */
public class Controller implements IController, ActionListener {
  private Timer timer;
  private int speed;
  private AnimationModel model;
  private IView view;
  private int tick = 1;
  private boolean isPlaying;

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
    this.speed = 1000 / view.getSpeed();
    this.isPlaying = true;

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
      ((EditorView) view).setListener(this);
      timer = new Timer(speed, this);
      timer.setActionCommand("timer listener");
    }
  }

  @Override
  public void playAnimation() {
    timer.start();
  }

  @Override
  public void replay() {
    tick = 0;
    timer.restart();
  }

  private void setPlaying() {
    isPlaying = true;
  }

  public void setPaused() {
    isPlaying = false;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Delete KeyFrame":
        String[] deleteKeyFrameFields = view.getDeleteKeyFrameFields();
        model.removeFrame(deleteKeyFrameFields[0], Integer.parseInt(deleteKeyFrameFields[1]));
        break;
      case "Add KeyFrame":
        String[] addKeyFrameFields = view.getAddKeyFrameFields();
        model.insertFrame(new KeyFrame(addKeyFrameFields[0],
                Integer.parseInt(addKeyFrameFields[1]),
                Integer.parseInt(addKeyFrameFields[2]),
                Integer.parseInt(addKeyFrameFields[3]),
                Integer.parseInt(addKeyFrameFields[4]),
                Integer.parseInt(addKeyFrameFields[5]),
                Integer.parseInt(addKeyFrameFields[6]),
                Integer.parseInt(addKeyFrameFields[7]),
                Integer.parseInt(addKeyFrameFields[8])));
        break;
      case "Delete Shape":
        String deleteShapeFields = view.getDeleteShapeField();
        model.removeShape(deleteShapeFields);
        break;
      case "Add Shape":
        String[] addShapeFields = view.getAddShapeFields();
        Shape shape = new ShapeFactory().getShapeFull(addShapeFields[1], addShapeFields[0],
                        Integer.parseInt(addShapeFields[2]),
                        Integer.parseInt(addShapeFields[3]),
                        new Position2D(Double.parseDouble(addShapeFields[4]),
                                Double.parseDouble(addShapeFields[5])),
                        new Color(Integer.parseInt(addShapeFields[6]),
                                Integer.parseInt(addShapeFields[7]),
                                Integer.parseInt(addShapeFields[8])));
        model.addShape(shape);
        model.addFrame(new KeyFrame(shape.getName(), 1,
                shape.getWidth(), shape.getHeight(),
                (int) shape.getPosition().getX(),
                (int) shape.getPosition().getY(),
                shape.getColor().getRed(),
                shape.getColor().getGreen(),
                shape.getColor().getBlue()));
        break;
      case "Replay":
        replay();
        break;
      case "Play":
        setPlaying();
        break;
      case "Pause":
        setPaused();
        break;
      case "Increase Speed":
        speed++;
        view.setSpeed(this.speed);
        view.setMessage("Speed set to: " + speed);

        break;
      case "Decrease Speed":
        if (speed > 1) {
          speed--;
          view.setSpeed(this.speed);
          view.setMessage("Speed set to: " + speed);
        } else {
          view.setMessage("Speed cannot be lowered past 1 tick per second");
        }

        break;
      case "timer listener":
        List<Shape> shapesToRender = model.moveShapes();
        if (isPlaying) {
          model.setTime(tick++);
          //System.out.println(model.getTime());
        }
        view.displayVisual(shapesToRender);
        break;
      default:
    }
  }
}