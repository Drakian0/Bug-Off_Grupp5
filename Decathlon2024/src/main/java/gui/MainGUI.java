
package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import common.Competitor;
import decathlon.*;
import excel.ExcelPrinter;
import heptathlon.*;


public class MainGUI {

    private JTextField nameField;
    private JTextField resultField;
    private JComboBox<String> disciplineBox;
    private JTextArea outputArea;
    private JTable competitorTable;
    private DefaultTableModel tableModel;
    private ArrayList<Competitor> competitors = new ArrayList<>();

    public static void main(String[] args) {
        new MainGUI().createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Track and Field Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(6, 1));

        // Input for competitor's name
        nameField = new JTextField(20);
        panel.add(new JLabel("Enter Competitor's Name:"));
        panel.add(nameField);

        // Dropdown for selecting discipline
        String[] disciplines = {
                "Dec 100m", "Dec 400m", "Dec 1500m", "Dec 110m Hurdles",
                "Dec Long Jump", "Dec High Jump", "Dec Pole Vault",
                "Dec Discus Throw", "Dec Javelin Throw", "Dec Shot Put",
                "Hep 100m Hurdles", "Hep 200m", "Hep 800m", "Hep Javelin Throw",
                "Hep High Jump", "Hep Long Jump", "Hep Shot Put"
        };
        disciplineBox = new JComboBox<>(disciplines);
        panel.add(new JLabel("Select Discipline:"));
        panel.add(disciplineBox);

        // Input for result
        resultField = new JTextField(10);
        panel.add(new JLabel("Enter Result:"));
        panel.add(resultField);

        // Button to calculate and display result
        JButton calculateButton = new JButton("Calculate Score");
        calculateButton.addActionListener(new CalculateButtonListener());
        panel.add(calculateButton);

        JButton exportButton = new JButton("Export to Excel");
        exportButton.addActionListener(new ExportButtonListener());  // New export button listener
        panel.add(exportButton);  // Add export button to the panel

        // Output area
        outputArea = new JTextArea(5, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        panel.add(scrollPane);

        // Table for displaying competitors and their results
        String[] columnNames = {"Name", "Dec 100m", "Dec 400m", "Dec 1500m",
                "Dec 110m Hurdles", "Dec Long Jump", "Dec High Jump", "Dec Pole Vault",
                "Dec Discus Throw", "Dec Javelin Throw", "Dec Shot Put",
                "Hep 100M Hurdles", "Hep 200M", "Hep 800M", "Hep Javelin Throw",
                "Hep High Jump", "Hep Long Jump", "Hep Shot Put", "Total Score"};
        tableModel = new DefaultTableModel(columnNames, 0);
        competitorTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(competitorTable);
        tableScrollPane.setPreferredSize(new Dimension(750, 200));

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);  // Top panel with inputs
        frame.add(tableScrollPane, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void updateCompetitorTable() {
        tableModel.setRowCount(0); // Rensa tabellen innan uppdatering
        for (Competitor competitor : competitors) {
            tableModel.addRow(competitor.getRowData());
        }
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String discipline = (String) disciplineBox.getSelectedItem();
            String resultText = resultField.getText();

            try {
                double result = Double.parseDouble(resultText);

                int score = 0;
                switch (discipline) {
                    case "Dec 100m":
                        Deca100M deca100M = new Deca100M();
                        score = deca100M.calculateResult(result);
                        break;
                    case "Dec 400m":
                        Deca400M deca400M = new Deca400M();
                        score = deca400M.calculateResult(result);
                        break;
                    case "Dec 1500m":
                        Deca1500M deca1500M = new Deca1500M();
                        score = deca1500M.calculateResult(result);
                        break;
                    case "Dec 110m Hurdles":
                        Deca110MHurdles deca110MHurdles = new Deca110MHurdles();
                        score = deca110MHurdles.calculateResult(result);
                        break;
                    case "Dec Long Jump":
                        DecaLongJump decaLongJump = new DecaLongJump();
                        score = decaLongJump.calculateResult(result);
                        break;
                    case "Dec High Jump":
                        DecaHighJump decaHighJump = new DecaHighJump();
                        score = decaHighJump.calculateResult(result);
                        break;
                    case "Dec Pole Vault":
                        DecaPoleVault decaPoleVault = new DecaPoleVault();
                        score = decaPoleVault.calculateResult(result);
                        break;
                    case "Dec Discus Throw":
                        DecaDiscusThrow decaDiscusThrow = new DecaDiscusThrow();
                        score = decaDiscusThrow.calculateResult(result);
                        break;
                    case "Dec Javelin Throw":
                        DecaJavelinThrow decaJavelinThrow = new DecaJavelinThrow();
                        score = decaJavelinThrow.calculateResult(result);
                        break;
                    case "Dec Shot Put":
                        DecaShotPut decaShotPut = new DecaShotPut();
                        score = decaShotPut.calculateResult(result);
                        break;
                    case "Hep 100m Hurdles":
                        Hep100MHurdles hep100MHurdles = new Hep100MHurdles();
                        score = hep100MHurdles.calculateResult(result);
                        break;
                    case "Hep 200m":
                        Hep200M hep200M = new Hep200M();
                        score = hep200M.calculateResult(result);
                        break;
                    case "Hep 800m":
                        Hep800M hep800M = new Hep800M();
                        score = hep800M.calculateResult(result);
                        break;
                    case "Hep Javelin Throw":
                        HeptJavelinThrow heptJavelinThrow = new HeptJavelinThrow();
                        score = heptJavelinThrow.calculateResult(result);
                        break;
                    case "Hep High Jump":
                        HeptHightJump heptHightJump = new HeptHightJump();
                        score = heptHightJump.calculateResult(result);
                        break;
                    case "Hep Long Jump":
                        HeptLongJump heptLongJump = new HeptLongJump();
                        score = heptLongJump.calculateResult(result);
                        break;
                    case "Hep Shot Put":
                        HeptShotPut heptShotPut = new HeptShotPut();
                        score = heptShotPut.calculateResult(result);
                        break;
                }

                if (competitors.size() >= 40) {
                    JOptionPane.showMessageDialog(null, "Maximum number of competitors reached (40).", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Competitor competitor = findCompetitorByName(name);
                    if (competitor == null) {
                        competitor = new Competitor(name);
                        competitors.add(competitor);
                    }

                    competitor.setScore(discipline, score);
                }

                outputArea.append("Competitor: " + name + "\n");
                outputArea.append("Discipline: " + discipline + "\n");
                outputArea.append("Result: " + result + "\n");
                outputArea.append("Score: " + score + "\n\n");

                updateCompetitorTable(); // Uppdaterar GUI-tabellen med den senaste informationen

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for the result.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Method to find an existing competitor by name
    private Competitor findCompetitorByName(String name) {
        for (Competitor competitor : competitors) {
            if (competitor.getName().equalsIgnoreCase(name)) {
                return competitor;
            }
        }
        return null;  // If not found, return null
    }

    private class ExportButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                exportToExcel();
                JOptionPane.showMessageDialog(null, "Results exported successfully!", "Export Successful", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Failed to export results to Excel.", "Export Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void exportToExcel() throws IOException {
        String[][] data = new String[competitors.size()][];
        int i = 0;
        for (Competitor competitor : competitors) {
            Object[] rowData = competitor.getRowData(); // Get the competitor's row data

            // Ensure the array size matches the number of columns in rowData
            data[i] = new String[rowData.length];

            // Safely copy rowData to data array
            for (int j = 0; j < rowData.length; j++) {
                data[i][j] = (rowData[j] != null) ? rowData[j].toString() : ""; // Handle null values
            }
            i++;
        }

        String[] headers = {
                "Name", "Dec 100m", "Dec 400m", "Dec 1500m", "Dec 110m Hurdles",
                "Dec Long Jump", "Dec High Jump", "Dec Pole Vault",
                "Dec Discus Throw", "Dec Javelin Throw", "Dec Shot Put",
                "Hep 100M Hurdles", "Hep 200M", "Hep 800M", "Hep Javelin Throw",
                "Hep High Jump", "Hep Long Jump", "Hep Shot Put", "Total Score"};

        ExcelPrinter printer = new ExcelPrinter("TrackAndFieldResults");
        printer.addHeaders(headers, "Results");  // Lägg till rubriker
        printer.add(data, "Results");            // Lägg till data
        printer.write();
    }

}
