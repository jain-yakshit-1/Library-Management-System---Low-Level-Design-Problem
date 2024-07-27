/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.library;

import java.util.List;

import com.library.search.SearchType;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        // Creating instances of the classes
        BookInventory inventory = new BookInventory();
        PatronManager patronManagement = new PatronManager();
        LendingSystem lendingSystem = new LendingSystem();

        // Adding books to inventory
        try {
            Book book1 = new Book("The Catcher in the Rye", "J.D. Salinger", "978-0-316-76948-0", 1951);
            Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "978-0-06-112008-4", 1960);
            Book book3 = new Book("1984", "George Orwell", "978-0-452-28423-4", 1949);
            Book book4 = new Book("Pride and Prejudice", "Jane Austen", "978-0-7432-7350-5", 1813);
            Book book5 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-0-7432-7350-6", 1925);
            inventory.addBook(book1);
            inventory.addBook(book2);
            inventory.addBook(book3);
            inventory.addBook(book4);
            inventory.addBook(book5);
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }

        // Adding patrons
        try {
            Patron patron1 = new Patron("Alice", "P001");
            Patron patron2 = new Patron("Bob", "P002");
            patronManagement.addPatron(patron1);
            patronManagement.addPatron(patron2);
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding patron: " + e.getMessage());
        }

        // Checking out a book
        try {
            Book bookToCheckout = inventory.searchBooks(SearchType.ISBN, "978-0-316-76948-0").get(0);
            Patron patronToCheckout = patronManagement.getPatronById("P001");
            if (bookToCheckout != null && patronToCheckout != null) {
                lendingSystem.checkoutBook(bookToCheckout, patronToCheckout);
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error during checkout: " + e.getMessage());
        }

        // Reserving a book
        try {
            Book bookToReserve = inventory.searchBooks(SearchType.ISBN, "978-0-316-76948-0").get(0);
            Patron patronToReserve = patronManagement.getPatronById("P002");
            if (bookToReserve != null && patronToReserve != null) {
                lendingSystem.reserveBook(bookToReserve, patronToReserve);
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error during reservation: " + e.getMessage());
        }

        // Returning a book
        try {
            Book bookToReturn = inventory.searchBooks(SearchType.ISBN, "978-0-316-76948-0").get(0);
            if (bookToReturn != null) {
                lendingSystem.returnBook(bookToReturn);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error during return: " + e.getMessage());
        }

        // Searching for a book
        try {
            List<Book> foundBooks = inventory.searchBooks(SearchType.TITLE, "To Kill a Mockingbird");
            for (Book book : foundBooks) {
                System.out.println("Found book: " + book.getTitle() + " by " + book.getAuthor());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error during search: " + e.getMessage());
        }

        // Displaying available books
        List<Book> availableBooks = inventory.getAvailableBooks();
        for (Book book : availableBooks) {
            System.out.println("Available book: " + book.getTitle() + " by " + book.getAuthor());
        }

        // Generating book recommendations for a patron
        try {
            Patron patronForRecommendation = patronManagement.getPatronById("P001");
            List<Book> recommendations = lendingSystem.recommendBooks(patronForRecommendation, inventory);
            System.out.println("Recommended books for " + patronForRecommendation.getName() + ":");
            for (Book book : recommendations) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error generating recommendations: " + e.getMessage());
        }
    }  
}
