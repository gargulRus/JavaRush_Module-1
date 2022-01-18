package main.java.ru.gabaraev.app.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static main.java.ru.gabaraev.app.cryptocore.DecryptFile.decryptAction;
import static main.java.ru.gabaraev.app.cryptocore.EncryptFile.encryptAction;

/*
 * @created 18/01/2022 - 13:51
 * @project JavaRush_Module-1
 * @author Nikolay Gabaraev
 */
public class CryptanalyzerGUI {

    public static File fileToEncrypt_file = null;
    public static File fileToDecrypt_file = null;

    private static final JLabel fileToEncryptLabel = new JLabel("Выберите файл для зашифровки:");
    private static final JButton fileToEncrypt = new JButton("Файл...");
    private static JLabel fileToEncryptSelected = new JLabel("...");

    private static final JLabel fileToDecryptLabel = new JLabel("Выберите файл для дешифровки:");
    private static final JButton fileToDecrypt = new JButton("Файл...");
    private static JLabel fileToDecryptSelected = new JLabel("...");

    private  static final JLabel cryptokeyLabel = new JLabel("Задайте крипто-ключ :");
    private  static JTextField cryptokey = new JTextField(20);

    public CryptanalyzerGUI (){
        /**
         * Создаем главное окно frame. В нем размещаем mainpamel(JPanel). На mainpanel размещаем элементы интерфейса
         * программы JLabel и JTextField, а так же кнопки "Ok" и "Cancel" (JButton).
         * Разметку задаем через GridBagLayout.
         * Далее создаем скроллбар scroll(JScrollPane). В него передаем mainpanel с объектами.
         * Далее объект scroll добавляем на frame.
         */
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.setTitle(Main.frameName);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new GridBagLayout());

        JButton btnOk = new JButton("Ok");
        JButton btnCancel = new JButton("Cancel");

        mainpanel.add(fileToEncryptLabel, new GridBagConstraints(1,0,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,2), 0,0)
        );
        mainpanel.add(fileToEncrypt, new GridBagConstraints(2,0,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,20), 0,0)
        );
        mainpanel.add(fileToEncryptSelected, new GridBagConstraints(3,0,1,1,0,0,
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
        mainpanel.add(fileToDecryptSelected, new GridBagConstraints(3,3,1,1,0,0,
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


        //КНОПКИ
        mainpanel.add(btnOk, new GridBagConstraints(5,18,1,1,1,1,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,0), 1,1)
        );
        mainpanel.add(btnCancel, new GridBagConstraints(6,18,1,1,1,1,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(20,10,0,0), 1,1)
        );

        btnOk.addActionListener(new ButtonOk());
        btnCancel.addActionListener(new ButtonCancel());
        fileToEncrypt.addActionListener(new fileToEncryptAction());
        fileToDecrypt.addActionListener(new fileToDecryptAction());

        JScrollPane scroll = new JScrollPane(mainpanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        frame.add(scroll);
        frame.setVisible(true);
        frame.pack();
    }

    /**
     * Создаем слушатель для кнопки "Ок".
     * По нажатию на кнопку в класс передаются данные их TextField и записываются в переменные
     * Формирование имени документа:
     * Значение переменной полученной в поле dName записывается в  переменную dogname
     * с подстановкой расширения документа (.docx)
     */
    static class ButtonOk implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {

            if (fileToEncrypt_file != null) {
                String cryptoKey = cryptokey.getText();
                if (encryptAction(fileToEncrypt_file, cryptoKey) == 1) {
                    JFrame mdFrame = new JFrame();
                    JOptionPane.showMessageDialog(mdFrame, "Файл успешно зашифрован!");
                } else if (encryptAction(fileToEncrypt_file, cryptoKey) == -1){
                    JFrame mdFrame = new JFrame();
                    JOptionPane.showMessageDialog(mdFrame, "Файл Существует! Удалите файл!");
                    System.out.println("Файл Существует! Удалите файл!");
                }
            }

            if (fileToDecrypt_file != null) {
                String cryptoKey = cryptokey.getText();
                if (decryptAction(fileToDecrypt_file, cryptoKey) == 1) {
                    JFrame mdFrame = new JFrame();
                    JOptionPane.showMessageDialog(mdFrame, "Файл успешно дешифрован!");
                } else if (decryptAction(fileToDecrypt_file, cryptoKey) == -1){
                    JFrame mdFrame = new JFrame();
                    JOptionPane.showMessageDialog(mdFrame, "Файл Существует! Удалите файл!");
                    System.out.println("Файл Существует! Удалите файл!");
                }
            }

            /** После получения всех данных с JTextField и записи значений в переменные, происходит их передача в
             * класс формирующий получившийся документ(DocAddressCreator).
             * После успешного формирования документа приложение закрывается.
             */
//            new DocAddressCreator(cName, cNum, dateS, dateE, eName, rName, rNameP,
//                    prise, pVat, aAdress, rInn, rKpp, rBank, rRs, rKs, rBik, rPhone, rEmail);

            //System.exit(0);
        }
    }

    static class ButtonCancel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }

    static class fileToEncryptAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                fileToEncrypt_file = fileopen.getSelectedFile();
                fileToEncryptSelected.setText(fileToEncrypt_file.getName());
            }
        }
    }
    static class fileToDecryptAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                fileToDecrypt_file = fileopen.getSelectedFile();
                fileToDecryptSelected.setText(fileToDecrypt_file.getName());
            }
        }
    }


}