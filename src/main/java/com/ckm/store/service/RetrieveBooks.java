package com.ckm.store.service;

import com.ckm.store.domain.dto.BookDetail;
import com.ckm.store.domain.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RetrieveBooks {

    public List<BookDetail> retrieveAllBooks(){
        List<BookDetail> bookDetails = new ArrayList<>();

        BookDetail bookDetail01 = new BookDetail("Moby Dick", 15.20, 1851);
        BookDetail bookDetail02 = new BookDetail("The Terrible Privacy of Maxwell Sim", 13.14, 2010);
        BookDetail bookDetail03 = new BookDetail("Still Life With Woodpecker", 11.05, 1980);
        BookDetail bookDetail04 = new BookDetail("Sleeping Murder", 10.24, 1974);
        BookDetail bookDetail05 = new BookDetail("Three Men in a Boat", 12.87, 1889);
        BookDetail bookDetail06 = new BookDetail("The Time Machine", 10.43, 1895);
        BookDetail bookDetail07 = new BookDetail("The Caves of Steel", 8.12, 1952);
        BookDetail bookDetail08 = new BookDetail("Idle Thoughts of an Idle Fellow", 7.32, 1886);
        BookDetail bookDetail09 = new BookDetail("A Christmas Carol", 4.23, 1843);
        BookDetail bookDetail10 = new BookDetail("A Tale of Two Cities", 6.32, 1859);
        BookDetail bookDetail11 = new BookDetail("Great Expectations", 13.21, 1861);

        bookDetails.add(bookDetail01);
        bookDetails.add(bookDetail02);
        bookDetails.add(bookDetail03);
        bookDetails.add(bookDetail04);
        bookDetails.add(bookDetail05);
        bookDetails.add(bookDetail06);
        bookDetails.add(bookDetail07);
        bookDetails.add(bookDetail08);
        bookDetails.add(bookDetail09);
        bookDetails.add(bookDetail10);
        bookDetails.add(bookDetail11);

        return bookDetails;
    }
}
