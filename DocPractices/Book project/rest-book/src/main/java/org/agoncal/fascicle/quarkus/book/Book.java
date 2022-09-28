package org.agoncal.fascicle.quarkus.book;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Random;

@Schema(description = "Book representation")
@Entity
public class Book extends PanacheEntity {

  @Schema(required = true)
  private String title;
  @Column(name = "isbn_13")
  private String isbn13;
  @Column(name = "isbn_10")
  private String isbn10;
  private String author;
  @Column(name = "year_of_publication")
  private Integer yearOfPublication;
  @Column(name = "nb_of_pages")
  private Integer nbOfPages;
  private Integer rank;
  private BigDecimal price;
  @Column(name = "small_image_url")
  private URL smallImageUrl;
  @Column(name = "medium_image_url")
  private URL mediumImageUrl;
  @Column(length = 10000)
  private String description;

  public static Book findRandom() {
    long countBooks = Book.count();
    int randomBook = new Random().nextInt((int) countBooks);
    return Book.findAll().page(randomBook, 1).firstResult();
  }

  public void setIsbn13(String isbn13) {
    this.isbn13 = isbn13;
  }

  public void setIsbn10(String isbn10) {
    this.isbn10 = isbn10;
  }
}
