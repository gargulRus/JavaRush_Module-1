package main.java.ru.gabaraev.app.cryptocore;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/*
 * @created 20/01/2022 - 15:21
 * @project JavaRush_Module-1
 * @author Nikolay Gabaraev
 */
public class BruteForceEngine {

    private static char[] alphabet = { 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж',
            'з', 'и','й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с',
            'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э','ю', 'я' , '.' , '"', ':', '-', '!', '?', ' '};


    public static List<String> bruteForce(List<String> readedLines) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < readedLines.size(); i++) {
            int stop = 0;
            int key = 0;
            while (stop < alphabet.length) {
                StringBuilder stringBuilder = new StringBuilder();

                char[] charLine = readedLines.get(i).toLowerCase(Locale.ROOT).toCharArray();

                for (int j = 0; j < charLine.length; j++) {
                    int index = getIndex(charLine[j]);
                    int alphabetIndex = index+key;
                    if (index > -1) {
                        if (alphabetIndex >= alphabet.length) {
                            alphabetIndex = alphabetIndex - alphabet.length;
                        }
                        stringBuilder.append(alphabet[alphabetIndex]);
                        System.out.println(alphabet[alphabetIndex]);
                    } else {
                        stringBuilder.append(charLine[j]);
                    }
                }

                System.out.println(stop);
                System.out.println(key);
                result.add(stringBuilder.toString() + " Ключ смещения - " + key);
                key++;
                stop++;
            }
        }

        return result;
    }

    public static int getIndex(char c) {
        int index = -1;
        for (int i = 0; i < alphabet.length; ++i) {
            if (c == alphabet[i]) index = i;
        }
        return index;
    }

}
