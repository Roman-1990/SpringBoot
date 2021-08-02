package guru.qa.restbackend.controller;

import guru.qa.restbackend.domain.BookInfo;
import guru.qa.restbackend.domain.BooksOutput;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class Controller {
    Map<String, BookInfo> books = Map.of(
            "Пушкин А. С.", BookInfo.builder()
                    .bookAuthor("Пушкин А. С.")
                    .bookTitle("Золотая рыбка")
                    .build(),
            "Толкин Д.", BookInfo.builder()
                    .bookAuthor("Толкин Д.")
                    .bookTitle("Властелин колец")
                    .build(),
            "Лермонтов М. Ю.", BookInfo.builder()
                    .bookAuthor("Лермонтов М. Ю.")
                    .bookTitle("Печорин")
                    .build()
    );

    @PostMapping("book/add")
    public BooksOutput addBookTitle(@RequestBody BookInfo bookInfo) {
        if (books.get(bookInfo.getBookTitle()) == null) {
            return BooksOutput.builder()
                    .bookTitle(bookInfo.getBookTitle())
                    .bookAuthor(bookInfo.getBookAuthor())
                    .message("Successful added")
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book used");
        }
    }

    @GetMapping("books/getAll")
    @ApiOperation("get all books")
    public List<BookInfo> getAllBooksInfo() {
        return books.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
