package org.acme.book;

import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import org.acme.book.mapper.BookMapper;
import org.acme.book.mapper.BookMapperStruct;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor
public class BookService {

    //dependency injection using the constructor
    private final BookMapper bookMapper;

    //dependency injection using annotation
    @Inject
    BookMapperStruct bookMapperStruct;

    @Inject
    BookRepository bookRepository;

    public Uni<List<BookDTO>> getAllBooks() {
        return bookRepository.findAll(Sort.by("name"))
                .stream()
                .map(bookMapperStruct::toBookDTO)
                .collect()
                .asList();
    }

    public Uni<List<BookDTO>> findByAuthor(String author){
        return bookRepository
                .find("lower(authorName) like lower(CONCAT('%', '" + author + "', '%'))")
                .stream()
                .map(bookMapperStruct::toBookDTO)
                .collect()
                .asList();
    }

    public Uni<BookDTO> addBook(BookDTO bookDTO) {
        return bookRepository.persist(bookMapperStruct.toBook(bookDTO))
                .map(bookMapperStruct::toBookDTO);
    }

}
