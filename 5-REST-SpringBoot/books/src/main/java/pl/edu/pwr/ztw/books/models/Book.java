package pl.edu.pwr.ztw.books.models;

import pl.edu.pwr.ztw.books.exceptions.AuthorNotFoundException;
import pl.edu.pwr.ztw.books.services.AuthorsService;

public class Book {

    private static int bookId = 0;
    private int id;
    private String title;
    private Author author;
    int pages;
    public Book(String title, Author author, int pages) {
        this.id = ++bookId;
        this.title = title;
        this.author = author;
        this.pages = pages;
    }
    public int getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) { this.author = author; }
    public int getPages() { return pages; }
    public void setPages(int pages) { this.pages = pages; }
}