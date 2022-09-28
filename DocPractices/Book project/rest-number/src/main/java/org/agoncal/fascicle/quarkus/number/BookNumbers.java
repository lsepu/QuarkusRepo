package org.agoncal.fascicle.quarkus.number;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import java.time.Instant;

@Data
@Schema(description = "Several formats of book numbers")
public class BookNumbers {

  @Schema(required = true)
  @JsonbProperty("isbn_10")
  private String isbn10;
  @Schema(required = true)
  @JsonbProperty("isbn_13")
  private String isbn13;
  private String asin;
  @JsonbProperty("ean_8")
  private String ean8;
  @JsonbProperty("ean_13")
  private String ean13;
  @JsonbTransient
  private Instant generationDate;


}
