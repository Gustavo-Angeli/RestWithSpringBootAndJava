package br.com.gusta.data.vo.v1;

import com.github.dozermapper.core.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.*;
import java.util.*;

public class BookVO extends RepresentationModel<BookVO> implements Serializable {

    @Mapping("id")
    private Long key;
    private String name;
    private String pages;
    private String author;

    public BookVO() {}

    public BookVO(Long key, String name, String pages, String author) {
        this.key = key;
        this.name = name;
        this.pages = pages;
        this.author = author;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long id) {
        this.key = key;
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
        BookVO bookVO = (BookVO) o;
        return key.equals(bookVO.key) && name.equals(bookVO.name) && pages.equals(bookVO.pages) && author.equals(bookVO.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, name, pages, author);
    }
}
