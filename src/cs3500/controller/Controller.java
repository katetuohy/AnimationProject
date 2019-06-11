package cs3500.controller;

import java.io.IOException;

import cs3500.model.AnimationModel;

public class Controller {

  private final Readable in;
  private final Appendable out;

  public Controller(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Input or output is null");
    }
    this.in = in;
    this.out = out;
  }

  public void playAnimation(AnimationModel model) {

  }

  @Override
  public void appendOut(String s) {
    try {
      this.out.append(s);
    } catch (IOException e) {
      throw new IllegalStateException("Can't append");
    }
  }
}
