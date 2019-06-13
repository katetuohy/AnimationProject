package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import cs3500.model.AShape;
import cs3500.model.Command;

public class VisualAnimationView extends JFrame implements IView {
  private JLabel display;
  private int speed;

  public VisualAnimationView() {
    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setMinimumSize(new Dimension(300,300));
    setMaximumSize(new Dimension(1000, 1000));

    this.setLayout(new FlowLayout());

    display = new JLabel("To be displayed");
    this.add(display);

    pack();
  }

  @Override
  public void render(List<AShape> Shapes) {

  }

  @Override
  public void display() {
    setVisible(true);
  }

  @Override
  public void setShapesAndMotions(List<AShape> shapes, List<Command> motions) {

  }

  @Override
  public void setOutput(String output) {

  }

  @Override
  public void setSpeed(int num) {
    this.speed = speed;
  }
}
