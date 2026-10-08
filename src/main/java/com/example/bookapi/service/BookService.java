package com.example.bookapi.service;

import com.example.bookapi.models.Book;
import com.example.bookapi.models.BookRequest;
import com.example.bookapi.models.BookResponse;
import com.example.bookapi.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<BookResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<BookResponse> getById(Long id) {
        return repository.findById(id)
                .map(this::toResponse);
    }

    public BookResponse add(BookRequest request) {
        Book book = new Book();
        book.setTitle(request.title());
        book.setAuthor(request.author());

        Book saved = repository.save(book);
        return toResponse(saved);
    }

    private BookResponse toResponse(Book book) {
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor());
    }
}