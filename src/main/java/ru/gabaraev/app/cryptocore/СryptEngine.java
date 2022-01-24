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
    public static List<String> encryption (List<String> readLines, int key, int mode) {

        List<String> result = new ArrayList();
        Integer keyCrypt = key;
        //Если ключ со смещением придет больше чем длинна массива - необходимо это обработать. Зациклить.
            if (keyCrypt > Enviroment.alphabet.length) {
                while (keyCrypt > Enviroment.alphabet.length) {
                    keyCrypt = keyCrypt - Enviroment.alphabet.length;
                }
            } else if (keyCrypt < 0) {
                while (keyCrypt < 0) {
                    keyCrypt = keyCrypt + Enviroment.alphabet.length;
                }
            }
        //Теперь обычный перебор. В полученном массиве каждую строчку бьем на символы и сдвигаем их
        //основываясь на алфавите
        for (int i = 0; i < readLines.size(); i++) {

            StringBuilder stringBuilder = new StringBuilder();
            char[] charLine = readLines.get(i).toLowerCase(Locale.ROOT).toCharArray();

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
