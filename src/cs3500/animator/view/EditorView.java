package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.*;

import cs3500.model.Command;
import cs3500.model.Shape;

/**
 * Represents an Animation View where the user may add and delete shapes, add and delete keyframes
 * to any point in the animation. The user may also replay the animation by clicking the "replay"
 * button.
 */
public class EditorView extends JFrame implements IView {
  private final JPanel mainPanel;
  private final DrawingPanel animationPanel;
  private final EditorPanel editorPanel;
  private final JScrollPane scrollAnimationPane;
  private Appendable out;
  private int speed;

  public EditorView() {
    super();
    this.out = System.out;
    this.speed = 1000;

    setSize(800, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(200, 200);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

    editorPanel = new EditorPanel();
    editorPanel.setMinimumSize(new Dimension(500, 1000));
    editorPanel.setPreferredSize(new Dimension(500, 1000));
    editorPanel.setBackground(Color.WHITE);
    editorPanel.setLayout(new BoxLayout(editorPanel, BoxLayout.PAGE_AXIS));

    animationPanel = new DrawingPanel();
    animationPanel.setMinimumSize(new Dimension(500, 500));
    animationPanel.setPreferredSize(new Dimension(1000, 1000));
    animationPanel.setBackground(Color.WHITE);

    scrollAnimationPane = new JScrollPane(animationPanel);

    mainPanel.add(scrollAnimationPane);
    mainPanel.add(editorPanel);

    setVisible(true);
  }

  @Override
  public void setOutput(Appendable output) {
    this.out = output;
  }

  @Override
  public void displaySVG(List<Command> motions, int[] canvas) {
    throw new UnsupportedOperationException("displaySVG() method" +
            " not supported for Editor View.");
  }

  @Override
  public void displayTextualView(LinkedHashMap<Command, Shape> commands, int[] canvas) {
    throw new UnsupportedOperationException("displayTextualView() method" +
            " not supported for Editor View.");
  }

  @Override
  public void displayVisual(List<Shape> shapes) {
    /** TODO: finish this
     **/
    animationPanel.draw(shapes);
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
}
