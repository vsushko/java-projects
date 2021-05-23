package com.vsushko;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.vsushko.LibraryService.DEFAULT_BOOK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * @author vsushko
 */
class GenericServiceTest {

    @Mock
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
    void testInvocationOrder() {
        when(dao.fetchBookById(anyInt())).thenReturn(DEFAULT_BOOK);

        service.fetchBookById(1);
        service.fetchBookById(2);

        final InOrder singleInOrder = inOrder(dao);
        singleInOrder.verify(dao).fetchBookById(1);
        singleInOrder.verify(dao).fetchBookById(2);

        final InOrder multipleInOrder = inOrder(logger1, logger2);
        multipleInOrder.verify(logger1).logToOut(anyString());
        multipleInOrder.verify(logger2).logToErr(anyString());

        final InOrder order = inOrder(dao, logger1, logger2);
        order.verify(dao).fetchBookById(1);
        order.verify(dao).fetchBookById(2);
        order.verify(logger1).logToOut(anyString());
        order.verify(logger2).logToErr(anyString());
    }

    @Test
    void testMethod() {
        when(dao.fetchBookById(42)).thenReturn(DEFAULT_BOOK);

        final Book book = service.fetchBookById(42);

        assertThat(book, is(equalTo(DEFAULT_BOOK)));

        verify(logger1).logToOut(anyString());
        verify(logger2).logToErr(anyString());
    }

    @Test
    void testNumberOfInvocations() {
        when(dao.fetchBookById(42)).thenReturn(DEFAULT_BOOK);

        service.fetchBookById(42);

        // equivalent
        verify(dao).fetchBookById(42);
        verify(dao, times(1)).fetchBookById(42);
        verify(dao, atLeast(1)).fetchBookById(42);
        verify(dao, atLeastOnce()).fetchBookById(42);
        verify(dao, atMost(1)).fetchBookById(42);
        verify(dao, atMostOnce()).fetchBookById(42);

        verify(logger1, never()).logToErr(anyString());
        verify(logger1, times(1)).logToOut(anyString());
        verify(logger1, atLeast(1)).logToOut(anyString());
        verify(logger1, atMost(10)).logToOut(anyString());

        verify(logger2, never()).logToOut(anyString());
        verify(logger2, times(1)).logToErr(anyString());

        verifyNoMoreInteractions(dao);
        verifyNoMoreInteractions(logger1);
        verifyNoMoreInteractions(logger2);

        final LoggerService unusedMock = mock(LoggerService.class);
        verifyZeroInteractions(unusedMock);
    }

    @Test
    void testDoThrow() {
        // stub an exception
        // when(dao.fetchBookById(anyInt())).thenThrow(RuntimeException.class);
        // when(dao.foo()).thenThrow(RuntimeException.class);

        doThrow(RuntimeException.class).when(dao).foo();
        // doThrow(new RuntimeException()).when(dao).foo();

        RuntimeException exception = Assertions.assertThrows(NullPointerException.class,
                () -> service.fetchBookById(42)
        );
        assertThat(exception.getClass(), is(equalTo(NullPointerException.class)));
    }

    @Test
    void testDoReturn() {
        final GenericService spy = spy(service);
        // when(spy.fetchBookById(42)).thenReturn(LibraryService.DEFAULT_BOOK);

        doReturn(DEFAULT_BOOK).when(spy).fetchBookById(42);
    }

    @Test
    void testAnswerMethod() {
        when(dao.fetchBookById(anyInt())).thenReturn(DEFAULT_BOOK);

        doAnswer(invocation -> {
            Integer id = invocation.getArgument(0, Integer.class);
            return id == 42 ? DEFAULT_BOOK : null;
        }).when(dao).fetchBookById(anyInt());

        final Book book = service.fetchBookById(42);
        assertThat(book, is(equalTo(DEFAULT_BOOK)));
    }

    @Test
    void testDoNothing() {
        doNothing().doThrow(RuntimeException.class).when(dao).foo();

        dao.foo();

        final RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> dao.foo());

        assertThat(exception.getClass(), is(equalTo(RuntimeException.class)));
    }

    @Test
    void testDoCallRealMethod() {
        doCallRealMethod().when(dao).fetchBookById(anyInt());
        when(dao.fetchBookByTitle(anyString())).thenReturn(DEFAULT_BOOK);
        service.fetchBookById(42);
    }
}
