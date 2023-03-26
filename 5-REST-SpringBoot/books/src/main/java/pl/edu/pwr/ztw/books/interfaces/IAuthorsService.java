package pl.edu.pwr.ztw.books.interfaces;
import pl.edu.pwr.ztw.books.models.Author;

import java.util.Collection;

public interface IAuthorsService {
    public abstract Author addAuthor(Author author);
    public abstract Collection<Author> getAuthors();
    public abstract Author getAuthor(int id);
    public abstract Author updateAuthor(int id, Author author);
    public abstract boolean deleteAuthor(int id);
}