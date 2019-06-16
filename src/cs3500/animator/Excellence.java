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
import cs3500.animator.view.VisualAnimationView;
import cs3500.controller.Controller;
import cs3500.controller.IController;
import cs3500.model.AnimationModel;
import cs3500.model.AnimationModelImpl;

/**
 * The main class for the animation. Takes in the arguments that run the animation, which are
 * type of animation, the input file, the type of output, and the speed of the animation.
 */
public final class Excellence {

  /**
   * The main method for the animation that runs it.
   * @param args the arguments for the animation. Must include view type and input file name, can
   *             also include the speed of the animation and the output source.
   */
  public static void main(String[] args) {
    boolean hasI = false;
    boolean hasV = false;
    String in = "";
    String view = "";
    int speed = 1;

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

    // Instantiate the correct view & set FileReader object
    ViewFactory factory = new ViewFactory();
    IView v = factory.getView(view);
    AnimationBuilder<AnimationModelImpl> builder = AnimationModelImpl.builder();
    Readable rn = null;
    try {
      rn = new FileReader(in);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    // Parse animation input file
    AnimationReader.parseFile(rn, builder);
    AnimationModel model = builder.build();
    if (Arrays.asList(args).contains("-out")) {
      int ind = Arrays.asList(args).indexOf("-out") + 1;
      String out = Arrays.asList(args).get(ind++);
      Appendable ap = null;
      try {
        ap = new FileWriter(out);
      } catch (IOException e) {
        e.printStackTrace();
      }
      v.setOutput(ap);
    }

    if (Arrays.asList(args).contains("-speed")) {
      int speedIndex = Arrays.asList(args).indexOf("-speed") + 1;
      speed = Integer.parseInt(Arrays.asList(args).get(speedIndex));
    }
    v.setSpeed(speed);
    IController controller = new Controller(model, v);
    if (v instanceof VisualAnimationView) {
      controller.playAnimation();
    }
    if (v.getOut() instanceof FileWriter) {
      try {
        ((FileWriter) v.getOut()).close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}

