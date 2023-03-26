package pl.edu.pwr.ztw.books.services;

import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.models.Author;
import pl.edu.pwr.ztw.books.interfaces.IAuthorsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuthorsService implements IAuthorsService{
    public static List<Author> authorsRepo = new ArrayList<>();
    static {
        authorsRepo.add(new Author(1 , "Henryk", "Sienkiewicz" ));
        authorsRepo.add(new Author(2 , "Stanisław", "Reymont" ));
        authorsRepo.add(new Author(3 , "Adam", "Mickiewicz" ));
    }

    @Override
    public Author addAuthor(Author author) {
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
                .orElse(null);
    }

    @Override
    public Author updateAuthor(int id, Author author) {
        deleteAuthor(id);
        addAuthor(author);
        return author;
    }

    @Override
    public boolean deleteAuthor(int id) {
        authorsRepo.remove(getAuthor(id));
        return true;
    }
}






