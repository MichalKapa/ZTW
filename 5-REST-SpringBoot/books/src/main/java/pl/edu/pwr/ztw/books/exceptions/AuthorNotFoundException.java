package pl.edu.pwr.ztw.books.exceptions;

import org.webjars.NotFoundException;

public class AuthorNotFoundException extends NotFoundException {

    public AuthorNotFoundException(int id) {
        super(String.format("Author id: %d not found", id));
    }
}
