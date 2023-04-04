package pl.edu.pwr.ztw.books.interfaces;

import pl.edu.pwr.ztw.books.models.Author;
import pl.edu.pwr.ztw.books.models.Book;

import java.util.Collection;

public interface IBooksService {
    public abstract Book addBook(String title, int authorId, int pages);
    public abstract Collection<Book> getBooks();
    public abstract Book getBook(int id);
    public abstract Book updateBook(int id, String title, int authorId, int pages);
    public abstract boolean deleteBook(int id);
}