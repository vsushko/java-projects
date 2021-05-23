package com.vsushko;

/**
 * @author vsushko
 */
public class SimpleGenericService {
    private LibraryService.DAO dao;
    private LoggerService logger;

//    public SimpleGenericService(LibraryService.DAO dao, LoggerService logger) {
//        System.out.println("SimpleGenericService constructor");
//        this.dao = dao;
//        this.logger = logger;
//    }

    public Book fetchBookById(Integer id) {
        System.out.println("GenericService.foo");

        final Book book = dao.fetchBookById(id);
        logger.log(book.getTitle());
        return book;
    }

    public LibraryService.DAO getDao() {
        return dao;
    }

    public void setDao(LibraryService.DAO dao) {
        System.out.println("Set DAO");
        this.dao = dao;
    }

    public LoggerService getLogger() {
        return logger;
    }

    public void setLogger(LoggerService logger) {
        System.out.println("Set logger");
        this.logger = logger;
    }
}
