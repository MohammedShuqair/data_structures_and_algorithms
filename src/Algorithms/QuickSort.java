package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


public class QuickSort {
    static int[] bubble(int[] collection) {
        for (int i = 1; i < collection.length; i++) {
            for (int j = collection.length - 1; j >= i; j--) {
                if (collection[j] < collection[j - 1]) {
                    int temp = collection[j];
                    collection[j] = collection[j - 1];
                    collection[j - 1] = temp;
                }
            }
        }
        return collection;
    }

    static int[] selectionSort(int[] collection) {
        for (int i = 0; i < collection.length - 1; i++) {
            int k = i;
            for (int j = i + 1; j < collection.length; j++) {
                if (collection[k] > collection[j]) {
                    k = j;
                }
                if (k != i && (j == collection.length - 1)) {
                    int temp = collection[i];
                    collection[i] = collection[k];
                    collection[k] = temp;
                }
            }
        }
        return collection;
    }

    public static int calcPivotIndex(int[] array) {
        int errorResult = -1;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] < array[i + 1]) {
                return i + 1;

            } else if (array[i] > array[i + 1]) {
                return i;

            }
        }
        return errorResult;
    }

    public static int[] quickSort(int[] array) {
        Stack<int[]> stack = new Stack<int[]>();
        List<int[]> result = new ArrayList<>();
        stack.push(array);
        while (!stack.isEmpty()) {
            final int[] currentArray = stack.pop();
            final int pivotIndex = calcPivotIndex(currentArray);
            if (pivotIndex != -1) {
                final int pivot = currentArray[pivotIndex];
                final int divider = partition(currentArray, pivot);
                stack.push(SharedMethods.subArray(currentArray, divider + 1, currentArray.length - 1));
                stack.push(SharedMethods.subArray(currentArray, 0, divider));
            } else {
                result.add(currentArray);
            }

        }


        return SharedMethods.convertListToArray(result);
    }


    public static int partition(int[] array, int pivot) {
        int leftIndex = 0;
        int rightIndex = array.length - 1;
        if (array.length > 1) {
            while (leftIndex < rightIndex) {
                while (array[leftIndex] < pivot) {
                    leftIndex++;
                }
                while (array[rightIndex] >= pivot) {
                    rightIndex--;
                }
                if (leftIndex < rightIndex) {
                    int temp = array[leftIndex];
                    array[leftIndex] = array[rightIndex];
                    array[rightIndex] = temp;
                }

            }
            return leftIndex - 1;
        }
        return -1;

    }

    public static void main(String[] args) {
        int[] collection = {10, 9, 8, 7, 6, 5, 3, 2, 1};
        System.out.println(Arrays.toString(quickSort(collection)));
    }

    private static void print(int[] collection) {
        for (int key : collection) {
            System.out.println(key);
        }
    }
}
