package cs3500.animator.view;

import java.awt.Graphics;
import java.util.List;


import javax.swing.JPanel;

import cs3500.model.Shape;

/**
 * Represents a graphics window. Allows for drawing shapes onto it and delegates specific shape
 * drawing to the Shape objects.
 */
public class DrawingPanel extends JPanel implements IDrawingPanel {
  List<Shape> shapes = null;

  public DrawingPanel() {
    super();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (shapes != null ) {
      for (Shape shape : shapes) {
        g.setColor(shape.getColor());
        shape.drawShape(g);
      }
    }
  }

  /**
   * Updates the Shape objects to draw on a given tick.
   * @param shapes the shapes to draw
   */
  @Override
  public void draw(List<Shape> shapes) {
    this.shapes = shapes;
    repaint();
  }
}
