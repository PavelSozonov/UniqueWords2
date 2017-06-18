package ru.inno.ps.words;

import ru.inno.ps.words.wordprocessor.ConcurrentWordProcessor;
import ru.inno.ps.words.resource.FileResource;

/**
 * Created by pavel on 09.06.17.
 */
public class Client {
    public static void main(String[] args) {
        ConcurrentWordProcessor wordProcessor = new ConcurrentWordProcessor(
                new FileResource("test_text_files/file1.txt"),
                new FileResource("test_text_files/file2.txt"),
                new FileResource("test_text_files/file3.txt"),
                new FileResource("test_text_files/file4.txt"),
                new FileResource("test_text_files/file5.txt"),
                new FileResource("test_text_files/file6.txt"),
                new FileResource("test_text_files/file7.txt"),
                new FileResource("test_text_files/file8.txt"),
                new FileResource("test_text_files/file9.txt"),
                new FileResource("test_text_files/file10.txt"),
                new FileResource("test_text_files/file11.txt"),
                new FileResource("test_text_files/file12.txt"));

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
