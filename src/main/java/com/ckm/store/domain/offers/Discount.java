package com.ckm.store.domain.offers;

import com.ckm.store.domain.dto.BookDetail;

import java.util.List;

public interface Discount {

    Double apply(List<BookDetail> bookDetails, Double sum);

    boolean filter(BookDetail bookDetail);

    boolean isApplicable();

    int order();
}
