package cs3500.animator.view;

import java.util.List;

import cs3500.model.Shape;

/**
 * Interface for drawing the visual animation shapes.
 */
public interface IDrawingPanel {

  /**
   * Draws the shape for the visual view class.
   *
   * @param shapes the shapes to draw
   */
  void draw(List<Shape> shapes);
}
