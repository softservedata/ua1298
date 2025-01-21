package com.softserve.edu;

import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

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
    void checkSubCollectionByGenreWithExistingGenreAndManyBooksDifferentGenres() {
        //arrange
        BookManager bookManager = new BookManager(books);
        //act
        Collection<Book> subCollectionInFantasy = bookManager.subCollectionByGenre(BookGenre.FANTASY);
        //assert
        assertEquals(5, subCollectionInFantasy.size());
        assertTrue(subCollectionInFantasy.contains(books[0]));
        assertTrue(subCollectionInFantasy.contains(books[2]));
        assertTrue(subCollectionInFantasy.contains(books[4]));
        assertFalse(subCollectionInFantasy.contains(books[5]));
        assertFalse(subCollectionInFantasy.contains(books[20]));
    }

    @Test
    void checkSubCollectionByGenreWithExistingGenreAndManyBooksOneGenre() {
        //arrange
        BookManager bookManager = new BookManager(new Book[]{books[20], books[19], books[18]});
        //act
        Collection<Book> subCollectionInRomance = bookManager.subCollectionByGenre(BookGenre.THRILLER);
        //assert
        assertEquals(3, subCollectionInRomance.size());
        assertTrue(subCollectionInRomance.contains(books[20]));
        assertTrue(subCollectionInRomance.contains(books[18]));
        assertFalse(subCollectionInRomance.contains(books[0]));
        assertFalse(subCollectionInRomance.contains(books[17]));
    }

    @Test
    void checkSubCollectionByGenreWithNonExistingGenreThrowsException() {
        //arrange
        BookManager bookManager = new BookManager(new Book[]{});
        //act
        //assert
        assertThrows(InputMismatchException.class, () -> bookManager.subCollectionByGenre(BookGenre.FANTASY));
        assertThrows(InputMismatchException.class, () -> bookManager.subCollectionByGenre(BookGenre.THRILLER));
        assertThrows(InputMismatchException.class, () -> bookManager.subCollectionByGenre(BookGenre.ROMANCE));
    }

    @Test
    void checkSubCollectionByGenreWithExistingGenreAndOneBook() {
        //arrange
        BookManager bookManager = new BookManager(new Book[]{books[6]});
        //act
        Collection<Book> subCollectionInRomance = bookManager.subCollectionByGenre(BookGenre.ROMANCE);
        //assert
        assertEquals(1, subCollectionInRomance.size());
        assertTrue(subCollectionInRomance.contains(books[6]));
        assertFalse(subCollectionInRomance.contains(books[0]));
        assertFalse(subCollectionInRomance.contains(books[20]));
    }

    @Test
    void checkCombineBookCollectionsWithValidCollectionsWithManyBooks() {
        //arrange
        BookManager bookManager1 = new BookManager(new Book[]{books[0], books[1], books[2], books[3], books[4]});
        BookManager bookManager2 = new BookManager(new Book[]{books[5], books[6], books[7], books[8], books[9]});
        int expectedLength = bookManager2.getBooks().size() + bookManager1.getBooks().size();
        //act
        BookManager combinationManager = BookManager.combineBookCollections(bookManager1.getBooks(), bookManager2.getBooks());
        //assert
        assertEquals(expectedLength, combinationManager.getBooks().size());
        assertEquals(books[0], combinationManager.getBooks().getFirst());
        assertEquals(books[expectedLength - 1], combinationManager.getBooks().get(expectedLength - 1));
        assertEquals(books[expectedLength / 2], combinationManager.getBooks().get(expectedLength / 2));
    }

    @Test
    void checkCombineBookCollectionsWithValidCollectionsWithOnlyOneBook() {
        //arrange
        BookManager bookManager1 = new BookManager(new Book[]{books[0]});
        BookManager bookManager2 = new BookManager(new Book[]{books[20]});
        int expectedLength = bookManager2.getBooks().size() + bookManager1.getBooks().size();
        Book expectedLastBook = books[20];
        //act
        BookManager combinationManager = BookManager.combineBookCollections(bookManager1.getBooks(), bookManager2.getBooks());
        //assert
        assertEquals(expectedLength, combinationManager.getBooks().size());
        assertEquals(books[0], combinationManager.getBooks().getFirst());
        assertEquals(expectedLastBook, combinationManager.getBooks().get(expectedLength - 1));
    }

    @Test
    void checkCombineBookCollectionsWithEmptyOneCollection() {
        //arrange
        BookManager bookManager1 = new BookManager(new Book[]{});
        BookManager bookManager2 = new BookManager(new Book[]{books[20]});
        //act
        //assert
        assertThrows(InputMismatchException.class, () -> BookManager.combineBookCollections(bookManager1.getBooks(), bookManager2.getBooks()), "There is no books in given collections");
        assertThrows(InputMismatchException.class, () -> BookManager.combineBookCollections(bookManager2.getBooks(), bookManager1.getBooks()), "There is no books in given collections");
    }

    @Test
    void checkCombineBookCollectionsWithEmptyTwoCollections() {
        //arrange
        BookManager bookManager1 = new BookManager(new Book[]{});
        BookManager bookManager2 = new BookManager(new Book[]{});
        //act
        //assert
        assertThrows(InputMismatchException.class, () -> BookManager.combineBookCollections(bookManager1.getBooks(), bookManager2.getBooks()), "There is no books in given collections");
    }

    @Test
    void checkSortCollectionByAge() {
        //arrange
        BookManager bookManager = new BookManager(books);
        Book firstExpected = new Book("Sense and Sensibility", "Jane Austen", BookGenre.ROMANCE, 1811);
        Book lastExpected = new Book("The Girl on the Train", "Paula Hawkins", BookGenre.THRILLER, 2015);
        //act
        bookManager.sortCollectionByCriteria(Comparator.comparingInt(Book::getYear));
        //assert
        assertEquals(firstExpected, bookManager.getBooks().getFirst());
        assertEquals(lastExpected, bookManager.getBooks().getLast());
    }

    @Test
    void checkSortCollectionByAuthor() {
        //arrange
        BookManager bookManager = new BookManager(books);
        Book firstExpected = new Book("Dracula", "Bram Stoker", BookGenre.HORROR, 1897);
        Book lastExpected = new Book("The Girl with the Dragon Tattoo", "Stieg Larsson", BookGenre.THRILLER, 2005);
        //act
        bookManager.sortCollectionByCriteria(Comparator.comparing(Book::getAuthor));
        //assert
        assertEquals(firstExpected, bookManager.getBooks().getFirst());
        assertEquals(lastExpected, bookManager.getBooks().getLast());
    }

    @Test
    void checkSortCollectionByGenre() {
        //arrange
        BookManager bookManager = new BookManager(books);
        Book firstExpected = new Book("The Hobbit", "J.R.R. Tolkien", BookGenre.FANTASY, 1937);
        Book lastExpected = new Book("Carrie", "Stephen King", BookGenre.HORROR, 1974);
        //act
        bookManager.sortCollectionByCriteria(Comparator.comparing(Book::getGenre));
        //assert
        assertEquals(firstExpected, bookManager.getBooks().getFirst());
        assertEquals(lastExpected, bookManager.getBooks().getLast());
    }

    @Test
    void checkSortCollectionByTitle() {
        //arrange
        BookManager bookManager = new BookManager(books);
        Book firstExpected = new Book("A Walk to Remember", "Nicholas Sparks", BookGenre.ROMANCE, 1999);
        Book lastExpected = new Book("The Wise Man's Fear", "Patrick Rothfuss", BookGenre.FANTASY, 2011);
        //act
        bookManager.sortCollectionByCriteria(Comparator.comparing(Book::getTitle));
        //assert
        assertEquals(firstExpected, bookManager.getBooks().getFirst());
        assertEquals(lastExpected, bookManager.getBooks().getLast());
    }

    @Test
    void checkSortCollectionWithEmptyManagerThrowsException() {
        //arrange
        BookManager bookManager = new BookManager(new Book[]{});
        //act
        //assert
        assertThrows(InputMismatchException.class, () -> bookManager.sortCollectionByCriteria(Comparator.comparingInt(Book::getYear)));
        assertThrows(InputMismatchException.class, () -> bookManager.sortCollectionByCriteria(Comparator.comparing(Book::getTitle)));
        assertThrows(InputMismatchException.class, () -> bookManager.sortCollectionByCriteria(Comparator.comparing(Book::getGenre)));
    }

    @Test
    void checkRemoveBooksByExistingAuthor() {
        //arrange
        BookManager bookManager = new BookManager(books);
        //act
        boolean isRecordWithKing = !bookManager.findBookByAuthor("Stephen King").equals("No matches!");
        bookManager.removeBooksByAuthor("Stephen King");
        boolean noRecordWithKing = bookManager.findBookByAuthor("Stephen King").equals("No matches!");
        boolean isRecordWithShelley = !bookManager.findBookByAuthor("Mary Shelley").equals("No matches!");
        bookManager.removeBooksByAuthor("Mary Shelley");
        boolean noRecordWithShelley = bookManager.findBookByAuthor("Mary Shelley").equals("No matches!");
        //assert
        assertTrue(isRecordWithKing);
        assertTrue(noRecordWithKing);
        assertTrue(isRecordWithShelley);
        assertTrue(noRecordWithShelley);
    }

    @Test
    void checkRemoveBooksByNonExistingAuthorThrowsException() {
        //arrange
        BookManager bookManager = new BookManager(books);
        //act
        //assert
        assertThrows(InputMismatchException.class, () -> bookManager.removeBooksByAuthor("Taras Shevchenko"), "There is nothing to be removed!");
        assertThrows(InputMismatchException.class, () -> bookManager.removeBooksByAuthor("abracadabra"), "There is nothing to be removed!");
    }

    @Test
    void checkRemoveBooksFromEmptyManagerThrowsException() {
        //arrange
        BookManager bookManager = new BookManager(new Book[]{});
        //act
        //assert
        assertThrows(InputMismatchException.class, () -> bookManager.removeBooksByAuthor("Taras Shevchenko"), "There is no books in your manager");
        assertThrows(InputMismatchException.class, () -> bookManager.removeBooksByAuthor("abracadabra"), "There is no books in your manager");
    }

    @Test
    void checkFindBooksByGenreThrowsTheExceptionWhileEmptyManager(){
        //arrange
        BookManager bookManager = new BookManager(new Book[]{});
        //act
        //assert
        assertThrows(InputMismatchException.class, () -> bookManager.findBooksByGenre(BookGenre.FANTASY), "There is no books in your manager");
        assertThrows(InputMismatchException.class, () -> bookManager.findBooksByGenre(BookGenre.HORROR), "There is no books in your manager");
    }

    @Test
    void checkFindBooksByGenreWithNonExistingGenre() {
        //arrange
        BookManager bookManager = new BookManager(new Book[]{books[0], books[1], books[2], books[3], books[4], books[5], books[6], books[7], books[8],
                books[9], books[10], books[11], books[12], books[13], books[14], books[15]});
        //act
        var result = bookManager.findBooksByGenre(BookGenre.THRILLER);
        //assert
        assertEquals("No matches!", result);
    }

    @Test
    void checkFindBooksByGenreWithExistingGenre() {
        //arrange
        BookManager bookManager = new BookManager(new Book[]{books[0], books[1], books[2], books[3], books[4], books[5], books[6], books[7], books[8],
                books[9], books[10], books[11], books[12], books[13], books[14], books[15], books[18]});
        //act
        var resultByGenreWithOneBook = bookManager.findBooksByGenre(BookGenre.THRILLER).split("[\\[,]");
        var resultByGenreWithManyBooks = bookManager.findBooksByGenre(BookGenre.HORROR).split("[\\[,]");
        //assert
        assertEquals(2, resultByGenreWithOneBook.length);
        assertTrue(Arrays.stream(resultByGenreWithOneBook).anyMatch(s -> s.contains("Book{title='The Girl with the Dragon Tattoo' - author='Stieg Larsson' - genre=THRILLER - year=2005")));
        assertTrue(Arrays.stream(resultByGenreWithOneBook).anyMatch(s -> s.contains("Books with genre THRILLER")));
        assertEquals(4, resultByGenreWithManyBooks.length);
        assertTrue(Arrays.stream(resultByGenreWithManyBooks).anyMatch(s -> s.contains("title='Dracula' - author='Bram Stoker' - genre=HORROR - year=1897")));
        assertTrue(Arrays.stream(resultByGenreWithManyBooks).anyMatch(s -> s.contains("title='Frankenstein' - author='Mary Shelley' - genre=HORROR - year=1818")));
        assertTrue(Arrays.stream(resultByGenreWithManyBooks).anyMatch(s -> s.contains("title='The Shining' - author='Stephen King' - genre=HORROR - year=1977")));
        assertTrue(Arrays.stream(resultByGenreWithManyBooks).anyMatch(s -> s.contains("Books with genre HORROR")));
    }

    @Test
    void checkFindBooksByPublicationYearThrowsTheExceptionWhileEmptyManager(){
        //arrange
        BookManager bookManager = new BookManager(new Book[]{});
        //act
        //assert
        assertThrows(InputMismatchException.class, () -> bookManager.findBooksByPublicationYear(2005), "There is no books in your manager");
        assertThrows(InputMismatchException.class, () -> bookManager.findBooksByPublicationYear(2025), "There is no books in your manager");
    }

    @Test
    void checkFindBooksByPublicationYearWithNonExistingYear() {
        //arrange
        BookManager bookManager = new BookManager(books);
        //act
        var result = bookManager.findBooksByPublicationYear(2024);
        //assert
        assertEquals("No matches!", result);
    }

    @Test
    void checkFindBooksByPublicationYearWithExistingYear() {
        //arrange
        BookManager bookManager = new BookManager(books);
        //act
        var resultByYearWithOneBook = bookManager.findBooksByPublicationYear(1974).split("[\\[,]");
        var resultByYearWithManyBooks = bookManager.findBooksByPublicationYear(2012).split("[\\[,]");
        //assert
        assertEquals(2, resultByYearWithOneBook.length);
        assertTrue(Arrays.stream(resultByYearWithOneBook).anyMatch(s -> s.contains("Carrie")));
        assertTrue(Arrays.stream(resultByYearWithOneBook).anyMatch(s -> s.contains("1974")));
        assertTrue(Arrays.stream(resultByYearWithOneBook).anyMatch(s -> s.contains("Stephen King")));
        assertTrue(Arrays.stream(resultByYearWithOneBook).anyMatch(s -> s.contains("HORROR")));
        assertTrue(Arrays.stream(resultByYearWithOneBook).anyMatch(s -> s.contains("Books")));

        assertEquals(3, resultByYearWithManyBooks.length);
        assertTrue(Arrays.stream(resultByYearWithManyBooks).anyMatch(s -> s.contains("Me Before You")));
        assertTrue(Arrays.stream(resultByYearWithManyBooks).anyMatch(s -> s.contains("Jojo Moyes")));
        assertTrue(Arrays.stream(resultByYearWithManyBooks).anyMatch(s -> s.contains("2012")));
        assertTrue(Arrays.stream(resultByYearWithManyBooks).anyMatch(s -> s.contains("THRILLER")));
        assertTrue(Arrays.stream(resultByYearWithManyBooks).anyMatch(s -> s.contains("Gillian Flynn")));
        assertTrue(Arrays.stream(resultByYearWithManyBooks).anyMatch(s -> s.contains("Gone Girl")));
        assertTrue(Arrays.stream(resultByYearWithManyBooks).anyMatch(s -> s.contains("ROMANCE")));
        assertTrue(Arrays.stream(resultByYearWithManyBooks).anyMatch(s -> s.contains("Books written in")));
    }

    @Test
    void checkFindBookByAuthorWithExistingAuthor() {
        //arrange
        BookManager bookManager = new BookManager(books);
        //act
        var resultFromAuthorWithOneBook = bookManager.findBookByAuthor("Graeme Simsion").split("[\\[,]");
        var resultFromAuthorWithManyBooks = bookManager.findBookByAuthor("J.R.R. Tolkien").split("[\\[,]");
        //assert
        assertEquals(2, resultFromAuthorWithOneBook.length);
        assertTrue(Arrays.stream(resultFromAuthorWithOneBook).anyMatch(s -> s.contains("Graeme Simsion")));
        assertTrue(Arrays.stream(resultFromAuthorWithOneBook).anyMatch(s -> s.contains("2013")));
        assertTrue(Arrays.stream(resultFromAuthorWithOneBook).anyMatch(s -> s.contains("The Rosie Project")));
        assertTrue(Arrays.stream(resultFromAuthorWithOneBook).anyMatch(s -> s.contains("ROMANCE")));
        assertTrue(Arrays.stream(resultFromAuthorWithOneBook).anyMatch(s -> s.contains("Book")));

        assertEquals(2, resultFromAuthorWithManyBooks.length);
        assertTrue(Arrays.stream(resultFromAuthorWithManyBooks).anyMatch(s -> s.contains("J.R.R. Tolkien")));
        assertTrue(Arrays.stream(resultFromAuthorWithManyBooks).anyMatch(s -> s.contains("The Hobbit")));
        assertTrue(Arrays.stream(resultFromAuthorWithManyBooks).anyMatch(s -> s.contains("1937")));
        assertTrue(Arrays.stream(resultFromAuthorWithManyBooks).anyMatch(s -> s.contains("FANTASY")));
        assertTrue(Arrays.stream(resultFromAuthorWithManyBooks).anyMatch(s -> s.contains("Book")));
    }

    @Test
    void checkFindBookByAuthorWithNonExistingAuthor() {
        //arrange
        BookManager bookManager = new BookManager(books);
        //act
        var result = bookManager.findBookByAuthor("Joan Rowling");
        //assert
        assertEquals("No matches!", result);
    }

    @Test
    void checkFindBookByAuthorThrowsTheExceptionWhileEmptyManager(){
        //arrange
        BookManager bookManager = new BookManager(new Book[]{});
        //act
        //assert
        assertThrows(InputMismatchException.class, () -> bookManager.findBookByAuthor("Stephen King"), "There is no books in your manager");
        assertThrows(InputMismatchException.class, () -> bookManager.findBookByAuthor("Ric Riordan"), "There is no books in your manager");
    }

    @Test
    void checkPrintListOfAuthorsByPublicationYearWithExistingYear() {
        //arrange
        BookManager bookManager = new BookManager(books);
        //act
        var resultWithTwoRecords = bookManager.printListOfAuthorsByPublicationYear(2015).split("[\\[,]");
        var resultWithSingleRecord = bookManager.printListOfAuthorsByPublicationYear(2005).split("[\\[,]");
        //assert
        assertEquals(3, resultWithTwoRecords.length);
        assertTrue(Arrays.stream(resultWithTwoRecords).anyMatch(s -> s.contains("Patrick Rothfuss")));
        assertTrue(Arrays.stream(resultWithTwoRecords).anyMatch(s -> s.contains("Paula Hawkins")));
        assertTrue(Arrays.stream(resultWithTwoRecords).anyMatch(s -> s.contains("Authors")));

        assertEquals(2, resultWithSingleRecord.length);
        assertTrue(Arrays.stream(resultWithSingleRecord).anyMatch(s -> s.contains("Stieg Larsson")));
        assertTrue(Arrays.stream(resultWithTwoRecords).anyMatch(s -> s.contains("Authors")));
    }

    @Test
    void checkPrintListOfAuthorsByPublicationYearWithNonExistingYear() {
        //arrange
        BookManager bookManager = new BookManager(books);
        //act
        var result = bookManager.printListOfAuthorsByPublicationYear(2024);
        //assert
        assertEquals("No matches!", result);
    }

    @Test
    void checkPrintListOfAuthorsByPublicationYearWithExistingYearOneBook() {
        //arrange
        BookManager bookManager = new BookManager(new Book[]{books[0]});
        //act
        var result = bookManager.printListOfAuthorsByPublicationYear(1937).split("[\\[,]");
        //assert
        assertEquals(2, result.length);
        assertTrue(Arrays.stream(result).anyMatch(s -> s.contains("J.R.R. Tolkien")));
        assertTrue(Arrays.stream(result).anyMatch(s -> s.contains("Authors")));
    }

    @Test
    void checkPrintListOfAuthorsByPublicationYearWithNonExistingYearOneBook() {
        //arrange
        BookManager bookManager = new BookManager(new Book[]{books[0]});
        //act
        var result = bookManager.printListOfAuthorsByPublicationYear(2024);
        //assert
        assertEquals("No matches!", result);
    }

    @Test
    void checkPrintAuthorsByYearThrowsTheExceptionWhileEmptyManager(){
        //arrange
        BookManager bookManager = new BookManager(new Book[]{});
        //act
        //assert
        assertThrows(InputMismatchException.class, () -> bookManager.printListOfAuthorsByPublicationYear(2000), "There is no books in your manager");
        assertThrows(InputMismatchException.class, () -> bookManager.printListOfAuthorsByPublicationYear(2005), "There is no books in your manager");
    }

    @Test
    void checkPrintListOfAuthorsWithDifferentCountOfBooksPerAuthor() {
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
    void checkPrintListOfAuthorsWithOneBookPerAuthor() {
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
    void checkPrintListOfAuthorsWithAllBookOneAuthor() {
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
    void checkPrintListOfAuthorsWithOneBookOneAuthor() {
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
}