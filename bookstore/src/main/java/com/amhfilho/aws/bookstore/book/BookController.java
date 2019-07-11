package com.amhfilho.aws.bookstore.book;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${s3.bucketName}")
    private String bucketName;

    @GetMapping("/books")
    public String books(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }

    @GetMapping("/new")
    public String showSignUpForm(Book book) {
        return "add-book";
    }

    @PostMapping("/addbook")
    @Transactional
    public String addBook(@ModelAttribute BookFormData bookData, Model model) throws IOException {
        Book book = fromFormData(bookData);
        bookRepository.save(book);
        savePictureFile(bookData.getPicture());
        model.addAttribute("books", bookRepository.findAll());
        return "index";
    }

    private void savePictureFile(MultipartFile file) throws IOException {
        if(file != null){
            amazonS3.putObject(new PutObjectRequest(bucketName,file.getOriginalFilename(),file.getInputStream(),null)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            //file.transferTo(new File("c:/Users/Mult-e/Downloads/" + file.getOriginalFilename()));
        }
    }

    private Book fromFormData(BookFormData bookFormData){
        String picture = null;
        if(bookFormData.getPicture() != null){
            picture = String.format("http://s3.amazonaws.com/%s/%s",bucketName, bookFormData.getPicture().getOriginalFilename());
        }
        return new Book(bookFormData.getIsbn(),bookFormData.getTitle(),bookFormData.getPrice(), picture);
    }
}
