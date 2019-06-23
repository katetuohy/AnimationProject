import org.junit.Test;

import java.awt.event.ActionEvent;

import cs3500.animator.view.EditorView;
import cs3500.controller.IController;

import static org.junit.Assert.assertEquals;

/**
 * Tests the use of the editor view in the controller by making a mock controller that notifies us
 * when the commands are called.
 */
public class EditorTests {
  private EditorView editorView = new EditorView();

  @Test
  public void testReplay() {
    StringBuilder stringBuilder = new StringBuilder();
    IController c = new TestController(stringBuilder);
    c.actionPerformed(new ActionEvent(this.editorView, 0, "Replay"));
    assertEquals(stringBuilder.toString(), "Got command to replay");
  }

  @Test
  public void testIncreaseSpeed() {
    StringBuilder stringBuilder = new StringBuilder();
    IController c = new TestController(stringBuilder);
    c.actionPerformed(new ActionEvent(this.editorView, 0, "Increase Speed"));
    assertEquals(stringBuilder.toString(), "Got command to increase speed");
  }

  @Test
  public void testDecreaseSpeed() {
    StringBuilder stringBuilder = new StringBuilder();
    IController c = new TestController(stringBuilder);
    c.actionPerformed(new ActionEvent(this.editorView, 0, "Decrease Speed"));
    assertEquals(stringBuilder.toString(), "Got command to decrease speed");
  }

  @Test
  public void testPlay() {
    StringBuilder stringBuilder = new StringBuilder();
    IController c = new TestController(stringBuilder);
    c.actionPerformed(new ActionEvent(this.editorView, 0, "Play"));
    assertEquals(stringBuilder.toString(), "Got command to start playing");
  }

  @Test
  public void testPause() {
    StringBuilder stringBuilder = new StringBuilder();
    IController c = new TestController(stringBuilder);
    c.actionPerformed(new ActionEvent(this.editorView, 0, "Pause"));
    assertEquals(stringBuilder.toString(), "Got command to pause");
  }

  @Test(expected = NullPointerException.class)
  public void testNullActionCommand() {
    StringBuilder stringBuilder = new StringBuilder();
    IController c = new TestController(stringBuilder);
    c.actionPerformed(new ActionEvent(this.editorView, 0, null));
  }

  public void testDeleteShape() {
    //can't test because needs other parameters
  }

  public void testAddShape() {
    //Not testing due to field requirements
  }

  public void testAddKeyFrame() {
    //Not testing because relies on other methods and fields
  }

  private class TestController implements IController {
    private final StringBuilder stringBuilder;

    private TestController(StringBuilder stringBuilder) {
      this.stringBuilder = stringBuilder;
    }

    @Override
    public void setPlaying() {
      stringBuilder.setLength(0);
      stringBuilder.append("Got command to start playing");
    }

    @Override
    public void setPaused() {
      stringBuilder.setLength(0);
      stringBuilder.append("Got command to pause");
    }

    @Override
    public void playAnimation() {
      //do nothing here, not testing
    }

    @Override
    public void replay() {
      stringBuilder.setLength(0);
      stringBuilder.append("Got command to replay");
    }

    @Override
    public void increaseSpeed() {
      stringBuilder.setLength(0);
      stringBuilder.append("Got command to increase speed");
    }

    @Override
    public void decreaseSpeed() {
      stringBuilder.setLength(0);
      stringBuilder.append("Got command to decrease speed");
    }

    //Did not include other action commands because they would have required other fields
    public void actionPerformed(ActionEvent e) {
      switch (e.getActionCommand()) {
        case "Replay":
          replay();
          break;
        case "Play":
          setPlaying();
          break;
        case "Pause":
          setPaused();
          break;
        case "Increase Speed":
          this.increaseSpeed();
          break;
        case "Decrease Speed":
          this.decreaseSpeed();
          break;
        default:
      }
    }
  }
}
