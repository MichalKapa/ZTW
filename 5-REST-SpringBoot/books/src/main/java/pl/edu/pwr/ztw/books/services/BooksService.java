package pl.edu.pwr.ztw.books.services;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.exceptions.BookNotFoundException;
import pl.edu.pwr.ztw.books.models.Author;
import pl.edu.pwr.ztw.books.models.Book;
import pl.edu.pwr.ztw.books.interfaces.IBooksService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static pl.edu.pwr.ztw.books.services.AuthorsService.getAuthor;

@Service
public class BooksService implements IBooksService {
    private static List<Book> booksRepo = new ArrayList<>();
    static {
        booksRepo.add(new Book("Potop", getAuthor(1), 936));
        booksRepo.add(new Book("Wesele", getAuthor(2), 150));
        booksRepo.add(new Book("Dziady", getAuthor(3), 292));
    }

    @Override
    public Book addBook(String title, Author author, int pages) {
        Book book = new Book(title, author, pages);
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
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public Book updateBook(int id, String title, int authorId, int pages) {
        Book book = this.getBook(id);
        book.setTitle(title);
        book.setAuthor(getAuthor(authorId));
        book.setPages(pages);
        return book;
    }

    @Override
    public boolean deleteBook(int id) {
        return booksRepo.remove(getBook(id));
    }
}
