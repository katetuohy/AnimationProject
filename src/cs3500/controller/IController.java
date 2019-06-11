package cs3500.controller;

import model.AnimationModel;

public interface IController {

  /**
   * Controls the animation using the commands and given animation model.
   * @param model the model given to use for the game
   * @throws IllegalArgumentException if the model is null
   * @throws IllegalStateException if the controller is unable to receive input or transmit output.
   */
  void playAnimation(AnimationModel model) throws IllegalArgumentException, IllegalStateException;

  /**
   * Takes in the string a tries to append it to the output field in the class.
   * @param s the string to be appended
   */
  void appendOut(String s);

}
