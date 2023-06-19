import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class UnitConversionProject extends JFrame {

    private JComboBox<String> fromComboBox;
    private JComboBox<String> toComboBox;
    private JTextField quantityField;
    private JButton convertButton;
    private JButton clearButton;
    private JButton exitButton;
    private JLabel resultLabel;

    private static final double FEET_TO_METERS = 0.3048;
    private static final double POUNDS_TO_KGS = 0.453592;
    private static final double FAHRENHEIT_TO_CELSIUS_OFFSET = 32;
    private static final double FAHRENHEIT_TO_CELSIUS_SCALE = 5 / 9.0;

    public UnitConversionProject() {
        super("Unit Conversion");

        // Set up the JFrame
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Java GUI Example");
        setSize(400, 300);

        // Set up the UI components
        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.green); // Set light blue color

        fromComboBox = new JComboBox<>(new String[]{"Feet", "Pounds", "Fahrenheit"});
        fromComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        fromComboBox.setBackground(Color.WHITE);

        toComboBox = new JComboBox<>(new String[]{"Meters", "Kgs", "Celsius"});
        toComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        toComboBox.setBackground(Color.WHITE);

        quantityField = new JTextField(10);
        quantityField.setFont(new Font("Arial", Font.PLAIN, 16));

        convertButton = new JButton("Convert");
        convertButton.setFont(new Font("Arial", Font.BOLD, 16));
        convertButton.setBackground(Color.DARK_GRAY);
        convertButton.setForeground(Color.WHITE);

        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 16));
        clearButton.setBackground(Color.DARK_GRAY);
        clearButton.setForeground(Color.WHITE);

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setBackground(Color.DARK_GRAY);
        exitButton.setForeground(Color.WHITE);

        resultLabel = new JLabel("Result: ");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Add event listener to the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convert();
            }
        });

        // Add event listener to the clear button
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });

        // Add event listener to the exit button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });

        // Add components to the frame
        add(fromComboBox);
        add(new JLabel("to"));
        add(toComboBox);
        add(new JLabel("Quantity:"));
        add(quantityField);
        add(convertButton);
        add(clearButton);
        add(exitButton);
        add(resultLabel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });

        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    private void convert() {
        String fromUnit = (String) fromComboBox.getSelectedItem();
        String toUnit = (String) toComboBox.getSelectedItem();
        double quantity;
        try {
            quantity = Double.parseDouble(quantityField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid quantity value. Please enter a valid number.");
            return;
        }

        if (quantityField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a quantity value.");
            return;
        }

        double result = 0;

        if (fromUnit.equals("Feet") && toUnit.equals("Meters")) {
            result = quantity * FEET_TO_METERS;
        } else if (fromUnit.equals("Pounds") && toUnit.equals("Kgs")) {
            result = quantity * POUNDS_TO_KGS;
        } else if (fromUnit.equals("Fahrenheit") && toUnit.equals("Celsius")) {
            result = (quantity - FAHRENHEIT_TO_CELSIUS_OFFSET) * FAHRENHEIT_TO_CELSIUS_SCALE;
        } else {
            JOptionPane.showMessageDialog(this, "Invalid unit conversion.");
            return;
        }

        resultLabel.setText("Result: " + result);
    }

    private void clear() {
        if (!quantityField.getText().isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear?", "Confirm Clear", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                fromComboBox.setSelectedIndex(0);
                toComboBox.setSelectedIndex(0);
                quantityField.setText("");
                resultLabel.setText("Result: ");
            }
        } else {
            JOptionPane.showMessageDialog(this, "There is nothing to clear.");
        }
    }

    private void exit() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UnitConversionProject();
            }
        });
    }
}
