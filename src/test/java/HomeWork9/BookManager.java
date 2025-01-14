package HomeWork9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BookManager {
    private List<Book> books;


    public BookManager() {
        this.books = new ArrayList<>();
    }


    public void addBook(Book book) {
        books.add(book);
    }


    public void printAuthors() {
        books.stream()
                .map(Book::getAuthor)
                .distinct()
                .forEach(System.out::println);
    }


    public List<String> getAuthorsByGenre(String genre) {
        return books.stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .map(Book::getAuthor)
                .distinct()
                .collect(Collectors.toList());
    }


    public List<String> getAuthorsByYear(int year) {
        return books.stream()
                .filter(book -> book.getYear() == year)
                .map(Book::getAuthor)
                .distinct()
                .collect(Collectors.toList());
    }


    public Book findBookByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .findFirst()
                .orElse(null);
    }


    public List<Book> findBooksByYear(int year) {
        return books.stream()
                .filter(book -> book.getYear() == year)
                .collect(Collectors.toList());
    }


    public List<Book> findBooksByGenre(String genre) {
        return books.stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }


    public void removeBooksByAuthor(String author) {
        books.removeIf(book -> book.getAuthor().equalsIgnoreCase(author));
    }


    public void sortBooksBy(String criterion) {
        switch (criterion.toLowerCase()) {
            case "title":
                books.sort((b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()));
                break;
            case "author":
                books.sort((b1, b2) -> b1.getAuthor().compareToIgnoreCase(b2.getAuthor()));
                break;
            case "year":
                books.sort((b1, b2) -> Integer.compare(b1.getYear(), b2.getYear()));
                break;
            default:
                System.out.println("Invalid sorting criterion");
        }
    }


    public void mergeCollections(List<Book> otherBooks) {
        books.addAll(otherBooks);
    }


    public List<Book> subcollectionByGenre(String genre) {
        return books.stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return books.toString();
    }
}