package org.acme.book.mapper;

//same mapper but using mapstruct

import org.acme.book.Book;
import org.acme.book.BookDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface BookMapperStruct {

    BookDTO toBookDTO(Book book);

    Book toBook(BookDTO bookDTO);


}
