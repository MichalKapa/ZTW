package pl.edu.pwr.ztw.books.services;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.models.Author;
import pl.edu.pwr.ztw.books.models.Book;
import pl.edu.pwr.ztw.books.interfaces.IBooksService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
public class BooksService implements IBooksService {
    private static List<Book> booksRepo = new ArrayList<>();
    static {
        booksRepo.add(new Book(1,"Potop", AuthorsService.authorsRepo.get(0), 936));
        booksRepo.add(new Book(2,"Wesele", AuthorsService.authorsRepo.get(1), 150));
        booksRepo.add(new Book(3,"Dziady", AuthorsService.authorsRepo.get(2), 292));
    }

    @Override
    public Book addBook(Book book) {
        booksRepo.add(book);
        return book;
    }

    @Override
    public Collection<Book> getBooks() {
        return booksRepo;
    }

    @Override
    public Book getBook(int id) {
        return booksRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public Book updateBook(int id, Book book) {
        deleteBook(id);
        addBook(book);
        return book;
    }

    @Override
    public boolean deleteBook(int id) {
        booksRepo.remove(getBook(id));
        return true;
    }
}
