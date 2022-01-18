package main.java.ru.gabaraev.app.gui;

import javax.swing.*;
import java.awt.*;

/*
 * @created 18/01/2022 - 13:50
 * @project JavaRush_Module-1
 * @author Nikolay Gabaraev
 */
public class EncryptionGUI {

    public EncryptionGUI() {
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.setTitle(Main.frameName);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new GridBagLayout());

        JButton btnOk = new JButton("Ok");
        JButton btnCancel = new JButton("Cancel");

    }
}
