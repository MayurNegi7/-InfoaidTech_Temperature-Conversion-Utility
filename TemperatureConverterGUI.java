
package internshipsproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TemperatureConverterGUI extends JFrame implements ActionListener {
    private JLabel inputLabel, outputLabel, resultLabel;
    private JTextField inputField;
    private JComboBox<String> inputUnitComboBox, outputUnitComboBox;
    private JButton convertButton;

    public TemperatureConverterGUI() {
        setTitle("Temperature Converter");
        setSize(450, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        inputLabel = new JLabel("Enter temperature:");
        inputLabel.setBounds(20, 20, 150, 20);
        add(inputLabel);

        inputField = new JTextField();
        inputField.setBounds(180, 20, 150, 20);
        add(inputField);

        inputUnitComboBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        inputUnitComboBox.setBounds(340, 20, 100, 20);
        add(inputUnitComboBox);

        outputLabel = new JLabel("Select output unit:");
        outputLabel.setBounds(20, 50, 150, 20);
        add(outputLabel);

        outputUnitComboBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        outputUnitComboBox.setBounds(180, 50, 150, 20);
        add(outputUnitComboBox);

        convertButton = new JButton("Convert");
        convertButton.setBounds(180, 80, 100, 30);
        convertButton.addActionListener(this);
        add(convertButton);

        resultLabel = new JLabel();
        resultLabel.setBounds(20, 120, 300, 20);
        add(resultLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            String inputUnit = (String) inputUnitComboBox.getSelectedItem();
            String outputUnit = (String) outputUnitComboBox.getSelectedItem();

            try {
                double temperature = Double.parseDouble(inputField.getText());
                double convertedTemperature = convertTemperature(temperature, inputUnit, outputUnit);
                resultLabel.setText("Converted temperature: " + convertedTemperature + " " + outputUnit);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a numeric temperature value.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private double convertTemperature(double temperature, String inputUnit, String outputUnit) {
        switch (inputUnit) {
            case "Celsius":
                switch (outputUnit) {
                    case "Celsius":
                        return temperature;
                    case "Fahrenheit":
                        return (temperature * 9 / 5) + 32;
                    case "Kelvin":
                        return temperature + 273.15;
                }
                break;
            case "Fahrenheit":
                switch (outputUnit) {
                    case "Celsius":
                        return (temperature - 32) * 5 / 9;
                    case "Fahrenheit":
                        return temperature;
                    case "Kelvin":
                        return (temperature - 32) * 5 / 9 + 273.15;
                }
                break;
            case "Kelvin":
                switch (outputUnit) {
                    case "Celsius":
                        return temperature - 273.15;
                    case "Fahrenheit":
                        return (temperature - 273.15) * 9 / 5 + 32;
                    case "Kelvin":
                        return temperature;
                }
                break;
        }
        return 0.0; // Default return
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TemperatureConverterGUI converter = new TemperatureConverterGUI();
            converter.setVisible(true);
        });
    }
}
