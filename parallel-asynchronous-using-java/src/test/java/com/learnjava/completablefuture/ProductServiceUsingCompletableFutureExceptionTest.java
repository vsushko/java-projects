package com.learnjava.completablefuture;

import com.learnjava.domain.Product;
import com.learnjava.domain.ProductOption;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceUsingCompletableFutureExceptionTest {
    @Mock
    private ProductInfoService pisMock;
    @Mock
    private ReviewService rssMock;
    @Mock
    private InventoryService isMock;

    @InjectMocks
    private ProductServiceUsingCompletableFuture pscf;

    @Test
    void retrieveProductDetails_reviewServiceError() {
        //given
        String productId = "ABC123";
        when(pisMock.retrieveProductInfo(any())).thenCallRealMethod();
        when(rssMock.retrieveReviews(any())).thenThrow(new RuntimeException("Exception Occurred"));
        when(isMock.retrieveInventory(any(ProductOption.class))).thenCallRealMethod();

        //when
        Product product = pscf.retrieveProductDetailsWithInventory_approach2(productId);

        //then
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        product.getProductInfo().getProductOptions().forEach(productOption -> assertNotNull(productOption.getInventory()));
        assertNotNull(product.getReview());
        assertEquals(0, product.getReview().getNoOfReviews());

        long count = product.getProductInfo().getProductOptions().stream().count();
        System.out.println("count : " + count);
    }

    @Test
    void retrieveProductDetails_productInfoServiceError() {
        //given
        String productId = "ABC123";
        when(pisMock.retrieveProductInfo(any())).thenThrow(new RuntimeException("Exception Occurred"));
        when(rssMock.retrieveReviews(any())).thenCallRealMethod();

        //when
        assertThrows(RuntimeException.class, () -> pscf.retrieveProductDetailsWithInventory_approach2(productId));
    }

    @Test
    void retrieveProductDetails_inteventoryServiceError() {
        //given
        String productId = "ABC123";
        when(pisMock.retrieveProductInfo(any())).thenCallRealMethod();
        when(rssMock.retrieveReviews(any())).thenCallRealMethod();
        when(isMock.retrieveInventory(any(ProductOption.class))).thenThrow(new RuntimeException("Exception Occurred"));

        //when

        //then
        assertThrows(RuntimeException.class, () -> pscf.retrieveProductDetailsWithInventory_approach2(productId));
    }
}