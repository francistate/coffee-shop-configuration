package gui;

import client.TestClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CoffeeShopGUI {
    TestClient testClient;
    private JFrame mainFrame;
    private JPanel menuPanel;

    // Constructor
    public CoffeeShopGUI() {
        prepareGUI();
        testClient = new TestClient();

    }

    // Helper methods
    private void printAllCoffeeShops() {
        String[] configurations = testClient.getAllCoffeeShopNames();
        if (configurations != null && configurations.length > 0) {
            StringBuilder sb = new StringBuilder();
            int i = 1;
            for (String config : configurations) {
                sb.append(i + ": ").append(config).append("\n");
                i++;
            }
            JOptionPane.showMessageDialog(mainFrame, sb.toString(), "Available Coffee Shops", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(mainFrame, "No coffee shops available.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    // create and set up the GUI
    private void prepareGUI() {
        mainFrame = new JFrame("Coffee Shop Application");
        mainFrame.setSize(800, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuPanel = createMenuPanel();
        mainFrame.add(menuPanel, BorderLayout.CENTER);

        mainFrame.setVisible(true);
    }

    // create the menu panel
    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1)); // 8 rows, 1 column for menu options
        // add title label
        JLabel titleLabel = new JLabel("Coffee Shop Menu");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel);

        // create buttons for each menu option
        // upload properties file
        JButton uploadFileButton = new JButton("Upload Properties File");
        uploadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JFileChooser fileChooser = new JFileChooser();
//                int result = fileChooser.showOpenDialog(mainFrame);
//                if (result == JFileChooser.APPROVE_OPTION) {
//                    File selectedFile = fileChooser.getSelectedFile();
//                    String filePath = "C:\\Users\\Tatenda\\Documents\\config.properties";
//                    String filePath = selectedFile.getAbsolutePath();
//
//
////                    testClient = new TestClient();
//                    System.out.println("Selected file: " + filePath);
//                    testClient.uploadPropertiesFile(filePath);
//                }
                String filePath = "C:\\Users\\Tatenda\\Documents\\config.properties";
                testClient.uploadPropertiesFile(filePath);
            }
        });
        panel.add(uploadFileButton);

        // show available configurations / coffee shops
        JButton showConfigurationsButton = new JButton("Show Available Configurations");
        showConfigurationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                printAllCoffeeShops();

            }
        });
        panel.add(showConfigurationsButton);

        // print a coffee shop
        JButton printCoffeeShopButton = new JButton("Print Coffee Shop");
        printCoffeeShopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
//                printAllCoffeeShops();
                String[] configurations = testClient.getAllCoffeeShopNames();
                if (configurations != null && configurations.length > 0) {
                    String selectedConfig = (String) JOptionPane.showInputDialog(mainFrame, "Select a coffee shop to print", "Print Coffee Shop", JOptionPane.QUESTION_MESSAGE, null, configurations, configurations[0]);
                    if (selectedConfig != null && !selectedConfig.isEmpty()) {
                        String coffeeShop = testClient.getCoffeeShop(selectedConfig);
                        if (coffeeShop != null && !coffeeShop.isEmpty()) {
                            JOptionPane.showMessageDialog(mainFrame, coffeeShop, "Coffee Shop Configuration", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(mainFrame, "Error occurred while retrieving coffee shop configuration.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "No coffee shops available.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        panel.add(printCoffeeShopButton);

        // delete a coffee shop
        JButton deleteCoffeeShopButton = new JButton("Delete Coffee Shop");
        deleteCoffeeShopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
//                printAllCoffeeShops();
                String[] configurations = testClient.getAllCoffeeShopNames();
                if (configurations != null && configurations.length > 0) {
                    String selectedConfig = (String) JOptionPane.showInputDialog(mainFrame, "Select a coffee shop to delete", "Delete Coffee Shop", JOptionPane.QUESTION_MESSAGE, null, configurations, configurations[0]);
                    if (selectedConfig != null && !selectedConfig.isEmpty()) {
                        testClient.deleteCoffeeShop(selectedConfig);
                        JOptionPane.showMessageDialog(mainFrame, "Coffee shop deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "No coffee shops available.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        panel.add(deleteCoffeeShopButton);

        // update base price
        JButton updateBasePriceButton = new JButton("Update Base Price");
        updateBasePriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                printAllCoffeeShops();
                String[] configurations = testClient.getAllCoffeeShopNames();
                if (configurations != null && configurations.length > 0) {
                    String selectedConfig = (String) JOptionPane.showInputDialog(mainFrame, "Select a coffee shop to update base price", "Update Base Price", JOptionPane.QUESTION_MESSAGE, null, configurations, configurations[0]);
                    if (selectedConfig != null && !selectedConfig.isEmpty()) {
                        String newPriceStr = JOptionPane.showInputDialog(mainFrame, "Enter new base price for " + selectedConfig, "Update Base Price", JOptionPane.QUESTION_MESSAGE);
                        if (newPriceStr != null && !newPriceStr.isEmpty()) {
                            try {
                                double newPrice = Double.parseDouble(newPriceStr);
                                testClient.updateBasePrice(selectedConfig, newPrice);
                                JOptionPane.showMessageDialog(mainFrame, "Base price updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(mainFrame, "Invalid base price entered.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "No coffee shops available.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        panel.add(updateBasePriceButton);

        // add an option to any option set
        JButton addOptionButton = new JButton("Add Option to Option Set");
        addOptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                printAllCoffeeShops();
                String[] configurations = testClient.getAllCoffeeShopNames();
                if (configurations != null && configurations.length > 0) {
                    String selectedConfig = (String) JOptionPane.showInputDialog(mainFrame, "Select a coffee shop to add option to option set", "Add Option to Option Set", JOptionPane.QUESTION_MESSAGE, null, configurations, configurations[0]);
                    String coffeeShop = testClient.getCoffeeShop(selectedConfig);
                    if (coffeeShop != null && !coffeeShop.isEmpty()) {
                        JOptionPane.showMessageDialog(mainFrame, coffeeShop, "Coffee Shop Configuration", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(mainFrame, "Error occurred while retrieving coffee shop configuration.", "Error", JOptionPane.ERROR_MESSAGE);
                    }


                    if (selectedConfig != null && !selectedConfig.isEmpty()) {
                        String[] optionSets = testClient.getOptionSetNames(selectedConfig);
                        for (String optionSet : optionSets) {
                            System.out.println("OptionSet: " + optionSet);
                        }
//                        System.out.println("OptionSets: " + optionSets);
                        if (optionSets != null && optionSets.length > 0) {
                            String selectedOptionSet = (String) JOptionPane.showInputDialog(mainFrame, "Select an option set to add option", "Add Option to Option Set", JOptionPane.QUESTION_MESSAGE, null, optionSets, optionSets[0]);
                            if (selectedOptionSet != null && !selectedOptionSet.isEmpty()) {
                                String newOptionName = JOptionPane.showInputDialog(mainFrame, "Enter new option name", "Add Option to Option Set", JOptionPane.QUESTION_MESSAGE);
                                if (newOptionName != null && !newOptionName.isEmpty()) {
                                    String newOptionPriceStr = JOptionPane.showInputDialog(mainFrame, "Enter new option price", "Add Option to Option Set", JOptionPane.QUESTION_MESSAGE);
                                    if (newOptionPriceStr != null && !newOptionPriceStr.isEmpty()) {
                                        try {
                                            double newOptionPrice = Double.parseDouble(newOptionPriceStr);
                                            testClient.addOptionToOptionSet(selectedConfig, selectedOptionSet, newOptionName, newOptionPrice);
                                            JOptionPane.showMessageDialog(mainFrame, "Option added to option set successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                                        } catch (NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(mainFrame, "Invalid option price entered.", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(mainFrame, "No option sets available.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
//                        String optionSetName = JOptionPane.showInputDialog(mainFrame, "Enter option set name", "Add Option to Option Set", JOptionPane.QUESTION_MESSAGE);
//                        if (optionSetName != null && !optionSetName.isEmpty()) {
//                            String newOptionName = JOptionPane.showInputDialog(mainFrame, "Enter new option name", "Add Option to Option Set", JOptionPane.QUESTION_MESSAGE);
//                            if (newOptionName != null && !newOptionName.isEmpty()) {
//                                String newOptionPriceStr = JOptionPane.showInputDialog(mainFrame, "Enter new option price", "Add Option to Option Set", JOptionPane.QUESTION_MESSAGE);
//                                if (newOptionPriceStr != null && !newOptionPriceStr.isEmpty()) {
//                                    try {
//                                        double newOptionPrice = Double.parseDouble(newOptionPriceStr);
//                                        testClient.addOptionToOptionSet(selectedConfig, optionSetName, newOptionName, newOptionPrice);
//                                        JOptionPane.showMessageDialog(mainFrame, "Option added to option set successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
//                                    } catch (NumberFormatException ex) {
//                                        JOptionPane.showMessageDialog(mainFrame, "Invalid option price entered.", "Error", JOptionPane.ERROR_MESSAGE);
//                                    }
//                                }
//                            }
//                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "No coffee shops available.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        panel.add(addOptionButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(exitButton);





        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CoffeeShopGUI();
        });
    }
}
