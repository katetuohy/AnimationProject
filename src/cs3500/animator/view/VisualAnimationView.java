package cs3500.animator.view;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.*;

import cs3500.model.Command;
import cs3500.model.Shape;

public class VisualAnimationView extends JFrame implements IView {
  private DrawingPanel panel;
  JScrollPane scrollPane;
  private int speed;
  private Appendable out;

  public VisualAnimationView() {
    super();
    this.out = System.out;
    panel = new DrawingPanel();
    panel.setMinimumSize( new Dimension(500,500));
    panel.setPreferredSize( new Dimension(2000,2000));
    panel.setBackground(Color.yellow);

    scrollPane = new JScrollPane(panel); // DECORATOR WHOO WHOO.

    setSize(800,800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(200,200);

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
  public void displayTextualView(LinkedHashMap<Command, Shape> commands, int[] canvas) {
    /**
     * TODO:
     * throw new UnsupportedOperationException()?
     */
  }

  @Override
  public void displaySVG(List<Command> motions, int[] canvas) {
    /**
     * TODO:
     * throw new UnsupportedOperationException()?
     */
  }
}
