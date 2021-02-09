package fr.esgi.solid_library_kata.core.services;

import com.google.gson.Gson;
import fr.esgi.solid_library_kata.core.models.Book;
import fr.esgi.solid_library_kata.core.utils.FileUtils;
import fr.esgi.solid_library_kata.core.utils.ResourceConverter;
import fr.esgi.solid_library_kata.core.utils.ResourceWriter;

import java.util.List;
import java.util.Optional;

public class BooksService {
    private static final String booksFilePath = "books.json";

    public static List<Book> findAll() {
        return new ResourceConverter().convertArray(booksFilePath, Book[].class);
    }

    public static Optional<Book> findOneByTitleAndAuthor(String title, String author) {
        var books = findAll();

        System.out.println(books);

        if (books == null) {
            return Optional.empty();
        }

        return books.stream()
                .filter(user -> user.getTitle().equals(title))
                .filter(user -> user.getAuthor().equals(author))
                .findAny();
    }

    public static void save(Book book) {
        var bookFound = findOneByTitleAndAuthor(book.getTitle(), book.getAuthor());

        if (bookFound.isPresent()) {
            throw new IllegalArgumentException("Book already exists");
        }

        var books = findAll();
        books.add(book);

        var json = new Gson().toJson(books);
        var file = FileUtils.getResourceFile(booksFilePath);
        new ResourceWriter().write(file.getAbsolutePath(), json);
    }
}
