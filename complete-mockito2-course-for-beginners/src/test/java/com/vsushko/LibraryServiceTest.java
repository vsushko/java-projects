package com.vsushko;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author vsushko
 */
class LibraryServiceTest {

    @Test
    void testMethod() {
        final LibraryService.DAO dao = mock(LibraryService.DAO.class);
        final LibraryService service = new LibraryService(dao);

        service.hasBookWithId(42);

        verify(dao).fetchBookById(anyInt());
    }
}
