package com.ckm.store.domain.repository;

import com.ckm.store.domain.dto.BookDetail;
import com.ckm.store.service.RetrieveBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Component
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    private RetrieveBooks retrieveBooks;

    List<BookDetail> bookDetails;
    @PostConstruct
    public void retrieveBooks(){
        bookDetails = retrieveBooks.retrieveAllBooks();
    }
    @Override
    public Optional<BookDetail> findByName(String name) {
        BookDetail bookDetail = null;

        return bookDetails.stream()
                .filter(bd -> bd.getName().equalsIgnoreCase(name))
                .findFirst();
    }
}
