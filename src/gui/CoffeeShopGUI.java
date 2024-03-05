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

    public CoffeeShopGUI() {
        prepareGUI();

    }

    private void prepareGUI() {
        mainFrame = new JFrame("Coffee Shop Application");
        mainFrame.setSize(600, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuPanel = createMenuPanel();
        mainFrame.add(menuPanel, BorderLayout.CENTER);

        mainFrame.setVisible(true);
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1)); // 8 rows, 1 column for menu options

        JLabel titleLabel = new JLabel("Coffee Shop Menu");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel);

        JButton uploadFileButton = new JButton("Upload Properties File");
        uploadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(mainFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();
                    // TODO: Call the uploadPropertiesFile method from the CoffeeShopClient
                    testClient = new TestClient();
                    System.out.println("Selected file: " + filePath);
                    testClient.uploadPropertiesFile(filePath);
                }
            }
        });
        panel.add(uploadFileButton);

        JButton showConfigurationsButton = new JButton("Show Available Configurations");
        panel.add(showConfigurationsButton);

        JButton printCoffeeShopButton = new JButton("Print Coffee Shop");
        panel.add(printCoffeeShopButton);

        JButton deleteCoffeeShopButton = new JButton("Delete Coffee Shop");
        panel.add(deleteCoffeeShopButton);

        JButton updateBasePriceButton = new JButton("Update Base Price");
        panel.add(updateBasePriceButton);

        JButton addOptionButton = new JButton("Add Option to Option Set");
        panel.add(addOptionButton);

        JButton exitButton = new JButton("Exit");
        panel.add(exitButton);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CoffeeShopGUI();
        });
    }
}
