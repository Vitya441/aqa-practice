package com.modsen.api.tests;

import com.modsen.api.pojo.Book;
import com.modsen.api.specification.RequestSpecs;
import com.modsen.api.specification.ResponseSpecs;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

class BookTests {

    @Test
    void testCreateBook() {
        // Arrange
        Book sentBook = Book.builder()
                .id(10)
                .title("The Science of Interstellar")
                .description("Some description")
                .pageCount(336)
                .excerpt("Some excerpt")
                .publishDate(OffsetDateTime.parse("2014-11-07T10:00:00Z"))
                .build();

        // Act
        Book response = RestAssured.given()
                .spec(RequestSpecs.basicRequestSpec())
                .body(sentBook)
                .when()
                .post("/api/v1/Books")
                .then()
                .spec(ResponseSpecs.checkStatusCode(200))
                .extract()
                .as(Book.class);

        // Assert
        assertThat(response.getTitle())
                .isNotNull();
        assertThat(sentBook.getTitle())
                .isEqualTo(response.getTitle());
    }

    @Test
    void testGetBookById() {
        // Arrange
        Book sentBook = Book.builder()
                .id(10)
                .title("The Science of Interstellar")
                .description("Some description")
                .pageCount(1234)
                .excerpt("Some excerpt")
                .publishDate(OffsetDateTime.parse("2014-11-07T10:00:00Z"))
                .build();

        // Act
        RestAssured.given()
                .spec(RequestSpecs.basicRequestSpec())
                .body(sentBook)
                .post("/api/v1/Books");

        Book receivedBook = RestAssured.given()
                .spec(RequestSpecs.basicRequestSpec())
                .when()
                .get("/api/v1/Books/" + sentBook.getId())
                .then()
                .spec(ResponseSpecs.response200ok())
                .extract()
                .as(Book.class);

        // Assert
        assertThat(sentBook.getId())
                .isEqualTo(receivedBook.getId());
    }

    @Test
    void testGetAllBooks() {
        // Act
        List<Book> books = RestAssured.given()
                .spec(RequestSpecs.basicRequestSpec())
                .when()
                .get("/api/v1/Books")
                .then()
                .spec(ResponseSpecs.response200ok())
                .extract()
                .body().jsonPath().getList("", Book.class);

        // Assert
        assertThat(books)
                .isNotEmpty();
    }

    @Test
    void testDeleteBook() {
        // Arrange
        Book sentBook = Book.builder()
                .id(10)
                .title("The Science of Interstellar")
                .description("Some description")
                .pageCount(100)
                .excerpt("Some excerpt")
                .publishDate(OffsetDateTime.now())
                .build();

        // Act & Assert
        RestAssured.given()
                .spec(RequestSpecs.basicRequestSpec())
                .body(sentBook)
                .when()
                .post("/api/v1/Books")
                .then()
                .spec(ResponseSpecs.response200ok());

        Book createdBook = RestAssured.given()
                .spec(RequestSpecs.basicRequestSpec())
                .when()
                .get("/api/v1/Books/" + sentBook.getId())
                .then()
                .spec(ResponseSpecs.response200ok())
                .extract()
                .as(Book.class);

        assertThat(createdBook.getId())
                .isEqualTo(sentBook.getId());

        RestAssured.given()
                .spec(RequestSpecs.basicRequestSpec())
                .when()
                .delete("/api/v1/Books/" + sentBook.getId())
                .then()
                .spec(ResponseSpecs.response200ok());

        RestAssured.given()
                .spec(RequestSpecs.basicRequestSpec())
                .when()
                .get("/api/v1/Books/" + sentBook.getId())
                .then()
                .spec(ResponseSpecs.checkStatusCode(200));
    }

    @Test
    void testNegativeGetNonExistingBook() {
        // Arrange
        int nonExistingBookId = 201;

        // Act & Assert
        RestAssured.given()
                .spec(RequestSpecs.basicRequestSpec())
                .when()
                .get("/api/v1/Books/" + nonExistingBookId)
                .then()
                .spec(ResponseSpecs.checkStatusCode(404));
    }

    @ParameterizedTest(name = "Создание книги: {0}, страниц: {1}")
    @MethodSource("provideBookData")
    void testCreateBookParametrized(String title, int pageCount, String description) {
        // Arrange
        Book sentBook = Book.builder()
                .id(10)
                .title(title)
                .description(description)
                .pageCount(pageCount)
                .excerpt("Some excerpt")
                .publishDate(OffsetDateTime.parse("2014-11-07T10:00:00Z"))
                .build();

        // Act
        Book response = RestAssured.given()
                .spec(RequestSpecs.basicRequestSpec())
                .body(sentBook)
                .when()
                .post("/api/v1/Books")
                .then()
                .spec(ResponseSpecs.checkStatusCode(200))
                .extract()
                .as(Book.class);

        // Assert
        assertThat(response)
                .isEqualTo(sentBook);
    }

    private static Stream<Arguments> provideBookData() {
        List<String> titles = List.of("Detective: Midnight Shadow", "Sci-Fi: Neon Dreams", "Romance: Sunset Love");
        List<Integer> pageCounts = List.of(150, 450, 900);
        List<String> descriptions = List.of("Short summary", "Average description length", "Very long and detailed book description");

        List<Arguments> combinations = new ArrayList<>();

        for (String title : titles) {
            for (Integer pages : pageCounts) {
                for (String desc : descriptions) {
                    combinations.add(Arguments.of(title, pages, desc));
                }
            }
        }

        return combinations.stream();
    }

    @Test
    void testCreateBookAndValidateSchema() {
        // Arrange
        Book sentBook = Book.builder()
                .id(10)
                .title("The Science of Interstellar")
                .description("Some description")
                .pageCount(336)
                .excerpt("Some excerpt")
                .publishDate(OffsetDateTime.parse("2014-11-07T10:00:00Z"))
                .build();

        // Act & Assert
        RestAssured.given()
                .spec(RequestSpecs.basicRequestSpec())
                .body(sentBook)
                .when()
                .post("/api/v1/Books")
                .then()
                .spec(ResponseSpecs.response200ok())
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/book-schema.json"));
    }
}
