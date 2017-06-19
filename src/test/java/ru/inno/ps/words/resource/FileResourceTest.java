package ru.inno.ps.words.resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by pavel on 18.06.17.
 */
public class FileResourceTest {
    private FileResource fileResource;

    // Unit test (with reading from mocked buffered reader)
    @Test
    public void nextWord_readFromMockedBufferedReaderWithCorrectWords_assertTrue() throws Exception {
        BufferedReader mockedBufferedReader = mock(BufferedReader.class);
        FileResource fileResource = new FileResource(
                "src/test/resources/empty_file_stub.txt");

        when(mockedBufferedReader.read())
                .thenReturn((int)'А')
                .thenReturn((int)'х')
                .thenReturn((int)'м')
                .thenReturn((int)'а')
                .thenReturn((int)'т')
                .thenReturn((int)'о')
                .thenReturn((int)'в')
                .thenReturn((int)'а')
                .thenReturn((int)' ')
                .thenReturn((int)'П')
                .thenReturn((int)'у')
                .thenReturn((int)'ш')
                .thenReturn((int)'к')
                .thenReturn((int)'и')
                .thenReturn((int)'н')
                .thenReturn(-1);

        Class fileResourceClass = FileResource.class;
        Field bufferedReaderField = fileResourceClass.getDeclaredField("bufferedReader");
        bufferedReaderField.setAccessible(true);
        bufferedReaderField.set(fileResource, mockedBufferedReader);
        assertEquals(fileResource.nextWord(), "Ахматова");
        assertEquals(fileResource.nextWord(), "Пушкин");
        assertTrue(fileResource.nextWord() == null);
    }

    // Unit test (with reading from mocked buffered reader)
    @Test
    public void nextWord_readFromMockedBufferedReaderSeveralNewLines_assertTrue() throws Exception {
        BufferedReader mockedBufferedReader = mock(BufferedReader.class);
        FileResource fileResource = new FileResource(
                "src/test/resources/empty_file_stub.txt");

        when(mockedBufferedReader.read())
                .thenReturn((int)'А')
                .thenReturn((int)'б')
                .thenReturn((int)'\n')
                .thenReturn((int)'\n')
                .thenReturn((int)'В')
                .thenReturn((int)'г')
                .thenReturn(-1);

        Class fileResourceClass = FileResource.class;
        Field bufferedReaderField = fileResourceClass.getDeclaredField("bufferedReader");
        bufferedReaderField.setAccessible(true);
        bufferedReaderField.set(fileResource, mockedBufferedReader);
        assertEquals(fileResource.nextWord(), "Аб");
        assertEquals(fileResource.nextWord(), "Вг");
        assertTrue(fileResource.nextWord() == null);
    }

    // Unit test (with reading from mocked buffered reader)
    @Test
    public void nextWord_readFromMockedBufferedReaderSeveralNewLinesAndSpaces_assertTrue() throws Exception {
        BufferedReader mockedBufferedReader = mock(BufferedReader.class);
        FileResource fileResource = new FileResource(
                "src/test/resources/empty_file_stub.txt");

        when(mockedBufferedReader.read())
                .thenReturn((int)'А')
                .thenReturn((int)'б')
                .thenReturn((int)' ')
                .thenReturn((int)' ')
                .thenReturn((int)'\n')
                .thenReturn((int)'\n')
                .thenReturn((int)' ')
                .thenReturn((int)' ')
                .thenReturn((int)'В')
                .thenReturn((int)'г')
                .thenReturn((int)' ')
                .thenReturn((int)' ')
                .thenReturn((int)'\n')
                .thenReturn((int)'\n')
                .thenReturn((int)' ')
                .thenReturn((int)' ')
                .thenReturn(-1);

        Class fileResourceClass = FileResource.class;
        Field bufferedReaderField = fileResourceClass.getDeclaredField("bufferedReader");
        bufferedReaderField.setAccessible(true);
        bufferedReaderField.set(fileResource, mockedBufferedReader);
        assertEquals(fileResource.nextWord(), "Аб");
        assertEquals(fileResource.nextWord(), "Вг");
        assertTrue(fileResource.nextWord() == null);
    }

    // Unit test (with reading from mocked buffered reader)
    @Test
    public void nextWord_readFromMockedBufferedReaderSymbolsIsWord_assertTrue() throws Exception {
        BufferedReader mockedBufferedReader = mock(BufferedReader.class);
        FileResource fileResource = new FileResource(
                "src/test/resources/empty_file_stub.txt");

        when(mockedBufferedReader.read())
                .thenReturn((int)'!')
                .thenReturn((int)'!')
                .thenReturn((int)' ')
                .thenReturn((int)'.')
                .thenReturn((int)'.')
                .thenReturn(-1);

        Class fileResourceClass = FileResource.class;
        Field bufferedReaderField = fileResourceClass.getDeclaredField("bufferedReader");
        bufferedReaderField.setAccessible(true);
        bufferedReaderField.set(fileResource, mockedBufferedReader);
        assertEquals(fileResource.nextWord(), "!!");
        assertEquals(fileResource.nextWord(), "..");
        assertTrue(fileResource.nextWord() == null);
    }

    // Unit test (with reading from mocked buffered reader)
    @Test
    public void nextWord_readFromMockedBufferedReaderSymbolsAndLettersIsWord_assertTrue() throws Exception {
        BufferedReader mockedBufferedReader = mock(BufferedReader.class);
        FileResource fileResource = new FileResource(
                "src/test/resources/empty_file_stub.txt");

        when(mockedBufferedReader.read())
                .thenReturn((int)'!')
                .thenReturn((int)'А')
                .thenReturn((int)'б')
                .thenReturn((int)' ')
                .thenReturn((int)'В')
                .thenReturn((int)'г')
                .thenReturn((int)'.')
                .thenReturn((int)' ')
                .thenReturn((int)'$')
                .thenReturn((int)'Д')
                .thenReturn((int)'е')
                .thenReturn((int)'$')
                .thenReturn(-1);

        Class fileResourceClass = FileResource.class;
        Field bufferedReaderField = fileResourceClass.getDeclaredField("bufferedReader");
        bufferedReaderField.setAccessible(true);
        bufferedReaderField.set(fileResource, mockedBufferedReader);
        assertEquals(fileResource.nextWord(), "!Аб");
        assertEquals(fileResource.nextWord(), "Вг.");
        assertEquals(fileResource.nextWord(), "$Де$");
        assertTrue(fileResource.nextWord() == null);
    }

    // Unit test (with reading from mocked buffered reader)
    @Test
    public void nextWord_readFromMockedBufferedReaderSeveralSpaces_assertTrue() throws Exception {
        BufferedReader mockedBufferedReader = mock(BufferedReader.class);
        FileResource fileResource = new FileResource(
                "src/test/resources/empty_file_stub.txt");

        when(mockedBufferedReader.read())
                .thenReturn((int)'А')
                .thenReturn((int)'б')
                .thenReturn((int)' ')
                .thenReturn((int)' ')
                .thenReturn((int)'В')
                .thenReturn((int)'г')
                .thenReturn(-1);

        Class fileResourceClass = FileResource.class;
        Field bufferedReaderField = fileResourceClass.getDeclaredField("bufferedReader");
        bufferedReaderField.setAccessible(true);
        bufferedReaderField.set(fileResource, mockedBufferedReader);
        assertEquals(fileResource.nextWord(), "Аб");
        assertEquals(fileResource.nextWord(), "Вг");
        assertTrue(fileResource.nextWord() == null);
    }

    // Unit test (with reading from mocked buffered reader)
    @Test
    public void nextWord_readFromMockedBufferedReaderWithLatinLetters_expectedException() throws Exception {
        BufferedReader mockedBufferedReader = mock(BufferedReader.class);
        FileResource fileResource = new FileResource(
                "src/test/resources/empty_file_stub.txt");

        when(mockedBufferedReader.read())
                .thenReturn((int)' ')
                .thenReturn((int)'W')
                .thenReturn((int)'o')
                .thenReturn((int)'r')
                .thenReturn((int)'d')
                .thenReturn(-1);

        Class fileResourceClass = FileResource.class;
        Field bufferedReaderField = fileResourceClass.getDeclaredField("bufferedReader");
        bufferedReaderField.setAccessible(true);
        bufferedReaderField.set(fileResource, mockedBufferedReader);

        try {
            fileResource.nextWord();
            fail();
        } catch (IllegalArgumentException expected) {
            System.out.printf("Expected exception was caught. Message: %s\n", expected.getMessage());
        }
    }

    // Integration test (with reading from the real txt file)
    @Test
    public void nextWord_correctWords_assertEqualsAndTrue() throws Exception {
        fileResource = new FileResource(
                "src/test/resources/test_words_correct.txt");
        assertEquals(fileResource.nextWord(), "Автобус");
        assertEquals(fileResource.nextWord(), "Биатлон");
        assertEquals(fileResource.nextWord(), "Вагон");
        assertEquals(fileResource.nextWord(), "Город");
        assertEquals(fileResource.nextWord(), "Депо");
        assertTrue(fileResource.nextWord() == null);
    }

    // Integration test (with reading from the real txt file)
    @Test
    public void nextWord_thereAreLatinLetters_expectedException() throws Exception {
        fileResource = new FileResource(
                "src/test/resources/test_words_with_latin_letters.txt");
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