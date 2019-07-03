package com.amhfilho.aws.bookstore.book;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
public class Book {
    @Id
    @NotBlank(message = "Isbn is mandatory")
    private String isbn;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotNull(message = "Price is mandatory")
    private BigDecimal price;

    private String picture;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    protected Book() {}

    public Book(String isbn,String title,BigDecimal price) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
    }

    public Book(String isbn,String title,BigDecimal price,String picture) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.picture = picture;
    }


}
