package com.ckm.store.service;

import com.ckm.store.domain.dto.BookDetail;
import com.ckm.store.domain.offers.Discount;
import com.ckm.store.domain.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class CheckoutTest {

    @InjectMocks
    private Checkout checkout;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private Discount discount;

    @BeforeEach
    void init(){
        ReflectionTestUtils.setField(checkout, "discounts", Collections.singletonList(discount));
    }
    
    @Test
    public void testCheckoutWithEmptyListofBooksThenReturnZero(){
        double total = checkout.checkout(Collections.emptyList());
        Assertions.assertEquals(0, total);
    }

    @Test
    public void testCheckoutWithOneInvalidbookThenExceptionThrown(){
        doReturn(Optional.empty()).when(bookRepository).findByName("my book");
        Assertions.assertThrows(RuntimeException.class, () -> checkout.checkout(Collections.singletonList("my book")));
    }

    @Test
    public void testCheckoutWithOnebookThenReturnPrice(){
        BookDetail bookDetail = new BookDetail("my book", 20.0, 1992);
        Optional<BookDetail> bookDetail1 = Optional.of(bookDetail);

        doReturn(true).when(discount).isApplicable();
        doReturn(20.0).when(discount).apply(Collections.singletonList(bookDetail), 20.0);
        doReturn(bookDetail1).when(bookRepository).findByName("my book");
        double total = checkout.checkout(Collections.singletonList("my book"));

        Assertions.assertEquals(20, total);
    }

    @Test
    public void testCheckoutWithTwobookThenReturnPrice(){
        BookDetail bookDetail1 = new BookDetail("my book 1", 20.0, 1992);
        BookDetail bookDetail2 = new BookDetail("my book 2", 30.0, 1994);
        Optional<BookDetail> obookDetail1 = Optional.of(bookDetail1);
        Optional<BookDetail> obookDetail2 = Optional.of(bookDetail2);
        List<BookDetail> bookDetails = new ArrayList<>();
        bookDetails.add(bookDetail1);
        bookDetails.add(bookDetail2);

        doReturn(true).doReturn(true).when(discount).isApplicable();
        doReturn(50.0).when(discount).apply(bookDetails, 50.0);
        doReturn(obookDetail1).when(bookRepository).findByName("my book 1");
        doReturn(obookDetail2).when(bookRepository).findByName("my book 2");
        double total = checkout.checkout(List.of("my book 1", "my book 2"));

        Assertions.assertEquals(50, total);
    }
}
