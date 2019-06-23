package cs3500.animator.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cs3500.model.KeyFrame;
import cs3500.model.Shape;

/**
 * Represents an Animation View where the user may add and delete shapes, add and delete keyframes
 * to any point in the animation. The user may also replay the animation by clicking the "replay"
 * button. The user can also increase, decrease speed, pause and play animation from a starting
 * point. The view will display helpful feedback to the user if say, they give bad inputs.
 */
public class EditorView extends JFrame implements IView {
  private final EditorPanel editorPanel;
  private DrawingPanel animationPanel;
  private Appendable out;
  private int speed;

  /**
   * Default constructor. Sets the three panels and adds them to the Frame.
   */
  public EditorView() {
    super();
    this.out = System.out;
    this.speed = 1;

    setSize(1500, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(0, 0);

    JPanel mainPanel = new JPanel();
    mainPanel.setMinimumSize(new Dimension(1500, 800));
    mainPanel.setPreferredSize(new Dimension(1500, 2000));
    mainPanel.setLayout(new FlowLayout());

    editorPanel = new EditorPanel();
    editorPanel.setMinimumSize(new Dimension(500, 500));
    editorPanel.setPreferredSize(new Dimension(400, 800));
    editorPanel.setBackground(Color.WHITE);

    animationPanel = new DrawingPanel();
    animationPanel.setMinimumSize(new Dimension(500, 500));
    animationPanel.setPreferredSize(new Dimension(800, 800));
    animationPanel.setBackground(Color.WHITE);

    JScrollPane scrollAnimationPane = new JScrollPane(animationPanel);

    mainPanel.add(scrollAnimationPane);
    mainPanel.add(editorPanel);

    this.add(mainPanel);
    this.setVisible(true);
  }

  @Override
  public void setOutput(Appendable output) {
    this.out = output;
  }

  @Override
  public void displaySVG(List<KeyFrame> frames, List<Shape> shapes, int[] canvas) {
    throw new UnsupportedOperationException("displaySVG() method" +
            " not supported for Editor View.");
  }

  @Override
  public void displayTextualView(List<KeyFrame> frames, List<Shape> shapes, int[] canvas) {
    throw new UnsupportedOperationException("displayTextualView() method" +
            " not supported for Editor View.");
  }

  @Override
  public void displayVisual(List<Shape> shapes) {
    animationPanel.draw(shapes);
    repaint();
  }

  @Override
  public Appendable getOut() {
    return this.out;
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public void setSpeed(int num) {
    this.speed = num;
  }

  /**
   * Set the listener for the buttons.
   *
   * @param listener the action listener.
   */
  public void setListener(ActionListener listener) {
    editorPanel.setListener(listener);
  }

  @Override
  public String[] getAddShapeFields() {
    return editorPanel.getAddShapeFields();
  }

  @Override
  public String[] getAddKeyFrameFields() {
    return editorPanel.getAddKeyFrameFields();
  }

  @Override
  public String getDeleteShapeField() {
    return editorPanel.getDeleteShapeField();
  }

  @Override
  public String[] getDeleteKeyFrameFields() {
    return editorPanel.getDeleteKeyFrameFields();
  }

  @Override
  public void setMessage(String message) {
    editorPanel.setMessage(message);
  }
}
