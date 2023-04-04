package pl.edu.pwr.ztw.books.models;

import pl.edu.pwr.ztw.books.exceptions.AuthorNotFoundException;
import pl.edu.pwr.ztw.books.services.AuthorsService;

public class Book {

    private static int bookId = 0;
    private int id;
    private String title;
    private int authorId;
    int pages;
    public Book(String title, int authorID, int pages) {
        this.id = ++bookId;
        this.title = title;
        this.authorId = authorID;
        this.pages = pages;
    }
    public int getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Author getAuthor() {
        try{
            return new AuthorsService().getAuthor(authorId);
        } catch (AuthorNotFoundException e){
            return null;
        }
    }
    public void setAuthor(int authorId) { this.authorId = authorId; }
    public int getPages() { return pages; }
    public void setPages(int pages) { this.pages = pages; }
}