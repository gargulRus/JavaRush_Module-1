package main.java.ru.gabaraev.app.gui;

/*
 * @created 18/01/2022 - 11:48
 * @project JavaRush_Module-1
 * @author Nikolay Gabaraev
 */
public class Main {

    static StartGUI mainGUI = null;
    static EncryptionGUI encryptionGUI = null;
    static CryptAnalyzerGUI cryptAnalyzerGUI = null;

    public static void main(String[] args) {
        //При старте сразу создаем несколько окон, переключаться на которые будем посредством setVisible
        mainGUI = new StartGUI();
        encryptionGUI = new EncryptionGUI();
        cryptAnalyzerGUI = new CryptAnalyzerGUI();
    }
}
