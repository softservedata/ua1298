package com.softserve.edu;

enum BookGenre{
    FANTASY, ROMANCE, THRILLER, HORROR
}

public class Book {
    private String title;
    private String author;
    private BookGenre genre;
    private int year;

    public Book(){
        this.title = "";
        this.author = "";
        this.genre = BookGenre.FANTASY;
        this.year = 1900;
    }

    public Book(String title, String author, BookGenre genre, int year) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
    }

    public Book(String title, String author, String genre, int year) {
        this.title = title;
        this.author = author;
        this.genre = BookGenre.valueOf(genre);
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public void setGenre(String genre) {
        this.genre = BookGenre.valueOf(genre);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre=" + genre +
                ", year=" + year +
                "}\n";
    }
}
