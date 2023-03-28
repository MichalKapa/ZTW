package pl.edu.pwr.ztw.books.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary="Add author to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author added", content = @Content),
            @ApiResponse(responseCode = "503", description = "Cannot connect to database", content = @Content),
    })
    @RequestMapping(value = "/add/author", method = RequestMethod.POST)
    public ResponseEntity<Object> addAuthor(Author author) {
        return new ResponseEntity<>(authorsService.addAuthor(author), HttpStatus.OK);
    }

    @Operation(summary="Get all authors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author retrieved", content = @Content),
            @ApiResponse(responseCode = "503", description = "Cannot connect to database", content = @Content),
    })
    @RequestMapping(value = "/get/authors", method = RequestMethod.GET)
    public ResponseEntity<Object> getAuthors(){
        return new ResponseEntity<>(authorsService.getAuthors(), HttpStatus.OK);
    }

    @Operation(summary="Get author by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Author with specified id not found", content = @Content),
            @ApiResponse(responseCode = "503", description = "Cannot connect to database", content = @Content),
    })
    @RequestMapping(value = "/get/author/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAuthor(@Parameter(description = "ID of author to get")
                                                @PathVariable("id") int id){
        return new ResponseEntity<>(authorsService.getAuthor(id), HttpStatus.OK);
    }

    @Operation(summary="Update author with specified id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Author with specified id not found", content = @Content),
            @ApiResponse(responseCode = "503", description = "Cannot connect to database", content = @Content),
    })
    @RequestMapping(value = "/update/author/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateAuthor(@Parameter(description = "ID of author to update")
                                                   @PathVariable("id") int id, Author author) {
        return new ResponseEntity<>(authorsService.updateAuthor(id, author), HttpStatus.OK);
    }

    @Operation(summary="Delete author by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author found and deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Author with specified id not found", content = @Content),
            @ApiResponse(responseCode = "503", description = "Cannot connect to database", content = @Content),
    })
    @RequestMapping(value = "/delete/author/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteAuthor(@Parameter(description = "ID of author to delete")
                                                   @PathVariable("id") int id) {
        return new ResponseEntity<>(authorsService.deleteAuthor(id), HttpStatus.OK);
    }

}