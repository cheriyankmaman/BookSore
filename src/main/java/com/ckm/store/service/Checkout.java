package com.ckm.store.service;

import com.ckm.store.domain.dto.BookDetail;
import com.ckm.store.domain.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Checkout {

    @Autowired
    private BookRepository bookRepository;

    public double checkout(List<String> books){
        double total = 0;
        List<BookDetail> bookDetails = books.stream()
                .map(name -> {
                    bookRepository.findByName(name)
                            .orElse(null);
                })
                .collect(Collectors.toList());

        bookDetails.stream()
                .map(bd -> yearBaseDiscount(bd))
                .map(p -> total += p);


        return total;
    }

    private double yearBaseDiscount(BookDetail bookDetailOptional) {

        double discount = 10;
        double price = 0;
        if(bookDetailOptional.get().getYear() > 2000){
            price = ((100 - discount) * bookDetailOptional.get().getPrice())/100;
        }
        price = bookDetailOptional.get().getPrice();
        return price
    }
}
