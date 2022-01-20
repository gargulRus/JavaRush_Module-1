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
    public static JFrame frame = null;
    public StartGUI() {
        frame = new JFrame();
        frame.setTitle(Enviroment.frameName);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frame.setBounds(dimension.width/2 - 400, dimension.height / 2 - 400, 800, 800);

        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new GridBagLayout());

        JButton buttonTask1 = new JButton(Enviroment.buttonTask1);
        JButton buttonTask2 = new JButton(Enviroment.buttonTask2);
        JButton btnExit = new JButton(Enviroment.buttonExit);

        mainpanel.add(buttonTask1, new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20, 10, 0, 2), 0, 0)
        );

        mainpanel.add(buttonTask2, new GridBagConstraints(3, 0, 1, 1, 0, 0,
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

        buttonTask1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EncryptionGUI();
                frame.setVisible(false);
            }
        });

        buttonTask2.addActionListener(new ActionListener() {
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