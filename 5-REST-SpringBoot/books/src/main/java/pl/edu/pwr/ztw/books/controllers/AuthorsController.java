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
import pl.edu.pwr.ztw.books.exceptions.AuthorNotFoundException;
import pl.edu.pwr.ztw.books.interfaces.IAuthorsService;
import pl.edu.pwr.ztw.books.models.Author;
import pl.edu.pwr.ztw.books.services.AuthorsService;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
public class AuthorsController {
    @Autowired
    AuthorsService authorsService;

    @Operation(summary="Add author to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author added", content = @Content),
            @ApiResponse(responseCode = "503", description = "Cannot connect to database", content = @Content),
    })
    @RequestMapping(value = "/add/author", method = RequestMethod.POST)
    public ResponseEntity<Object> addAuthor(String firstName, String lastName) {
        return new ResponseEntity<>(authorsService.addAuthor(firstName, lastName), HttpStatus.OK);
    }

    @Operation(summary="Get all authors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author retrieved", content = @Content),
            @ApiResponse(responseCode = "503", description = "Cannot connect to database", content = @Content),
    })
    @RequestMapping(value = "/get/authors", method = RequestMethod.GET)
//    public ResponseEntity<Object> getAuthors(@RequestParam(defaultValue = "0") Integer pageNo,
//                                             @RequestParam(defaultValue = "10") Integer pageSize,
//                                             @RequestParam(defaultValue = "id") String sortBy){
    public ResponseEntity<Object> getAuthors(){
        return new ResponseEntity<>(authorsService.getAuthors(), HttpStatus.OK);
//        return new ResponseEntity<Object>(authorsService.getAllAuthors(pageNo, pageSize, sortBy), HttpStatus.OK);
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
        try {
            Author author = authorsService.getAuthor(id);
            return new ResponseEntity<>(author, HttpStatus.OK);
        } catch (AuthorNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary="Update author with specified id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Author with specified id not found", content = @Content),
            @ApiResponse(responseCode = "503", description = "Cannot connect to database", content = @Content),
    })
    @RequestMapping(value = "/update/author/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateAuthor(@Parameter(description = "ID of author to update")
                                                   @PathVariable("id") int id, String firstName, String lastName) {
        try {
            Author updatedAuthor = authorsService.updateAuthor(id, firstName, lastName);
            return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
        }catch (AuthorNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
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
        try {
            authorsService.deleteAuthor(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch (AuthorNotFoundException e) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

}