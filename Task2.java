import java.util.Arrays; // для работы с массивом
import java.util.HashSet; //хранит элементы без какого-либо порядка, используя хэш-таблицу для обеспечения быстрого доступа к элементам.
import java.util.Random; //
import java.util.Set; //уникальные элементы
public class Task2 {
    public static void main(String args[]) {

        System.out.println("задание1: " + duplicateChars("Donald"));
        System.out.println(duplicateChars("orange"));

        System.out.println("задание2: " + getInitials("Ryan Gosling"));
        System.out.println(getInitials("Barack Obama"));

        System.out.println("задание3: " + differenceEvenOdd(new int[]{44, 32, 86, 19}));
        System.out.println(differenceEvenOdd(new int[]{22, 50, 16, 63, 31, 55}));

        System.out.println("задание4: " + equalToAvg(new int[]{1, 2, 3, 4, 5}));
        System.out.println(equalToAvg (new int[]{1, 2, 3, 4, 6}));

        System.out.println("задание5: " + Arrays.toString(indexMult(new int[]{1, 2, 3})));
        System.out.println(indexMult(new int[]{3, 3, -2, 408, 3, 31}));

        System.out.println("задание6: " + reverse("Hello World"));
        System.out.println(reverse("The quick brown fox."));

        System.out.println("задание7: " + Tribonacci(7));
        System.out.println(Tribonacci(11));

        System.out.println("задание8: " + pseudoHash(5));
        System.out.println(pseudoHash(10));
        System.out.println(pseudoHash(0));

        System.out.println("задание9: " + botHelper("Hello, I’m under the water, please help me"));
        System.out.println(botHelper("Two pepperoni pizzas please"));

        System.out.println("задание10: " + isAnagram("listen", "silent"));
        System.out.println(isAnagram("eleven plus two", "twelve plus one"));
        System.out.println(isAnagram("hello", "world"));
    }

    //задание 1
    public static boolean duplicateChars(String str) {
        Set<Character> charSet = new HashSet<>();//таблица
        for (char c : str.toCharArray()) {//преобразование строки в массив символов
            if (charSet.contains(c)) {//метод проверяет содержит ли строка подстроку
                return true;
            }
            charSet.add(c);//добавление в множество если нет повторений
        }
        return false;
    }

    //задание 2
    public static String getInitials(String fullName) {
        String[] names = fullName.split(" ");//делаем массив строк
        StringBuilder initials = new StringBuilder();//создание обьекта
        for (String name : names) {//перебор каждого слова из массива
            initials.append(name.charAt(0));//возвращаем первую букву по индексу и добавляет в наш обьект
        }
        return initials.toString();
    }

    //задание 3 (разницу между суммой четных и нечетных)
    public static int differenceEvenOdd(int[] arr) {
        int evenSum = 0, oddSum = 0;//хранение сумм
        for (int num : arr) {
            if (num % 2 == 0) {
                evenSum += num;
            } else {
                oddSum += num;
            }
        }
        return Math.abs(evenSum - oddSum);//тк разница в модуле
    }

    //задание 4 (да если есть эл. = среднему арифметическому всех эл.)
    public static boolean equalToAvg(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }//считаем общуу сумму вех элементов в массиве
        double avg = (double) sum / arr.length;//arr.length - возвращает число элементов в заданном массиве
        for (int num : arr) {//перебор всех элементов массива
            if (num == avg) {//сравнение с средним ариф.
                return true;
            }
        }
        return false;
    }

    //задание 5 (берет массив целых чисел и возвращает массив, в котором каждое целое число умножено на индекс этого числа в массиве)
    public static int[] indexMult(int[] arr) {
        for (int i = 0; i < arr.length; i++) {//перебор индексов
            arr[i] *= i; //перемножение значение элемента массива на его индекс
        }
        return arr;
    }

    //задание 6 (!типа полиндромы!)
    public static String reverse(String s){
        StringBuilder reversed = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--)//length() возвращает длину строки
        {reversed.append(s.charAt(i));//charAt(int index)возвращает символ по указанному индексу.appened-скленивание(конкатенация)
        } return reversed.toString();//преобразование в строку
    }

    //задание 7! (трибоначчи???)
    public static int Tribonacci(int n) {
        if (n == 0 || n == 1) return 0;//тк первые два числа триб.=0
        if (n == 2) return 1;//база тк третье число триб.=1
        int a = 0, b = 0, c = 1, d;
        for (int i = 4; i <= n; i++) {
            d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        return c;
    }

    //задание 8 (генератор)
    public static String pseudoHash(int length) {
        StringBuilder sb = new StringBuilder();//обьект для работы со строкой
        Random rand = new Random();//обьект для генерации случайных чисел(источник случайных чисел)
        String chars = "abcdef0123456789";//строки с набором используемых символов
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
            //rand.nextInt() генерация случайного числа
        }
        return sb.toString();
    }

    //задание 9 (которая находит слово "help". Ответьте "Вызов сотрудника", если слово найдено, в противном случае – "Продолжайте ожидание")
    public static String botHelper(String str) {
        str = str.toLowerCase();//в строчную
        str = str.replaceAll("[,.!?]", " "); //препинания на пробелы
        String[] words = str.split("\\s+"); //pазделение
        for (String word : words) {
            if ( word.equals("help")) {
                return "Calling for a staff member";
            }
        }
        return "Keep waiting";
    }

    //задание 10 (проверка соотношения символов)
    public static boolean isAnagram(String str1, String str2) {
        char[] chars1 = str1.replaceAll("\\s", "").toCharArray();
        //массивы из 2х строк(замена пробела, преобразование в массив символов)
        char[] chars2 = str2.replaceAll("\\s", "").toCharArray();
        Arrays.sort(chars1);//сортировка по Unicode
        Arrays.sort(chars2);
        return Arrays.equals(chars1, chars2);//сравнение
    }
}
