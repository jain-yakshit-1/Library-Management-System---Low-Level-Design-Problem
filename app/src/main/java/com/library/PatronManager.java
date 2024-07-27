package com.library;

import java.util.ArrayList;
import java.util.List;

public class PatronManager {
    private List<Patron> patrons = new ArrayList<>();

    // Add Patron
    public void addPatron(Patron patron) {
        if (patron == null) {
            throw new IllegalArgumentException("Patron cannot be null.");
        }
        patrons.add(patron);
    }

    // Update Patron
    public void updatePatron(Patron oldPatron, Patron newPatron) {
        if (oldPatron == null || newPatron == null || !patrons.contains(oldPatron)) {
            throw new IllegalArgumentException("Invalid patron or patron not found.");
        }
        int index = patrons.indexOf(oldPatron);
        patrons.set(index, newPatron);
    }

    // Get Borrow History
    public List<Book> getBorrowHistory(Patron patron) {
        if (patron == null || !patrons.contains(patron)) {
            throw new IllegalArgumentException("Patron not found.");
        }
        return patron.getBorrowHistory();
    }

    // Get Patron by ID
    public Patron getPatronById(String patronId) {
        if (patronId == null || patronId.isEmpty()) {
            throw new IllegalArgumentException("Patron ID cannot be null or empty.");
        }
        for (Patron patron : patrons) {
            if (patron.getPatronId().equalsIgnoreCase(patronId)) {
                return patron;
            }
        }
        return null;
    }
}