package br.com.gusta.model;

import javax.persistence.*;
import java.io.*;
import java.util.*;

@Entity
@Table(name = "Books")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String pages;
    private String author;

    public Book() {}

    public Book(Long id, String name, String pages, String author) {
        this.id = id;
        this.name = name;
        this.pages = pages;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id) && name.equals(book.name) && pages.equals(book.pages) && author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pages, author);
    }
}
