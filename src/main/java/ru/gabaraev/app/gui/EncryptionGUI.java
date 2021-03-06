package main.java.ru.gabaraev.app.gui;

import main.java.ru.gabaraev.app.config.Enviroment;
import main.java.ru.gabaraev.app.cryptocore.DecryptFile;
import main.java.ru.gabaraev.app.cryptocore.EncryptFile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/*
 * @created 18/01/2022 - 13:51
 * @project JavaRush_Module-1
 * @author Nikolay Gabaraev
 */

public class EncryptionGUI {

    public static JFrame frame = null;

    public static File fileToEncrypt_file = null;
    public static File fileToDecrypt_file = null;

    private static final JLabel fileToEncryptLabel = new JLabel(Enviroment.encryptLabel);
    private static final JButton fileToEncrypt = new JButton(Enviroment.encDecFile);
    private static JLabel fileToEncryptSelected = new JLabel(Enviroment.encDecSelected);

    private static final JLabel fileToDecryptLabel = new JLabel(Enviroment.decryptLabel);
    private static final JButton fileToDecrypt = new JButton(Enviroment.encDecFile);
    private static JLabel fileToDecryptSelected = new JLabel(Enviroment.encDecSelected);

    private  static final JLabel cryptokeyLabel = new JLabel(Enviroment.cryptoLabel);
    private  static JTextField cryptokey = new JTextField(10);

    public static final JButton btnOk = new JButton(Enviroment.buttonOk);
    public static final JButton buttonBack = new JButton(Enviroment.buttonBack);

    public EncryptionGUI(){
        frame = new JFrame();
        frame.setTitle(Enviroment.frameName);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frame.setBounds(dimension.width/2 - 400, dimension.height / 2 - 150, 800, 300);

        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new GridBagLayout());

        mainpanel.add(fileToEncryptLabel, new GridBagConstraints(1,0,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,2), 0,0)
        );
        mainpanel.add(fileToEncrypt, new GridBagConstraints(2,0,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,20), 0,0)
        );
        mainpanel.add(fileToEncryptSelected, new GridBagConstraints(3,0,3,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,20), 0,0)
        );

        mainpanel.add(fileToDecryptLabel, new GridBagConstraints(1,3,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,2), 0,0)
        );
        mainpanel.add(fileToDecrypt, new GridBagConstraints(2,3,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,20), 0,0)
        );
        mainpanel.add(fileToDecryptSelected, new GridBagConstraints(3,3,3,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,20), 0,0)
        );

        mainpanel.add(cryptokeyLabel, new GridBagConstraints(3,4,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,2), 0,0)
        );
        mainpanel.add(cryptokey, new GridBagConstraints(4,4,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,20), 0,0)
        );

        mainpanel.add(btnOk, new GridBagConstraints(5,18,1,1,1,1,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,0), 1,1)
        );
        mainpanel.add(buttonBack, new GridBagConstraints(6,18,1,1,1,1,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,0), 1,1)
        );

        btnOk.addActionListener(new ButtonOk());
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                StartGUI.frame.setVisible(true);
            }
        });

        fileToEncrypt.addActionListener(new fileToEncryptAction());
        fileToDecrypt.addActionListener(new fileToDecryptAction());

        JScrollPane scroll = new JScrollPane(mainpanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        frame.add(scroll);
        frame.setVisible(false);
    }

    static class ButtonOk implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (fileToEncrypt_file != null) {
                int cryptoKey = 0;
                try {
                    cryptoKey = Integer.parseInt(cryptokey.getText());
                    int result = EncryptFile.encryptAction(fileToEncrypt_file, cryptoKey);
                    if (result == 1) {
                        JFrame mdFrame = new JFrame();
                        JOptionPane.showMessageDialog(mdFrame, Enviroment.encryptSuccess);
                        fileToEncrypt_file = null;
                        fileToEncryptSelected.setText(Enviroment.encDecSelected);
                    } else if (result == -1){
                        JFrame mdFrame = new JFrame();
                        JOptionPane.showMessageDialog(mdFrame, Enviroment.encryptError);
                    }
                }catch (NumberFormatException e) {
                    JFrame mdFrame = new JFrame();
                    JOptionPane.showMessageDialog(mdFrame, Enviroment.errorInt, Enviroment.errorTitle, JOptionPane.ERROR_MESSAGE);
                }
            }

            if (fileToDecrypt_file != null) {
                int cryptoKey = 0;
                try {
                    cryptoKey = Integer.parseInt(cryptokey.getText());
                    int result = DecryptFile.decryptAction(fileToDecrypt_file, cryptoKey);
                    if (result == 1) {
                        JFrame mdFrame = new JFrame();
                        JOptionPane.showMessageDialog(mdFrame, Enviroment.decryptSuccess);
                        fileToDecrypt_file = null;
                        fileToDecryptSelected.setText(Enviroment.encDecSelected);
                    } else if (result == -1){
                        JFrame mdFrame = new JFrame();
                        JOptionPane.showMessageDialog(mdFrame, Enviroment.decryptError);
                    }
                }catch (NumberFormatException e) {
                    JFrame mdFrame = new JFrame();
                    JOptionPane.showMessageDialog(mdFrame, Enviroment.errorInt, Enviroment.errorTitle, JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    static class fileToEncryptAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, Enviroment.showDialogText);
            if (ret == JFileChooser.APPROVE_OPTION) {
                fileToEncrypt_file = fileopen.getSelectedFile();
                fileToEncryptSelected.setText(fileToEncrypt_file.getName());
            }
        }
    }

    static class fileToDecryptAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, Enviroment.showDialogText);
            if (ret == JFileChooser.APPROVE_OPTION) {
                fileToDecrypt_file = fileopen.getSelectedFile();
                fileToDecryptSelected.setText(fileToDecrypt_file.getName());
            }
        }
    }

}