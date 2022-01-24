package main.java.ru.gabaraev.app.config;

/*
 * @created 18/01/2022 - 18:38
 * @project JavaRush_Module-1
 * @author Nikolay Gabaraev
 */
public class Enviroment {
    //Настройки окружения
    public static final String frameName = "Криптоанализатор";

    //Кнопки главного меню
    public static final String buttonTask1 = "Шифрование / расшифровка";
    public static final String buttonTask2 = "Криптоанализ";
    public static final String buttonExit = "Выход";

    //Кнопки и текст для Шифрования-Дешифрования
    public static final String encryptLabel = "Выберите файл для зашифровки:";
    public static final String decryptLabel = "Выберите файл для дешифровки:";
    //Эти поля используются везде - start
    public static final String encDecFile = "Файл...";
    public static final String encDecSelected = "...";
    //Эти поля используются везде - end
    public static final String showDialogText = "Открыть файл";
    public static final String cryptoLabel = "Задайте крипто-ключ :";
    public static final String buttonOk = "Ок";
    public static final String buttonBack = "Назад";

    //Кнопки и текст для Криптоанализатора
    public static final String fileToParseLabel = "Выберите файл для анализа";
    public static final String fileToHelpLabel = "Выберите второй файл для статистики";
    public static final String bruteForce = "Метод BruteForce";
    public static final String statistical = "Статистический метод";

    //Сообщения об ошибках
    public static final String errorTitle = "Внимание!";
    public static final String errorInt = "Укажите число!";
    public static final String encryptSuccess = "Файл успешно зашифрован!";
    public static final String encryptError = "Файл Существует! Удалите файл!";
    public static final String decryptSuccess = "Файл успешно дешифрован!";
    public static final String decryptError = "Файл Существует! Удалите файл!";
    public static final String selectAnalyzeType = "Выберите метод анализа!";
    public static final String selectFile = "Выберите файл!";

    //Алфавит
    public static final char[] alphabet = { 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж',
            'з', 'и','й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с',
            'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э','ю', 'я' , '.' , '\"', ':', '-', '!', '?', '\u0020'};

}
