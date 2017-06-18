package ru.inno.ps.words;

import ru.inno.ps.words.wordprocessor.ConcurrentWordProcessor;
import ru.inno.ps.words.resource.FileResource;

/**
 * Created by pavel on 09.06.17.
 */
public class Client {
    public static void main(String[] args) {
        ConcurrentWordProcessor wordProcessor = new ConcurrentWordProcessor(
                new FileResource("file1.txt"),
                new FileResource("file2.txt"),
                new FileResource("file3.txt"),
                new FileResource("file4.txt"),
                new FileResource("file5.txt"),
                new FileResource("file6.txt"),
                new FileResource("file7.txt"),
                new FileResource("file8.txt"),
                new FileResource("file9.txt"),
                new FileResource("file10.txt"),
                new FileResource("file11.txt"),
                new FileResource("file12.txt"));

        printResult(wordProcessor.checkDuplicates());
    }

    private static void printResult(boolean duplicatesNotFound) {
        if (duplicatesNotFound) {
            System.out.println("There are no duplicates");
        } else {
            System.out.println("There are duplicates");
        }
    }
}
