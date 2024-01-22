import java.util.*;
import java.util.stream.*;

public class Task6 {
    public static void main(String[] args) {

        System.out.println("задание1: " + hiddenAnagram("My world evolves in a beautiful space called Tesh.", "sworn love lived"));
        System.out.println(hiddenAnagram("An old west action hero actor", "Clint Eastwood"));
        System.out.println(hiddenAnagram("Mr. Mojo Rising could be a song title", "Jim Morrison"));
        System.out.println(hiddenAnagram("Banana? margaritas", "ANAGRAM"));
        System.out.println(hiddenAnagram("D  e b90it->?$ (c)a r...d,,#~", "bad credit"));
        System.out.println(hiddenAnagram("Bright is the moon", "Bongo mirth") + "\n");

        System.out.println("задание2: " + collect("intercontinentalisationalism", 6));
        System.out.println(collect("strengths", 3));
        System.out.println(collect("pneumonoultramicroscopicsilicovolcanoconiosis", 15) + "\n");

        System.out.println("задание3: " + nicoCipher("myworldevolvesinhers", "tesh"));
        System.out.println(nicoCipher("andiloveherso", "tesha"));
        System.out.println(nicoCipher("mubashirhassan", "crazy"));
        System.out.println(nicoCipher("edabitisamazing", "matt"));
        System.out.println(nicoCipher("iloveher", "612345") + "\n");

        System.out.println("задание4: " + Arrays.toString(twoProduct(new int[] {1, 2, 3, 9, 4, 5, 15}, 45)));
        System.out.println(Arrays.toString(twoProduct(new int[] {1, 2, 3, 9, 4, 15, 3, 5}, 45)));
        System.out.println(Arrays.toString(twoProduct(new int[] {1,  2, -1,  4,  5,  6,  10, 7}, 20)));
        System.out.println(Arrays.toString(twoProduct(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10)));
        System.out.println(Arrays.toString(twoProduct(new int[] {100, 12, 4, 1, 2}, 15)) + "\n");

        System.out.println("задание5: " + Arrays.toString(isExact(6)));
        System.out.println(Arrays.toString(isExact(24)));
        System.out.println(Arrays.toString(isExact(125)));
        System.out.println(Arrays.toString(isExact(720)));
        System.out.println(Arrays.toString(isExact(1024)));
        System.out.println(Arrays.toString(isExact(40320)) + "\n");

        System.out.println("задание6: " + fractions("0.(6)"));
        System.out.println(fractions("1.(1)"));
        System.out.println(fractions("3.(142857)"));
        System.out.println(fractions("0.19(2367)"));
        System.out.println(fractions("0.1097(3)") + "\n");

        System.out.println("задание7: " + pilish_string("33314444"));
        System.out.println(pilish_string("TOP"));
        System.out.println(pilish_string("X"));
        System.out.println(pilish_string("") + "\n");

        System.out.println("задание8: " + generateNonconsecutive("3 + 5 * (2 - 6)"));
        System.out.println(generateNonconsecutive("6 - 18 / (-1 + 4)") + "\n");

        System.out.println("задание9: " + isValid("aabbcd"));
        System.out.println(isValid("aabbccddeefghi"));
        System.out.println(isValid("abcdefghhgfedecba")+ "\n");

        System.out.println("задание10: " + findLCS("abcd", "bd"));
        System.out.println(findLCS("aggtab", "gxtxamb"));
        }

    //задание1
        public static String hiddenAnagram(String a, String b) {
            a = a.toLowerCase().replaceAll("[^a-z]", "");
            b = b.toLowerCase().replaceAll("[^a-z]", "");
            int[] setB = new int[26];
            for (char c : b.toCharArray())
                setB[c - 97]++;//a-0,b-1
            for (int i = 0; i <= a.length() - b.length(); i++) {
                int[] setA = new int[26];
                for (char c : a.substring(i, i + b.length()).toCharArray()) setA[c - 97]++;
                if (Arrays.equals(setA, setB)) {
                    return a.substring(i, i + b.length());
                }
            }
            return "notfound";
        }

    //задание2
        public static List<String> collect(String str, int n) {
            if (str.length() < n) {
                return new ArrayList<>();
            } else {
                List<String> list = new ArrayList<>();
                list.add(str.substring(0, n));
                list.addAll(collect(str.substring(n), n));
                return list.stream().sorted().collect(Collectors.toList());
            }
        }

    //задание3
        public static String nicoCipher(String message, String key) {
            int keyLength = key.length();
            int extraSpaces = (keyLength - (message.length() % keyLength)) % keyLength;
            message += new String(new char[extraSpaces]).replace("\0", " ");

            Integer[] order = new Integer[keyLength];
            for (int i = 0; i < keyLength; i++) {
                order[i] = i;
            }

            Arrays.sort(order, (a, b) -> Character.compare(key.charAt(a), key.charAt(b)));

            char[] result = new char[message.length()];
            for (int i = 0; i < message.length(); i++) {
                int columnIndex = order[i % keyLength];
                int rowIndex = i / keyLength;
                result[i] = message.charAt(rowIndex * keyLength + columnIndex);
            }
            return new String(result);
        }

    //задание4
        public static int[] twoProduct(int[] arr, int n) {
            HashSet<Integer> set = new HashSet<>();
            for (int m : arr) {
                int target = n / m;
                if (n % m == 0 && set.contains(target))
                    return new int[] { target, m };
                set.add(m);
            }
            return new int[] {};
        }

    //задание5
        public static int[] isExact(int f, int m, int n) {
            return (f < n) ? isExact(f * (m + 1), m + 1, n) : new int[] { f, m };
        }
        public static int[] isExact(int n) {
            int[] res = isExact(1, 1, n);
            return (res[0] == n) ? res : new int[] {};
        }

    //задание6
        public static String fractions(String frac) {
            int startBracket = frac.indexOf('(');
            if (startBracket != -1) {
                String repeating = frac.substring(startBracket + 1, frac.length() - 1);
                frac = frac.substring(0, startBracket) + repeating.repeat(9 - repeating.length());
            }
            double a = Double.parseDouble(frac);
            int div = 2;
            while (Math.ceil(a * div) - a * div > 0.000001) div++;
            return (int) Math.ceil(a * div) + "/" + div;
        }

    //задание7
        public static String pilish_string(String str) {
            String res = "";
            String pi = String.valueOf(Math.PI).replace(".", "");
            int piIndex = 0, strIndex = 0;

            while (strIndex < str.length()) {
                int p = pi.charAt(piIndex++) - '0';
                int n = Math.min(p, str.length() - strIndex);
                res += str.substring(strIndex, strIndex + n);
                strIndex += n;
                if (strIndex < str.length()) res += ' ';
                else
                    while (n++ < p) res += res.charAt(res.length() - 1);
            }
            return res;
        }

    //задание8
        public static Integer generateNonconsecutive(String expression) {
            Stack<Integer> values = new Stack<>();
            Stack<Character> ops = new Stack<>();
            char[] tokens = expression.toCharArray();

            for (int i = 0; i < tokens.length; i++) {
                if (tokens[i] == ' ') continue;

                if (tokens[i] >= '0' && tokens[i] <= '9') {
                    StringBuilder sb = new StringBuilder();
                    while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                        sb.append(tokens[i++]);
                    values.push(Integer.parseInt(sb.toString()));
                    i--;
                } else if (tokens[i] == '(') {
                    ops.push(tokens[i]);
                } else if (tokens[i] == ')') {
                    while (ops.peek() != '(')
                        values.push(applyOp(ops.pop(), values.pop(), values.isEmpty() ? 0 : values.pop()));
                    ops.pop();
                } else if ("+-*/".indexOf(tokens[i]) >= 0) {
                    while (!ops.isEmpty() && hasPrecedence(tokens[i], ops.peek()))
                        values.push(applyOp(ops.pop(), values.pop(), values.isEmpty() ? 0 : values.pop()));
                    ops.push(tokens[i]);
                }
            }

            while (!ops.isEmpty())
                values.push(applyOp(ops.pop(), values.pop(), values.isEmpty() ? 0 : values.pop()));

            return values.pop();
        }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        return !((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'));
    }

    private static int applyOp(char op, int b, int a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': if (b == 0) throw new UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }

    //задание9
        public static String isValid(String str) {
            int[] charCounts = new int[26];
            for (char c : str.toCharArray()) {
                charCounts[c - 'a']++;
            }
            int prevCount = -1;
            int removals = 0;
            for (int count : charCounts) {
                if (count > 0) {
                    if (prevCount == -1) {
                        prevCount = count;
                    } else if (prevCount != count) {
                        removals += Math.abs(prevCount - count);
                        if (removals > 1) return "NO";
                    }
                }
            }
            return "YES";
        }

    //задание10
        public static String findLCS(String str_1, String str_2) {
            int m = str_1.length(), n = str_2.length();
            int[][] dp = new int[m + 1][n + 1];

            IntStream
                    .range(1, m + 1)
                    .forEach(i -> IntStream.range(1, n + 1)
                            .forEach(j -> dp[i][j] = (str_1.charAt(i - 1) == str_2.charAt(j - 1))
                                    ? dp[i - 1][j - 1] + 1
                                    : Math.max(dp[i - 1][j], dp[i][j - 1]))
                    );

            StringBuilder lcs = new StringBuilder();
            for (int i = m, j = n; i > 0 && j > 0; ) {
                if (str_1.charAt(i - 1) == str_2.charAt(j - 1)) {
                    lcs.append(str_1.charAt(i - 1));
                    i--; j--;
                } else if (dp[i - 1][j] > dp[i][j - 1]) i--; else j--;
            }
            return lcs.reverse().toString();
        }
    }