package cs3500.animator.view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import cs3500.model.Shape;

public class VisualAnimationView extends JFrame implements IView {
  private DrawingPanel panel;
  JScrollPane scrollPane;
  private int speed;
  Appendable out;

  public VisualAnimationView() {
    super();
    panel = new DrawingPanel();
    panel.setMinimumSize( new Dimension(500,500));
    panel.setPreferredSize( new Dimension(2000,2000));
    panel.setBackground(Color.yellow);

    scrollPane = new JScrollPane(panel); // DECORATOR WHOO WHOO.

    setSize(800,800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(200,200);

    add(scrollPane);

    setVisible(true);
  }

  @Override
  public void display(List<Shape> shapes) {
    panel.draw(shapes);
  }

  @Override
  public void setOutput(String output) {

  }

  @Override
  public void setSpeed(int num) {
    this.speed = speed;
  }
}
