package main.java.ru.gabaraev.app.cryptocore;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

/*
 * @created 20/01/2022 - 14:37
 * @project JavaRush_Module-1
 * @author Nikolay Gabaraev
 */
public class BruteForceFile {

    public static int bruteForceAction(File file) {

        int result = 0;

        List<String> readedLines = null;
        try {
            readedLines = Files.readAllLines(Paths.get(file.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String encryptFileName = file.getName().split("[.]")[0] + "_bruteforce." + file.getName().split("[.]")[1];
        String filePathToSave = file.getParent() + "\\" + encryptFileName;
        List<String> arrayToSave = BruteForceEngine.bruteForce(readedLines);
        System.out.println("Брутфорсим и сохраняем новый файл по пути - ");
        System.out.println(filePathToSave);

        for (String s : arrayToSave) {
            System.out.println(s);
        }
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
