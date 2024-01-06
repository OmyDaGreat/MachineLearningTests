package org.example;

import javax.swing.*;
import java.awt.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoanPredictor extends JFrame {
    private static final Log LOGGER = LogFactory.getLog(LoanPredictor.class);
    private final JTextField ageField;
    private final JTextField incomeField;
    private final JComboBox<String> ownershipField;
    private final JTextField empLengthField;
    private final transient LoanClassifier loanClassifier;

    public LoanPredictor(String arffFilePath) throws Exception {
        loanClassifier = new LoanClassifier(arffFilePath);

        setLayout(new GridLayout(5, 2));

        add(new JLabel("Person Age:"));
        ageField = new JTextField();
        add(ageField);

        add(new JLabel("Person Income:"));
        incomeField = new JTextField();
        add(incomeField);

        add(new JLabel("Person Home Ownership:"));
        ownershipField = new JComboBox<>(new String[]{"RENT", "OWN", "MORTGAGE", "OTHER"});
        add(ownershipField);

        add(new JLabel("Person Employment Length:"));
        empLengthField = new JTextField();
        add(empLengthField);

        JButton predictButton = getjButton();
        add(predictButton);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JButton getjButton() {
        JButton predictButton = new JButton("Predict Loan Grade");
        predictButton.addActionListener(e -> {
            try {
                double[] values = {
                        Double.parseDouble(ageField.getText()),
                        Double.parseDouble(incomeField.getText()),
                        ownershipField.getSelectedIndex(),
                        Double.parseDouble(empLengthField.getText())
                };
                String predictedLoanGrade = loanClassifier.predictLoanGrade(values);
                JOptionPane.showMessageDialog(null, "Predicted Loan Grade: " + predictedLoanGrade);
            } catch (Exception ex) {
                LOGGER.error("An error occurred", ex);
            }
        });
        return predictButton;
    }

    public static void main(String[] args) throws Exception {
        new LoanPredictor(System.getProperty("user.home") + "\\IdeaProjects\\MachineLearningTests\\src\\main\\java\\org\\example\\creditRisk.arff");
    }
}
