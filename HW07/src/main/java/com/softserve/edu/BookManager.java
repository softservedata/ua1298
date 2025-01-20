package com.softserve.edu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BookManager {
    List<Book> books;
    public BookManager(){
        books = new ArrayList<Book>();
    }

    public BookManager(ArrayList<Book> books){
        this.books = (ArrayList<Book>) books.clone();
    }

    public BookManager(Book[] books){
        this.books = (ArrayList<Book>) List.of(books);
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
        var authors = books.stream().map(Book::getAuthor).toList();
        if(authors.isEmpty()){
            result = "No matches!";
        }
        else{
            result = "Authors of all books: " + authors.toString();
        }
        System.out.println(result);
        return result;
    }

    public String printListOfAuthorsByGenre(BookGenre genre){
        if(books.isEmpty()){
            throw new InputMismatchException("There is no books in your manager");
        }
        String result;
        var authors = books.stream().filter(book -> book.getGenre() == genre).map(Book::getAuthor).toList();
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
        var authors = books.stream().filter(book-> book.getYear() == year).map(Book::getAuthor).toList();
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
}
