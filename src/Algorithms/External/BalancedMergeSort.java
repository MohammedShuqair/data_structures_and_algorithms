package Algorithms.External;

import Algorithms.MergeSort;
import Algorithms.SharedMethods;

import java.io.*;

public class BalancedMergeSort {
    static String basePath = "src\\Algorithms\\External\\";
    static String fPath = basePath + "f.text";
    static String f1Path = basePath + "f1.text";
    static String f2Path = basePath + "f2.text";
    static String f3Path = basePath + "f3.text";
    static String f4Path = basePath + "f4.text";

    static File[] init() throws IOException {
        File f = new File(fPath);
        File f1 = new File(f1Path);
        File f2 = new File(f2Path);
        File f3 = new File(f3Path);
        File f4 = new File(f4Path);
        createFiles(f, f1, f2, f3, f4);

        FileWriter fWriter = new FileWriter(f);

        if (f.length() == 0) {
            String data = "20,8,5,17,21,9,3,11,2,18,15,23,14,6,15,24,10,21,13,16,19,15,22,5,18,20,8,5,12,1,26,25,4,15,7,";
          /*  for (int j = 0; j < data.length(); j++) {
                fWriter.write(data.charAt(j));
            }*/
            fWriter.write(data);
            fWriter.close();
        }
        return new File[]{f, f1, f2, f3, f4};
    }

    static void sort(
            File f,
            File f1,
            File f2,
            File f3,
            File f4
    ) {
        try {

            FileReader fReader = new FileReader(f);
            FileReader f1Reader = new FileReader(f1);
            FileWriter f1Writer = new FileWriter(f1);
            FileWriter f2Writer = new FileWriter(f2);
            int index = 0;
            int maxRunLength = 4;
            destribute(fReader, f1Writer, f2Writer, index, maxRunLength);
            maxRunLength *= 2;
            f1Writer.close();
            f2Writer.close();
            fReader.close();
            PrintWriter writer = new PrintWriter(f1);
            writer.print("eof");
            writer.close();


        } catch (IOException e) {

        }
    }

    private static void destribute(FileReader fReader, FileWriter f1Writer, FileWriter f2Writer, int index, int maxRunLength) throws IOException {
        while (fReader.ready()) {
            int runLength = 0;
            int ch;
            StringBuilder runString = new StringBuilder();
            int[] runInts;

            while (
                    (runLength < maxRunLength && (ch = fReader.read()) != -1)) {
                if (ch == ',') {
                    runLength++;
                }
                runString.append((char) ch);
            }

            if (runString.length() > 0) {
                runInts = SharedMethods.stringToArray(runString.toString());
                runInts = MergeSort.reqMergeSort(runInts);
                runString = new StringBuilder(SharedMethods.arrayToString(runInts));
                if (index % 2 == 0) {
                    f1Writer.write(runString.toString());
                } else {
                    f2Writer.write(runString.toString());
                }
                index++;
            }
        }
    }

    private static void createFiles(File f, File f1, File f2, File f3, File f4) throws IOException {
        if (!f.exists()) f.createNewFile();
        if (!f1.exists()) f1.createNewFile();
        if (!f2.exists()) f2.createNewFile();
        if (!f3.exists()) f3.createNewFile();
        if (!f4.exists()) f4.createNewFile();
    }

    public static void main(String[] args) throws IOException {
        File[] files = init();
        sort(files[0], files[1], files[2], files[3], files[4]);
    }
}
