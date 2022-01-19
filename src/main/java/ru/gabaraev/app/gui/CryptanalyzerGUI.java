package main.java.ru.gabaraev.app.gui;

import main.java.ru.gabaraev.app.config.Enviroment;
import javax.swing.*;
import java.awt.*;

/*
 * @created 18/01/2022 - 13:50
 * @project JavaRush_Module-1
 * @author Nikolay Gabaraev
 */
public class CryptanalyzerGUI {

    public CryptanalyzerGUI() {
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.setTitle(Enviroment.frameName);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new GridBagLayout());

        JButton btnOk = new JButton(Enviroment.buttonOk);
        JButton btnCancel = new JButton(Enviroment.buttonCancel);

    }
}
