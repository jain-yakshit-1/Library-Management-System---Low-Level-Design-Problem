package com.library.search;

import java.util.ArrayList;
import java.util.List;

import com.library.Book;

public class SearchByIsbnStrategy implements SearchStrategy {
    @Override
    public List<Book> search(List<Book> books, String query) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getISBN().equals(query)) {
                result.add(book);
            }
        }
        return result;
    }
}

