package com.interview.shoppingbasket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class BasketTest {
    @Test
    void emptyBasket() {
        Basket basket = new Basket();
        List<BasketItem> basketSize = basket.getItems();

        assertEquals(basketSize.size(), 0);
    }

    @Test
    void createBasketFullConstructor() {
        Basket basket = new Basket();
        basket.add("productCode", "myProduct", 10, Promotion.PAY_ONE_TAKE_TWO);
        List<BasketItem> basketSize = basket.getItems();

        assertEquals(basketSize.size(), 1);
        assertEquals(basketSize.get(0).getProductCode(), "productCode");
        assertEquals(basketSize.get(0).getProductName(), "myProduct");
        assertEquals(basketSize.get(0).getQuantity(), 10);
        assertEquals(basketSize.get(0).getPromotion(), Promotion.PAY_ONE_TAKE_TWO);
    }

    @Test
    void createBasketWithMultipleProducts() {
        Basket basket = new Basket();
        basket.add("productCode", "myProduct", 10, Promotion.PAY_ONE_TAKE_TWO);
        basket.add("productCode2", "myProduct2", 10, Promotion.FIFTY_PERCENT_DISCOUNT);
        basket.add("productCode3", "myProduct3", 10, Promotion.TEN_PERCENT_DISCOUNT);

        List<BasketItem> basketSize = basket.getItems();

        assertEquals(basketSize.size(),3);
        assertEquals(basketSize.get(0).getProductCode(), "productCode");
        assertEquals(basketSize.get(0).getProductName(), "myProduct");
        assertEquals(basketSize.get(0).getQuantity(), 10);
        assertEquals(basketSize.get(0).getPromotion(), Promotion.PAY_ONE_TAKE_TWO);

        assertEquals(basketSize.get(1).getProductCode(), "productCode2");
        assertEquals(basketSize.get(1).getProductName(), "myProduct2");
        assertEquals(basketSize.get(1).getQuantity(), 10);
        assertEquals(basketSize.get(1).getPromotion(), Promotion.FIFTY_PERCENT_DISCOUNT);

        assertEquals(basketSize.get(2).getProductCode(), "productCode3");
        assertEquals(basketSize.get(2).getProductName(), "myProduct3");
        assertEquals(basketSize.get(2).getQuantity(), 10);
        assertEquals(basketSize.get(2).getPromotion(), Promotion.TEN_PERCENT_DISCOUNT);
    }

    @Test
    void consolidateBasketTest() {
        Basket basket = new Basket();
        basket.add("productCode", "myProduct", 10, Promotion.TEN_PERCENT_DISCOUNT);
        basket.add("productCode1", "myProduct", 5, Promotion.PAY_ONE_TAKE_TWO);
        basket.add("productCode", "myProduct", 10, Promotion.TEN_PERCENT_DISCOUNT);
        basket.add("productCode2", "myProduct", 10, Promotion.FIFTY_PERCENT_DISCOUNT);
        basket.add("productCode", "myProduct", 10, Promotion.TEN_PERCENT_DISCOUNT);
        basket.add("productCode3", "myProduct", 20, Promotion.PAY_ONE_TAKE_TWO);

        basket.consolidateItems();
        List<BasketItem> basketSize = basket.getItems();

        assertEquals(basketSize.size(),4);

        BasketItem basketItem = basket.getByCode("productCode");
        BasketItem basketItem1 = basket.getByCode("productCode1");
        BasketItem basketItem2 = basket.getByCode("productCode2");
        BasketItem basketItem3 = basket.getByCode("productCode3");

        assertEquals(basketItem1.getProductCode(), "productCode1");
        assertEquals(basketItem1.getProductName(), "myProduct");
        assertEquals(basketItem1.getQuantity(), 5);

        assertEquals(basketItem2.getProductCode(), "productCode2");
        assertEquals(basketItem2.getProductName(), "myProduct");
        assertEquals(basketItem2.getQuantity(), 10);

        assertEquals(basketItem3.getProductCode(), "productCode3");
        assertEquals(basketItem3.getProductName(), "myProduct");
        assertEquals(basketItem3.getQuantity(), 20);

        assertEquals(basketItem.getProductCode(), "productCode");
        assertEquals(basketItem.getProductName(), "myProduct");
        assertEquals(basketItem.getQuantity(), 30);

    }
}
