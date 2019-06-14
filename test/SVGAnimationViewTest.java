import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewFactory;
import cs3500.model.AnimationModel;
import cs3500.model.AnimationModelImpl;

public class SVGAnimationViewTest {

  @Test
  public void testSVGSimple() {
    ViewFactory factory = new ViewFactory();
    IView v = factory.getView("svg");
    AnimationBuilder<AnimationModelImpl> builder = AnimationModelImpl.builder();
    Readable rn = null;
    try {
      rn = new FileReader("toh-3.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    AnimationModel model = AnimationReader.parseFile(rn, builder);
    v.displaySVG(model.getMotions());
    //...
  }
}
