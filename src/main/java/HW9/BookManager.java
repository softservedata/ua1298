package HW9;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookManager {
    private List<Book> books = new ArrayList<>();

    public BookManager() {
        books = new ArrayList<>();
    }


    public void addBook(Book book) {
        if (book == null) throw new IllegalArgumentException("Book cannot be null");
        if (books.stream()
                .anyMatch(b -> b.getTitle().equalsIgnoreCase(book.getTitle())
                        && b.getAuthor().equalsIgnoreCase(book.getAuthor()))) {
            throw new IllegalArgumentException("Duplicate book entry");
        }
        books.add(book);
    }

    public void printAuthors() {
        books.stream()
                .map(Book::getAuthor)
                .distinct()
                .forEach(System.out::println);
    }

    public List<String> listAuthorsByGenre(String genre) {
        books.stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .map(Book::getAuthor)
                .distinct()
                .forEach(System.out::println);
        return null;
    }

    public void listAuthorsByYear(int year) {
        books.stream()
                .filter(book -> book.getYear() == year)
                .map(Book::getAuthor)
                .distinct()
                .forEach(System.out::println);
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

    public void sortByTitle() {
        books.sort((book1, book2) -> book1.getTitle().compareTo(book2.getTitle()));
    }

    public void sortByAuthor() {
        books.sort((book1, book2) -> book1.getAuthor().compareTo(book2.getAuthor()));
    }

    public void sortByYear() {
        books.sort((book1, book2) -> Integer.compare(book1.getYear(), book2.getYear()));
    }

    public List<Book> getBooks() {
        return books;
    }

    public int size() {
        return books.size();
    }

    public List<String> listOfAllAuthors() {
        return books.stream()
                .map(Book::getAuthor)
                .distinct()
                .collect(Collectors.toList());
    }
}
