package cs3500.model;

import java.awt.Color;
import java.util.ArrayList;

public class KeyFrame {

  private final int time;
  private final String shapeName;
  private int x;
  private int y;
  private int w;
  private int h;
  private int r;
  private int g;
  private int b;

  public KeyFrame(String shapeName, int time, int x, int y, int w,
                  int h, int r, int g, int b) {
    this.time = time;
    this.shapeName = shapeName;
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.r = r;
    this.g = g;
    this.b = b;
  }

  public int getTime() {
    return this.time;
  }

  public String getName() {
    return this.shapeName;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int getW() {
    return this.w;
  }

  public int getH() {
    return this.h;
  }

  public int getR() {
    return this.r;
  }

  public int getG() {
    return this.g;
  }

  public int getB() {
    return this.b;
  }

  public Color getColor() {
    return new Color(r, g, b);
  }

  public boolean equals(KeyFrame other) {
    return other.getName().equals(shapeName) && other.getTime() == time && other.getX() == x
            && other.getY() == y && other.getW() == w && other.getH() == h && other.getR() == r
            && other.getG() == g && other.getB() == b;
  }

  public String getXML(Shape s, int speed, KeyFrame next) {
    int newStartTime = this.time * (1000 / speed);
    int newEndTime = next.getTime() * (1000 / speed);
    return s.getPositionXML(newStartTime, newEndTime, new Position2D(this.x,
            + this.y), new Position2D(next.getX(), next.getY()))
            + "<animate attributeType=\"xml\" begin=\"" + newStartTime
            + "ms\" dur=\"" + (newEndTime - newStartTime) + "ms\" "
            + "attributeName=\"fill\" from=\""
            + "rgb(" + this.r + "," + this.g + "," + this.b + ")\""
            + " to=\"" + "rgb(" + next.getR() + "," + next.getG() + "," + next.getB()
            + ")\"" + " />\n"
            + "<animate attributeType=\"xml\" begin=\"" + newStartTime
            + "ms\" dur=\"" + (newEndTime - newStartTime) + "ms\" "
            + s.animateWidthXML(this.w, next.getW()) + " />\n"
            + "<animate attributeType=\"xml\" begin=\"" + newStartTime
            + "ms\" dur=\"" + (newEndTime - newStartTime) + "ms\" "
            + s.animateHeightXML(this.h, next.getH()) + " />\n";
  }
}
