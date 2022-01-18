package main.java.ru.gabaraev.app.gui;

import main.java.ru.gabaraev.app.config.Enviroment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * @created 18/01/2022 - 13:45
 * @project JavaRush_Module-1
 * @author Nikolay Gabaraev
 */
public class StartGUI {

    public StartGUI() {
        final JFrame frame = new JFrame();
        frame.setSize(600, 200);
        frame.setTitle(Enviroment.frameName);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new GridBagLayout());

        JButton btnDocLease = new JButton("Шифрование / расшифровка");
        JButton btnDocAddress = new JButton("Криптоанализ");
        JButton btnExit = new JButton("Выход");

        mainpanel.add(btnDocLease, new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20, 10, 0, 2), 0, 0)
        );

        mainpanel.add(btnDocAddress, new GridBagConstraints(3, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20, 10, 0, 2), 0, 0)
        );

        mainpanel.add(btnExit, new GridBagConstraints(5, 15, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20, 10, 0, 0), 1, 1)
        );

        frame.add(mainpanel);
        frame.setVisible(true);
        frame.pack();

        btnDocLease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EncryptionGUI();
                frame.setVisible(false);
            }
        });

        btnDocAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CryptanalyzerGUI();
                frame.setVisible(false);
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}