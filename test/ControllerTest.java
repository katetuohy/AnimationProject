import org.junit.Test;

import cs3500.animator.view.IView;
import cs3500.animator.view.SVGAnimationView;
import cs3500.controller.Controller;
import cs3500.controller.IController;
import cs3500.model.AnimationModel;
import cs3500.model.AnimationModelImpl;

/**
 * Test the {@link Controller} Class.
 */
public class ControllerTest {

  @Test
  public void testSVG1() {
    IView v = new SVGAnimationView();
    AnimationModel m = new AnimationModelImpl();
    IController c;
  }
}
