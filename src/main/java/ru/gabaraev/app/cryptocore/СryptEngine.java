package main.java.ru.gabaraev.app.cryptocore;

import main.java.ru.gabaraev.app.config.Enviroment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/*
 * @created 19/01/2022 - 14:50
 * @project JavaRush_Module-1
 * @author Nikolay Gabaraev
 */
public class СryptEngine {

    public static List<String> encryption (List<String> readedLines, String key, int mode) {
        List<String> result = new ArrayList();

        Integer keyCrypt = 0;
        if (key.length() == 0) {
            keyCrypt = 1;
        } else {
            keyCrypt = Integer.parseInt(key);
            if (keyCrypt > Enviroment.alphabet.length) {
                while (keyCrypt > Enviroment.alphabet.length) {
                    keyCrypt = keyCrypt - Enviroment.alphabet.length;
                }
            } else if (keyCrypt < 0) {
                while (keyCrypt < 0) {
                    keyCrypt = keyCrypt + Enviroment.alphabet.length;
                }
            }
        }
        System.out.println(keyCrypt);
        for (int i = 0; i < readedLines.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();

            char[] charLine = readedLines.get(i).toLowerCase(Locale.ROOT).toCharArray();

            for (int j = 0; j < charLine.length; j++) {
                int getIndex = getCryptIndex(charLine[j]);
                if ( getIndex > -1) {
                    int cryptIndex = 0;
                    if (mode == 0) {
                        cryptIndex = getIndex + keyCrypt;
                        if (cryptIndex >= Enviroment.alphabet.length) {
                            cryptIndex = cryptIndex - Enviroment.alphabet.length;
                        }
                    } else if(mode == 1){
                        cryptIndex = getIndex - keyCrypt;
                        if (cryptIndex < 0) {
                            cryptIndex = Enviroment.alphabet.length + (cryptIndex);
                        }
                    }
                    System.out.println(getIndex + " _ " + cryptIndex + " _ " + Enviroment.alphabet[cryptIndex] + " _ " + charLine[j]);
                    stringBuilder.append(Enviroment.alphabet[cryptIndex]);
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
        for (int i = 0; i < Enviroment.alphabet.length; ++i) {
            if (c == Enviroment.alphabet[i]) index = i;
        }
        return index;
    }
}
