package cs3500.controller;

import java.awt.event.ActionEvent;

/**
 * Interface for the controller of the animation.
 */
public interface IController {

  /**
   * Begin the animation. Start the timer.
   */
  void playAnimation();

  /**
   * Restarts the animation.
   */
  void replay();

  void setPlaying();

  void setPaused();

  void increaseSpeed();

  void decreaseSpeed();

  void actionPerformed(ActionEvent e);
}
