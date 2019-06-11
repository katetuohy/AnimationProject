package cs3500.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
    if (model == null) {
      throw new IllegalArgumentException("Model given is null");
    }
    Scanner scan = new Scanner(this.in);
    ArrayList<Integer> nums = new ArrayList<Integer>();
    while (scan.hasNext()) {
      String s = scan.next();
    }
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
