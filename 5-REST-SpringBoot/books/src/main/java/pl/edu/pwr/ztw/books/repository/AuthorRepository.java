package pl.edu.pwr.ztw.books.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.ztw.books.models.Author;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long>{

}
