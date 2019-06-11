package cs3500.animator;

import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;

import cs3500.controller.Controller;

public final class Excellence {

  private static Controller controller;

  public static void main(String[] args) {
    String bType = args[0];
    boolean hasI = false;
    boolean hasO = false;
    boolean hasV = false;
    boolean hasS = false;
    String in = "";
    String view = "";
    String out = "System.out";
    int speed = -1;
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader(bType);
    controller = new Controller(in, out);

    if (Arrays.asList(args).contains("-in")) {
      int ind = Arrays.asList(args).indexOf("-in") + 1;
      in = Arrays.asList(args).get(ind++);
      hasI = true;
    }

    if (Arrays.asList(args).contains("-out")) {
      int ind = Arrays.asList(args).indexOf("-out") + 1;
      out = Arrays.asList(args).get(ind++);
      hasO = true;
    }

    if (Arrays.asList(args).contains("-view")) {
      int ind = Arrays.asList(args).indexOf("-view") + 1;
      view = Arrays.asList(args).get(ind++);
      hasV = true;
    }

    if (Arrays.asList(args).contains("-speed")) {
      int speedIndex = Arrays.asList(args).indexOf("-speed");
      speed = Integer.parseInt(Arrays.asList(args).get(speedIndex++));
      hasS = true;
    }
    if (!(hasV && hasI)) {
      throw new IllegalArgumentException("Must input an in and a view type");
    }
    if (hasS && hasH) {
      Excellence.runGameAllArgs(bType, boardSize, row, col);
    } else if (hasS) {
      Excellence.runGameSize(bType, boardSize);
    } else if (hasH) {
      Excellence.runGameHole(bType, row, col);
    } else {
      Excellence.runNoArgs(bType);
    }
  }
}

