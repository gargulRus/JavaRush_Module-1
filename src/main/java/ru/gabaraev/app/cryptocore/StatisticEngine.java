package main.java.ru.gabaraev.app.cryptocore;

import main.java.ru.gabaraev.app.config.Enviroment;
import java.util.*;

/*
 * @created 20/01/2022 - 18:56
 * @project JavaRush_Module-1
 * @author Nikolay Gabaraev
 */
public class StatisticEngine {

    public static int allCharsEnc = 0;
    public static int allCharsPatt = 0;

    public static List<String> statisticAction(List<String> encryptFileList, List<String> patternFileList) {

        List<String> resultList = new ArrayList<>();
        //Создаем две мапы из принимаемых в методе строк, pointer нужен для подсчета кол-ва символов.
        Map<Character, Integer> encryptMap = fillMap(encryptFileList , 0);
        Map<Character, Integer> patternMap = fillMap(patternFileList , 1);
        //Создаем две мапы для подсчета процентного соотношения.
        Map<Character, Double> sortedEncryptPercentMap = percentCountMap(encryptMap, allCharsEnc);
        Map<Character, Double> sortedPatternPercentMap = percentCountMap(patternMap, allCharsPatt);
        //Итоговые мапы для сравнения.
        Map<Integer, Character> resultEncryptPercentMap = countingMap(sortedEncryptPercentMap);
        Map<Integer, Character> resultPatternPercentMap = countingMap(sortedPatternPercentMap);

        //Далее перебираем первую мапу, и сравнивая ключи, подставляем значения из второй мапы.
        for (int i = 0; i < encryptFileList.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();

            char[] charLine = encryptFileList.get(i).toLowerCase(Locale.ROOT).toCharArray();
            for (int j = 0; j < charLine.length; j++) {
                //Имея две отсортированные мапы по процентному соотношению символов в текстах,
                // подменяем символ из зашифрованной мапы, символом из мапы полученной из файла-шаблона.
                if (resultEncryptPercentMap.containsValue(charLine[j])) {
                    //если символ присутствует в зашифрованной мапе,
                    // ищем соотвествующую позицию в мапе из файла-шаблона.
                    int key = findKey(resultEncryptPercentMap, charLine[j]);
                    if (key > -1) {
                        stringBuilder.append(resultPatternPercentMap.get(key));
                    } else {
                        //если вдруг случиться так, что не найдем - оставим символ как есть.
                        stringBuilder.append(charLine[j]);
                    }
                } else {
                    //и аналогично, если символа нету в зашифрованной мапе - оставляем символ как есть.
                    stringBuilder.append(charLine[j]);
                }
            }
            resultList.add(stringBuilder.toString());
        }

        return resultList;
    }

    //Метод наполнения мапы из входного массива с данными.
    //Исползуем тут метод checkChar(), сравнивания символы с алфавитом Enviroment.alphabet, для урощения процесса.
    public static Map<Character, Integer> fillMap (List<String> arrayList, int pointer) {
        HashMap<Character, Integer> filledMap = new HashMap<>();
        int allChars = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            char[] charLine = arrayList.get(i).toLowerCase(Locale.ROOT).toCharArray();
            for (int j = 0; j < charLine.length; j++) {
                allChars++;
                if(checkChar(charLine[j])) {
                    if (!filledMap.containsKey(charLine[j])) {
                        filledMap.put(charLine[j], 1);
                    } else {
                        Integer count = filledMap.get(charLine[j]) + 1;
                        filledMap.put(charLine[j], count);
                    }
                }
            }
        }
        //Указатель для какой мапы считали общее количество символов.
        if (pointer == 0) {
            allCharsEnc = allChars;
        } else if (pointer == 1) {
            allCharsPatt = allChars;
        }
        return filledMap;
    }

    //Метод поиска символа в алфавите.
    public static boolean checkChar (char c) {
        boolean result = false;
        for (int i = 0; i < Enviroment.alphabet.length; ++i) {
            if (c == Enviroment.alphabet[i]) result = true;
        }
        return result;
    }
    //Метод для поиска ключа в мапе
    public static int findKey (Map<Integer, Character> map, char c) {
        int key = -1;
        for (Map.Entry<Integer, Character> entry : map.entrySet()) {
            if (entry.getValue().equals(c)) {
                key = entry.getKey();
            }
        }
        return key;
    }
    //Метод подсчета процентного соотношения каждого символа, относительно общего количества.
    public static Map<Character, Double> percentCountMap (Map<Character, Integer> parentMap, int charsCount) {
        Map<Character, Double> sortedMap;
        Map<Character, Double> percentMap = new HashMap<>();

        for (Map.Entry<Character, Integer> entry : parentMap.entrySet()) {
            Double persentCount = ( entry.getValue() * 100 ) / (double) charsCount;
            persentCount = (Math.round(persentCount * 100) / 100D);
            percentMap.put(entry.getKey(), persentCount);
        }

        sortedMap = percentMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(LinkedHashMap::new,
                        (m ,c ) -> m.put(c.getKey(), c.getValue()),
                        LinkedHashMap::putAll);

        return sortedMap;
    }
    //Метод для создания указателей в мапе, для последующей выборки.
    public static Map<Integer, Character> countingMap (Map<Character, Double> sortedMap) {
        Map<Integer, Character> countedMap = new LinkedHashMap<>();

        int pp = 1;
        for (Map.Entry<Character, Double> entry : sortedMap.entrySet()) {
            countedMap.put(pp, entry.getKey());
            pp++;
        }
        return countedMap;
    }

}
