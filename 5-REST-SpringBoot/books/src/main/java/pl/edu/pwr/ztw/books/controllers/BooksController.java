package pl.edu.pwr.ztw.books.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pwr.ztw.books.interfaces.IBooksService;
import pl.edu.pwr.ztw.books.models.Book;

@RestController
public class BooksController {
    @Autowired
    IBooksService booksService;

    @RequestMapping(value = "/add/book", method = RequestMethod.POST)
    public ResponseEntity<Object> addBook(Book book) {
        return new ResponseEntity<>(booksService.addBook(book), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/books", method = RequestMethod.GET)
    public ResponseEntity<Object> getBooks(){
        return new ResponseEntity<>(booksService.getBooks(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/book/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getBook(@PathVariable("id") int id){
        return new ResponseEntity<>(booksService.getBook(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/update/book/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateBook(@PathVariable("id") int id, Book book) {
        return new ResponseEntity<>(booksService.updateBook(id, book), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBook(@PathVariable("id") int id) {
        return new ResponseEntity<>(booksService.deleteBook(id), HttpStatus.OK);
    }
}