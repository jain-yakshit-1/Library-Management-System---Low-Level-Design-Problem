package com.library;

import java.util.*;

class LendingSystem {
    private Map<Book, Patron> borrowedBooks = new HashMap<>();
    private Map<Book, Queue<Patron>> reservationQueue = new HashMap<>();

    // Checkout Book
    public void checkoutBook(Book book, Patron patron) {
        if (book == null || patron == null) {
            throw new IllegalArgumentException("Book and Patron cannot be null.");
        }
        if (borrowedBooks.containsKey(book)) {
            throw new IllegalStateException("Book is already borrowed.");
        }
        borrowedBooks.put(book, patron);
        patron.addBorrowedBook(book);
    }

    // Return Book
    public void returnBook(Book book) {
        if (book == null || !borrowedBooks.containsKey(book)) {
            throw new IllegalArgumentException("Book not found in borrowed books.");
        }
        Patron patron = borrowedBooks.get(book);
        patron.removeBorrowedBook(book);
        borrowedBooks.remove(book);

        // Notify next patron in the reservation queue
        if (reservationQueue.containsKey(book) && !reservationQueue.get(book).isEmpty()) {
            Patron nextPatron = reservationQueue.get(book).poll();
            nextPatron.notify("The book '" + book.getTitle() + "' you reserved is now available.");
        }
    }

    // Reserve Book
    public void reserveBook(Book book, Patron patron) {
        if (book == null || patron == null) {
            throw new IllegalArgumentException("Book and Patron cannot be null.");
        }
        if (!borrowedBooks.containsKey(book)) {
            throw new IllegalStateException("Book is currently available. No need to reserve.");
        }

        reservationQueue.putIfAbsent(book, new LinkedList<>());
        reservationQueue.get(book).offer(patron);
        patron.notify("You have successfully reserved the book '" + book.getTitle() + "'.");
    }

    // Get Borrowed Books
    public List<Book> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks.keySet());
    }

    // Get Reserved Books for a Patron
    public List<Book> getReservedBooks(Patron patron) {
        if (patron == null) {
            throw new IllegalArgumentException("Patron cannot be null.");
        }
        List<Book> reservedBooks = new ArrayList<>();
        for (Map.Entry<Book, Queue<Patron>> entry : reservationQueue.entrySet()) {
            if (entry.getValue().contains(patron)) {
                reservedBooks.add(entry.getKey());
            }
        }
        return reservedBooks;
    }

    // Recommend Books based on borrowing history
    public List<Book> recommendBooks(Patron patron, BookInventory inventory) {
        if (patron == null || inventory == null) {
            throw new IllegalArgumentException("Patron and Inventory cannot be null.");
        }
        
        List<Book> recommendations = new ArrayList<>();
        List<Book> borrowedBooks = patron.getBorrowHistory();

        // Calculate book similarities (simple collaborative filtering)
        Map<Book, Integer> bookCount = new HashMap<>();
        for (Book book : inventory.getAllBooks()) {
            if (!borrowedBooks.contains(book)) {
                bookCount.put(book, bookCount.getOrDefault(book, 0) + 1);
            }
        }

        // Sort books by their similarity score
        bookCount.entrySet()
                .stream()
                .sorted(Map.Entry.<Book, Integer>comparingByValue().reversed())
                .limit(5)  // Recommend top 5 books
                .forEach(entry -> recommendations.add(entry.getKey()));

        return recommendations;
    }
}
