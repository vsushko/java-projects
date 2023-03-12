package com.learnjava.completablefuture;

import com.learnjava.domain.Inventory;
import com.learnjava.domain.Product;
import com.learnjava.domain.ProductInfo;
import com.learnjava.domain.ProductOption;
import com.learnjava.domain.Review;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.learnjava.util.CommonUtil.stopWatch;
import static com.learnjava.util.LoggerUtil.log;

/**
 * @author vsushko
 */
public class ProductServiceUsingCompletableFuture {
    private final ProductInfoService productInfoService;
    private final ReviewService reviewService;
    private final InventoryService inventoryService;

    public ProductServiceUsingCompletableFuture(ProductInfoService productInfoService, ReviewService reviewService, InventoryService inventoryService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
        this.inventoryService = inventoryService;
    }

    public Product retrieveProductDetails(String productId) {
        stopWatch.start();

        CompletableFuture<ProductInfo> piFuture =
                CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId));
        CompletableFuture<Review> rFuture =
                CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));

        Product product = piFuture
                .thenCombine(rFuture, (productInfo, review) -> new Product(productId, productInfo, review))
                .join(); // block the thread

        stopWatch.stop();
        log("Total Time Taken : " + stopWatch.getTime());
        return new Product(productId, product.getProductInfo(), product.getReview());
    }

    public Product retrieveProductDetailsWithInventory(String productId) {
        stopWatch.start();

        CompletableFuture<ProductInfo> piFuture =
                CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId))
                        .thenApply(productInfo -> {
                            productInfo.setProductOptions(updateInventory(productInfo));
                            return productInfo;
                        });
        CompletableFuture<Review> rFuture =
                CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));

        Product product = piFuture
                .thenCombine(rFuture, (productInfo, review) -> new Product(productId, productInfo, review))
                .join(); // block the thread

        stopWatch.stop();
        log("Total Time Taken : " + stopWatch.getTime());
        return new Product(productId, product.getProductInfo(), product.getReview());
    }

    public Product retrieveProductDetailsWithInventory_approach2(String productId) {
        CompletableFuture<ProductInfo> piFuture =
                CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId))
                        .thenApply(productInfo -> {
                            productInfo.setProductOptions(updateInventory_approach2(productInfo));
                            return productInfo;
                        });
        CompletableFuture<Review> rFuture =
                CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));

        Product product = piFuture
                .thenCombine(rFuture, (productInfo, review) -> new Product(productId, productInfo, review))
                .join(); // block the thread

        stopWatch.stop();
        log("Total Time Taken : " + stopWatch.getTime());
        return new Product(productId, product.getProductInfo(), product.getReview());
    }

    private List<ProductOption> updateInventory(ProductInfo productInfo) {
        return productInfo.getProductOptions()
                .stream()
                .map(productOption -> {
                    Inventory inventory = inventoryService.retrieveInventory(productOption);
                    productOption.setInventory(inventory);
                    return productOption;
                })
                .collect(Collectors.toList());
    }

    private List<ProductOption> updateInventory_approach2(ProductInfo productInfo) {
        List<CompletableFuture<ProductOption>> productOptionList = productInfo.getProductOptions()
                .stream()
                .map(productOption -> {
                    return CompletableFuture.supplyAsync(() -> inventoryService.retrieveInventory(productOption))
                            .thenApply(inventory -> {
                                productOption.setInventory(inventory);
                                return productOption;
                            });
                })
                .collect(Collectors.toList());
        return productOptionList.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    // don't block the thread
    public CompletableFuture<Product> retrieveProductDetails_approach2(String productId) {
        CompletableFuture<ProductInfo> piFuture =
                CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId));
        CompletableFuture<Review> rFuture =
                CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));

        return piFuture.thenCombine(rFuture, (productInfo, review) -> new Product(productId, productInfo, review));
    }

    public static void main(String[] args) {
        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        InventoryService inventoryService = new InventoryService();
        ProductServiceUsingCompletableFuture productService = new ProductServiceUsingCompletableFuture(productInfoService, reviewService, inventoryService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetailsWithInventory(productId);
        log("Product is " + product);
    }
}
