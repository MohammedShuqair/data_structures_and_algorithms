package Algorithms;

import Heep.HeepTree;

import java.util.Arrays;

public class SortByHeep {
    //sort more than two elements
    public static int[] sort(int[] array) {
        int[] result = new int[array.length];
        int index = array.length - 1;
        while (array.length > 0) {
            array = new HeepTree(array.length, array).heapify();
            //add value descending
            result[index] = array[0];
            //index always point on last element of array variable
            array = SharedMethods.subArray(array, 1, index);
            index--;
        }


        return result;
    }

    public static void main(String[] args) {
        int[] array = {5, 10, 27, 60, 59, 62, 14, 73};
        System.out.println(Arrays.toString(sort(array)));
    }
}
