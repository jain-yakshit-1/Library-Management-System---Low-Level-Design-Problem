package com.library.search;

import java.util.ArrayList;
import java.util.List;

import com.library.Book;

public class SearchByTitleStrategy implements SearchStrategy {
    @Override
    public List<Book> search(List<Book> books, String query) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(query)) {
                result.add(book);
            }
        }
        return result;
    }
}
