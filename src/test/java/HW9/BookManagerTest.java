package HW9;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookManagerTest {
    private BookManager bookManager;

    @BeforeEach
    void setUp() {
        bookManager = new BookManager();
        bookManager.addBook(new Book("The Warmth of Other Suns", "Isabel Wilkerson", "Fiction", 2010));
        bookManager.addBook(new Book("Wolf Hall", "Hilary Mantel", "Drama", 2010));
        bookManager.addBook(new Book("The Known World", "Edward P. Jones", "Historical novel", 2003));
        bookManager.addBook(new Book("The Corrections", "Jonathan Franzen", "Fiction", 2001));
    }

    @Test
    void testPrintAuthors() {
        bookManager.printAuthors();
    }

    @Test
    void testListAuthorsByGenre() {
        bookManager.listAuthorsByGenre("Fiction");
    }

    @Test
    void testListAuthorsByYear() {
        bookManager.listAuthorsByYear(2010);
    }

    @Test
    void testFindBookByAuthor() {
        Book book = bookManager.findBookByAuthor("Isabel Wilkerson");
        assertNotNull(book);
        assertEquals("The Warmth of Other Suns", book.getTitle());
    }

    @Test
    void testFindBooksByYear() {
        List<Book> books = bookManager.findBooksByYear(2010);
        assertEquals(2, books.size());
    }

    @Test
    void testFindBooksByGenre() {
        List<Book> books = bookManager.findBooksByGenre("Fiction");
        assertEquals(2, books.size());
    }

    @Test
    void testRemoveBooksByAuthor() {
        bookManager.removeBooksByAuthor("Isabel Wilkerson");
        assertNull(bookManager.findBookByAuthor("Isabel Wilkerson"));
    }

    @Test
    void testSortByTitle() {
        bookManager.sortByTitle();
        assertEquals("The Corrections", bookManager.getBooks().get(0).getTitle());
    }

    @Test
    void testSortByAuthor() {
        bookManager.sortByAuthor();
        assertEquals("Edward P. Jones", bookManager.getBooks().get(0).getAuthor());
    }

    @Test
    void testSortByYear() {
        bookManager.sortByYear();
        assertEquals(2001, bookManager.getBooks().get(0).getYear());
    }

    @Test
    @DisplayName("Add Book: Successfully adds a new unique book")
    void testAddBookPositive() {
        Book newBook = new Book("Gilead", "Marilynne Robinson", "Drama", 2009);
        bookManager.addBook(newBook);
        assertEquals(5, bookManager.size(), "The collection should contain 5 books after adding a new book");
    }

    @Test
    @DisplayName("Add Book: Throws exception when attempting to add null")
    void testAddBookNull() {
        try {
            bookManager.addBook(null);
            fail("Expected IllegalArgumentException, but no exception was thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Book cannot be null", e.getMessage());
        }
    }

    @Test
    @DisplayName("Add Book: Throws exception when attempting to add a duplicate book")
    void testAddBookDuplicate() {
        Book duplicateBook = new Book("The Warmth of Other Suns", "Isabel Wilkerson", "Fiction", 2010);
        assertThrows(IllegalArgumentException.class, () -> bookManager.addBook(duplicateBook),
                "Adding a duplicate book should throw IllegalArgumentException");
    }

    @Test
    @DisplayName("List All Authors: Returns a list of unique authors in the collection")
    void testListAllAuthors() {
        List<String> authors = bookManager.listOfAllAuthors();
        assertEquals(4, authors.size(), "The collection should contain 4 unique authors");
        assertTrue(authors.contains("Isabel Wilkerson"), "Author list should contain 'Isabel Wilkerson'");
        assertTrue(authors.contains("Hilary Mantel"), "Author list should contain 'Hilary Mantel'");
        assertTrue(authors.contains("Edward P. Jones"), "Author list should contain 'Edward P. Jones'");
        assertTrue(authors.contains("Jonathan Franzen"), "Author list should contain 'Jonathan Franzen'");
    }

    @Test
    void testFindBookByNonExistingAuthor() {
        Book book = bookManager.findBookByAuthor("NonExistingAuthor");
        assertNull(book);
    }

    @Test
    void testRemoveBooksByNonExistingAuthor() {
        bookManager.removeBooksByAuthor("NonExistingAuthor");
        assertEquals(4, bookManager.getBooks().size());
    }
}