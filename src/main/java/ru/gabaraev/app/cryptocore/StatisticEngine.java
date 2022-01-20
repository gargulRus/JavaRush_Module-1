package main.java.ru.gabaraev.app.cryptocore;

import java.util.*;
import java.util.stream.Collectors;

/*
 * @created 20/01/2022 - 18:56
 * @project JavaRush_Module-1
 * @author Nikolay Gabaraev
 */
public class StatisticEngine {

    public static List<String> statisticAction(List<String> encryptFileList, List<String> patternFileList) {
        List<String> resultList = new ArrayList<>();
        System.out.println("Создаем мапы");
        HashMap<Character, Integer> encryptMap = fillMap(encryptFileList);
        HashMap<Character, Integer> patternMap = fillMap(patternFileList);
        System.out.println("Шифрованная");
        int pp1 = 1;
        int pp2 = 1;
        for (Map.Entry<Character, Integer> entry : encryptMap.entrySet()) {
            System.out.println(pp1 + " - " + entry.getKey() + " : " + entry.getValue());
            pp1++;
        }
        System.out.println("Оригинал");
        for (Map.Entry<Character, Integer> entry : patternMap.entrySet()) {
            System.out.println(pp2 + " - " + entry.getKey() + " : " + entry.getValue());
            pp2++;
        }

        return resultList;
    }

    public static HashMap<Character, Integer> fillMap (List<String> arrayList) {
        HashMap<Character, Integer> filledMap = new HashMap<>();

        for (int i = 0; i < arrayList.size(); i++) {
            char[] charLine = arrayList.get(i).toLowerCase(Locale.ROOT).toCharArray();
            System.out.println("Наполняем мапу");
            for (int j = 0; j < charLine.length; j++) {
                if (!filledMap.containsKey(charLine[j])) {
                    filledMap.put(charLine[j], 1);
                } else {
                    Integer count = filledMap.get(charLine[j]) + 1;
                    filledMap.put(charLine[j], count);
                }
            }
        }

        filledMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));
        return filledMap;
    }

}
