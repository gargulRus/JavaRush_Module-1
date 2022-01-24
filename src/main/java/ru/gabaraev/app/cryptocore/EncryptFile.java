package main.java.ru.gabaraev.app.cryptocore;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/*
 * @created 18/01/2022 - 15:04
 * @project JavaRush_Module-1
 * @author Nikolay Gabaraev
 */
public class EncryptFile {
    public static int encryptAction(File file, int key) {
        //если файл записать не удастся - вернем -1 в качестве ошибки
        int result = 0;
        List<String> readedLines = null;

        try {
            readedLines = Files.readAllLines(Paths.get(file.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Формируем новый путь для сохранения файла. Добавляем к имени строчку с описанным действием
        String encryptFileName = file.getName().split("[.]")[0] + "_encrypted." + file.getName().split("[.]")[1];
        String filePathToSave = file.getParent() + "\\" + encryptFileName;
        Path out = Paths.get(filePathToSave);
        //Пробуем сохранить файл
        if (!Files.exists(out)) {
            List<String> arrayToSave = СryptEngine.encryption(readedLines, key, 0);
            try {
                Files.write(out, arrayToSave, Charset.defaultCharset());
            } catch (IOException e) {
                e.printStackTrace();
            }
            result = 1;
        } else {
            result = - 1;
        }

        return result;
    }
}
