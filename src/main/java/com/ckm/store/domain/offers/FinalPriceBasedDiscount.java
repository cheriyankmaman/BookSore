package com.ckm.store.domain.offers;

import com.ckm.store.domain.dto.BookDetail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FinalPriceBasedDiscount implements Discount {

    @Value(value = "${store.discounts.final-price-based.cutoff}")
    private double cutoff;

    @Value(value = "${store.discounts.final-price-based.percent}")
    private double percent;

    @Value(value = "${store.discounts.final-price-based.enabled}")
    private boolean enabled;

    @Override
    public Double apply(List<BookDetail> bookDetails, Double sum) {
        return sum>cutoff?(sum*(100-percent))/100:sum;
    }

    @Override
    public boolean isApplicable(){
        return enabled;
    }

    @Override
    public int order() {
        return 10;
    }
}
