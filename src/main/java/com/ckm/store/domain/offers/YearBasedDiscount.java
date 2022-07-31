package com.ckm.store.domain.offers;

import com.ckm.store.domain.dto.BookDetail;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class YearBasedDiscount implements Discount {
    @Override
    public Double apply(List<BookDetail> bookDetails, Double sum) {
        double discount = bookDetails.stream()
                .map(this::evaluateDiscount)
                .mapToDouble(Double::doubleValue).sum();
        return sum - discount;
    }

    private Double evaluateDiscount(BookDetail bookDetail) {
        double discount = 10;
        if(bookDetail.getYear() > 2000) {
            return discount * bookDetail.getPrice()/100;
        }
        return (double) 0;
    }

    @Override
    public boolean filter(BookDetail bookDetail) {
        return false;
    }

    @Override
    public boolean isApplicable() {
        return true;
    }

    @Override
    public int order() {
        return 1;
    }
}
