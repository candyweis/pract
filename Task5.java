import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Task5 {
        public static void main(String[] args) {

            System.out.println("задание1: " + sameLetterPattern("ABAB", "CDCD"));
            System.out.println(sameLetterPattern("ABCBA", "BCDCB"));
            System.out.println(sameLetterPattern("FFGG", "CDCD"));
            System.out.println(sameLetterPattern("FFFF", "ABCD"));

            System.out.println("задание2: " + spiderVsFly("H3", "E2"));
            System.out.println(spiderVsFly("A4", "B2"));
            System.out.println(spiderVsFly("A4", "C2"));

            System.out.println("задание3: " + digitsCount(4666)); // 4
            System.out.println(digitsCount(544)); // 3
            System.out.println(digitsCount(121317)); // 6
            System.out.println(digitsCount(0)); // 1
            System.out.println(digitsCount(12345)); // 5
            System.out.println(digitsCount(1289396387328L));

            System.out.println("задание4: " + totalPoints(new String[]{"cat", "create", "sat"}, "caster")); // 2
            System.out.println(totalPoints(new String[]{"trance", "recant"}, "recant")); // 108
            System.out.println(totalPoints(new String[]{"dote", "dotes", "toes", "set", "dot", "dots", "sted"}, "tossed")); // 13

            System.out.println("задание5: " + Arrays.deepToString(sumsUp(new int[]{1, 2, 3, 4, 5}))); // [[3, 5]]
            System.out.println(Arrays.deepToString(sumsUp(new int[]{1, 2, 3, 7, 9}))); // [[1, 7]]
            System.out.println(Arrays.deepToString(sumsUp(new int[]{10, 9, 7, 2, 8}))); // []
            System.out.println(Arrays.deepToString(sumsUp(new int[]{1, 6, 5, 4, 8, 2, 3, 7}))); // [[2, 6], [3, 5], [1, 7]]

            System.out.println("задание6: " + takeDownAverage(new String[]{"95%", "83%", "90%", "87%", "88%", "93%"})); // "54%"
            System.out.println(takeDownAverage(new String[]{"10%"})); // "0%"
            System.out.println(takeDownAverage(new String[]{"53%", "79%"})); // "51%"

            System.out.println("задание7: " + caesarCipher(new String[] {"encode"}, "HELLO WORLD", 3));
            System.out.println(caesarCipher(new String[] {"encode"}, "almost last task!", 4));

            System.out.println("задание8: " + setSetup(5, 3)); // 60
            System.out.println(setSetup(7, 3)); // 210

            System.out.println("задание9: " + timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra")); // 2011-4-2 17:23
            System.out.println(timeDifference("London", "July 31, 1983 23:01", "Rome")); // 1983-8-1 00:01
            System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing")); // 1971-1-1 02:40

            System.out.println("задание10: " + isNew(3));
            System.out.println(isNew(30));
            System.out.println(isNew(321));
            System.out.println(isNew(123));
        }

        //задание1(один ли шаблон)
    public static boolean sameLetterPattern(String str1, String str2) {
        if (str1.length() != str2.length()) {//размер
            return false;
        }
        for (int i = 1; i < str1.length(); i++) {
            if (str1.charAt(i) - str1.charAt(i - 1) != str2.charAt(i) - str2.charAt(i - 1)) {
            return false;
            }
        }
        return true;
    }

        //задание2
    public static String spiderVsFly(String Spider, String Fly){
        int spider = Character.getNumericValue(Spider.charAt(1));//круг
        int fly = Character.getNumericValue(Fly.charAt(1));
        int share = Math.abs(Spider.charAt(0) - Fly.charAt(0));//доля круга

        int walk = 1;
        double minLen = 100;
        int min_route = 0;

        if (share > 8 - share){
            share = 8 - share;
            walk = -1;
        }

        for (int i = 0; i < 5; i++){//длина подходящей дуги
            double len = (Math.abs(spider - i) + Math.abs(fly - i));//разница между кругами
            len += share * Math.sqrt(2*i*i - 2*i*i*Math.cos(Math.toRadians(45)));
            //дуга=корню из суммы квадратов радиусов-2*радиусов на косинус
            if (len < minLen) {
                minLen = len;
                min_route = i;
            }
        }

        int spiderMove = min_route > spider ? 1 : -1;//направление от ц или к
        StringBuilder path = new StringBuilder();//для построения пути
        //окружность, пока не= минимальный круг+движение к А или от,
        for (int i = spider; i != min_route + spiderMove; i += spiderMove){
            if (i == 0){//центр
                path.append('A');
            } else {
                path.append(Spider.charAt(0));//текущая позиция
            }
            path.append(i).append('-');//+номер круга и разделитель
        }

        //по кругу если не через центр
        if (min_route != 0){
            for (int i = 0; i < share; i++){//возвращаем по индексу,-3 тк ы4-,+walk определение сектора
                path.append(Character.toString(path.charAt(path.length() - 3) + walk)).append(min_route).append('-');
            }
        }

        //путь от min_круга к Fly(движение по радиане)
        int flyMove = min_route > fly ? -1 : 1;
        for (int i = min_route + flyMove; i != fly + flyMove; i += flyMove){
            path.append(Fly.charAt(0));
            path.append(i).append('-');
        }

        path.deleteCharAt(path.length()-1);
        return path.toString();
    }

        //задание3(колво цифр)
    public static int digitsCount(long n) {
         return n < 10 ? 1 : 1 + digitsCount(n / 10);//через т.оператор
    }

        //задание4(найти слова за баллы)
    public static int totalPoints(String[] words, String scrambled) {
        int result = 0;
        Map<Character, Integer> scrambledMap = createCharMap(scrambled);//словарь символов из ключевого слова

          for (String word : words) {
             if (isValidWord(word, scrambledMap)) {
             result += word.length() == 3 ? 1 : word.length() == 4 ? 2 : word.length() == 5 ? 3 : 54;
             }
          }
        return result;
    }
    private static boolean isValidWord(String word, Map<Character, Integer> scrambledMap) {
        Map<Character, Integer> wordMap = createCharMap(word);//словарь символов из слова
        for (char c : wordMap.keySet()) {
            if (!scrambledMap.containsKey(c) || wordMap.get(c) > scrambledMap.get(c)) {
                return false;
            }
        }
        return true;
    }

    private static Map<Character, Integer> createCharMap(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

        //задание5(пара дающая 8)
    public static int[][] sumsUp(int[] arr) {
        List<int[]> pairs = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

            for (int num : arr) {
                if (set.contains(8 - num)) {//if "8 - 5" есть в сете,то "3,5"
                    pairs.add(new int[]{8 - num, num});
                }
                set.add(num);
            }
            for (int[] pair : pairs) {
                Arrays.sort(pair);//сортировка внутри пар
            }
        return pairs.toArray(new int[pairs.size()][]);
    }

        //задание6
    public static String takeDownAverage(String[] arr) {
        int result = 0;
            for (String s : arr)//убрать процент и в число
                result += Integer.parseInt(s.replaceAll("%", ""));
            return (result / arr.length - arr.length*5 - 5) + "%";
    }

        //задание7
    public static String caesarCipher(String[] operation, String message, int shift) {
        StringBuilder result = new StringBuilder();
        String mode = operation[0].toLowerCase();

            if (mode.equals("decode")) {
                shift = (26 - shift % 26) % 26;
            } else {
                shift = shift % 26;
            }

            for (char character : message.toCharArray()) {
                if (Character.isLetter(character)) {
                    int base = Character.isUpperCase(character) ? 'A' : 'a';//заглавная или нет
                    char shifted = (char) (((character - base + shift + 26) % 26) + base);
                    result.append(shifted);
                } else {
                    result.append(character);
                }
            }

        return result.toString();
    }


    //задание8
        public static int setSetup(int n, int k) {
            return factorial(n) / factorial(n - k);
        }
    private static int factorial(int number) {
        if (number <= 1) {
            return 1;
        }
        return number * factorial(number - 1);
    }

        //задание9!!!
        public static String timeDifference(String cityA, String time, String cityB) {
            Map<String, String> timeZones = new HashMap<>();
            timeZones.put("Los Angeles", "GMT-08:00");//по гринвичискому времени
            timeZones.put("New York", "GMT-05:00");
            timeZones.put("Caracas", "GMT-04:30");
            timeZones.put("Buenos Aires", "GMT-03:00");
            timeZones.put("London", "GMT");
            timeZones.put("Rome", "GMT+01:00");
            timeZones.put("Moscow", "GMT+03:00");
            timeZones.put("Tehran", "GMT+03:30");
            timeZones.put("New Delhi", "GMT+05:30");
            timeZones.put("Beijing", "GMT+08:00");
            timeZones.put("Canberra", "GMT+10:00");

            SimpleDateFormat originalFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm", Locale.ENGLISH);
            originalFormat.setTimeZone(TimeZone.getTimeZone(timeZones.get(cityA)));

            SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-M-d HH:mm", Locale.ENGLISH);
            targetFormat.setTimeZone(TimeZone.getTimeZone(timeZones.get(cityB)));

            try {
                Calendar cal = Calendar.getInstance();
                cal.setTime(originalFormat.parse(time));//
                return targetFormat.format(cal.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
                return "Invalid date format";
            }
        }

        //задание10
            public static boolean isNew(int number) {
                if (number < 10) {//всегда нов.
                    return true;
                }
                char[] numbers = String.valueOf(number).toCharArray();//строка-массив символов
                for (int i = 1; i < numbers.length; i++) {
                    if (numbers[i] != '0' && numbers[i] < numbers[0]) {
                        return false;
                    }
                }
                return true;
            }
}