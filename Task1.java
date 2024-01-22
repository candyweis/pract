public class Task1 {
    public static void main(String[] args) {
        System.out.println(convert (5));
        System.out.println(convert (3));
        System.out.println(convert (8));

        System.out.println(fitCalc (15, 1));
        System.out.println(fitCalc (24, 2));
        System.out.println(fitCalc (41, 3));

        System.out.println(containers (2, 4, 2));
        System.out.println(containers (5, 0, 2));
        System.out.println(containers (4, 1, 4));

        System.out.println(triangleType (5, 5, 5));
        System.out.println(triangleType (5, 4, 5));
        System.out.println(triangleType (3, 4, 5));
        System.out.println(triangleType (5, 1, 1));

        System.out.println(ternaryEvaluation (8, 4));
        System.out.println(ternaryEvaluation (1, 11));
        System.out.println(ternaryEvaluation (5, 9));

        System.out.println(howManyItems (22, 1.4, 2));
        System.out.println(howManyItems (100, 1.8, 1.9));
        System.out.println(howManyItems (100, 2, 2));

        System.out.println(factorial (3));
        System.out.println(factorial (5));
        System.out.println(factorial (7));

        System.out.println(gcd (48, 18));
        System.out.println(gcd (52, 8));
        System.out.println(gcd (259, 28));

        System.out.println(ticketSaler (70, 1500));
        System.out.println(ticketSaler (24, 950));
        System.out.println(ticketSaler (53, 1250));

        System.out.println(tables (5, 2));
        System.out.println(tables (31, 20));
        System.out.println(tables (123, 58));

    }
    //задание 1
    public static float convert (int x){
        return x * 3.785f;
    }

    //задание 2
    public static int fitCalc (int x, int y){
        return x * y;
    }

    //задание 3
    public static int containers (int x, int y, int z) {
        return x * 20 + y * 50 + z * 100;
    }

    //задание 4 (ИЛИ ||, И &&)
    public static String triangleType (int x, int y, int z) {
        if ((x+y<z) || (y+z<x) || (z+x<y)) {
            return "not a triangle";
        } else if ((x == z)&&(z == y)){
            return "isosceles";
        } else if ((x == z) || (z == y) || (x == y)) {
            return "equilateral";
        } else {
            return "different-sided";
        }
    }

    //задание 5
    // (тернарный оператор или условный оператор, 1?2:3 -if 1==true, то 2, else 3)
    public static int ternaryEvaluation (int a, int b) {
        int x;
        x = a > b ? a : b;{
            return x;
        }
    }

    //задание 6(разобраться с округлением в меньшую сторону!!!)
    public static double howManyItems (double n,double w,double h) {
        return Math.floor(n/(w*h*2));
    }

    //задание 7(факториал 5! = 1*2*3*4*5)
    public static int factorial (int x) {
        int n = 1;
        for (int i = 1; i<=x; i++){
            n = n*i;
        }
        return n;
    }

    //задание 8(наибольший общий делитель)(a != b - равны или нет значения) 48 18
    public static int gcd (int a, int b) {
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    //задание 9
    public static int ticketSaler (int x, int y){
        return (int) (x*y - x*(y*0.28));
    }


    //задание 10(округление!!!!)(1 стол 2 студента, сколько не хватит)
    public static double tables (double x, double y) {
        if (x / 2 > y) {
            return Math.ceil((x / 2) - y);
        } else {
            return 0;
        }
    }
}
