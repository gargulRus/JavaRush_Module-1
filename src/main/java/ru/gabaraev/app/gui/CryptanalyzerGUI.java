package main.java.ru.gabaraev.app.gui;

import main.java.ru.gabaraev.app.config.Enviroment;
import main.java.ru.gabaraev.app.cryptocore.BruteForceFile;
import main.java.ru.gabaraev.app.cryptocore.StatisticFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/*
 * @created 18/01/2022 - 13:50
 * @project JavaRush_Module-1
 * @author Nikolay Gabaraev
 */
public class CryptanalyzerGUI {

    static JFrame frame = new JFrame();
    static JFrame helpFrame = new JFrame();
    public static File fileToParse_file = null;
    public static File fileToHelp_file = null;

    public static final JLabel fileToParseLabel = new JLabel("Выберите файл для анализа");
    public static final JLabel fileToHelpLabel = new JLabel("Выберите второй файл для статистики ");
    public static final JButton fileToParse = new JButton("Файл...");
    public static final JButton fileToHelp = new JButton(" Файл...");
    public static final JLabel fileToParseSelected = new JLabel("...");
    public static final JLabel fileToHelpSelected = new JLabel(" ...");
    public static final ButtonGroup btnGroup = new ButtonGroup();
    public static final JRadioButton bruteForce = new JRadioButton("Метод BruteForceFile");
    public static final JRadioButton statistical = new JRadioButton("Статистический метод");

    public static final JButton btnOk = new JButton(Enviroment.buttonOk);
    public static final JButton btnHelpOk = new JButton(Enviroment.buttonOk);
    public static final JButton buttonBack = new JButton(Enviroment.buttonBack);

    public CryptanalyzerGUI() {
//        JFrame frame = new JFrame();
        //frame.setSize(800, 800);
        frame.setTitle(Enviroment.frameName);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frame.setBounds(dimension.width/2 - 350, dimension.height / 2 - 150, 700, 300);

        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new GridBagLayout());

        mainpanel.add(fileToParseLabel, new GridBagConstraints(1,0,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,20), 0,0)
        );

        mainpanel.add(bruteForce, new GridBagConstraints(1,1,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,2), 0,0)
        );

        mainpanel.add(statistical, new GridBagConstraints(1,2,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,2), 0,0)
        );
        mainpanel.add(fileToParse, new GridBagConstraints(2,0,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,2), 0,0)
        );
        mainpanel.add(fileToParseSelected, new GridBagConstraints(3,0,3,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,0), 0,0)
        );

        mainpanel.add(btnOk, new GridBagConstraints(4,3,1,1,1,1,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,5,2), 1,1)
        );
        mainpanel.add(buttonBack, new GridBagConstraints(5,3,1,1,1,1,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,5,20), 1,1)
        );

        btnGroup.add(bruteForce);
        btnGroup.add(statistical);

        btnOk.addActionListener(new ButtonOk());

        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                StartGUI.frame.setVisible(true);
            }
        });

        fileToParse.addActionListener(new fileToEncryptAction());

        JScrollPane scroll = new JScrollPane(mainpanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        frame.add(scroll);
        frame.setVisible(false);
        //frame.pack();

        helpFrame.setSize(700, 200);
        helpFrame.setTitle(Enviroment.frameName);
        helpFrame.setLocationRelativeTo(null);
        JPanel helppanel = new JPanel();
        helppanel.setLayout(new GridBagLayout());
        helppanel.add(fileToHelpLabel,new GridBagConstraints(1,0,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,20), 0,0));
        helppanel.add(fileToHelp,new GridBagConstraints(2,0,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,20), 0,0));
        helppanel.add(fileToHelpSelected,new GridBagConstraints(3,0,3,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,20), 0,0));
        helppanel.add(btnHelpOk,new GridBagConstraints(4,1,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,0), 0,0));

        JScrollPane scrollHelp = new JScrollPane(helppanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        helpFrame.add(scrollHelp);
        helpFrame.setVisible(false);
    }

    static class ButtonOk implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (fileToParse_file != null) {
                System.out.println("Файл получен - готовы к взлому");

                if (bruteForce.isSelected()) {
                    System.out.println("Будем брутфорсить");
                    int result = BruteForceFile.bruteForceAction(fileToParse_file);
                    if (result == 1 ) {
                        JFrame mdFrame = new JFrame();
                        JOptionPane.showMessageDialog(mdFrame, "Расшифровано!", "Внимание!", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Расшифровано");
                        fileToParse_file = null;
                        fileToParseSelected.setText(Enviroment.encDecSelected);
                    } else if (result == 0) {
                        System.out.println("Ошибка");
                    } else if (result == -1) {
                        JFrame mdFrame = new JFrame();
                        JOptionPane.showMessageDialog(mdFrame, "Файл существует! Удалите файл!", "Внимание!", JOptionPane.ERROR_MESSAGE);
                    }

                } else if (statistical.isSelected()) {
                    System.out.println("Будем дешифровать статистикой");
                    helpFrame.setVisible(true);
                    fileToHelp.addActionListener(new fileToHelpAction());
                    btnHelpOk.addActionListener(new ButtonHelpOk());
                } else {
                    JFrame mdFrame = new JFrame();
                    JOptionPane.showMessageDialog(mdFrame, "Выберите метод анализа!", "Внимание!", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JFrame mdFrame = new JFrame();
                JOptionPane.showMessageDialog(mdFrame, "Выберите файл!", "Внимание!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    static class ButtonHelpOk implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(fileToParse_file != null && fileToHelp_file != null) {
                System.out.println("Получили два файла. Выбран метод статистики");
                int result = StatisticFile.statisticAction(fileToParse_file, fileToHelp_file);
                if (result == 1) {
                    JFrame mdFrame = new JFrame();
                    JOptionPane.showMessageDialog(mdFrame, "Файл расшифрован");
                    fileToParse_file = null;
                    fileToHelp_file = null;
                    fileToParseSelected.setText(Enviroment.encDecSelected);
                    helpFrame.setVisible(false);
                } else if (result == -1){
                    JFrame mdFrame = new JFrame();
                    JOptionPane.showMessageDialog(mdFrame, "Ошибка! файл сущетсвует удалите!");
                    fileToHelp_file = null;
                    fileToHelpSelected.setText(Enviroment.encDecSelected);
                }

            }
        }
    }
    static class fileToEncryptAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, Enviroment.showDialogText);
            if (ret == JFileChooser.APPROVE_OPTION) {
                fileToParse_file = fileopen.getSelectedFile();
                fileToParseSelected.setText(fileToParse_file.getName());

            }
        }
    }

    static class fileToHelpAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, Enviroment.showDialogText);
            if (ret == JFileChooser.APPROVE_OPTION) {
                fileToHelp_file = fileopen.getSelectedFile();
                fileToHelpSelected.setText(fileToHelp_file.getName());

            }
        }
    }

}
