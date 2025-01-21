package com.softserve.edu;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

class BookManagerTest {
    public static Book[] books;
    @BeforeAll
    static void BeforeAll(){
        books = new Book[]{
            new Book("The Hobbit", "J.R.R. Tolkien", BookGenre.FANTASY, 1937),
            new Book("The Fellowship of the Ring", "J.R.R. Tolkien", BookGenre.FANTASY, 1954),
            new Book("The Name of the Wind", "Patrick Rothfuss", BookGenre.FANTASY, 2007),
            new Book("The Wise Man's Fear", "Patrick Rothfuss", BookGenre.FANTASY, 2011),
            new Book("The Kingkiller Chronicle", "Patrick Rothfuss", BookGenre.FANTASY, 2015),
            new Book("Pride and Prejudice", "Jane Austen", BookGenre.ROMANCE, 1813),
            new Book("Sense and Sensibility", "Jane Austen", BookGenre.ROMANCE, 1811),
            new Book("Emma", "Jane Austen", BookGenre.ROMANCE, 1815),
            new Book("The Notebook", "Nicholas Sparks", BookGenre.ROMANCE, 1996),
            new Book("A Walk to Remember", "Nicholas Sparks", BookGenre.ROMANCE, 1999),
            new Book("The Lucky One", "Nicholas Sparks", BookGenre.ROMANCE, 2008),
            new Book("Me Before You", "Jojo Moyes", BookGenre.ROMANCE, 2012),
            new Book("The Rosie Project", "Graeme Simsion", BookGenre.ROMANCE, 2013),
            new Book("Dracula", "Bram Stoker", BookGenre.HORROR, 1897),
            new Book("Frankenstein", "Mary Shelley", BookGenre.HORROR, 1818),
            new Book("The Shining", "Stephen King", BookGenre.HORROR, 1977),
            new Book("It", "Stephen King", BookGenre.HORROR, 1986),
            new Book("Carrie", "Stephen King", BookGenre.HORROR, 1974),
            new Book("The Girl with the Dragon Tattoo", "Stieg Larsson", BookGenre.THRILLER, 2005),
            new Book("Gone Girl", "Gillian Flynn", BookGenre.THRILLER, 2012),
            new Book("The Girl on the Train", "Paula Hawkins", BookGenre.THRILLER, 2015)
        };
    }

    @Test
    void printListOfAuthorsWithDifferentCountOfBooksPerAuthor() {
        //arrange
        BookManager bookManager = new BookManager(books);
        //act
        var result = bookManager.printListOfAuthors().split("[\\[,]");
        //assert
        assertEquals(13, result.length);
        assertTrue(Arrays.stream(result).anyMatch(s -> s.contains(books[0].getAuthor())));
        assertTrue(Arrays.stream(result).anyMatch(s -> s.contains(books[books.length - 1].getAuthor())));
        assertTrue(Arrays.stream(result).anyMatch(s -> s.contains(books[books.length / 2].getAuthor())));
        assertTrue(Arrays.stream(result).anyMatch(s -> s.contains("Authors")));
    }

    @Test
    void printListOfAuthorsWithOneBookPerAuthor() {
        //arrange
        BookManager bookManager = new BookManager(new Book[]{books[0], books[2],books[5], books[8],
                                                             books[11], books[12],books[13], books[14],
                                                             books[15], books[18],books[19], books[20]});
        //act
        var result = bookManager.printListOfAuthors().split("[\\[,]");
        //assert
        assertEquals(13, result.length);
        assertTrue(Arrays.stream(result).anyMatch(s -> s.contains(books[0].getAuthor())));
        assertTrue(Arrays.stream(result).anyMatch(s -> s.contains(books[books.length - 1].getAuthor())));
        assertTrue(Arrays.stream(result).anyMatch(s -> s.contains(books[books.length / 2].getAuthor())));
        assertTrue(Arrays.stream(result).anyMatch(s -> s.contains("Authors")));
    }

    @Test
    void printListOfAuthorsWithAllBookOneAuthor() {
        //arrange
        int firstIndex = 15;
        int lastIndex = 17;
        BookManager bookManager = new BookManager(new Book[]{books[firstIndex], books[(firstIndex+lastIndex)/2], books[lastIndex]});
        //act
        var result = bookManager.printListOfAuthors().split("[\\[,]");
        //assert
        assertEquals(2, result.length);
        assertTrue(Arrays.stream(result).anyMatch(s -> s.contains(books[firstIndex].getAuthor())));
        assertTrue(Arrays.stream(result).anyMatch(s -> s.contains(books[lastIndex].getAuthor())));
        assertTrue(Arrays.stream(result).anyMatch(s -> s.contains("Authors")));
    }

    @Test
    void printListOfAuthorsWithOneBookOneAuthor() {
        //arrange
        int oneRecordIndex = 20;
        BookManager bookManager = new BookManager(new Book[]{books[oneRecordIndex]});
        //act
        var result = bookManager.printListOfAuthors().split("[\\[,]");
        //assert
        assertEquals(2, result.length);
        assertTrue(Arrays.stream(result).anyMatch(s -> s.contains(books[oneRecordIndex].getAuthor())));
        assertTrue(Arrays.stream(result).anyMatch(s -> s.contains("Authors")));
    }

    @Test
    void checkPrintAuthorsThrowsTheExceptionWhileEmptyManager(){
        //arrange
        BookManager bookManager = new BookManager(new Book[]{});
        //act
        //assert
        assertThrows(InputMismatchException.class, bookManager::printListOfAuthors, "There is no books in your manager");
    }

    @Test
    void checkPrintListOfAuthorsByGenreWithExistingGenreInManager() {
        //arrange
        BookManager bookManager = new BookManager(books);
        //act
        var result = bookManager.printListOfAuthorsByGenre(BookGenre.FANTASY).split("[\\[,]");
        //assert
        assertEquals(3, result.length);
        assertTrue(Arrays.stream(result).anyMatch(s -> s.contains("J.R.R. Tolkien")));
        assertTrue(Arrays.stream(result).anyMatch(s -> s.contains("Patrick Rothfuss")));
        assertTrue(Arrays.stream(result).anyMatch(s -> s.contains("Authors")));
    }

    @Test
    void checkPrintListOfAuthorsByGenreWithNotExistingGenreInManager() {
        //arrange
        BookManager bookManager = new BookManager(new Book[]{books[5], books[8], books[11], books[15], books[18]});
        //act
        var result = bookManager.printListOfAuthorsByGenre(BookGenre.FANTASY);
        //assert
        assertEquals("No matches!", result);
    }

    @Test
    void checkPrintListOfAuthorsByGenreWithNotExistingGenreInManagerWithOneBook() {
        //arrange
        BookManager bookManager = new BookManager(new Book[]{books[5]});
        //act
        var result = bookManager.printListOfAuthorsByGenre(BookGenre.FANTASY);
        //assert
        assertEquals("No matches!", result);
    }

    @Test
    void checkPrintListOfAuthorsByGenreWithExistingGenreInManagerWithOneBook() {
        //arrange
        BookManager bookManager = new BookManager(new Book[]{books[3]});
        //act
        var result = bookManager.printListOfAuthorsByGenre(BookGenre.FANTASY).split("[\\[,]");
        //assert
        assertEquals(2, result.length);
        assertTrue(Arrays.stream(result).anyMatch(s -> s.contains("Patrick Rothfuss")));
        assertTrue(Arrays.stream(result).anyMatch(s -> s.contains("Authors")));
    }

    @Test
    void checkPrintAuthorsByGenreThrowsTheExceptionWhileEmptyManager(){
        //arrange
        BookManager bookManager = new BookManager(new Book[]{});
        //act
        //assert
        assertThrows(InputMismatchException.class, () -> bookManager.printListOfAuthorsByGenre(BookGenre.FANTASY), "There is no books in your manager");
    }

    @Test
    void printListOfAuthorsByGenre() {
    }

    @Test
    void printListOfAuthorsByPublicationYear() {
    }

    @Test
    void findBookByAuthor() {
    }

    @Test
    void findBooksByPublicationYear() {
    }

    @Test
    void findBooksByGenre() {
    }

    @Test
    void removeBooksByAuthor() {
    }

    @Test
    void testRemoveBooksByAuthor() {
    }

    @Test
    void combineBookCollections() {
    }

    @Test
    void subCollectionByGenre() {
    }
}