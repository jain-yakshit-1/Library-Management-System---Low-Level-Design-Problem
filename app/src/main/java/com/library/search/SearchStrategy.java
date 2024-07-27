package com.library.search;
import java.util.List;

import com.library.Book;

public interface SearchStrategy {
    List<Book> search(List<Book> books, String query);
}
