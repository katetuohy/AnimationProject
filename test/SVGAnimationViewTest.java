import org.junit.Test;

import cs3500.model.AnimationModel;
import cs3500.model.AnimationModelImpl;

public class SVGAnimationViewTest {
  @Test
  public void testBasicXMLOneShapeNoAnimation() {
    AnimationModel m = new AnimationModelImpl();
    m.addMotion();
  }
}
