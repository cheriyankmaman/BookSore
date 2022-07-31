package com.ckm.store.domain.offers;

import com.ckm.store.domain.dto.BookDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class YearBasedDiscountTest {

    @InjectMocks
    private YearBasedDiscount yearBasedDiscount;

    @BeforeEach
    void init(){
        ReflectionTestUtils.setField(yearBasedDiscount, "cutoff", 2000);
        ReflectionTestUtils.setField(yearBasedDiscount, "percent", 10);
        ReflectionTestUtils.setField(yearBasedDiscount, "enabled", true);
    }

    @Test
    public void testYearBasedDiscountIsEnabledThenReturnTrue(){
        Assertions.assertTrue(yearBasedDiscount.isApplicable());
    }

    @Test
    public void testYearBasedDiscountOrderThenReturn10(){
        Assertions.assertEquals(1, yearBasedDiscount.order());
    }

    @Test
    public void testYearBasedDiscountLogicThenReturnTotal(){
        BookDetail bookDetail = new BookDetail("my book", 20.0, 1990);
        List<BookDetail> bookDetails = Collections.singletonList(bookDetail);

        Assertions.assertEquals(20.0, yearBasedDiscount.apply(bookDetails, 20.0));
    }

    @Test
    public void testYearBasedDiscountLogicThenReturnTotalAfterDiscount(){
        BookDetail bookDetail = new BookDetail("my book", 40.0, 2001);
        List<BookDetail> bookDetails = Collections.singletonList(bookDetail);

        Assertions.assertEquals(36.0, yearBasedDiscount.apply(bookDetails, 40.0));
    }
}
