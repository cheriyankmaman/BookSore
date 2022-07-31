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
public class FinalPriceBasedDiscountTest {

    @InjectMocks
    private FinalPriceBasedDiscount finalPriceBasedDiscount;

    @BeforeEach
    void init(){
        ReflectionTestUtils.setField(finalPriceBasedDiscount, "cutoff", 30.0);
        ReflectionTestUtils.setField(finalPriceBasedDiscount, "percent", 5);
        ReflectionTestUtils.setField(finalPriceBasedDiscount, "enabled", true);
    }

    @Test
    public void testFinalPriceBasedDiscountIsEnabledThenReturnTrue(){
        Assertions.assertTrue(finalPriceBasedDiscount.isApplicable());
    }

    @Test
    public void testFinalPriceBasedDiscountOrderThenReturn10(){
        Assertions.assertEquals(10, finalPriceBasedDiscount.order());
    }

    @Test
    public void testFinalPriceBasedDiscountLogicThenReturnTotal(){
        BookDetail bookDetail = new BookDetail("my book", 20.0, 2000);
        List<BookDetail> bookDetails = Collections.singletonList(bookDetail);

        Assertions.assertEquals(20.0, finalPriceBasedDiscount.apply(bookDetails, 20.0));
    }

    @Test
    public void testFinalPriceBasedDiscountLogicThenReturnTotalAfterDiscount(){
        BookDetail bookDetail = new BookDetail("my book", 40.0, 2000);
        List<BookDetail> bookDetails = Collections.singletonList(bookDetail);

        Assertions.assertEquals(38.0, finalPriceBasedDiscount.apply(bookDetails, 40.0));
    }

}
