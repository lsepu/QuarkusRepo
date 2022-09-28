package org.agoncal.fascicle.quarkus.number;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;

@QuarkusTest
class NumberResourceTest {

  @Test
  void shouldGenerateBookNumber () {
    given()
        .header(ACCEPT, MediaType.APPLICATION_JSON).
      when()
        .get("/api/numbers/book").
      then()
      .statusCode(OK.getStatusCode())
      .body("$", hasKey("isbn_10"))
      .body("$", hasKey("isbn_13"))
      .body("$", hasKey("asin"))
      .body("$", hasKey("ean_8"))
      .body("$", hasKey("ean_13"))
      .body("$", not(hasKey("generationDate")));

  }

}
