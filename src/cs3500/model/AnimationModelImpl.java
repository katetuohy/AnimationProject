package cs3500.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.util.AnimationBuilder;

/**
 * Represents the model for an animation. The animation saves the current time and map of commands
 * to the shapes they act on. A method is used to update all of the animation shapes for a given
 * time. Motions are also sorted as well as time gaps are filled with new commands. Commands must
 * not overlap otherwise an exception is thrown. There must always be a list of shapes given to the
 * model as well.
 */
public final class AnimationModelImpl implements AnimationModel {

  int time;
  private ArrayList<Shape> shapes;
  private int[] canvas;
  private ArrayList<KeyFrame> frames;

  /**
   * Construct an animation model at time = 0.
   */
  public AnimationModelImpl() {
    this.time = 0;
    //this.commands = new LinkedHashMap<Command, Shape>();
    //this.motions = new ArrayList<Command>();
    this.shapes = new ArrayList<Shape>();
    this.canvas = new int[4];
    for (int i = 0; i < canvas.length; i++) {
      canvas[i] = 0;
    }
    this.frames = new ArrayList<KeyFrame>();
  }

  @Override
  public void insertFrame(KeyFrame key) {
    for (int i = 0; i < frames.size() - 1; i++) {
      KeyFrame first = frames.get(i);
      KeyFrame second = frames.get(i + 1);
      if (first.getName().equals(key.getName())
              && second.getName().equals(key.getName()) && key.getTime() >= first.getTime()
              && second.getTime() >= key.getTime()) {
        frames.add(i + 1, key);
        return;
      }
    }
    frames.add(key); //Adds the frame to the end of the list if it can't find a location
  }

  @Override
  public void removeFrame(String name, int time) {
    for (int i = 0; i < frames.size(); i++) {
      if (name.equals(frames.get(i).getName()) && frames.get(i).getTime() == time) {
        frames.remove(i);
        return;
      }
    }
  }

  @Override
  public void removeShape(String shapeName) {
    for (Shape s : shapes) {
      if (shapeName.equals(s.getName())) {
        shapes.remove(s);
      }
    }
    for (KeyFrame frame : frames) {
      if (frame.getName().equals(shapeName)) {
        frames.remove(frame);
      }
    }
  }

  @Override
  public int[] getCanvas() {
    return this.canvas;
  }

  @Override
  public void setTime(int time) {
    this.time = time;
  }

  @Override
  public int getTime() {
    return this.time;
  }

  @Override
  public List<Shape> getShapes() {
    return this.shapes;
  }

  @Override
  public List<KeyFrame> getFrames() {
    return this.frames;
  }

  @Override
  public List<Shape> moveShapes() {
    List<Shape> shapesToRender = new ArrayList<Shape>();
    for (int i = 0; i < frames.size() - 1; i++) {
      KeyFrame first = frames.get(i);
      KeyFrame second = frames.get(i + 1);
      if (first.getName().equals(second.getName()) && this.time >= first.getTime()
              && this.time <= second.getTime()) {
        int startTime = first.getTime();
        int endTime = second.getTime();
        String name = first.getName();
        Shape shape = null;
        int index = 0;
        for (Shape s : shapes) {
          if (s.getName().equals(name)) {
            shape = s;
            shapes.remove(index);
          } else {
            index++;
          }
        }
        shape.setPosition(this.time, startTime, endTime, first, second);
        shape.setSize(this.time, startTime, endTime, first, second);
        shape.setColor(this.time, startTime, endTime, first, second);
        shapes.add(index, shape);
        shapesToRender.add(shape);
      }
    }
    return shapesToRender;
  }

  @Override
  public void addShape(Shape s) {
    this.shapes.add(s);
  }

  @Override
  public void addFrame(KeyFrame k) {
    this.frames.add(k);
  }

  @Override
  public void setCanvas(int x, int y, int width, int height) {
    this.canvas[0] = x;
    this.canvas[1] = y;
    this.canvas[2] = width;
    this.canvas[3] = height;
  }


  /**
   * Builder for Animation Model.
   * @return Builder object.
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Represents a builder pattern for an animation model. Animation model can be constructed by
   * adding shapes, motions, canvas.
   * Keyframes not supported in this implementation.
   */
  public static final class Builder implements AnimationBuilder<AnimationModelImpl> {
    AnimationModelImpl model;
    int[] canvas = {0, 0, 0, 0};
    ArrayList<Shape> shapes = new ArrayList<Shape>();
    ArrayList<KeyFrame> frames = new ArrayList<KeyFrame>();

    @Override
    public AnimationModelImpl build() {
      this.setAnimation();
      this.model = new AnimationModelImpl();
      model.setCanvas(canvas[0], canvas[1], canvas[2], canvas[3]);
      for (int i = 0; i < shapes.size(); i++) {
        model.addShape(shapes.get(i));
      }
      for (KeyFrame k : frames) {
        model.addFrame(k);
      }
      return this.model;
    }

    public void setAnimation() {
      if (frames != null && shapes != null && frames.size() > 0 && shapes.size() > 0) {
        //Removes duplicate frames added when parsing file
        this.removeDuplicates();
        // Sort the frames by shape
        this.sortMotions();
        // Make sure motions have no overlapping time intervals.
        this.validateMotionsNotOverlapping();
        // Fill in time gaps in motions.
        // this.fixRemainingTimeGaps();
      } else {
        throw new IllegalStateException("Motions or commands must not be null or empty!");
      }
    }

    private void removeDuplicates() {
      ArrayList<KeyFrame> newList = new ArrayList<KeyFrame>();
      for (KeyFrame frame : frames) {
        if(!newList.contains(frame)) {
          newList.add(frame);
        }
      }
      this.frames = newList;
    }

    // Sorts list of frames in same order of the given list of shapes.
    //This does not take into account the times
    private void sortMotions() {
      ArrayList<KeyFrame> sorted = new ArrayList<KeyFrame>();
      for (Shape shape : shapes) {
        for (KeyFrame k : frames) {
          if (k.getName().equals(shape.getName())) {
            sorted.add(k);
          }
        }
      }
      this.frames = sorted;
    }

    private void validateMotionsNotOverlapping() {
      for (int i = 0; i < frames.size() - 1; i++) {
        KeyFrame first = frames.get(i);
        KeyFrame second = frames.get(i + 1);
        if (first.getTime() > second.getTime()) {
          frames.set(i, second);
          frames.set(i + 1, first);
        }
      }
    }

    @Override
    public AnimationBuilder<AnimationModelImpl> setBounds(int x, int y, int width, int height) {
      canvas[0] = x;
      canvas[1] = y;
      canvas[2] = width;
      canvas[3] = height;
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModelImpl> declareShape(String name, String type) {
      shapes.add(new ShapeFactory().getShape(name, type));
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModelImpl> addMotion(String name, int t1, int x1, int y1,
                                                          int w1, int h1, int r1, int g1, int b1,
                                                          int t2, int x2, int y2, int w2, int h2,
                                                          int r2, int g2, int b2) {
      return this;
    }

    /**
     *
     */
    @Override
    public AnimationBuilder<AnimationModelImpl> addKeyframe(String name, int t, int x, int y, int w,
                                                            int h, int r, int g, int b) {
      frames.add(new KeyFrame(name, t, x, y, w, h, r, g, b));
      return this;
    }
  }
}