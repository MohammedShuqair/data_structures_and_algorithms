package Algorithms;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SharedMethods {
    //return sub array from input array
    //from startIndex to endIndex
    public static int[] subArray(int[] array, int startIndex, int endIndex) {
        int[] subArray = new int[(endIndex - startIndex) + 1];
        for (int i = startIndex; i <= endIndex; i++) {
            subArray[i - startIndex] = array[i];
        }
        return subArray;
    }

    public static int[] convertListToArray(List<int[]> list) {
        // Calculate the total length of the resulting array
        int totalLength = 0;
        for (int[] array : list) {
            totalLength += array.length;
        }

        // Create the resulting array
        int[] resultArray = new int[totalLength];

        // Copy each array from the list into the resulting array
        int currentIndex = 0;
        for (int[] array : list) {
            System.arraycopy(array, 0, resultArray, currentIndex, array.length);
            currentIndex += array.length;
        }

        return resultArray;
    }

    public static String arrayToString(int[] array) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
/*
            if (i != array.length - 1) {
*/
            str.append(array[i]).append(",");
            /*} else {
                str.append(array[i]);
            }*/
        }
        return str.toString();
    }

    public static int[] stringToArray(String str) {
        String[] nums = str.split(",");
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (!Objects.equals(nums[i], "")) {
                result[i] = Integer.parseInt(nums[i]);

            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(
                        stringToArray(
                                arrayToString(
                                        new int[]{1, 2, 3, 4}
                                ))));
    }

}
