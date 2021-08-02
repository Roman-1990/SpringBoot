package guru.qa.restbackend;

import guru.qa.restbackend.domain.BookInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static guru.qa.restbackend.Specs.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class RestBackendApplicationTests {

    @Test
    void getAllBooksTest() {
        given()
                .spec(request)
                .when()
                .get("books/getAll")
                .then()
                .log().body()
                .spec(responseSpec)
                .extract()
                .as(BookInfo[].class);
    }

    @Test
    void getBookAuthorTest() {
        given()
                .spec(requestToGetAuthor)
                .when()
                .get("books/getAll")
                .then()
                .log().body()
                .spec(responseSpec)
                .body("book_author", hasItem("Пушкин А. С."));
    }

    @Test
    void addBookTest() {
        given()
                .spec(request)
                .body("{\"book_author\": \"Джордж М.\"," +
                        "\"book_title\": \"Ветра зимы\"}")
                .when()
                .post("book/add")
                .then()
                .log().body()
                .spec(responseSpec)
                .body("book_author", is("Джордж М."))
                .body("book_title", is("Ветра зимы"))
                .body("message", is("Successful added"));
    }

}
