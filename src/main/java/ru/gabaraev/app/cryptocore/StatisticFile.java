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
        int result = 0;

        List<String> encryptFileList = null;
        List<String> patternFileList = null;

        try {
            encryptFileList = Files.readAllLines(Paths.get(encryptFile.getPath()));
            patternFileList = Files.readAllLines(Paths.get(patternFile.getPath()));
            System.out.println("Получили два листа с символами из файла");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String encryptFileName = encryptFile.getName().split("[.]")[0] + "_statistic." + encryptFile.getName().split("[.]")[1];
        String filePathToSave = encryptFile.getParent() + "\\" + encryptFileName;
        System.out.println("Запускаем метод дешифровки статистикой");
        List<String> arrayToSave = StatisticEngine.statisticAction(encryptFileList, patternFileList);
        System.out.println("Брутфорсим и сохраняем новый файл по пути - ");
        System.out.println(filePathToSave);

        Path out = Paths.get(filePathToSave);
        if (!Files.exists(out)) {
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
