package cs3500.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

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
 * animation. The controller can act as an ActionListener for views using user interaction with
 * event commands. The controller can also be used to set the output for SVG and textual views.
 * The animation can be replayed for certain views.
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
    tick = 1;
    timer.restart();
  }

  @Override
  public void setPlaying() {
    isPlaying = true;
  }

  @Override
  public void setPaused() {
    isPlaying = false;
  }

  @Override
  public void increaseSpeed() {
    speed++;
    view.setSpeed(this.speed);
    view.setMessage("Speed set to: " + speed);
  }

  @Override
  public void decreaseSpeed() {
    if (speed > 1) {
      speed--;
      view.setSpeed(speed);
      view.setMessage("Speed set to: " + speed);
    } else {
      view.setMessage("Speed cannot be lowered past 1 tick per second");
    }
  }

  /**
   * Event handler for the editor view. Listens for events from the view such as
   * adding, deleting shapes, adding, deleting keyframes.
   * @param e ActionEvent
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Delete KeyFrame":
        String[] deleteKeyFrameFields = view.getDeleteKeyFrameFields();
        model.removeFrame(deleteKeyFrameFields[0], Integer.parseInt(deleteKeyFrameFields[1]));
        break;
      case "Add KeyFrame":
        String[] addKeyFrameFields = view.getAddKeyFrameFields();
        try {
          model.insertFrame(new KeyFrame(addKeyFrameFields[0],
                  Integer.parseInt(addKeyFrameFields[1]),
                  Integer.parseInt(addKeyFrameFields[2]),
                  Integer.parseInt(addKeyFrameFields[3]),
                  Integer.parseInt(addKeyFrameFields[4]),
                  Integer.parseInt(addKeyFrameFields[5]),
                  Integer.parseInt(addKeyFrameFields[6]),
                  Integer.parseInt(addKeyFrameFields[7]),
                  Integer.parseInt(addKeyFrameFields[8])));
        } catch (NumberFormatException ex) {
          view.setMessage("Illegal Argument for Add Keyframe.");
        }
        break;
      case "Delete Shape":
        String deleteShapeFields = view.getDeleteShapeField();
        try {
          model.removeShape(deleteShapeFields);
        } catch (IllegalArgumentException ex) {
          view.setMessage(ex.getMessage());
        }
        break;
      case "Add Shape":
        String[] addShapeFields = view.getAddShapeFields();
        String name = addShapeFields[1];
        String type = addShapeFields[0];
        int w = Integer.parseInt(addShapeFields[2]);
        int h = Integer.parseInt(addShapeFields[3]);
        double x = Double.parseDouble(addShapeFields[4]);
        double y = Double.parseDouble(addShapeFields[5]);
        try {
          Shape shape = new ShapeFactory().getShapeFull(type, name, w, h,
                  new Position2D(x, y),
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
        } catch (NumberFormatException ex) {
          view.setMessage("Illegal argument for Add Shape.");
        }
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
        this.increaseSpeed();
        break;
      case "Decrease Speed":
        this.decreaseSpeed();
        break;
      case "timer listener":
        List<Shape> shapesToRender = model.moveShapes();
        if (isPlaying) {
          model.setTime(tick++);
        }
        view.displayVisual(shapesToRender);
        break;
      default:
    }
  }
}