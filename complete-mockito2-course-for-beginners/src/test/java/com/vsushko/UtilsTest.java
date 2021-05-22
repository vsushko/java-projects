package com.vsushko;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.invocation.MatcherApplicationStrategy;

import java.nio.file.Path;
import java.time.Year;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author vsushko
 */
class UtilsTest {

    @Test
    void testMethod() {
        final Utils utils = mock(Utils.class);

        List<Book> expect = Collections.singletonList(
                new Book("Effective Java", 280, Topic.COMPUTING, Year.of(2000), "Joshua Block"));

        when(utils.parseLibraryFrom(any(Path.class))).thenReturn(expect);

        System.out.println(utils.parseLibraryFrom(BookDAO.DEFAULT_PATH));

        MatcherAssert.assertThat(utils.parseLibraryFrom(BookDAO.DEFAULT_PATH), is(equalTo(expect)));
    }

    @Test
    void testStubbingOfException() {
        final Utils utils = mock(Utils.class);

        when(utils.getBook(anyString())).thenThrow(RuntimeException.class);

        final RuntimeException exception =
                Assertions.assertThrows(RuntimeException.class, () -> utils.getBook(""));

        MatcherAssert.assertThat(exception.getClass(), is(equalTo(RuntimeException.class)));
    }
}
