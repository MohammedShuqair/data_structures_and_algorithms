package Algorithms;

public class Recursion {
    public static int sumSquares(int first, int second) {
//        int pivot =
//                (second-first)%2!=0?
//                        first+((second-first-1)/2)
//                        :first+((second-first)/2);
        if (first == second) {
            return first * second;
        }
        int pivot = (first + second) / 2;

        return sumSquares(first, pivot) + sumSquares(pivot + 1, second);
    }

    public static int fact(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * fact(n - 1);
    }

    public static String reverse(String value) {
        if (value.length() == 1) return value;
        String subProbSoul = reverse(value.substring(0, value.length() - 1));
        return value.substring(value.length() - 1).concat(subProbSoul);
    }

    public static void main(String[] args) {
        System.out.println(Recursion.reverse("12345"));
    }
}
