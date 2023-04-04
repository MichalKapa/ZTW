package pl.edu.pwr.ztw.books.interfaces;
import pl.edu.pwr.ztw.books.models.Author;

import java.util.Collection;

public interface IAuthorsService {
    public abstract Author addAuthor(String firstName, String lastName);
    public abstract Collection<Author> getAuthors();
    public abstract Author getAuthor(int id);
    public abstract Author updateAuthor(int id, String firstName, String lastName);
    public abstract boolean deleteAuthor(int id);
}