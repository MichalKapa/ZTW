package pl.edu.pwr.ztw.books.models;

public class BookData {
    public String title;
    public int authorId;
    public int pages;

    @Override
    public String toString() {
        return "title: " + title + " authorID: " + authorId + " pages: " + pages;
    }
}


