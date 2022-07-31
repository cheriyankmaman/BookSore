package com.ckm.store.domain.offers;

import com.ckm.store.domain.dto.BookDetail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class YearBasedDiscount implements Discount {

    @Value(value = "${store.discounts.year-based.cutoff}")
    private double cutoff;

    @Value(value = "${store.discounts.year-based.percent}")
    private double percent;

    @Value(value = "${store.discounts.year-based.enabled}")
    private boolean enabled;

    @Override
    public Double apply(List<BookDetail> bookDetails, Double sum) {
        double discount = bookDetails.stream()
                .map(this::evaluateDiscount)
                .mapToDouble(Double::doubleValue).sum();
        return sum - discount;
    }

    private Double evaluateDiscount(BookDetail bookDetail) {

        if(bookDetail.getYear() > cutoff) {
            return percent * bookDetail.getPrice()/100;
        }
        return (double) 0;
    }

    @Override
    public boolean isApplicable() {
        return enabled;
    }

    @Override
    public int order() {
        return 1;
    }
}
