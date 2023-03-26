package pl.edu.pwr.ztw.books.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pwr.ztw.books.interfaces.IAuthorsService;
import pl.edu.pwr.ztw.books.models.Author;

@RestController
public class AuthorsController {
    @Autowired
    IAuthorsService authorsService;

    @RequestMapping(value = "/add/author", method = RequestMethod.POST)
    public ResponseEntity<Object> addAuthor(Author author) {
        return new ResponseEntity<>(authorsService.addAuthor(author), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/authors", method = RequestMethod.GET)
    public ResponseEntity<Object> getAuthors(){
        return new ResponseEntity<>(authorsService.getAuthors(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/author/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAuthor(@PathVariable("id") int id){
        return new ResponseEntity<>(authorsService.getAuthor(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/update/author/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateAuthor(@PathVariable("id") int id, Author author) {
        return new ResponseEntity<>(authorsService.updateAuthor(id, author), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/author/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteAuthor(@PathVariable("id") int id) {
        return new ResponseEntity<>(authorsService.deleteAuthor(id), HttpStatus.OK);
    }

}