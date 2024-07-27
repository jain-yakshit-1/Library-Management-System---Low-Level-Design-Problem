package com.library;

import java.util.ArrayList;
import java.util.List;

public class Patron {
    private String name;
    private String patronId;
    private List<Book> borrowHistory = new ArrayList<>();

    // Constructor
    public Patron(String name, String patronId) {
        if (name == null || name.isEmpty() || patronId == null || patronId.isEmpty()) {
            throw new IllegalArgumentException("Name and Patron ID cannot be null or empty.");
        }
        this.name = name;
        this.patronId = patronId;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public String getPatronId() {
        return patronId;
    }

    public void setPatronId(String patronId) {
        if (patronId == null || patronId.isEmpty()) {
            throw new IllegalArgumentException("Patron ID cannot be null or empty.");
        }
        this.patronId = patronId;
    }

    public List<Book> getBorrowHistory() {
        return new ArrayList<>(borrowHistory);
    }

    public void addBorrowedBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null.");
        }
        borrowHistory.add(book);
    }

    public void removeBorrowedBook(Book book) {
        if (book == null || !borrowHistory.contains(book)) {
            throw new IllegalArgumentException("Book not found in borrow history.");
        }
        borrowHistory.remove(book);
    }

    // Notify patron when a reserved book becomes available
    public void notify(String message) {
        System.out.println("Notification for " + name + ": " + message);
    }
}
