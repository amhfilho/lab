package com.amhfilho.aws.bookstore.book;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Getter
@Setter
public class BookFormData {
    private String isbn;
    private String title;
    private BigDecimal price;
    private MultipartFile picture;
}
