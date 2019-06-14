package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.*;

import cs3500.model.Command;
import cs3500.model.Shape;

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
  public void displayTextualView(LinkedHashMap<Command, Shape> commands) {
    throw new UnsupportedOperationException("Cannot display textual view.");
  }

  @Override
  public void display(ArrayList<Shape> shapes) {
    setVisible(true);
  }

  @Override
  public void setShapesAndMotions(List<Shape> shapes, List<Command> motions) {
  //so can commit
  }

  @Override
  public void setOutput(Appendable output) {

  }

  @Override
  public void setSpeed(int num) {
    this.speed = speed;
  }
}
