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

  private int time;
  private ArrayList<Shape> shapes;
  private int[] canvas;
  private ArrayList<KeyFrame> frames;
  private int longestTime;

  /**
   * Construct an animation model at time = 0.
   */
  public AnimationModelImpl() {
    this.time = 0;
    this.shapes = new ArrayList<Shape>();
    this.canvas = new int[4];
    for (int i = 0; i < canvas.length; i++) {
      canvas[i] = 0;
    }
    this.frames = new ArrayList<KeyFrame>();
    this.longestTime = 0;
  }

  /**
   * Builder for Animation Model.
   *
   * @return Builder object.
   */
  public static Builder builder() {
    return new Builder();
  }

  public int getLongestTime() {
    return this.longestTime;
  }

  public void setLongestTime(int t) {
    this.longestTime = t;
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
    int indexToRemove = 0;
    boolean exists = false;
    for (int i = 0; i < frames.size(); i++) {
      if (name.equals(frames.get(i).getName()) && frames.get(i).getTime() == time) {
        exists = true;
        indexToRemove = i;
        break;
      }
    }
    if (exists) {
      frames.remove(indexToRemove);
    }
  }

  @Override
  public void removeShape(String shapeName) {
    boolean exists = false;
    for (Shape s : shapes) {
      if (shapeName.equals(s.getName())) {
        exists = true;
        shapes.remove(s);
      }
    }
    if (!exists) {
      throw new IllegalArgumentException("Shape to delete: \"" + shapeName + "\" doesn't exist.");
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
  public int getTime() {
    return this.time;
  }

  @Override
  public void setTime(int time) {
    this.time = time;
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
    for (Shape s : shapes) {
      Shape shapeToRender = s;
      for (int i = 0; i < frames.size(); i++) {
        KeyFrame current = frames.get(i);
        if (current.getName().equalsIgnoreCase(s.getName())) {
          // If the next frame is also for the current shape.
          if (i < frames.size() - 1 && frames.get(i + 1).getName().equalsIgnoreCase(s.getName())) {
            KeyFrame next = frames.get(i + 1);
            // If current time is between two of the keyframes' times -> tween!
            if (frames.get(i).getTime() <= this.time
                    && this.time <= frames.get(i + 1).getTime()) {
              int startTime = frames.get(i).getTime();
              int endTime = frames.get(i + 1).getTime();
              shapeToRender.setPosition(this.time, startTime, endTime, current, next);
              shapeToRender.setSize(this.time, startTime, endTime, current, next);
              shapeToRender.setColor(this.time, startTime, endTime, current, next);
              shapesToRender.add(shapeToRender);
              break;
            } else if (frames.get(i).getTime() > this.time) {
              // If current time is before first keyframe.
              // Don't add to list of shapes.
            }
          } else {
            // If at last keyframe for this shape, Keep shape in the current state.
            shapeToRender.setPosition(this.time, this.time, this.time + 1, current, current);
            shapeToRender.setColor(this.time, this.time, this.time + 1, current, current);
            shapeToRender.setPosition(this.time, this.time, this.time + 1, current, current);
          }
        }
      }
    }
    return shapesToRender;
  }

  @Override
  public void addShape(Shape s) {
    for (Shape shape : shapes) {
      if (shape.getName().equals(s.getName())) {
        throw new IllegalArgumentException("Can't have duplicate shape name.");
      }
    }
    this.shapes.add(s);
  }

  @Override
  public void addFrame(KeyFrame k) {
    for (KeyFrame keyframe : frames) {
      if (keyframe.equals(k)) {
        throw new IllegalArgumentException("Can't have duplicate keyframe for a shape.");
      }
    }
    this.frames.add(k);
    if (k.getTime() > longestTime) {
      longestTime = k.getTime();
    }
  }

  @Override
  public void setCanvas(int x, int y, int width, int height) {
    this.canvas[0] = x;
    this.canvas[1] = y;
    this.canvas[2] = width;
    this.canvas[3] = height;
  }

  /**
   * Represents a builder pattern for an animation model. Animation model can be constructed by
   * adding shapes, motions, canvas. Keyframes not supported in this implementation.
   */
  public static final class Builder implements AnimationBuilder<AnimationModelImpl> {
    AnimationModelImpl model;
    int[] canvas = {0, 0, 0, 0};
    ArrayList<Shape> shapes = new ArrayList<Shape>();
    ArrayList<KeyFrame> frames = new ArrayList<KeyFrame>();
    int longestTime;

    @Override
    public AnimationModelImpl build() {
      this.setAnimation();
      this.model = new AnimationModelImpl();
      model.setCanvas(canvas[0], canvas[1], canvas[2], canvas[3]);
      for (int i = 0; i < shapes.size(); i++) {
        try {
          model.addShape(shapes.get(i));
        } catch (IllegalArgumentException e) {
          // Skip this duplicate shape.
        }
      }
      for (KeyFrame k : frames) {
        try {
          model.addFrame(k);
        } catch (IllegalArgumentException e) {
          // Skip this duplicate frame.
        }
      }
      model.setLongestTime(longestTime);
      return this.model;
    }

    public void setAnimation() {
      if (frames != null && shapes != null && frames.size() > 0 && shapes.size() > 0) {
        // Sort the frames by shape
        this.sortFrames();
      } else {
        throw new IllegalStateException("Motions or commands must not be null or empty!");
      }
    }

    private void removeDuplicates() {
      ArrayList<KeyFrame> newList = new ArrayList<KeyFrame>();
      boolean dup = false;
      for (KeyFrame frame : frames) {
        for (KeyFrame f : newList) {
          if (frame.getName().equals(f.getName()) && frame.getTime() == (f.getTime())) {
            dup = true;
            break;
          }
        }
        if (!dup) {
          newList.add(frame);
        } else {
          dup = false;
        }
      }
      this.frames = newList;
    }



    // Sorts list of frames in same order of the given list of shapes.
    //This does not take into account the times
    private void sortFrames() {
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