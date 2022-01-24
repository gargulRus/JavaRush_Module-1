package main.java.ru.gabaraev.app.cryptocore;

import main.java.ru.gabaraev.app.config.Enviroment;
import java.util.ArrayList;
import java.util.List;

/*
 * @created 20/01/2022 - 15:21
 * @project JavaRush_Module-1
 * @author Nikolay Gabaraev
 */
public class BruteForceEngine {

    public static List<String> bruteForce(List<String> readLines) {

        List<String> result = new ArrayList<>();
        StringBuilder toCharBuilder = new StringBuilder();

        for (String s : readLines) {
            toCharBuilder.append(s + "\n");
        }

        char[] charsFromArr = toCharBuilder.toString().toCharArray();
        int stop = 0;
        int key = 0;

        //Принцип схож с тем, что используется в задании 1 при шифровании.
        //Обходим полученный массив построчно, задавая смещение на +1 каждую итерацию. Проверяем условия.
        //если условие подошло - в даннм случае - score < 26, то это необходимое смещение.
        while (stop < Enviroment.alphabet.length) {
            StringBuilder endBuilder = new StringBuilder();
            for (int i = 0; i < charsFromArr.length; i++) {
                int index = getIndex(charsFromArr[i]);
                int alphabetIndex = index+key;
                if (index > -1) {
                    if (alphabetIndex >= Enviroment.alphabet.length) {
                        alphabetIndex = alphabetIndex - Enviroment.alphabet.length;
                    }
                    endBuilder.append(Enviroment.alphabet[alphabetIndex]);
                }else {
                    endBuilder.append(charsFromArr[i]);
                }
            }

            key++;
            stop++;

            char[] afterChar = endBuilder.toString().toCharArray();
            int score = 0;

            for (int i = 0; i < afterChar.length; i++) {
                if(score < 26) {
                    //учитываем такое условие - длинна слова не более 26 символов.
                    //дополнительно учитываем слова с дефисами, если имеются.
                    if (Character.isWhitespace(afterChar[i])) {
                        score = 0;
                    } else if (Character.isSpaceChar(afterChar[i])) {
                        score = 0;
                    } else if (afterChar[i] == '\u0020') {
                        score = 0;
                    } else if (afterChar[i] == '-') {
                        if (i < afterChar.length - 1 && i > 0) {
                            if (Character.isLetter(afterChar[i-1]) && afterChar[i] == '-' && Character.isLetter(afterChar[i+1])) {
                                score = 0;
                            }
                        }
                    }
                    else {
                        score++;
                    }
                } else {
                    break;
                }
            }
            if (score < 26) {
                result.add(endBuilder.toString());
                break;
            }
        }
        return result;
    }

    public static int getIndex(char c) {
        int index = -1;
        for (int i = 0; i < Enviroment.alphabet.length; ++i) {
            if (c == Enviroment.alphabet[i]) index = i;
        }
        return index;
    }

}
