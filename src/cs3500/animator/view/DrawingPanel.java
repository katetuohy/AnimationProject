package cs3500.animator.view;
import org.omg.CORBA.Object;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import cs3500.model.AShape;

public class DrawingPanel extends JPanel implements IDrawingPanel {
  List<AShape> shapes = null;

  public DrawingPanel(){
    super();
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    if ( shapes != null ){
      g.setColor( Color.pink );
      for ( AShape shape : shapes ){
        g.fillRect((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                shape.getWidth(), shape.getHeight());
      }
    }
  }

  @Override
  public void draw(List<AShape> shapes) {
    this.shapes = shapes;
    repaint();
  }
}