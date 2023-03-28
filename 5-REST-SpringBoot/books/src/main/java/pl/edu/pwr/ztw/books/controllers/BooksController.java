package pl.edu.pwr.ztw.books.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.ztw.books.interfaces.IBooksService;
import pl.edu.pwr.ztw.books.models.Book;

@RestController
public class BooksController {
    @Autowired
    IBooksService booksService;

    @Operation(summary="Add new book to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book added", content = @Content),
            @ApiResponse(responseCode = "503", description = "Cannot connect to database", content = @Content),
    })
    @RequestMapping(value = "/add/book", method = RequestMethod.POST)
    public ResponseEntity<Object> addBook(@RequestBody Book book) {
        return new ResponseEntity<>(booksService.addBook(book), HttpStatus.OK);
    }

    @Operation(summary="Get all books from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books found", content = @Content),
            @ApiResponse(responseCode = "503", description = "Cannot connect to database", content = @Content),
    })
    @RequestMapping(value = "/get/books", method = RequestMethod.GET)
    public ResponseEntity<Object> getBooks(){
        return new ResponseEntity<>(booksService.getBooks(), HttpStatus.OK);
    }

    @Operation(summary="Get book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content),
            @ApiResponse(responseCode = "503", description = "Cannot connect to database", content = @Content),
    })
    @RequestMapping(value = "/get/book/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getBook(@Parameter(description = "ID of book to get")
                                              @PathVariable("id") int id){
        Book foundBook = booksService.getBook(id);
        if (foundBook == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(foundBook, HttpStatus.OK);
        }
    }

    @Operation(summary="Update book with specified id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content),
            @ApiResponse(responseCode = "503", description = "Cannot connect to database", content = @Content),
    })
    @RequestMapping(value = "/update/book/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateBook(@Parameter(description = "ID of book to update")
                                                 @PathVariable("id") int id, @RequestBody Book book) {
        Book foundBook = booksService.updateBook(id, book);
        if (foundBook == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(foundBook, HttpStatus.OK);
        }
    }

    @Operation(summary="Delete book with specified id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content),
            @ApiResponse(responseCode = "503", description = "Cannot connect to database", content = @Content),
    })
    @RequestMapping(value = "/delete/book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBook(@Parameter(description = "ID of book to delete")
                                                 @PathVariable("id") int id) {
        boolean foundBook = booksService.deleteBook(id);
        if (foundBook){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}