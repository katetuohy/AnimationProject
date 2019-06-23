package cs3500.animator.view;

import java.awt.Dimension;
import java.awt.Color;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs3500.model.KeyFrame;
import cs3500.model.Shape;

/**
 * Represents the animation in a visual manner, returning a on screen movement.
 */
public class VisualAnimationView extends JFrame implements IView {

  private DrawingPanel panel;
  JScrollPane scrollPane;
  private int speed;
  private Appendable out;

  /**
   * Creates the Visual Animation setup to later display the animation given the input.
   */
  public VisualAnimationView() {
    super();
    this.out = System.out;
    panel = new DrawingPanel();
    panel.setMinimumSize(new Dimension(500, 500));
    panel.setPreferredSize(new Dimension(2000, 2000));
    panel.setBackground(Color.WHITE);

    scrollPane = new JScrollPane(panel);

    setSize(800, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(200, 200);

    add(scrollPane);

    this.out = System.out;

    setVisible(true);
    this.speed = 1;
  }

  @Override
  public void displayVisual(List<Shape> shapes) {
    panel.draw(shapes);
  }

  @Override
  public Appendable getOut() {
    return this.out;
  }

  @Override
  public void setOutput(Appendable output) {
    this.out = output;
  }

  @Override
  public void setSpeed(int num) {
    this.speed = num;
  }


  @Override
  public void displayTextualView(List<KeyFrame> frames, List<Shape> shapes,
                                 int[] canvas) {
    throw new UnsupportedOperationException("displayTextualView() not supported for Visual View");
  }

  @Override
  public void displaySVG(List<KeyFrame> frames, List<Shape> shapes, int[] canvas) {
    throw new UnsupportedOperationException("displaySVG() not supported for Visual View");
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public String[] getAddShapeFields() {
    throw new UnsupportedOperationException("Can't add shape for Visual View");
  }

  @Override
  public String[] getAddKeyFrameFields() {
    throw new UnsupportedOperationException("Can't add KeyFrame for Visual View");
  }

  @Override
  public String getDeleteShapeField() {
    throw new UnsupportedOperationException("Can't delete shape for Visual View");
  }

  @Override
  public String[] getDeleteKeyFrameFields() {
    throw new UnsupportedOperationException("Can't delete KeyFrame for Visual View");
  }

  @Override
  public void setMessage(String message) {
    throw new UnsupportedOperationException();
  }
}
