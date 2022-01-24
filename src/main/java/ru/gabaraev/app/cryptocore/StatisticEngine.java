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
        //Создаем две мапы из принимаемых в методе строк, pointer нужен для подсчета кол-ва символов
        Map<Character, Integer> encryptMap = fillMap(encryptFileList , 0);
        Map<Character, Integer> patternMap = fillMap(patternFileList , 1);
        //Создаем две мапы для подсчета процентного соотношения
        Map<Character, Double> encryptPercentMap = new TreeMap<>();
        Map<Character, Double> patternPercentMap = new TreeMap<>();
        //Мапы для отсортированных данных
        Map<Character, Double> sortedEncryptPercentMap;
        Map<Character, Double> sortedPatternPercentMap;
        //Итоговые мапы для сравнения
        Map<Integer, Character> resultEncryptPercentMap = new LinkedHashMap<>();
        Map<Integer, Character> resultPatternPercentMap = new LinkedHashMap<>();

        //В Полученных Мапах рассчитываем процентное соотношение символов к общему числу символов
        //и после через стримы сортируем по полученным процентам и сохраняем в новую мапу.
        //Не самое элегантное решение....но на что хватило мозгов =)
        for (Map.Entry<Character, Integer> entry : encryptMap.entrySet()) {
            Double persentCount = (entry.getValue() * 100) / (double) allCharsEnc;
            persentCount = (Math.round(persentCount * 100) )/ 100.0;
            encryptPercentMap.put(entry.getKey(), persentCount);
        }
        sortedEncryptPercentMap = encryptPercentMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(LinkedHashMap::new,
                        (m ,c ) -> m.put(c.getKey(), c.getValue()),
                        LinkedHashMap::putAll);

        for (Map.Entry<Character, Integer> entry : patternMap.entrySet()) {
            Double persentCount = (entry.getValue() * 100) / (double) allCharsPatt;
            persentCount = (Math.round(persentCount * 100) )/ 100.0;
            patternPercentMap.put(entry.getKey(), persentCount);
        }
        sortedPatternPercentMap = patternPercentMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(LinkedHashMap::new,
                        (m ,c ) -> m.put(c.getKey(), c.getValue()),
                        LinkedHashMap::putAll);
        //Создаем еще две Мапы. В них кладем значение ключа отсортированной мапы в качестве значения
        //а в качестве ключа просто порядковый номер.
        int pp1 = 1;
        int pp2 = 1;
        for (Map.Entry<Character, Double> entry : sortedEncryptPercentMap.entrySet()) {
            resultEncryptPercentMap.put(pp1, entry.getKey());
            pp1++;
        }
        for (Map.Entry<Character, Double> entry : sortedPatternPercentMap.entrySet()) {
            resultPatternPercentMap.put(pp2, entry.getKey());
            pp2++;
        }

        //Далее перебираем первую мапу, и сравнивая ключи, подставляем значения из второй мапы.
        for (int i = 0; i < encryptFileList.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();

            char[] charLine = encryptFileList.get(i).toLowerCase(Locale.ROOT).toCharArray();
            for (int j = 0; j < charLine.length; j++) {
                if (resultEncryptPercentMap.containsValue(charLine[j])) {
                    int key = findKey(resultEncryptPercentMap, charLine[j]);
                    if (key > -1) {
                        stringBuilder.append(resultPatternPercentMap.get(key));
                    }
                } else {
                    stringBuilder.append(charLine[j]);
                }
            }
            resultList.add(stringBuilder.toString());
        }

        return resultList;
    }

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

        if (pointer == 0) {
            allCharsEnc = allChars;
        } else if (pointer == 1) {
            allCharsPatt = allChars;
        }
        return filledMap;
    }

    public static boolean checkChar (char c) {
        boolean result = false;
        for (int i = 0; i < Enviroment.alphabet.length; ++i) {
            if (c == Enviroment.alphabet[i]) result = true;
        }
        return result;
    }

    public static int findKey (Map<Integer, Character> map, char c) {
        int key = -1;
        for (Map.Entry<Integer, Character> entry : map.entrySet()) {
            if (entry.getValue().equals(c)) {
                key = entry.getKey();
            }
        }
        return key;
    }

}
