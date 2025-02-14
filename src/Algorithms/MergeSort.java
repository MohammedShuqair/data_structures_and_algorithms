package Algorithms;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MergeSort {
    static int[] merge(int[] first, int[] second) {
        int[] mergedArray = new int[first.length + second.length];
        int mergedIndex = 0;
        int firstIndex = 0;
        int secondIndex = 0;
        while (firstIndex < first.length && secondIndex < second.length) {
            if (first[firstIndex] < second[secondIndex]) {
                mergedArray[mergedIndex] = first[firstIndex];
                firstIndex++;
            } else {
                mergedArray[mergedIndex] = second[secondIndex];
                secondIndex++;
            }
            mergedIndex++;
        }
        while (firstIndex < first.length) {
            mergedArray[mergedIndex] = first[firstIndex];
            firstIndex++;
            mergedIndex++;
        }
        while (secondIndex < second.length) {
            mergedArray[mergedIndex] = second[secondIndex];
            secondIndex++;
            mergedIndex++;
        }
        return mergedArray;
    }

    public static int[] reqMergeSort(int[] array) {
        int inputLength = array.length;
        if (inputLength <= 1) {
            return array;
        }
        int midIndex = (inputLength - 1) / 2;
        int[] leftPart = reqMergeSort(SharedMethods.subArray(array, 0, midIndex));
        int[] rightPart = reqMergeSort(SharedMethods.subArray(array, midIndex + 1, inputLength - 1));

        return merge(leftPart, rightPart);
    }

    static int[] mergeSort(int[] array) {
        Stack<int[]> stack = new Stack<>();
        int[] result = null;
        stack.push(array);
        while (!stack.isEmpty()) {
            int[] subArray = stack.pop();
            int midIndex = (subArray.length - 1) / 2;
            if (subArray.length > 1) {
                stack.push(SharedMethods.subArray(subArray, midIndex + 1, subArray.length - 1));
                stack.push(SharedMethods.subArray(subArray, 0, midIndex));
            } else {
                if (result == null) {
                    result = merge(subArray, stack.pop());
                } else {
                    result = merge(result, merge(subArray, stack.pop()));
                }

            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                        reqMergeSort(
                                new int[]{66, 22, 36, 6, 79, 26, 45, 74}
                        )
                )
        );
    }
}
