/**
 * P3GUI.java - Constructs the main interface
 * Begun 11/21/17
 * @author Andrew Eissen
 */

package binarysearchtreeproject3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Main GUI class, extends <code>JFrame</code>
 * @see javax.swing.JFrame
 */
final class P3GUI extends JFrame {

    // Window-related variables
    private String title;
    private int width;
    private int height;

    // GUI-related variables
    private JFrame mainFrame;
    private JPanel mainPanel, originalListPanel, buttonPanel, sortedListPanel, radioPanel,
        sortOrderPanel, numericTypePanel;
    private JLabel originalListLabel, sortedListLabel;
    private JTextField originalListField, sortedListField;
    private JButton sortButton;
    private JRadioButton ascendingButton, descendingButton, integerButton, fractionButton;
    private ButtonGroup sortOrderGroup, numericTypeGroup;

    /**
     * Default constructor
     */
    public P3GUI() {
        super("Binary Search Tree Sort");
        this.setWindowTitle("Binary Search Tree Sort");
        this.setWindowWidth(400);
        this.setWindowHeight(300);
    }

    /**
     * Fully parameterized constructor
     * @param title
     * @param width
     * @param height
     */
    public P3GUI(String title, int width, int height) {
        super(title);
        this.setWindowTitle(title);
        this.setWindowWidth(width);
        this.setWindowHeight(height);
    }

    /**
     * Setter for window <code>title</code>
     * @param title
     * @return void
     */
    private void setWindowTitle(String title) {
        this.title = title;
    }

    /**
     * Setter for window <code>width</code>
     * @param width
     * @return void
     */
    private void setWindowWidth(int width) {
        if (width < 400) {
            this.width = 400;
        } else {
            this.width = width;
        }
    }

    /**
     * Setter for window <code>height</code>
     * @param height
     * @return void
     */
    private void setWindowHeight(int height) {
        if (height < 300) {
            this.height = 300;
        } else {
            this.height = height;
        }
    }

    /**
     * Getter for <code>title</code>
     * @return this.title
     */
    private String getWindowTitle() {
        return this.title;
    }

    /**
     * Getter for <code>width</code>
     * @return this.width
     */
    private int getWindowWidth() {
        return this.width;
    }

    /**
     * Getter for <code>height</code>
     * @return this.height
     */
    private int getWindowHeight() {
        return this.height;
    }

    /**
     * Method assembles the GUI
     * @return void
     */
    private void constructGUI() {
        mainPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Mini-panels
        originalListPanel = new JPanel(new FlowLayout());
        sortedListPanel = new JPanel(new FlowLayout());
        buttonPanel = new JPanel(new FlowLayout());
        radioPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        sortOrderPanel = new JPanel(new GridLayout(2, 1));
        numericTypePanel = new JPanel(new GridLayout(2, 1));

        // Definitions
        originalListLabel = new JLabel("Original List");
        originalListField = new JTextField(20);
        sortedListLabel = new JLabel("Sorted List");
        sortedListField = new JTextField(20);
        sortButton = new JButton("Perform Sort");

        sortOrderGroup = new ButtonGroup();
        numericTypeGroup = new ButtonGroup();
        ascendingButton = new JRadioButton("Ascending");
        descendingButton = new JRadioButton("Descending");
        integerButton = new JRadioButton("Integer");
        fractionButton = new JRadioButton("Fraction");

        // Add to ButtonsGroups
        sortOrderGroup.add(ascendingButton);
        sortOrderGroup.add(descendingButton);
        numericTypeGroup.add(integerButton);
        numericTypeGroup.add(fractionButton);

        // Set default button options so users can't input unselected options
        ascendingButton.setSelected(true);
        integerButton.setSelected(true);

        // Add elements to proper mini-panels
        originalListPanel.add(originalListLabel);
        originalListPanel.add(originalListField);

        buttonPanel.add(sortButton);

        sortedListPanel.add(sortedListLabel);
        sortedListPanel.add(sortedListField);

        sortOrderPanel.add(ascendingButton);
        sortOrderPanel.add(descendingButton);
        numericTypePanel.add(integerButton);
        numericTypePanel.add(fractionButton);

        sortOrderPanel.setBorder(BorderFactory.createTitledBorder("Sort Order"));
        numericTypePanel.setBorder(BorderFactory.createTitledBorder("Numeric Type"));
        radioPanel.add(sortOrderPanel);
        radioPanel.add(numericTypePanel);

        // Add mini-panels to the mainPanel
        mainPanel.add(originalListPanel);
        mainPanel.add(sortedListPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(radioPanel);

        // Results should not be editable
        sortedListField.setEditable(false);

        // Handler for click events
        sortButton.addMouseListener(new GUIMouseAdapter());

        // Assemble Frame
        mainFrame = new JFrame(this.getWindowTitle());
        mainFrame.setSize(this.getWindowWidth(), this.getWindowHeight());
        mainFrame.setContentPane(mainPanel);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Main method, init
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        P3GUI newGUI = new P3GUI();
        newGUI.constructGUI();
    }

    /**
     * Class for handling button clicks, extends <code>MouseAdapter</code>
     * @see java.awt.event.MouseAdapter
     */
    final class GUIMouseAdapter extends MouseAdapter {

        /**
         * Default constructor
         */
        public GUIMouseAdapter() {}

        /**
         * Handles mouse clicks of <code>Perform sort</code> button. Admittedly this implementation
         * is a bit <span style="font-family:Comic Sans MS; color:yellow;">jAnKeD</span> and messy
         * in its essential organization, but it should handle all cases of illegitimate user input.
         * <br><br>
         * Initially, the program was a mess of <code>if/else</code> statements that were not
         * organized according to which numeric type was selected. This current implementation is a
         * reorganization intended to rectify that mistake and make the progression a bit more
         * readable.
         * @param e
         * @return void
         */
        @Override
        public void mousePressed(MouseEvent e) {
            String expression = originalListField.getText();

            // Again, the author made the assumption that input is properly separated by spaces.
            String[] expressionArray = expression.split("\\s+");

            try {
                // Forbid empty input from being processed
                if (expression.equals("")) {
                    this.displayErrorPopup("Error: Input required.", "Error");
                    throw new RuntimeException("Input required.");
                } else if (integerButton.isSelected()) {
                    // Forbid fraction input if integer button is selected
                    if (expression.contains("/")) {
                        this.displayErrorPopup("Error: Integer input cannot contain fractions.",
                            "Error");
                        throw new RuntimeException("Integer input cannot contain fractions.");
                    // Checks that user input is either spaces or proper integers
                    } else if (!expression.matches("^([\\s]|[0-9])+$")) {
                        throw new NumberFormatException("Input must be numeric.");
                    } else {
                        /**
                         * Author's Note
                         *
                         * Admittedly, this code (and the related code below for Fractions) is a bit
                         * messy, but the author couldn't think of how better to attempt this. It
                         * seems a bit unoptimized to create a type array, pass it
                         * <code>Integer</code>/<code>Fraction</code> objects, then pass that array
                         * to the <code>BinarySearchTree</code>. I'm sure there's a better way.
                         */
                        Integer[] integerArray = new Integer[expressionArray.length];

                        for (int i = 0; i < expressionArray.length; i++) {
                            integerArray[i] = Integer.parseInt(expressionArray[i]);
                        }

                        // Pass constructed BinarySearchTree to method to determine sort order
                        this.determineOrder(new BinarySearchTree<>(integerArray));
                    }
                } else if (fractionButton.isSelected()) {
                    // Checks that user input is either spaces, forward slashes, or integers
                    if (!expression.matches("^([\\s]|[\\/]|[0-9])+$")) {
                        throw new NumberFormatException("Input must be numeric.");
                    } else {
                        // See commentary above
                        Fraction[] fractionArray = new Fraction[expressionArray.length];

                        for (int i = 0; i < expressionArray.length; i++) {
                            // Handle cases in which users input simple integers
                            if (!expressionArray[i].contains("/")) {
                                expressionArray[i] = expressionArray[i] + "/1";
                            // Each fraction should have both a numerator and denominator
                            } else if (expressionArray[i].split("/").length != 2) {
                                throw new MalformedFractionException("Malformed fraction.");
                            }

                            fractionArray[i] = new Fraction(expressionArray[i]);
                        }

                        // Pass constructed BinarySearchTree to method to determine sort order
                        this.determineOrder(new BinarySearchTree<>(fractionArray));
                    }
                }

            /**
             * Per rubric, both <code>NumberFormatException</code>s and
             * <code>MalformedFractionException</code>s are caught and handled accordingly.
             */
            } catch (MalformedFractionException ex) {
                this.displayErrorPopup("Error: Malformed fraction.", "Error");
            } catch (NumberFormatException ex) {
                this.displayErrorPopup("Error: Input must be numeric.", "Error");
            }
        }

        /**
         * Utility method to handle sort order. The <code>BinarySearchTree</code> is passed here and
         * the proper sort order method is invoked depending on the user's button selection.
         * Originally included in the above method but moved outside for readability's sake.
         * @param newBST
         * @return void
         */
        private void determineOrder(BinarySearchTree newBST) {
            String sortedString;

            if (ascendingButton.isSelected()) {
                sortedString = newBST.inAscendingOrder(newBST.getRoot());
            } else if (descendingButton.isSelected()) {
                sortedString = newBST.inDescendingOrder(newBST.getRoot());
            } else { // Just in case
                sortedString = "";
            }

            sortedListField.setText(sortedString);
        }

        /**
         * Method simply displays an error pop-up modal.
         * @param message
         * @param title
         * @return void
         */
        private void displayErrorPopup(String message, String title) {
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
        }
    }
}