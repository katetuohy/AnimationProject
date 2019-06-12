package cs3500.animator;

import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;

import cs3500.animator.view.IView;
import cs3500.animator.view.ViewFactory;

public final class Excellence {

  public static void main(String[] args) {
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
    v.setInput(in);

    if (Arrays.asList(args).contains("-out")) {
      int ind = Arrays.asList(args).indexOf("-out") + 1;
      out = Arrays.asList(args).get(ind++);
      v.setOutput(out);
    }

    if (Arrays.asList(args).contains("-speed")) {
      int speedIndex = Arrays.asList(args).indexOf("-speed");
      speed = Integer.parseInt(Arrays.asList(args).get(speedIndex++));
      v.setSpeed(speed);
    }
  }
}
