package pl.edu.pwr.ztw.books.interfaces;

import pl.edu.pwr.ztw.books.models.Author;
import pl.edu.pwr.ztw.books.models.Book;

import java.util.Collection;

public interface IBooksService {
    public abstract Book addBook(Book book);
    public abstract Collection<Book> getBooks();
    public abstract Book getBook(int id);
    public abstract Book updateBook(int id, Book book);
    public abstract boolean deleteBook(int id);
}