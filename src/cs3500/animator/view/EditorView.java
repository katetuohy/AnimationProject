package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

import cs3500.model.KeyFrame;
import cs3500.model.Shape;

/**
 * Represents an Animation View where the user may add and delete shapes, add and delete keyframes
 * to any point in the animation. The user may also replay the animation by clicking the "replay"
 * button.
 */
public class EditorView extends JFrame implements IView {
  private final JPanel mainPanel;
  private DrawingPanel animationPanel;
  private final EditorPanel editorPanel;
  private final JScrollPane scrollAnimationPane;
  private Appendable out;
  private int speed;

  public EditorView() {
    super();
    this.out = System.out;
    this.speed = 1;

    setSize(800, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(200, 200);

    mainPanel = new JPanel();
    mainPanel.setMinimumSize(new Dimension(1500, 1500));
    mainPanel.setPreferredSize(new Dimension(4000, 2000));
    mainPanel.setLayout(new FlowLayout());

    editorPanel = new EditorPanel();
    editorPanel.setMinimumSize(new Dimension(500, 500));
    editorPanel.setPreferredSize(new Dimension(1000, 1000));
    editorPanel.setBackground(Color.WHITE);

    animationPanel = new DrawingPanel();
    animationPanel.setMinimumSize(new Dimension(500, 500));
    animationPanel.setPreferredSize(new Dimension(800, 800));
    animationPanel.setBackground(Color.WHITE);

    scrollAnimationPane = new JScrollPane(animationPanel);

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
    /** TODO: finish this
     **/
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

  public String[] getAddShapeFields() {
    return editorPanel.getAddShapeFields();
  }

  public String[] getAddKeyFrameFields() {
    return editorPanel.getAddKeyFrameFields();
  }

  public String getDeleteShapeField() {
    return editorPanel.getDeleteShapeField();
  }

  public String[] getDeleteKeyFrameFields() {
    return editorPanel.getDeleteKeyFrameFields();
  }

  @Override
  public void setMessage(String message) {
    editorPanel.setMessage(message);
  }
}
