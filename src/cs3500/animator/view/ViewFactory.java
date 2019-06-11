package cs3500.animator.view;

public class ViewFactory {

  public IView getView(String type) {
    if (type == null) { //maybe throw null exception here
      return null;
    }
    if (type.equalsIgnoreCase("text")) {
      return new TextualAnimationView();
    }
    if (type.equalsIgnoreCase("visual")) {
      return new VisualAnimationView();
    }
    if (type.equalsIgnoreCase("svg")) {
      return new SVGAnimationVIew();
    }
    return null;
  }
}
