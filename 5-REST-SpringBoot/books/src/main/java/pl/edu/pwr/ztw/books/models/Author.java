package pl.edu.pwr.ztw.books.models;

public class Author {

    private static int authorId = 0;
    private int id;
    private String firstName;
    private String lastName;
    public Author(String firstName, String lastName) {
        this.id = ++authorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}