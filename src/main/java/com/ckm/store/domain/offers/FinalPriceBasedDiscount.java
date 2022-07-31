package com.ckm.store.domain.offers;

import com.ckm.store.domain.dto.BookDetail;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FinalPriceBasedDiscount implements Discount {
    @Override
    public Double apply(List<BookDetail> bookDetails, Double sum) {
        return sum>30?(sum*(100-5))/100:sum;
    }

    @Override
    public boolean filter(BookDetail bookDetail) {
        return false;
    }

    @Override
    public boolean isApplicable(){
        return true;
    }

    @Override
    public int order() {
        return 10;
    }
}
