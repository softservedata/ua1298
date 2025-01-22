package com.softserve.edu;

import java.util.*;
import java.util.stream.Collectors;

public class BookManager {
    List<Book> books;
    public BookManager(){
        books = new ArrayList<Book>();
    }

    public BookManager(ArrayList<Book> books){
        this.books = new ArrayList<>(books);
    }

    public BookManager(Book[] books){
        this.books = new ArrayList<>(List.of(books));
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String printListOfAuthors(){
        if(books.isEmpty()){
            throw new InputMismatchException("There is no books in your manager");
        }
        String result;
        var authors = books.stream().map(Book::getAuthor).collect(Collectors.toSet());
        result = "Authors of all books: " + authors.toString();
        System.out.println(result);
        return result;
    }

    public String printListOfAuthorsByGenre(BookGenre genre){
        if(books.isEmpty()){
            throw new InputMismatchException("There is no books in your manager");
        }
        String result;
        var authors = books.stream().filter(book -> book.getGenre() == genre).map(Book::getAuthor).collect(Collectors.toSet());
        if(authors.isEmpty()){
            result = "No matches!";
        }
        else{
            result = "Authors of all books: " + authors.toString();
        }
        System.out.println(result);
        return result;
    }

    public String printListOfAuthorsByPublicationYear(int year){
        if(books.isEmpty()){
            throw new InputMismatchException("There is no books in your manager");
        }
        String result;
        var authors = books.stream().filter(book-> book.getYear() == year).map(Book::getAuthor).collect(Collectors.toSet());
        if(authors.isEmpty()){
            result = "No matches!";
        }
        else{
            result = "Authors of all books: " + authors.toString();
        }
        System.out.println(result);
        return result;
    }

    public String findBookByAuthor(String author){
        if(books.isEmpty()){
            throw new InputMismatchException("There is no books in your manager");
        }
        String result;
        var firstBookByAuthor = books.stream().filter(book-> Objects.equals(book.getAuthor(), author)).findFirst();
        if(firstBookByAuthor.isEmpty()){
            result = "No matches!";
        }
        else{
            result = "Book: " + firstBookByAuthor.toString();
        }
        System.out.println(result);
        return result;
    }

    public String findBooksByPublicationYear(int year){
        if(books.isEmpty()){
            throw new InputMismatchException("There is no books in your manager");
        }
        String result;
        var booksByYear = books.stream().filter(book-> book.getYear() == year).toList();
        if(booksByYear.isEmpty()){
            result = "No matches!";
        }
        else{
            result = "Books written in " + year + ": " + booksByYear.toString();
        }
        System.out.println(result);
        return result;
    }

    public String findBooksByGenre(BookGenre genre){
        if(books.isEmpty()){
            throw new InputMismatchException("There is no books in your manager");
        }
        String result;
        var booksByYear = books.stream().filter(book-> book.getGenre() == genre).toList();
        if(booksByYear.isEmpty()){
            result = "No matches!";
        }
        else{
            result = "Books with genre " + genre + ": " + booksByYear.toString();
        }
        System.out.println(result);
        return result;
    }

    public void removeBooksByAuthor(String author){
        if(books.isEmpty()){
            throw new InputMismatchException("There is no books in your manager");
        }
        if(findBookByAuthor(author).equals("No matches!")){
            throw new InputMismatchException("There is nothing to be removed!");
        }
        books.removeAll(books.stream().filter(book-> Objects.equals(book.getAuthor(), author)).toList());
    }

    public void sortCollectionByCriteria(Comparator<Book> comparator){
        if(books.isEmpty()){
            throw new InputMismatchException("There is no books in your manager");
        }
        books.sort(comparator);
    }

    public static BookManager combineBookCollections(Collection<Book> firstCollection, Collection<Book> secondCollection){
        if(firstCollection.isEmpty() || secondCollection.isEmpty()){
            throw new InputMismatchException("There is no books in given collections");
        }
        firstCollection.addAll(secondCollection);
        BookManager result = new BookManager();
        result.setBooks(firstCollection.stream().toList());
        return result;
    }

    public Collection<Book> subCollectionByGenre(BookGenre genre){
        if(findBooksByGenre(genre).equals("No matches!")){
            throw new InputMismatchException("There is nothing to add to subcollection!");
        }
        return new ArrayList<>(books.stream().filter(book -> book.getGenre() == genre).toList());
    }


    public static void main(String[] args) {
        Book[] books = new Book[]{
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
        BookManager bookManager = new BookManager(books);
        bookManager.removeBooksByAuthor("Stephen King");
    }
}
