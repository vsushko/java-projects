package com.vsushko;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author vsushko
 */
class SimpleGenericServiceTest {

    @Mock
    private LibraryService.DAO dao;

    @Mock
    private LoggerService logger;

    @InjectMocks
    private SimpleGenericService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testMethodSimple() {
        when(dao.fetchBookById(42)).thenReturn(LibraryService.DEFAULT_BOOK);
        final Book book = service.fetchBookById(42);

        assertThat(book, is(equalTo(LibraryService.DEFAULT_BOOK)));

        verify(logger).log(anyString());
    }
}
