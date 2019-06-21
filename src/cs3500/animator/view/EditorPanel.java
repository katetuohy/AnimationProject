package cs3500.animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * A panel containing the components for an animation editor.
 * Create or delete shapes. Create, delete, or modify keyframes of existing shapes.
 * Can add a keyframe before the first or after the last existing keyframe
 * and add the very first keyframe to a shape that has none.
 */
public class EditorPanel extends JPanel implements ActionListener {

  private final JLabel shapeTypeLabel;
  private final JLabel shapeNameLabel;
  private final JLabel shapeWidthLabel;
  private final JLabel shapeHeightLabel;
  private final JLabel shapePosXLabel;
  private final JLabel shapePosYLabel;
  private final JLabel shapeRedLabel;
  private final JLabel shapeGreenLabel;
  private final JLabel shapeBlueLabel;

  private final JLabel deleteShapeNameLabel;

  private final JLabel addKeyFrameShapeNameLabel;
  private final JLabel addKeyFrameTimeLabel;
  private final JLabel addKeyFrameColorLabel;
  private final JLabel addKeyFramePosXLabel;
  private final JLabel addKeyFramePosYLabel;

  private final JLabel deleteKeyFrameNameLabel;
  private final JLabel deleteKeyFrameTimeLabel;

  private final JButton deleteKeyFrameButton;
  private final JButton addKeyFrameButton;
  private final JButton deleteShapeButton;
  private final JButton addShapeButton;
  private final JButton replayButton;
  private final JButton incrementSpeed;
  private final JButton decrementSpeed;

  private final JTextField addShapeTypeField;
  private final JTextField addShapeNameField;
  private final JTextField addShapeWidthField;
  private final JTextField addShapeHeightField;
  private final JTextField addShapePosXField;
  private final JTextField addShapePosYField;
  private final JTextField addShapeRedField;
  private final JTextField addShapeGreenField;
  private final JTextField addShapeBlueField;

  private final JTextField deleteShapeNameField;

  private final JTextField addKeyFrameShapeNameField;
  private final JTextField addKeyFrameTimeField;
  private final JTextField addKeyFramePosXField;
  private final JTextField addKeyFramePosYField;
  private final JTextField addKeyFrameWidthField;
  private final JTextField addKeyFrameHeightField;
  private final JTextField addKeyFrameRedField;
  private final JTextField addKeyFrameGreenField;
  private final JTextField addKeyFrameBlueField;

  private final JTextField deleteKeyFrameShapeNameField;
  private final JTextField deleteKeyFrameTimeField;

  private JTextArea message;

  /**
   * Default constructor. Initializes all components.
   */
  public EditorPanel() {
    super();

    /* BUTTONS */
    this.deleteKeyFrameButton = new JButton("Delete KeyFrame");
    this.addKeyFrameButton = new JButton("Add KeyFrame");
    this.deleteShapeButton = new JButton("Delete Shape");
    this.addShapeButton = new JButton("Add Shape");
    this.replayButton = new JButton("Replay Animation");
    this.incrementSpeed = new JButton("+ Speed");
    this.decrementSpeed = new JButton("- Speed");

    /* TEXT FIELDS */
    addShapeTypeField = new JTextField();
    addShapeNameField = new JTextField();
    addShapeWidthField = new JTextField();
    addShapeHeightField = new JTextField();
    addShapePosXField = new JTextField();
    addShapePosYField = new JTextField();
    addShapeRedField = new JTextField();
    addShapeGreenField = new JTextField();
    addShapeBlueField = new JTextField();

    deleteShapeNameField = new JTextField();

    addKeyFrameShapeNameField = new JTextField();
    addKeyFrameTimeField = new JTextField();
    addKeyFramePosXField = new JTextField();
    addKeyFramePosYField = new JTextField();
    addKeyFrameWidthField = new JTextField();
    addKeyFrameHeightField = new JTextField();
    addKeyFrameRedField = new JTextField();
    addKeyFrameGreenField = new JTextField();
    addKeyFrameBlueField = new JTextField();

    deleteKeyFrameShapeNameField = new JTextField();
    deleteKeyFrameTimeField = new JTextField();

    /* LABELS */
    shapeTypeLabel = new JLabel("Shape Type:");
    shapeNameLabel = new JLabel("Shape Name:");
    shapeWidthLabel = new JLabel("Shape Width");
    shapeHeightLabel = new JLabel("Shape Height");
    shapePosXLabel = new JLabel("X Position:");
    shapePosYLabel = new JLabel("Y Position:");
    shapeRedLabel = new JLabel("Shape Color:");
    shapeGreenLabel = new JLabel("Shape Color:");
    shapeBlueLabel = new JLabel("Shape Color:");

    deleteShapeNameLabel = new JLabel("Shape Name:");

    addKeyFrameShapeNameLabel = new JLabel("Shape Name:");
    addKeyFrameTimeLabel = new JLabel("Time:");
    addKeyFrameColorLabel = new JLabel("Color:");
    addKeyFramePosXLabel = new JLabel("X Position:");
    addKeyFramePosYLabel = new JLabel("Y Position:");

    deleteKeyFrameNameLabel = new JLabel("Shape Name:");
    deleteKeyFrameTimeLabel = new JLabel("Time:");

    /* MESSAGE BOX */
    message = new JTextArea(5, 20);

    /* Add the components to the panel. */
    this.add(deleteKeyFrameButton);
    this.add(addKeyFrameButton);
    this.add(deleteShapeButton);
    this.add(addShapeButton);
    this.add(replayButton);
    this.add(incrementSpeed);
    this.add(decrementSpeed);

    this.add(addShapeTypeField);
    this.add(addShapeNameField);
    this.add(addShapeWidthField);
    this.add(addShapeHeightField);
    this.add(addShapePosXField);
    this.add(addShapePosYField);
    this.add(addShapeRedField);
    this.add(addShapeGreenField);
    this.add(addShapeBlueField);

    this.add(deleteShapeNameField);

    this.add(addKeyFrameShapeNameField);
    this.add(addKeyFrameTimeField);
    this.add(addKeyFramePosXField);
    this.add(addKeyFramePosYField);
    this.add(addKeyFrameWidthField);
    this.add(addKeyFrameHeightField);
    this.add(addKeyFrameRedField);
    this.add(addKeyFrameGreenField);
    this.add(addKeyFrameBlueField);

    this.add(deleteKeyFrameShapeNameField);
    this.add(deleteKeyFrameTimeField);

    this.add(shapeTypeLabel);
    this.add(shapeNameLabel);
    this.add(shapeWidthLabel);
    this.add(shapeHeightLabel);
    this.add(shapePosXLabel);
    this.add(shapePosYLabel);
    this.add(shapeRedLabel);
    this.add(shapeGreenLabel);
    this.add(shapeBlueLabel);

    this.add(deleteShapeNameLabel);

    this.add(addKeyFrameShapeNameLabel);
    this.add(addKeyFrameTimeLabel);
    this.add(addKeyFrameColorLabel);
    this.add(addKeyFramePosXLabel);
    this.add(addKeyFramePosYLabel);

    this.add(deleteKeyFrameNameLabel);
    this.add(deleteKeyFrameTimeLabel);
  }

  public String[] getAddShapeFields() {
    String[] result = new String[7];
    result[0] = addShapeTypeField.getText();
    result[1] = addShapeNameField.getText();
    result[2] = addShapeWidthField.getText();
    result[3] = addShapeHeightField.getText();
    result[4] = addShapePosXField.getText();
    result[5] = addShapePosYField.getText();
    result[6] = addShapeRedField.getText();
    result[7] = addShapeGreenField.getText();
    result[8] = addShapeBlueField.getText();
    if (isValidFieldsList(result)) {
      return result;
    } else {
      setMessage("Please enter all fields to add a Shape.");
      return result;
    }
  }

  public String[] getAddKeyFrameFields() {
    String[] result = new String[9];
    result[0] = addKeyFrameShapeNameField.getText();
    result[1] = addKeyFrameTimeField.getText();
    result[2] = addKeyFramePosXField.getText();
    result[3] = addKeyFramePosYField.getText();
    result[4] = addKeyFrameWidthField.getText();
    result[5] = addKeyFrameHeightField.getText();
    result[6] = addKeyFrameRedField.getText();
    result[7] = addKeyFrameGreenField.getText();
    result[8] = addKeyFrameBlueField.getText();
    if (isValidFieldsList(result)) {
      return result;
    } else {
      setMessage("Please enter all fields to add a Keyframe.");
      return result;
    }
  }

  public String getDeleteShapeField() {
    String res = deleteShapeNameField.getText();
    if (res.isEmpty()) {
      return res;
    } else {
      setMessage("Please enter the field to delete a shape.");
      return res;
    }
  }

  public String[] getDeleteKeyFrameFields() {
    String[] result = new String[2];
    result[0] = deleteKeyFrameShapeNameField.getText();
    result[1] = deleteKeyFrameTimeField.getText();
    if (isValidFieldsList(result)) {
      return result;
    } else {
      setMessage("Please enter all fields to delete a keyframe.");
      return result;
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // When a button is pressed, send that command to the controller which will pass it to the model.
  }

  /**
   * Set the listener for the buttons.
   *
   * @param listener the action listener.
   */
  public void setListener(ActionListener listener) {
    deleteKeyFrameButton.setActionCommand("Delete KeyFrame");
    deleteKeyFrameButton.addActionListener(listener);
    addKeyFrameButton.setActionCommand("Add KeyFrame");
    addKeyFrameButton.addActionListener(listener);
    deleteShapeButton.setActionCommand("Delete Shape");
    deleteShapeButton.addActionListener(listener);
    addShapeButton.setActionCommand("Add Shape");
    addShapeButton.addActionListener(listener);
    replayButton.setActionCommand("Replay");
    replayButton.addActionListener(listener);
    incrementSpeed.setActionCommand("Increase Speed");
    incrementSpeed.addActionListener(listener);
    decrementSpeed.setActionCommand("Decrease Speed");
    decrementSpeed.addActionListener(listener);
  }

  private boolean isValidFieldsList(String[] fields) {
    for (String f : fields) {
      if (f.isEmpty()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Set the output message to the user of the editor view.
   */
  public void setMessage(String message) {
    this.message.setText(message);
  }
}
