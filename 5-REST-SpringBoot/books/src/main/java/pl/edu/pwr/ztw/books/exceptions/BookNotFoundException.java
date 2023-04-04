package pl.edu.pwr.ztw.books.exceptions;

import org.webjars.NotFoundException;

public class BookNotFoundException extends NotFoundException {

    public BookNotFoundException(int id) {
        super(String.format("Book id: %d not found", id));
    }
}
