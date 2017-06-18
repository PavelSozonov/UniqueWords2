package ru.inno.ps.words.resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pavel on 18.06.17.
 */
public class FileResourceTest {
    private FileResource fileResource;

    @Test
    public void nextWord_correctWords_assertEqualsAndTrue() throws Exception {
         fileResource = new FileResource(
                 "src/test/java/ru/inno/ps/words/resource/test_words_correct.txt");
         assertEquals(fileResource.nextWord(), "Автобус");
         assertEquals(fileResource.nextWord(), "Биатлон");
         assertEquals(fileResource.nextWord(), "Вагон");
         assertEquals(fileResource.nextWord(), "Город");
         assertEquals(fileResource.nextWord(), "Депо");
         assertTrue(fileResource.nextWord() == null);
    }

    @Test
    public void nextWord_thereAreLatinLetters_expectedException() throws Exception {
        fileResource = new FileResource(
                "src/test/java/ru/inno/ps/words/resource/test_words_with_latin_letters.txt");
        assertEquals(fileResource.nextWord(), "Автомат");
        assertEquals(fileResource.nextWord(), "Батон");
        assertEquals(fileResource.nextWord(), "Ваза");
        try {
            fileResource.nextWord();
            fail();
        } catch (IllegalArgumentException expected) {
            System.out.printf("Expected exception was caught. Message: %s\n", expected.getMessage());
        }
    }

}