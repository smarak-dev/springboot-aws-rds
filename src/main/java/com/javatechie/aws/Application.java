package com.javatechie.aws;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/book")
public class Application {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping
    public List<Book> findBooks() {
        return bookRepository.findAll();
    }


    @SneakyThrows
    @GetMapping("/{id}")
    public Book findBook(@PathVariable int id) {
        Book book = new Book();
		try {
			book = bookRepository.findById(id).orElseThrow(() -> new Exception("Book not available"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return book;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
