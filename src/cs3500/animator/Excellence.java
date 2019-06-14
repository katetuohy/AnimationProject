package cs3500.animator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewFactory;
import cs3500.controller.Controller;
import cs3500.controller.IController;
import cs3500.model.AnimationModel;
import cs3500.model.AnimationModelImpl;

public final class Excellence {

  public static void main(String[] args) throws IOException {
    boolean hasI = false;
    boolean hasV = false;
    String in = "";
    String view = "";
    String out = "System.out";
    int speed = -1;

    if (Arrays.asList(args).contains("-view")) {
      int ind = Arrays.asList(args).indexOf("-view") + 1;
      view = Arrays.asList(args).get(ind++);
      hasV = true;
    }

    if (Arrays.asList(args).contains("-in")) {
      int ind = Arrays.asList(args).indexOf("-in") + 1;
      in = Arrays.asList(args).get(ind++);
      hasI = true;
    }
    if (!(hasV && hasI)) {
      throw new IllegalArgumentException("Must input an in and a view type");
    }

    ViewFactory factory = new ViewFactory();
    IView v = factory.getView(view);
    AnimationBuilder<AnimationModelImpl> builder = AnimationModelImpl.builder();
    Readable rn = null;
    try {
      rn = new FileReader(in);
    } catch (FileNotFoundException e) {
      e.getMessage();
    }
    AnimationReader.parseFile(rn, builder);
    AnimationModel model = builder.build();

    // Set the output.
    if (Arrays.asList(args).contains("-out")) {
      int ind = Arrays.asList(args).indexOf("-out") + 1;
      out = Arrays.asList(args).get(ind++);
      FileWriter f = new FileWriter(out);
      v.setOutput(f);
    } else {
      v.setOutput(System.out);
    }

    if (Arrays.asList(args).contains("-speed")) {
      int speedIndex = Arrays.asList(args).indexOf("-speed");
      speed = Integer.parseInt(Arrays.asList(args).get(speedIndex++));
      v.setSpeed(speed);
    } else {
      v.setSpeed(1);
    }

    IController controller = new Controller(model, v);
    controller.playAnimation();
  }
}

