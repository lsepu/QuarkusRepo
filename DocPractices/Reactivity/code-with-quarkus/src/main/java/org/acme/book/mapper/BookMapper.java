package org.acme.book.mapper;

import org.acme.book.Book;
import org.acme.book.BookDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.function.Function;

@ApplicationScoped
public class BookMapper {

    public Function<Book, BookDTO> mapBookToBookDTO(){
        return Book -> new BookDTO(
                Book.getId(),
                Book.getName(),
                Book.getNumPages(),
                Book.getAuthorName()
        );
    }

    public Function<BookDTO, Book> mapBookDTOToBook(){
        return BookDTO -> new Book(
                BookDTO.getId(),
                BookDTO.getName(),
                BookDTO.getNumPages(),
                BookDTO.getAuthorName()
        );
    }

}
