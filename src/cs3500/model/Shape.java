package cs3500.model;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents a geometrical shape for the animation.
 */
public interface Shape {

  /**
   * Determines if the two shapes are the same based on name, color, width, height, and position.
   * @param other the other shape to compare this to
   * @return true if the same shape.
   */
  boolean sameShape(AShape other);

  /**
   * Returns the current color of the shape.
   * @return the color
   */
  Color getColor();

  /**
   * Returns the current width of the shape.
   * @return the width
   */
  int getWidth();

  /**
   * Returns the current height of the shape.
   * @return the height
   */
  int getHeight();

  /**
   * Returns the name of the shape.
   * @return the name
   */
  String getName();

  /**
   * Returns the current position of the shape.
   * @return the position
   */
  Position2D getPosition();

  /**
   * Sets showing to the opposite of what it currently is.
   */
  void setShow();

  /**
   * Updates the shape to reflect the given command and current time for its position.
   * @param currTime  Current time of the program.
   * @param startTime The start time for the given command.
   * @param endTime   the end time for the given command
   * @param first     the Keyframe for the beginning of the motion
   * @param second    the keyframe for the end of the motion
   */
  void setPosition(int currTime, int startTime, int endTime, KeyFrame first, KeyFrame second);

  /**
   * Updates the shape to reflect the given command and current time for its color.
   * @param currTime  Current time of the program.
   * @param startTime The start time for the given command.
   * @param endTime   the end time for the given command
   * @param first     the Keyframe for the beginning of the motion
   * @param second    the keyframe for the end of the motion
   */
  void setColor(int currTime, int startTime, int endTime, KeyFrame first, KeyFrame second);

  /**
   * Updates the shape to reflect the given command and current time for its size.
   * @param currTime  Current time of the program.
   * @param startTime The start time for the given command.
   * @param endTime   the end time for the given command
   * @param first     the Keyframe for the beginning of the motion
   * @param second    the keyframe for the end of the motion
   */
  void setSize(int currTime, int startTime, int endTime, KeyFrame first, KeyFrame second);

  /**
   * Return shape's XML.
   */
  String getXML();

  /**
   * Return shape's end tag XML.
   */
  String getEndXML();

  /**
   * Return shape's width changing animate xml statement.
   */
  String animateWidthXML(int width1, int width2);

  /**
   * Return shape's height changing animate xml statement.
   */
  String animateHeightXML(int height1, int height2);

  /**
   * Draws the shape in the given Graphics.
   * @param g the Graphics.
   */
  void drawShape(Graphics g);

  /**
   * Returns the XML for the position of a shape.
   *
   * @param startTime the start time of the shape.
   * @param endTime the end time of the shape.
   * @param from the from position of the shape.
   * @param to the to position of the shape.
   * @return the XML format.
   */
  String getPositionXML(int startTime, int endTime, Position2D from, Position2D to);
}
