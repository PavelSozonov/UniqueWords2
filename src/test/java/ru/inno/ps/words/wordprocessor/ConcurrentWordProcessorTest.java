package ru.inno.ps.words.wordprocessor;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.inno.ps.words.resource.Resource;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by pavel on 18.06.17.
 */
public class ConcurrentWordProcessorTest {

    private ConcurrentWordProcessor concurrentWordProcessor;
    private Resource mockedResource1;
    private Resource mockedResource2;

    @Before
    public void setUp() throws Exception {
        mockedResource1 = mock(Resource.class);
        mockedResource2 = mock(Resource.class);
    }

    @Test
    public void checkDuplicates_thereIsDuplicate_returnsFalse() throws Exception {

        when(mockedResource1.nextWord()).thenReturn("Ахматова")
                .thenReturn("Пушкин").thenReturn(null);


        when(mockedResource2.nextWord()).thenReturn("Есенин")
                .thenReturn("Маяковский").thenReturn("Пушкин")
                .thenReturn("Чехов").thenReturn(null);

        concurrentWordProcessor = new ConcurrentWordProcessor(mockedResource1, mockedResource2);

        assertFalse(concurrentWordProcessor.checkDuplicates());
    }

    @Test
    public void checkDuplicates_thereIsNoDuplicate_returnsTrue() throws Exception {

        when(mockedResource1.nextWord()).thenReturn("Ахматова")
                .thenReturn("Пушкин").thenReturn(null);

        when(mockedResource2.nextWord()).thenReturn("Есенин")
                .thenReturn("Маяковский").thenReturn("Чехов").thenReturn(null);

        concurrentWordProcessor = new ConcurrentWordProcessor(mockedResource1, mockedResource2);

        assertTrue(concurrentWordProcessor.checkDuplicates());
    }

}