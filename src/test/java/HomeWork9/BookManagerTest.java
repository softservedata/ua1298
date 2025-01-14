package HomeWork9;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookManagerTest {
    private BookManager manager;

    @BeforeEach
    void setUp() {
        manager = new BookManager();
        manager.addBook(new Book("1984", "George Orwell", "Dystopian", 1949));
        manager.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "Classic", 1960));
        manager.addBook(new Book("Brave New World", "Aldous Huxley", "Dystopian", 1932));
    }

    @Test
    void testPrintAuthors() {
        manager.printAuthors();
    }

    @Test
    void testGetAuthorsByGenre() {
        List<String> authors = manager.getAuthorsByGenre("Dystopian");
        assertEquals(2, authors.size());
        assertTrue(authors.contains("George Orwell"));
        assertTrue(authors.contains("Aldous Huxley"));
    }

    @Test
    void testFindBookByAuthor() {
        Book book = manager.findBookByAuthor("George Orwell");
        assertNotNull(book);
        assertEquals("1984", book.getTitle());
    }

    @Test
    void testRemoveBooksByAuthor() {
        manager.removeBooksByAuthor("George Orwell");
        assertNull(manager.findBookByAuthor("George Orwell"));
    }

    @Test
    void testSortBooksBy() {
        manager.sortBooksBy("year");
        assertEquals(1932, manager.findBooksByYear(1932).get(0).getYear());
    }
}
