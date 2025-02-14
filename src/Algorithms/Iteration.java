package Algorithms;

public class Iteration {
    public static int sumSquares (int first,int second){
        int result = 0;
        for (int i=first;i<=second;i++){
            result+=i*i;
        }
        return result;
    }

    public static void fact(int n){
        int result=1;
        while (n>0){
            result *=n--;
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        fact(4);
    }
}
