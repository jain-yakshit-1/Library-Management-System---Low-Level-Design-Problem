package com.library;

import java.util.*;

import com.library.search.SearchStrategy;
import com.library.search.SearchStrategyFactory;
import com.library.search.SearchType;

class BookInventory {
    private List<Book> books = new ArrayList<>();

    // Add Book
    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null.");
        }
        books.add(book);
    }

    // Remove Book
    public void removeBook(Book book) {
        if (book == null || !books.contains(book)) {
            throw new IllegalArgumentException("Book not found in inventory.");
        }
        books.remove(book);
    }

    // Update Book
    public void updateBook(Book oldBook, Book newBook) {
        if (oldBook == null || newBook == null || !books.contains(oldBook)) {
            throw new IllegalArgumentException("Invalid book or book not found in inventory.");
        }
        int index = books.indexOf(oldBook);
        books.set(index, newBook);
    }

    // Search Books
    public List<Book> searchBooks(SearchType searchType, String query) {
        SearchStrategy searchStrategy = SearchStrategyFactory.getSearchStrategy(searchType);
        if (searchStrategy == null) {
            throw new IllegalArgumentException("Invalid search type.");
        }
        return searchStrategy.search(books, query);
    }

    

    

    // Get Available Books
    public List<Book> getAvailableBooks() {
        return new ArrayList<>(books);
    }

    // Get All Books
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }
}
