package cs3500.animator.view;

/**
 * Determines what view to return given the string type.
 */
public class ViewFactory {

  /**
   * Returns a new view corresponding to the given type.
   *
   * @param type the view type
   * @return a view instance
   */
  public IView getView(String type) {
    if (type.equalsIgnoreCase("text")) {
      return new TextualAnimationView();
    }
    if (type.equalsIgnoreCase("visual")) {
      return new VisualAnimationView();
    }
    if (type.equalsIgnoreCase("svg")) {
      return new SVGAnimationView();
    }
    if (type.equalsIgnoreCase("edit")) {
      return new EditorView();
    }
    return null;
  }
}
