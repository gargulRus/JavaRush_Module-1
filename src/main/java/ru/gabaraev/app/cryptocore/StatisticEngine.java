package main.java.ru.gabaraev.app.cryptocore;

import java.util.*;
import java.util.stream.Collectors;

/*
 * @created 20/01/2022 - 18:56
 * @project JavaRush_Module-1
 * @author Nikolay Gabaraev
 */
public class StatisticEngine {

    public static int allCharsEnc = 0;
    public static int allCharsPatt = 0;

    private static char[] alphabet = { 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж',
            'з', 'и','й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с',
            'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э','ю', 'я' , '.' , '"', ':', '-', '!', '?', ' '};

    public static List<String> statisticAction(List<String> encryptFileList, List<String> patternFileList) {
        List<String> resultList = new ArrayList<>();
        System.out.println("Создаем мапы");
        Map<Character, Integer> encryptMap = fillMap(encryptFileList , 0);
        Map<Character, Integer> patternMap = fillMap(patternFileList , 1);
        Map<Integer, Character> resultEncryptMap = new TreeMap<>();
        Map<Integer, Character> resultPatternMap = new TreeMap<>();
        //testmap
        Map<Character, Double> testEncMap = new TreeMap<>();
        Map<Character, Double> testEncMap1 = new TreeMap<>();
        Map<Character, Double> testPattMap = new TreeMap<>();
        Map<Character, Double> testPattMap1 = new TreeMap<>();
        Map<Integer, Character> testEncMapResult = new TreeMap<>();
        Map<Integer, Character> testPattMapResult = new TreeMap<>();
        int pp1 = 1;
        int pp2 = 1;
        int pp3 = 1;
        System.out.println("Оригинал");
        for (Map.Entry<Character, Integer> entry : encryptMap.entrySet()) {
            //System.out.println(pp1 + " - " + entry.getKey() + " : " + entry.getValue());
            pp1++;
        }
        System.out.println("Паттерн");
        for (Map.Entry<Character, Integer> entry : patternMap.entrySet()) {
            //System.out.println(pp2 + " - " + entry.getKey() + " : " + entry.getValue());
            pp2++;
        }
        pp1=1;
        pp2=1;
        System.out.println("Начнем сопоставление");

        for (Map.Entry<Character, Integer> entry : encryptMap.entrySet()) {
            resultEncryptMap.put(pp1, entry.getKey());
            pp1++;
        }
        for (Map.Entry<Character, Integer> entry : patternMap.entrySet()) {
            resultPatternMap.put(pp2, entry.getKey());
            pp2++;
        }

        //testing
        for (Map.Entry<Character, Integer> entry : encryptMap.entrySet()) {
            Double persentCount = (entry.getValue() * 100) / (double) allCharsEnc;
            persentCount = (Math.round(persentCount * 100) )/ 100.0;
            testEncMap.put(entry.getKey(), persentCount);

            testEncMap1 = testEncMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(LinkedHashMap::new,
                            (m ,c ) -> m.put(c.getKey(), c.getValue()),
                            LinkedHashMap::putAll);
        }
        for (Map.Entry<Character, Integer> entry : patternMap.entrySet()) {
            Double persentCount = (entry.getValue() * 100) / (double) allCharsPatt;
            persentCount = (Math.round(persentCount * 100) )/ 100.0;
            testPattMap.put(entry.getKey(), persentCount);

            testPattMap1 = testPattMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(LinkedHashMap::new,
                            (m ,c ) -> m.put(c.getKey(), c.getValue()),
                            LinkedHashMap::putAll);
        }
//        pp1=1;
//        pp2=1;
//        for (Map.Entry<Character, Double> entry : testEncMap1.entrySet()) {
//            System.out.println(pp1 + "- " + entry.getKey() + " - " + entry.getValue());
//            pp1++;
//        }
//        System.out.println("======");
//        for (Map.Entry<Character, Double> entry : testPattMap1.entrySet()) {
//            System.out.println(pp2 + "- " + entry.getKey() + " - " + entry.getValue());
//            pp2++;
//        }


        pp1=1;
        pp2=1;

        for (Map.Entry<Character, Double> entry : testEncMap1.entrySet()) {
            testEncMapResult.put(pp1, entry.getKey());
            pp1++;
        }
        for (Map.Entry<Character, Double> entry : testPattMap1.entrySet()) {
            testPattMapResult.put(pp2, entry.getKey());
            pp2++;
        }

        for (Map.Entry<Integer, Character> entry : testEncMapResult.entrySet()) {
            System.out.println(entry.getKey() + ".- " + entry.getValue());
        }
        System.out.println("============");
        for (Map.Entry<Integer, Character> entry : testPattMapResult.entrySet()) {
            System.out.println(entry.getKey() + ".- " + entry.getValue());
        }
        //end of test

//        for (Map.Entry<Integer, Character> entry : resultEncryptMap.entrySet()) {
//            for (Map.Entry<Integer, Character> entry1 : resultPatternMap.entrySet()) {
//                if (entry.getKey().equals(entry1.getKey())) {
//                    System.out.println(entry.getValue() + " - " + entry1.getValue());
//                }
//            }
//        }

        for (int i = 0; i < encryptFileList.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();

            char[] charLine = encryptFileList.get(i).toLowerCase(Locale.ROOT).toCharArray();
            for (int j = 0; j < charLine.length; j++) {
                if (resultEncryptMap.containsValue(charLine[j])) {
                    int key = findKey( resultEncryptMap, charLine[j]);
                    if (key > -1) {
                        stringBuilder.append(resultPatternMap.get(key));
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

        Map<Character, Integer> returnMap =
        filledMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(LinkedHashMap::new,
                        (m ,c ) -> m.put(c.getKey(), c.getValue()),
                        LinkedHashMap::putAll);
        System.out.println("Всего символов - " + allChars);
        if (pointer == 0) {
            allCharsEnc = allChars;
        } else if (pointer == 1) {
            allCharsPatt = allChars;
        }
        return returnMap;
    }

    public static boolean checkChar (char c) {
        boolean result = false;
        for (int i = 0; i < alphabet.length; ++i) {
            if (c == alphabet[i]) result = true;
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
