package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import cs3500.model.AShape;
import cs3500.model.Command;

public class TextualAnimationView implements IView {

  private JTextField input;

  @Override
  public void render(List<AShape> Shapes) {

  }

  @Override
  public void display() {

  }

  @Override
  public void setShapesAndMotions(List<AShape> shapes, List<Command> motions) {

  }

  @java.lang.Override
  public void setSpeed(int num) {

  }

  @java.lang.Override
  public void setOutput(java.lang.String output) {
    //doesn't output
  }

  @java.lang.Override
  public void setInput(java.lang.String input) {
    this.input.setText(input);
  }
}
