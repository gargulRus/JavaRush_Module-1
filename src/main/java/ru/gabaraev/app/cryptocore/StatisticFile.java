package main.java.ru.gabaraev.app.cryptocore;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/*
 * @created 20/01/2022 - 18:55
 * @project JavaRush_Module-1
 * @author Nikolay Gabaraev
 */
public class StatisticFile {
    public static int statisticAction(File encryptFile, File patternFile) {
        //если файл записать не удастся - вернем -1 в качестве ошибки
        int result = 0;
        List<String> encryptFileList = null;
        List<String> patternFileList = null;

        try {
            encryptFileList = Files.readAllLines(Paths.get(encryptFile.getPath()));
            patternFileList = Files.readAllLines(Paths.get(patternFile.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Формируем новый путь для сохранения файла. Добавляем к имени строчку с описанным действием
        String encryptFileName = encryptFile.getName().split("[.]")[0] + "_statistic." + encryptFile.getName().split("[.]")[1];
        String filePathToSave = encryptFile.getParent() + "\\" + encryptFileName;
        Path out = Paths.get(filePathToSave);
        //Пробуем сохранить файл
        if (!Files.exists(out)) {
            List<String> arrayToSave = StatisticEngine.statisticAction(encryptFileList, patternFileList);
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
