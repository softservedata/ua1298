package hm9;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookManagerTest {
    private BookManager bookManager;

    @BeforeEach
    void setUp() {
        bookManager = new BookManager();
        bookManager.addBook(new Book("The Great Adventure", "Alice Johnson", "Drama", 2022));
        bookManager.addBook(new Book("Space Odyssey", "Alice Johnson", "Fantastic", 2024));
        bookManager.addBook(new Book("Life's Journey", "Bob Smith", "Drama", 2021));
        bookManager.addBook(new Book("Science Explained", "Charlie Brown", "Science", 2019));
        bookManager.addBook(new Book("Dungeon Meshi", "Ryoko Kui", "Adventure", 2021));
    }

    @Test
    @DisplayName("Add Book: Successfully adds a new unique book")
    void testAddBookPositive() {
        Book newBook = new Book("New Discoveries", "Diana Green", "Drama", 2024);
        bookManager.addBook(newBook);
        assertEquals(6, bookManager.size(), "The collection should contain 6 books after adding a new book");
    }

    @Test
    @DisplayName("Add Book: Throws exception when attempting to add null")
    void testAddBookNull() {
        assertThrows(IllegalArgumentException.class, () -> bookManager.addBook(null),
                "Adding a null book should throw IllegalArgumentException");
    }

    @Test
    @DisplayName("Add Book: Throws exception when attempting to add a duplicate book")
    void testAddBookDuplicate() {
        Book duplicateBook = new Book("The Great Adventure", "Alice Johnson", "Drama", 2022);
        assertThrows(IllegalArgumentException.class, () -> bookManager.addBook(duplicateBook),
                "Adding a duplicate book should throw IllegalArgumentException");
    }

    @Test
    @DisplayName("List All Authors: Returns a list of unique authors in the collection")
    void testListAllAuthors() {
        List<String> authors = bookManager.printListOfAuthors();
        assertEquals(3, authors.size(), "The collection should contain 3 unique authors");
        assertTrue(authors.contains("Alice Johnson"), "Author list should contain 'Alice Johnson'");
        assertTrue(authors.contains("Bob Smith"), "Author list should contain 'Bob Smith'");
        assertTrue(authors.contains("Charlie Brown"), "Author list should contain 'Charlie Brown'");
    }

    @ParameterizedTest
    @CsvSource({
            "Drama, 2",
            "Fantastic, 1",
            "Science, 1"
    })
    @DisplayName("List Authors by Genre: Returns correct number of authors by genre")
    void testListAuthorsByGenre(String genre, int expected) {
        List<String> authors = bookManager.listAuthorsByGenre(genre);
        assertEquals(expected, authors.size(),
                String.format("The genre '%s' should have %d unique authors", genre, expected));
    }

    @ParameterizedTest
    @CsvSource({
            "2021, 2",
            "2019, 1",
            "2010, 0"
    })
    @DisplayName("List Authors by Publication Year: Returns correct number of authors by publication year")
    void testUniqueAuthorsForYear(int publicationYear, int expected){
        List<String> authors = bookManager.listAuthorsByPublicationYear(publicationYear);
        assertEquals(expected, authors.size());
    }

    @ParameterizedTest
    @CsvSource({
            "Bob Smith, Life's Journey",
            "Alice Johnson, The Great Adventure",
    })
    @DisplayName("Book by Author: Returns book which match with author")
    void testFindBookByAuthor(String author, String expectedTitle){
        Optional<Book> books = bookManager.findBookByAuthor(author);
        assertTrue(books.isPresent(), "Book should be found");
        assertEquals(expectedTitle, books.get().getTitle(), "Title should match");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testFindBookByAuthorNullAndEmptySource(String author){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bookManager.findBookByAuthor(author));
        assertEquals("Author cannot be null or empty", exception.getMessage(), "Exception message should match");
    }

    @ParameterizedTest
    @CsvSource({
            "2021, 2",
            "2019, 1",
            "2010, 0"
    })
    void testFindBooksByYear(int year, int expected){
        List<Book> books = bookManager.findBooksByYear(year);
        assertEquals(expected, books.size(), String.format("Should be '%s' instead of %d books", expected, books.size()));
    }

    @ParameterizedTest
    @CsvSource({
            "-2021, 0",
            "-2019, 0",
            "-2010, 0"
    })
    void testFindBooksByYearWithNegativeNumbers(int year, int expected){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bookManager.findBooksByYear(year));
        assertEquals("Year must be positive", exception.getMessage(), "Exception message should match");
    }

    @ParameterizedTest
    @CsvSource({
            "Drama, 2",
            "Fantastic, 1",
            "Science, 1"
    })
    void testFindBooksByGenre(String genre, int expected){
        List<Book> books = bookManager.findBooksByGenre(genre);
        assertEquals(expected, books.size(), String.format("Should be '%s' instead of %d books", expected, books.size()));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testFindBooksByGenreWithNegativeNumbers(String genre){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bookManager.findBooksByGenre(genre));
        assertEquals("Genre cannot be null or empty", exception.getMessage(), "Exception message should match");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Alice Johnson", "Bob Smith", "Charlie Brown"})
    void testRemoveBooksByAuthor_Valid(String author) {
        bookManager.removeBooksByAuthor(author);
        assertFalse(bookManager.printListOfAuthors().contains(author));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "   "})
    void testRemoveBooksByAuthor_Invalid(String author) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bookManager.removeBooksByAuthor(author));
        assertEquals("Author cannot be null or empty", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "title, Dungeon Meshi, Life's Journey",
            "author, The Great Adventure, Space Odyssey",
            "year, Science Explained, Life's Journey"
    })
    void testSortBooksByCriterion(String criterion, String firstExpected, String secondExpected) {
        bookManager.sortBooksByCriterion(criterion);
        List<Book> sortedBooks = bookManager.getBooks();
        assertEquals(firstExpected, sortedBooks.get(0).getTitle());
        assertEquals(secondExpected, sortedBooks.get(1).getTitle());
    }

    @Test
    void testMergeCollections_Valid() {
        List<Book> newBooks = Arrays.asList(
                new Book("New Adventure", "New Author", "Drama", 2025),
                new Book("Space Odyssey", "Alice Johnson", "Fantastic", 2024)
        );
        bookManager.mergeCollections(newBooks);
        assertEquals(6, bookManager.size());
        assertTrue(bookManager.printListOfAuthors().contains("New Author"));
    }

    @Test
    void testMergeCollections_NullCollection() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bookManager.mergeCollections(null));
        assertEquals("Other collection cannot be null", exception.getMessage());
    }

    @Test
    void testMergeCollections_IgnoreNullBook() {
        List<Book> newBooks = Arrays.asList(
                new Book("New Adventure", "New Author", "Drama", 2025),
                null
        );
        bookManager.mergeCollections(newBooks);
        assertEquals(6, bookManager.size());
        assertTrue(bookManager.printListOfAuthors().contains("New Author"));
    }

    @ParameterizedTest
    @CsvSource({
            "Drama, 2",
            "Fantastic, 1",
            "Adventure, 1",
            "Science, 1",
            "Fantasy, 0"
    })
    void testSubCollectionByGenre(String genre, int expectedSize) {
        List<Book> subCollection = bookManager.subCollectionByGenre(genre);
        assertEquals(expectedSize, subCollection.size());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "   "})
    void testSubCollectionByGenre_Invalid(String genre) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bookManager.subCollectionByGenre(genre));
        assertEquals("Genre cannot be null or empty", exception.getMessage());
    }
}