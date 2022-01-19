package main.java.ru.gabaraev.app.cryptocore;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/*
 * @created 18/01/2022 - 16:26
 * @project JavaRush_Module-1
 * @author Nikolay Gabaraev
 */
public class DecryptFile {

    public static int decryptAction(File file, String key) {
        int result = 0;

        List<String> readedLines = null;
        try {
            readedLines = Files.readAllLines(Paths.get(file.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encryptFileName = file.getName().split("[.]")[0] + "_decrypted." + file.getName().split("[.]")[1];
        String filePathToSave = file.getParent() + "\\" + encryptFileName;
        List<String> arrayToSave = СryptEngine.encryption(readedLines, key, 1);
        System.out.println("Дешифруем и сохраняем новый файл по пути - ");
        System.out.println(filePathToSave);
        System.out.println("Смещение " + key);

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
