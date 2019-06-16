import org.junit.Test;

import cs3500.animator.view.IView;
import cs3500.animator.view.SVGAnimationView;
import cs3500.animator.view.TextualAnimationView;
import cs3500.animator.view.ViewFactory;
import cs3500.animator.view.VisualAnimationView;

import static org.junit.Assert.assertTrue;

/**
 * Tests the view factory for the animation.
 */
public class ViewFactoryTest {

  @Test
  public void testTextFactory() {
    ViewFactory vf = new ViewFactory();
    IView t = vf.getView("text");
    assertTrue(t instanceof TextualAnimationView);
  }

  @Test
  public void testSVGFactory() {
    ViewFactory vf = new ViewFactory();
    IView svg = vf.getView("svg");
    assertTrue(svg instanceof SVGAnimationView);
  }

  @Test
  public void testVisualFactory() {
    ViewFactory vf = new ViewFactory();
    IView vis = vf.getView("visual");
    assertTrue(vis instanceof VisualAnimationView);
  }

  @Test
  public void testNullFactory() {
    ViewFactory vf = new ViewFactory();
    IView t = vf.getView("");
    assertTrue(t == null);
  }
}
