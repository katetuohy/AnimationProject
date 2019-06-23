package cs3500.animator.view;

import java.awt.*;
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
  private final JLabel addKeyFrameWidthLabel;
  private final JLabel addKeyFrameHeightLabel;
  private final JLabel addKeyFramePosXLabel;
  private final JLabel addKeyFramePosYLabel;
  private final JLabel addKeyFrameRedLabel;
  private final JLabel addKeyFrameGreenLabel;
  private final JLabel addKeyFrameBlueLabel;

  private final JLabel deleteKeyFrameNameLabel;
  private final JLabel deleteKeyFrameTimeLabel;

  private final JButton deleteKeyFrameButton;
  private final JButton addKeyFrameButton;
  private final JButton deleteShapeButton;
  private final JButton addShapeButton;
  private final JButton replayButton;
  private final JButton incrementSpeed;
  private final JButton decrementSpeed;
  private final JButton pauseButton;
  private final JButton playButton;

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

  private JLabel message;

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
    pauseButton = new JButton("||");
    playButton = new JButton("|>");

    /* TEXT FIELDS */
    addShapeTypeField = new JTextField(4);
    addShapeNameField = new JTextField(4);
    addShapeWidthField = new JTextField(4);
    addShapeHeightField = new JTextField(4);
    addShapePosXField = new JTextField(4);
    addShapePosYField = new JTextField(4);
    addShapeRedField = new JTextField(4);
    addShapeGreenField = new JTextField(4);
    addShapeBlueField = new JTextField(4);

    deleteShapeNameField = new JTextField(4);

    addKeyFrameShapeNameField = new JTextField(4);
    addKeyFrameTimeField = new JTextField(4);
    addKeyFramePosXField = new JTextField(4);
    addKeyFramePosYField = new JTextField(4);
    addKeyFrameWidthField = new JTextField(4);
    addKeyFrameHeightField = new JTextField(4);
    addKeyFrameRedField = new JTextField(4);
    addKeyFrameGreenField = new JTextField(4);
    addKeyFrameBlueField = new JTextField(4);

    deleteKeyFrameShapeNameField = new JTextField(4);
    deleteKeyFrameTimeField = new JTextField(4);


    /* LABELS */
    shapeTypeLabel = new JLabel("Shape Type:");
    shapeNameLabel = new JLabel("Shape Name:");
    shapeWidthLabel = new JLabel("Shape Width");
    shapeHeightLabel = new JLabel("Shape Height");
    shapePosXLabel = new JLabel("X Position:");
    shapePosYLabel = new JLabel("Y Position:");
    shapeRedLabel = new JLabel("Red:");
    shapeGreenLabel = new JLabel("Green:");
    shapeBlueLabel = new JLabel("Blue:");

    deleteShapeNameLabel = new JLabel("Shape Name:");

    addKeyFrameShapeNameLabel = new JLabel("Shape Name:");
    addKeyFrameTimeLabel = new JLabel("Time:");
    addKeyFramePosXLabel = new JLabel("X Position:");
    addKeyFramePosYLabel = new JLabel("Y Position:");
    addKeyFrameWidthLabel = new JLabel("Width:");
    addKeyFrameHeightLabel = new JLabel("Height:");
    addKeyFrameRedLabel = new JLabel("Red:");
    addKeyFrameGreenLabel = new JLabel("Green:");
    addKeyFrameBlueLabel = new JLabel("Blue:");

    deleteKeyFrameNameLabel = new JLabel("Shape Name:");
    deleteKeyFrameTimeLabel = new JLabel("Time:");

    /* MESSAGE BOX */
    message = new JLabel();

    /* Set Layout */
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    /* Panel for adding shapes. */
    JPanel addShapePanel = new JPanel();
    addShapePanel.setPreferredSize(new Dimension(700, 60));
    addShapePanel.setMaximumSize(new Dimension(700, 150));
    addShapePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    addShapePanel.add(addShapeButton);
    addShapePanel.add(shapeTypeLabel);
    addShapePanel.add(addShapeTypeField);
    addShapePanel.add(shapeNameLabel);
    addShapePanel.add(addShapeNameField);
    addShapePanel.add(shapeWidthLabel);
    addShapePanel.add(addShapeWidthField);
    addShapePanel.add(shapeHeightLabel);
    addShapePanel.add(addShapeHeightField);
    addShapePanel.add(shapePosXLabel);
    addShapePanel.add(addShapePosXField);
    addShapePanel.add(shapePosYLabel);
    addShapePanel.add(addShapePosYField);
    addShapePanel.add(shapeRedLabel);
    addShapePanel.add(addShapeRedField);
    addShapePanel.add(shapeGreenLabel);
    addShapePanel.add(addShapeGreenField);
    addShapePanel.add(shapeBlueLabel);
    addShapePanel.add(addShapeBlueField);

    /* Panel for deleting shapes. */
    JPanel deleteShapePanel = new JPanel();
    deleteShapePanel.setPreferredSize(new Dimension(700, 50));
    deleteShapePanel.setMaximumSize(new Dimension(700, 80));
    deleteShapePanel.setLayout(new FlowLayout());
    deleteShapePanel.add(deleteShapeButton);
    deleteShapePanel.add(deleteShapeNameLabel);
    deleteShapePanel.add(deleteShapeNameField);

    /*Panel for adding keyframes */
    JPanel addKeyFramePanel = new JPanel();
    addKeyFramePanel.setLayout(new FlowLayout());
    addKeyFramePanel.setPreferredSize(new Dimension(700, 70));
    addKeyFramePanel.setMaximumSize(new Dimension(700, 150));
    addKeyFramePanel.add(addKeyFrameButton);
    addKeyFramePanel.add(addKeyFrameShapeNameLabel);
    addKeyFramePanel.add(addKeyFrameShapeNameField);
    addKeyFramePanel.add(addKeyFrameTimeLabel);
    addKeyFramePanel.add(addKeyFrameTimeField);
    addKeyFramePanel.add(addKeyFramePosXLabel);
    addKeyFramePanel.add(addKeyFramePosXField);
    addKeyFramePanel.add(addKeyFramePosYLabel);
    addKeyFramePanel.add(addKeyFramePosYField);
    addKeyFramePanel.add(addKeyFrameWidthLabel);
    addKeyFramePanel.add(addKeyFrameWidthField);
    addKeyFramePanel.add(addKeyFrameHeightLabel);
    addKeyFramePanel.add(addKeyFrameHeightField);
    addKeyFramePanel.add(addKeyFrameRedLabel);
    addKeyFramePanel.add(addKeyFrameRedField);
    addKeyFramePanel.add(addKeyFrameGreenLabel);
    addKeyFramePanel.add(addKeyFrameGreenField);
    addKeyFramePanel.add(addKeyFrameBlueLabel);
    addKeyFramePanel.add(addKeyFrameBlueField);

    /* Panel for deleting keyframes. */
    JPanel deleteKeyFramePanel = new JPanel();
    deleteKeyFramePanel.setPreferredSize(new Dimension(700, 50));
    deleteKeyFramePanel.setMaximumSize(new Dimension(700, 80));
    deleteKeyFramePanel.add(deleteKeyFrameButton);
    deleteKeyFramePanel.add(deleteKeyFrameNameLabel);
    deleteKeyFramePanel.add(deleteKeyFrameShapeNameField);
    deleteKeyFramePanel.add(deleteKeyFrameTimeLabel);
    deleteKeyFramePanel.add(deleteKeyFrameTimeField);

    /* Add the components to this panel. */
    this.add(addShapePanel);
    this.add(deleteShapePanel);
    this.add(addKeyFramePanel);
    this.add(deleteKeyFramePanel);
    this.add(replayButton);
    this.add(playButton);
    this.add(pauseButton);
    this.add(incrementSpeed);
    this.add(decrementSpeed);
    this.add(message);
  }

  public String[] getAddShapeFields() {
    String[] result = new String[9];
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
    playButton.setActionCommand("Play");
    playButton.addActionListener(listener);
    pauseButton.setActionCommand("Pause");
    pauseButton.addActionListener(listener);
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
