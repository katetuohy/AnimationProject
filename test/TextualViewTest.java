import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewFactory;
import cs3500.model.AnimationModel;
import cs3500.model.AnimationModelImpl;

import static org.junit.Assert.assertEquals;

public class TextualViewTest {

  @Test
  public void testTextSimple() {
    ViewFactory factory = new ViewFactory();
    IView v = factory.getView("text");
    AnimationBuilder<AnimationModelImpl> builder = AnimationModelImpl.builder();
    Readable rn = null;
    try {
      rn = new FileReader("toh-3.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    AnimationModel model = AnimationReader.parseFile(rn, builder);
    v.displayTextualView(model.getMap(), model.getCanvas());
    assertEquals(v.getOut().toString(), "");
  }

}
