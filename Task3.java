import java.util.*;


public class Task3 {
    public static void main(String[] args) {
        System.out.println("задание1: " + duplicateChars("apple"));
        System.out.println(duplicateChars("Even if you did this task not by yourself, you have to understand every single line of code."));

        System.out.println("задание2: " + stringTransform("heDoubleLo"));
        System.out.println(stringTransform("bookkeeper"));

        System.out.println("задание3: " + doesBlockkFit(1, 3, 5, 4, 5));
        System.out.println(doesBlockkFit(1, 8, 1, 1, 1));
        System.out.println(doesBlockkFit(1, 2, 2, 1, 1));

        System.out.println("задание4:" + numCheck(243));
        System.out.println(numCheck(52));

        System.out.println("задание5:" + countRoots(new int[]{1, -3, 2}));
        System.out.println(countRoots(new int[]{2, 5, 2}));
        System.out.println(countRoots(new int[]{1, -6, 9}));

        System.out.println("задание6:" + Arrays.toString(salesData(new String[][]{
                {"Apple", "Shop1", "Shop2", "Shop3", "Shop4"},
                {"Banana", "Shop2", "Shop3", "Shop4"},
                {"Orange", "Shop1", "Shop3", "Shop4"},
                {"Pear", "Shop2", "Shop4"}})));
        System.out.println(Arrays.toString(salesData(new String[][]{
                {"Fridge", "Shop2", "Shop3"},
                {"Microwave", "Shop1", "Shop2", "Shop3", "Shop4"},
                {"Laptop", "Shop3", "Shop4"},
                {"Phone", "Shop1", "Shop2", "Shop3", "Shop4"}})));


        System.out.println("задание7:" + validSplit("apple eagle egg goat"));
        System.out.println(validSplit("cat dog goose fish"));
        System.out.println(validSplit("cat Town"));

        System.out.println("задание8" + waveForm(new int[]{3, 1, 4, 2, 7, 5}));
        System.out.println(waveForm(new int[]{1, 2, 3, 4, 5}));
        System.out.println(waveForm(new int[]{1, 2, -6, 10, 3}));

        System.out.println("задание9: " + commonVovel("Hello world"));
        System.out.println(commonVovel("Actions speak louder than words."));

        System.out.println("задание10: " + Arrays.deepToString(dataScience(new int[][]{
                {6, 4, 19, 0, 0},
                {81, 25, 3, 1, 17},
                {48, 12, 60, 32, 14},
                {91, 47, 16, 65, 217},
                {5, 73, 0, 4, 21}})));
        System.out.println(Arrays.deepToString(dataScience(new int[][]{
                        {1, 2, 3, 4, 5},
                        {6, 7, 8, 9, 10},
                        {5, 5, 5, 5, 5},
                        {7, 4, 3, 14, 2},
                        {1, 0, 11, 10, 1}})));
    }

    //задание 1
    public static String duplicateChars(String str) {
        str = str.toLowerCase();
        str = str.replaceAll("[eyuioa]", "*");
        return str;
    }

    //задание 2(разобраться с метасимволами)
    public static String stringTransform(String str) {
        return (str.replaceAll("(\\w)\\1", "Double$1"));
    }

    //задание 3
    static boolean doesBlockkFit(int a, int b, int c, int w, int h) {
        int[] kub = {a, b, c};
        int[] otverstie = {w, h};

        Arrays.sort(kub);
        Arrays.sort(otverstie);

        return kub[0] <= otverstie[0] && kub[1] <= otverstie[1];
    }

    //задание 4//чет нечет
    public static boolean numCheck(int x) {

        String numberString = Integer.toString(x);//перевод числа в строку
        char[] charArray = numberString.toCharArray();//перевод строки в массив элементов

        int[] intArray = new int[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            intArray[i] = Character.getNumericValue(charArray[i]);//цикл для перевода в массив чисел
        }

        int sum = 0;

        for (int i = 0; i < intArray.length; i++) {//по индексам
            int sqr = intArray[i] * intArray[i];
            sum += sqr;
        }
        return (x % 2 == 0 && sum % 2 == 0) || (x % 2 != 0 && sum % 2 != 0);//учитываем оба случая
    }

    //задание 5 сколько корней
    public static int countRoots(int[] arr) {

        int a = arr[0];
        int b = arr[1];
        int c = arr[2];
        int result = 0;
        int discriminant = b * b - 4 * a * c;


        if (discriminant < 0) {// Нет корней
            return 0;
        } else if (discriminant == 0) {// Один корень
            double x_1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            result = (int)x_1 == x_1 ? 1 : 0;


        } else {// Два корня
            double x_1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double x_2  = (-b - Math.sqrt(discriminant)) / (2 * a);
            result += (int)x_1 == x_1 ? 1 : 0;
            result += (int)x_2 == x_2 ? 1 : 0;
        }

    return result;
    }

    //задание 6 разобраться с двумерными массивами(!)
    public static String[] salesData(String[][] arr) {
        int x = 0;
        for (String[] row : arr) {
            if (row.length > x) {
                x = row.length;
            }
        }
        int y = 0;//местро в результате
        String[] result = new String[arr.length];
        for (String[] row : arr) {
            if (row.length == x) {
                result[y] = row[0];
                y++;
            }
        }
        return Arrays.copyOf(result, y);//ответ в виде массива
    }

    //задание 7
    public static boolean validSplit(String str) {

        str = str.toLowerCase();//в строчную
        str = str.replaceAll("[,.!?]", " ");
        String[] words = str.split(" ");//результат массив строк

        for (int i = 1; i < words.length; i++) {
            if (words[i - 1].charAt(words[i - 1].length() - 1) != words[i].charAt(0)) {
                return false;
            }
        }
        return true;
    }

    //задание 8 переделать(можно сделать взяв другой флаг)
    public static boolean waveForm(int[] arr) {
        for (int i = 0; i < arr.length - 2; i++) {
            int first = arr[i];
            int second = arr[i + 1];
            int third = arr[i + 2];

            if ((second - first) * (third - second) >= 0) {
                return false;

                //5 3 9 2 4

            }
        }

        return true;
    }



    //задание 9 переделать
    public static List<Character> commonVovel(String str) {
        str = str.toLowerCase();
        Map<Character, Integer> vowelCount = new HashMap<>();
        String vowels = "aeiou"; // доп строка

        for (char vowel : vowels.toCharArray()) {
            vowelCount.put(vowel, 0);//запись в словать каждой гласной со значением 0
        }

        for (char letter : str.toCharArray()) {
            if (vowelCount.containsKey(letter)) {
                vowelCount.put(letter, vowelCount.get(letter) + 1);
            }
        }
        int max = 0;
        List<Character> most = new ArrayList<>();

        for (char vowel : vowels.toCharArray()) {
            int count = vowelCount.get(vowel); // значение объекта по ключу
            if (count > max) {
                max = count;
                most.clear();
                most.add(vowel);
            } else if (count == max) {
                most.add(vowel);
            }
        }
        return most;
    }

    //задание 10
    public static int[][] dataScience(int[][] arrays) {
        int n = arrays.length;
        for (int i = 0; i < n; i++) {//перебор индексов строк
            int x = 0;
            for (int k = 0; k < n; k++) {//перебор индексов столбцов
                if (k != i) {//диаг не учитываем
                    x += arrays[k][i];
                }
            }
            arrays[i][i] = x / (n - 1);
        }
        return arrays;
    }
}

