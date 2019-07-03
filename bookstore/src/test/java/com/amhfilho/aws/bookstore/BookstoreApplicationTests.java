package com.amhfilho.aws.bookstore;

import com.amhfilho.aws.bookstore.book.Book;
import com.amhfilho.aws.bookstore.book.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookstoreApplicationTests {
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private BookRepository bookRepository;

	@Test
	public void shouldLoadTheBooksFromDataFile(){
		Book effectiveJava = entityManager.find(Book.class, "123456");
		assertNotNull(effectiveJava);

		Book designPatterns = entityManager.find(Book.class, "789012");
		assertNotNull(designPatterns);
	}

	@Test
	public void shouldLoadBooksUsingRepository(){
		Iterable<Book> booksFound = bookRepository.findAll();
		assertNotNull(booksFound);

		assertThat(booksFound, hasItems(new Book("123456","any",new BigDecimal("0")),
								   new Book("789012", "any", new BigDecimal("0"))));
	}

	@Test
	@Transactional
	public void shouldAvoidCreateBookWithEmptyTitle(){
		Book book = new Book("ABCDE",null,new BigDecimal("0"));
		//entityManager.persist(book);

		bookRepository.save(book);
	}

	@Test
	@Transactional
	public void shouldInsertNewBook(){
		entityManager.merge(new Book("234567", "OCPJP 8", new BigDecimal("209.0"), "ocjp.jpg"));
		long numberOfBooks = entityManager.createQuery("select count(*) from Book b", Long.class).getSingleResult();
		assertEquals(3, numberOfBooks);
	}


}
