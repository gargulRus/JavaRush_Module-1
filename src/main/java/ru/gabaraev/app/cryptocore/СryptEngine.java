package main.java.ru.gabaraev.app.cryptocore;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/*
 * @created 19/01/2022 - 14:50
 * @project JavaRush_Module-1
 * @author Nikolay Gabaraev
 */
public class СryptEngine {

    private static char[] alphabet = { 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж',
            'з', 'и','й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с',
            'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э','ю', 'я' , '.' , '"', ':', '-', '!', '?', ' '};

    public static List<String> encryption (List<String> readedLines, String key, int mode) {
        List<String> result = new ArrayList();

        Integer keyCrypt = 0;
        if (key.length() == 0) {
            keyCrypt = 1;
        } else {
            keyCrypt = Integer.parseInt(key);
        }

        for (int i = 0; i < readedLines.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();

            char[] charLine = readedLines.get(i).toLowerCase(Locale.ROOT).toCharArray();

            for (int j = 0; j < charLine.length; j++) {
                int getIndex = getCryptIndex(charLine[j]);
                if ( getIndex > -1) {
                    int cryptIndex = 0;
                    if (mode == 0) {
                        cryptIndex = getIndex + keyCrypt;
                        if (cryptIndex >= alphabet.length) {
                            cryptIndex = cryptIndex - alphabet.length;
                        }
                    } else if(mode == 1){
                        cryptIndex = getIndex - keyCrypt;
                        if (cryptIndex < 0) {
                            cryptIndex = alphabet.length + (cryptIndex);
                        }
                    }
                    System.out.println(getIndex + " _ " + cryptIndex + " _ " + alphabet[cryptIndex] + " _ " + charLine[j]);
                    stringBuilder.append(alphabet[cryptIndex]);
                } else {
                    stringBuilder.append(charLine[j]);
                }
            }
            result.add(stringBuilder.toString());
        }

        return result;
    }

    public static int getCryptIndex (char c) {
        int index = -1;
        for (int i = 0; i < alphabet.length; ++i) {
            if (c == alphabet[i]) index = i;
        }
        return index;
    }
}
