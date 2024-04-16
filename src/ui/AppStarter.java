package ui;

import javax.swing.*;

public class AppStarter {
    public static void main(String[] args) {

        CharacterBasedUI cbui = new CharacterBasedUI();
        SwingUtilities.invokeLater(CoffeeShopGUI::new);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cbui.start();

    }
}
