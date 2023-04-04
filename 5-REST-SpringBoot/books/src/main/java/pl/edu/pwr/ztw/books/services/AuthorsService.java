package pl.edu.pwr.ztw.books.services;

import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.exceptions.AuthorNotFoundException;
import pl.edu.pwr.ztw.books.models.Author;
import pl.edu.pwr.ztw.books.interfaces.IAuthorsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuthorsService implements IAuthorsService{
    public static List<Author> authorsRepo = new ArrayList<>();
    static {
        authorsRepo.add(new Author("Henryk", "Sienkiewicz" ));
        authorsRepo.add(new Author("Stanisław", "Reymont" ));
        authorsRepo.add(new Author("Adam", "Mickiewicz" ));
    }

    @Override
    public Author addAuthor(String firstName, String lastName) {
        Author author = new Author(firstName, lastName);
        authorsRepo.add(author);
        return author;
    }

    @Override
    public Collection<Author> getAuthors() {
        return authorsRepo;
    }

    @Override
    public Author getAuthor(int id) {
        return authorsRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @Override
    public Author updateAuthor(int id, String firstName, String lastName) {
        Author author = this.getAuthor(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        return author;
    }

    @Override
    public boolean deleteAuthor(int id) {
        return authorsRepo.remove(getAuthor(id));
    }
}






