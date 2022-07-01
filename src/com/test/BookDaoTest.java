package com.test;

import com.DAO.BookDao;;
import com.DAO.impl.BookDaoImpl;
import com.pojo.Book;
import com.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author : chentao
 * @time : 11:17 2022/6/24
 */
public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "爬虫从入门到入狱", "沃兹基谢德", new BigDecimal(9999), 1000, 0, null));
    }

    @Test
    public void deleteBookByid() {
        bookDao.deleteBookById(20);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(20, "黑客从入门到入狱", "沃兹基谢德", new BigDecimal(9999), 1000, 0, null));
    }

    @Test
    public void queryBookByid() {
        System.out.println(bookDao.queryBookById(20));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        for (Book book : bookDao.queryForPageItems(8, Page.PAGE_SIZE)) {
            System.out.println(book);
        }
    }

}