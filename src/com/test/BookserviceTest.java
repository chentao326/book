package com.test;

import com.pojo.Book;
import com.pojo.Page;
import com.service.impl.BookServiceImpl;
import com.service.service.BookService;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author : chentao
 * @time : 11:38 2022/6/24
 */
public class BookserviceTest {
    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"秘籍在手，天下我有！", "1125", new BigDecimal(1000000), 100000000, 0, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(23,"社会，！", "1125", new BigDecimal(999999), 10, 111110, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(10));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }


    @Test
    public void page(){
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }

}