package pl.edu.pwr.ztw.books.services;

import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.exceptions.AuthorNotFoundException;
import pl.edu.pwr.ztw.books.models.Author;
import pl.edu.pwr.ztw.books.models.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import pl.edu.pwr.ztw.books.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuthorsService{
//    @Autowired
//    AuthorRepository authorRepository;

    public static List<Author> authorsRepo = new ArrayList<>();
    static {
        authorsRepo.add(new Author("Mariusz", "Pudzianowski" ));
        authorsRepo.add(new Author("Henryk", "Sienkiewicz" ));
        authorsRepo.add(new Author("Stanisław", "Reymont" ));
        authorsRepo.add(new Author("Adam", "Mickiewicz" ));
    }

//    @Override
    public Author addAuthor(Person person) {
        Author author = new Author(person.firstName, person.lastName);
        authorsRepo.add(author);
        return author;
    }

//    @Override
    public Collection<Author> getAuthors() {
        return authorsRepo;
    }

//    public List<Author> getAllAuthors(Integer pageNo, Integer pageSize, String sortBy){
//        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//        Page<Author> pagedResult = authorRepository.findAll(paging);
//
//        if(pagedResult.hasContent()){
//            return pagedResult.getContent();
//        }else{
//            return new ArrayList<Author>();
//        }
//    }

//    @Override
    public static Author getAuthor(int id) {
        return authorsRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

//    @Override
    public Author updateAuthor(int id, String firstName, String lastName) {
        Author author = this.getAuthor(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        return author;
    }

//    @Override
    public boolean deleteAuthor(int id) {
        return authorsRepo.remove(getAuthor(id));
    }
}






