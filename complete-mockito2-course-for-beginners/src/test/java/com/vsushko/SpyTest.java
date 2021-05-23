package com.vsushko;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static com.vsushko.LibraryService.DEFAULT_BOOK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * @author vsushko
 */
class SpyTest {

    @Spy
    private LibraryService.DAO dao;

    @Mock(name = "outLogger")
    private LoggerService logger1;

    @Mock(name = "errLogger")
    private LoggerService logger2;

    @InjectMocks
    private GenericService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSpy() {
        // final LibraryService.DAO spy = spy(LibraryService.DAO.class); // we use annotation

        // doesn't work
        // when(dao.fetchBookById(anyInt())).thenReturn(null);

        doReturn(null).when(dao).fetchBookById(anyInt());

        final Book book = service.fetchBook(42, "Effective Java");

        assertThat(book, is(equalTo(DEFAULT_BOOK)));

        verify(dao).fetchBookByTitle(anyString());
    }

    @Test
    void testMethod() {
        final LibraryService.DAO real = new LibraryService.DAO();
        final LibraryService.DAO spy = spy(real);

        // interaction with real object are not going to the spy object and vice versa

        // real.fetchBookById(42);
        // verify(spy).fetchBookById(anyInt());

        // spy.addId(1);
        // assertThat(real.getId(), is(equalTo(1)));

        // fetchBookById should be final
        doReturn(null).when(spy).fetchBookById(42);
        final Book book = spy.fetchBookById(42);
        assertThat(book, is(nullValue()));

    }
}
