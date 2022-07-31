package com.ckm.store.service;

import com.ckm.store.domain.dto.BookDetail;
import com.ckm.store.domain.offers.Discount;
import com.ckm.store.domain.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class Checkout {

    private final BookRepository bookRepository;

    private final List<Discount> discounts;

    public Checkout(BookRepository bookRepository, List<Discount> discounts) {
        this.bookRepository = bookRepository;
        this.discounts = discounts;
    }

    public double checkout(List<String> books){
        List<BookDetail> bookDetails = books.stream()
                .map(name -> bookRepository.findByName(name)
                        .orElseThrow(() -> new RuntimeException("Unknown item in the cart")))
                .collect(Collectors.toList());


        AtomicReference<Double> sum = new AtomicReference<>(bookDetails.stream()
                .map(BookDetail::getPrice)
                .mapToDouble(Double::doubleValue).sum());

        discounts.stream()
                .sorted(Comparator.comparingInt(Discount::order))
                .filter(Discount::isApplicable)
                .forEach(discount -> {
                    sum.set(discount.apply(bookDetails, sum.get()));
                });


        return sum.get();
    }

    private double yearBaseDiscount(BookDetail bookDetailOptional) {

        double discount = 10;
        double price = 0;
        if(bookDetailOptional.getYear() > 2000){
            price = ((100 - discount) * bookDetailOptional.getPrice())/100;
        }
        price = bookDetailOptional.getPrice();
        return price;
    }
}
