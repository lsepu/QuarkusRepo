package org.agoncal.fascicle.quarkus.book;

import org.agoncal.fascicle.quarkus.book.client.IsbnNumbers;
import org.agoncal.fascicle.quarkus.book.client.NumberProxy;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class BookService {

  private static final Logger LOGGER = Logger.getLogger(BookService.class);

  @Inject
  EntityManager em;

  @Inject
  @RestClient
  NumberProxy numberProxy;

  @Fallback(fallbackMethod = "fallbackPersistBook")
  public Book persistBook(@Valid Book book) {

    IsbnNumbers isbnNumbers = numberProxy.generateIsbnNumbers();
    book.setIsbn13(isbnNumbers.getIsbn13());
    book.setIsbn10(isbnNumbers.getIsbn10());

    Book.persist(book);
    return book;
  }

  private Book fallbackPersistBook(Book book) throws FileNotFoundException {
    LOGGER.warn("Falling back on persisting a book");
    String bookJson = JsonbBuilder.create().toJson(book);
    try (PrintWriter out = new PrintWriter("book-" + Instant.now().toEpochMilli() +
      ".json")) {
      out.println(bookJson);
    }
    throw new IllegalStateException();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<Book> findAllBooks() {
    return Book.listAll();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public Optional<Book> findBookById(Long id) {
    return Book.findByIdOptional(id);
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public Book findRandomBook() {
    Book randomBook = null;
    while (randomBook == null) {
      randomBook = Book.findRandom();
    }
    return randomBook;
  }

  public Book updateBook(@Valid Book book) {
    Book entity = em.merge(book);
    return entity;
  }

  public void deleteBook(Long id) {
    Book.deleteById(id);
  }

}
