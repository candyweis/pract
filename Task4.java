import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4 {
    public static void main(String[] args) {
        System.out.println("задание1: " + nonRepeatable("abracadabra"));
        System.out.println(nonRepeatable("paparazzi"));

        System.out.println("задание2: " + generateBrackets(1));
        System.out.println(generateBrackets(2));
        System.out.println(generateBrackets(3));

        System.out.println("задание3: " + binarySystem(3));
        System.out.println(binarySystem(4));

        System.out.println("задание4: " + alphabeticRow("ababa")); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        System.out.println(alphabeticRow("klmabzyxw"));

        System.out.println("задание5: " + countAndSortCharacters("aaaa"));
        System.out.println(countAndSortCharacters("vvvvaajaaaaa"));

        System.out.println("задание6: " + convertToNum("eight"));
        System.out.println(convertToNum("five hundred sixty seven"));
        System.out.println(convertToNum("thirty one"));

         System.out.println("задание7: " + uniqueSubstring("123412324"));
        System.out.println(uniqueSubstring("111111"));
        System.out.println(uniqueSubstring("77897898"));

        System.out.println("задание8: " + shortestWay(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        System.out.println(shortestWay(new int[][]{{2, 7, 3}, {1, 4, 8}, {4, 5, 9}}));

         System.out.println("задание9: " + numericOrder("t3o the5m 1One all6 r4ule ri2ng"));
        System.out.println(numericOrder("re6sponsibility Wit1h gr5eat power3 4comes g2reat"));

        System.out.println("задание10: " + switchNums(519, 723));
        System.out.println(switchNums(491, 3912));
        System.out.println(switchNums(6274, 71259));
    }

    // Задание 1 Удаление повтора
    public static String nonRepeatable(String str) {
        return nonRepeatable(str, new HashSet<>());
    }
    private static String nonRepeatable(String str, HashSet<Character> set) {
        if (str.isEmpty()) {
            return "";
        }
        char firstChar = str.charAt(0);
        if (set.contains(firstChar)) {
            return nonRepeatable(str.substring(1), set);
        } else {
            set.add(firstChar);
            return firstChar + nonRepeatable(str.substring(1), set);
        }
    }

    // Задание 2 комбо строк
    public static List<String> generateBrackets(int n) {
        List<String> result = new ArrayList<>();
        generateBracketsHelper(n, n, "", result);
        return result;
    }

    private static void generateBracketsHelper(int open, int close, String current, List<String> result) {
        if (open == 0 && close == 0) {
            result.add(current);
            return;
        }
        if (open > 0) {
            generateBracketsHelper(open - 1, close, current + "(", result);
        }
        if (close > open) {
            generateBracketsHelper(open, close - 1, current + ")", result);
        }
    }

    // Задание 3 сочетание без сосед. 0
    public static List<String> binarySystem(int n) {
        List<String> result = new ArrayList<>();
        generateBinaryCombinations(n, "", result);
        return result;
    }

    private static void generateBinaryCombinations(int n, String current, List<String> result) {
        if (current.length() == n) {
            result.add(current);
            return;
        }
        if (current.length() == 0 || current.charAt(current.length() - 1) == '1') {//послед симв=1
            generateBinaryCombinations(n, current + "0", result);
        }
        generateBinaryCombinations(n, current + "1", result);
    }

    // Задание 4 послед букв !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public static String alphabeticRow(String str) {
        String maxRow = "";//последовательность
        int maxLen = 0;//длина

        for (int i = 0; i < str.length(); i++) {//в прямом
            StringBuilder current01 = new StringBuilder();
            current01.append(str.charAt(i));
            while (i < str.length() - 1 && str.charAt(i) + 1 == str.charAt(i + 1)) {//в алфавитном
                current01.append(str.charAt(i + 1));
                i++;
            }
            if (current01.length() > maxLen) {//обновление
                maxLen = current01.length();
                maxRow = current01.toString();
            }
        }

        for (int i = 0; i < str.length(); i++) {//в обратном
            StringBuilder current10 = new StringBuilder();
            current10.append(str.charAt(i));
            while (i < str.length() - 1 && str.charAt(i) == str.charAt(i + 1) + 1) {//в обратном
                current10.append(str.charAt(i + 1));
                i++;
            }
            if (current10.length() > maxLen) {//обновление
                maxLen = current10.length();
                maxRow = current10.toString();
            }
        }
        return maxRow;
    }

    // Задание 5 сортировка символов
    public static String countAndSortCharacters(String str) {
        StringBuilder comp = new StringBuilder();//сжатая строка
        int count = 1;//колво повторений

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                count++;
            } else {
                comp.append(str.charAt(i - 1));
                comp.append(count);
                count = 1;
            }
        }
        comp.append(str.charAt(str.length() - 1));
        comp.append(count);//последний символ

        List<String> blocks = new ArrayList<>();//блоки буква+цифра
        Matcher matcher = Pattern.compile("[a-zA-Z]\\d+").matcher(comp.toString());
        while (matcher.find()) {
            blocks.add(matcher.group());//добавление регулятого выраж
        }

        blocks.sort((o1, o2) -> {// Сортировка с лямбдой
            int num1 = Integer.parseInt(o1.substring(1));//извлекаем число(0,1)
            int num2 = Integer.parseInt(o2.substring(1));
            return Integer.compare(num1, num2);//-if num2>.. +if num1>
        });

        StringBuilder result = new StringBuilder();
        for (String block : blocks) {
            result.append(block);
        }

        return result.toString();
    }
    // Задание 6 из стр в число
    public static int convertToNum(String str) {

        Map<String, Integer> numDict = new HashMap<>();//словарь чисел

        numDict.put("zero", 0);
        numDict.put("one", 1);
        numDict.put("two", 2);
        numDict.put("three", 3);
        numDict.put("four", 4);
        numDict.put("five", 5);
        numDict.put("six", 6);
        numDict.put("seven", 7);
        numDict.put("eight", 8);
        numDict.put("nine", 9);
        numDict.put("ten", 10);
        numDict.put("eleven", 11);
        numDict.put("twelve", 12);
        numDict.put("thirteen", 13);
        numDict.put("fourteen", 14);
        numDict.put("fifteen", 15);
        numDict.put("sixteen", 16);
        numDict.put("seventeen", 17);
        numDict.put("eighteen", 18);
        numDict.put("nineteen", 19);
        numDict.put("twenty", 20);
        numDict.put("thirty", 30);
        numDict.put("forty", 40);
        numDict.put("fifty", 50);
        numDict.put("sixty", 60);
        numDict.put("seventy", 70);
        numDict.put("eighty", 80);
        numDict.put("ninety", 90);
        numDict.put("hundred", 100);
        numDict.put("thousand", 1000);

        String[] words = str.split(" ");//строку на слова
        int result = 0;

        for (String word : words) {
            if (numDict.containsKey(word)) {
                if (word.contains("hundred")) {
                    result = result * 100;
                } else if (word.contains("thousand")){
                    result = result * 1000;
                } else {
                    result = result + numDict.get(word);
                }
            }
        }
        return result;
    }

    // Задание 7 Поиск подстроки максимальной длины с уникальными элементами
    public static String uniqueSubstring(String str) {
        Set<Character> unique = new HashSet<>();
        String max = "";
        String current = "";

        for (char c : str.toCharArray()) { //123789
            if (unique.contains(c)) {//если симв уже есть удаляем все до его первого появления
                int startIndex = current.indexOf(c);
                current = current.substring(startIndex + 1);
            }
            unique.add(c);
            current += c;
            if (current.length() > max.length()) {
                max = current;
            }
        }
        return max;
    }

    // Задание 8 наименьший путь
    public static int shortestWay(int[][] arr) {
        int m = arr.length;//строки
        int n = arr[0].length;//столбцы
        int[][] x = new int[m][n];//новый масс, хран.пути

        x[0][0] = arr[0][0];//первые ячейки совпадают

        for (int i = 1; i < m; i++) {// строки(суммы если бы двигались)
            x[i][0] = x[i - 1][0] + arr[i][0];
        }
        for (int j = 1; j < n; j++) {// столбца
            x[0][j] = x[0][j - 1] + arr[0][j];
        }
        for (int i = 1; i < m; i++) {// стр
            for (int j = 1; j < n; j++) {//столб
                x[i][j] = Math.min(x[i - 1][j], x[i][j - 1]) + arr[i][j];
            }
        }
        return x[m - 1][n - 1];//- тк с 0
    }

    // Задание 9 правильный порядок слов в предложении
    public static String numericOrder(String str) {
        String[] words = str.split(" ");
        Map<Integer, String> wordMap = new HashMap<>();

        for (String word : words) {
            int order = Integer.parseInt(word.replaceAll("[^0-9]", ""));
            wordMap.put(order, word);
        }
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= words.length; i++) {
            result.append(wordMap.get(i));
                result.append(" ");
            }

        String finalResult = result.toString().trim().replaceAll("\\d", "");
        return finalResult; // удаление лишних пробелов
    }

    // Задача 10 макс второе число за счет первого(ура)
    public static int switchNums(int num1, int num2) {
        char[] num1Digits = Integer.toString(num1).toCharArray();
        char[] num2Digits = Integer.toString(num2).toCharArray();

        Arrays.sort(num1Digits);

        for (int j = 0; j < num2Digits.length; j++){
            for (int i = num1Digits.length - 1; i >= 0; i--) {
                if (num1Digits[i] > num2Digits[j]) {
                    num2Digits[j] = num1Digits[i];
                    num1Digits[i] = 0;
                }
            }
        }
        return Integer.parseInt(new String(num2Digits));
    }
}
