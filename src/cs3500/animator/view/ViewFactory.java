package cs3500.animator.view;

/**
 * Determines what view to return given the string type.
 */
public class ViewFactory {

  /**
   * Returns a new view corresponding to the given type.
   * @param type the view type
   * @return a view instance
   */
  public IView getView(String type) {
    if (type == null) {
      // TODO:
      // maybe throw null exception here
      // Maybe change to switch- case
      // Need to check for illegal strings and do illegal state exception
      return null;
    }
    if (type.equalsIgnoreCase("text")) {
      return new TextualAnimationView();
    }
    if (type.equalsIgnoreCase("visual")) {
      return new VisualAnimationView();
    }
    if (type.equalsIgnoreCase("svg")) {
      return new SVGAnimationView();
    }
    return null;
  }
}
