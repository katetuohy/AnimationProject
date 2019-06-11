package cs3500.animator;

import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;

import cs3500.controller.Controller;

public final class Excellence {

  private static Controller controller;

  public static void main(String[] args) {
    String bType = args[0];
    int row = -1;
    int col = -1;
    boolean hasS = false;
    boolean hasH = false;
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader(bType);
    controller = new Controller(in, out);
    int boardSize = -1;

    if (Arrays.asList(args).contains("-in")) {
      int ind = Arrays.asList(args).indexOf("-size") + 1;
      boardSize = Integer.parseInt(Arrays.asList(args).get(ind));
      hasS = true;
    }

    if (Arrays.asList(args).contains("-out")) {
      int ind = Arrays.asList(args).indexOf("-size") + 1;
      boardSize = Integer.parseInt(Arrays.asList(args).get(ind));
      hasS = true;
    }

    if (Arrays.asList(args).contains("-view")) {
      int ind = Arrays.asList(args).indexOf("-size") + 1;
      boardSize = Integer.parseInt(Arrays.asList(args).get(ind));
      hasS = true;
    }

    if (Arrays.asList(args).contains("-speed")) {
      int holeIndex = Arrays.asList(args).indexOf("-hole");
      row = Integer.parseInt(Arrays.asList(args).get(holeIndex++));
      col = Integer.parseInt(Arrays.asList(args).get(holeIndex + 2));
      hasH = true;
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

