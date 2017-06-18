package ru.inno.ps.words.wordprocessor;

import ru.inno.ps.words.resource.Resource;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pavel on 09.06.17.
 *
 * Вариант 2
 *
 * Необходимо разработать программу, которая получает на вход список ресурсов,
 * содержащих текст, и проверяет уникальность каждого слова.
 * Каждый ресурс должен быть обработан в отдельном потоке, текст не должен
 * содержать инностранных символов, только кириллица, знаки препинания и цифры.
 * В случае нахождения дубликата, программа должна прекращать выполнение
 * с соответсвующим сообщением. Все ошибки должны быть корректно обработаны,
 * все API покрыто модульными тестами
 */
public class ConcurrentWordProcessor {

    private Resource[] resources;

    // Flag to stop all threads
    private volatile boolean duplicateFound = false;

    // All threads (distinct thread for each resource)
    // add new words in this dictionary
    // until the word is already in the dictionary,
    // then all threads stop
    private Set<String> dictionary = new HashSet<>();

    public ConcurrentWordProcessor(Resource... resources) {
        this.resources = resources;
    }

    /**
     * Starts distinct threads for each resource and finds duplicates
     * @return true is duplicate was not found, else false
     */
    public boolean checkDuplicates() {
        ExecutorService threadExecutor = Executors.newFixedThreadPool(10);
        for (Resource resource : resources) {
            threadExecutor.execute(() -> {
                extractWordsUntilDuplicateFound(resource);
            });
        }
        threadExecutor.shutdown();
        while (!threadExecutor.isTerminated()) {}

        if (duplicateFound) return false; // Duplicate was found
        return true; // Duplicate was not found
    }

    /**
     * Body of the threads that find duplicates
     * @param resource - an object that contains words being processed
     */
    private void extractWordsUntilDuplicateFound(Resource resource) {
        String nextWord;
        boolean stop = false;

        while (!stop) {
            nextWord = resource.nextWord();
            synchronized (this) {
                if (nextWord == null || duplicateFound) {
                    stop = true;
                } else {
                    // Checks if the word is already in the dictionary
                    if (!dictionary.add(nextWord)) {
                        duplicateFound = true;
                        System.out.printf("Duplicate is found: %s\n", nextWord);
                    }
                }
            }
        }
    }
}
