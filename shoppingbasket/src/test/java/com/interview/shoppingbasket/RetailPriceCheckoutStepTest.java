package com.interview.shoppingbasket;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RetailPriceCheckoutStepTest {

    PricingService pricingService;
    CheckoutContext checkoutContext;
    Basket basket;

    @BeforeEach
    void setup() {
        pricingService = Mockito.mock(PricingService.class);
        checkoutContext = Mockito.mock(CheckoutContext.class);
        basket = new Basket();

        when(checkoutContext.getBasket()).thenReturn(basket);
    }

    @Test
    void setPriceZeroForEmptyBasket() {

        RetailPriceCheckoutStep retailPriceCheckoutStep = new RetailPriceCheckoutStep(pricingService);

        retailPriceCheckoutStep.execute(checkoutContext);

        Mockito.verify(checkoutContext).setRetailPriceTotal(0.0);
    }

    @Test
    void setPriceOfProductToBasketItem() {

        basket.add("product1", "myproduct1", 10, Promotion.NONE);
        basket.add("product2", "myproduct2", 10, Promotion.NONE);

        when(pricingService.getPrice("product1")).thenReturn(3.99);
        when(pricingService.getPrice("product2")).thenReturn(2.0);
        RetailPriceCheckoutStep retailPriceCheckoutStep = new RetailPriceCheckoutStep(pricingService);

        retailPriceCheckoutStep.execute(checkoutContext);
        Mockito.verify(checkoutContext).setRetailPriceTotal(3.99*10+2*10);

    }

    @Test
    void setPriceOfProductWithDiscountToBasketItem() {

        basket.add("product1", "myproduct", 10, Promotion.PAY_ONE_TAKE_TWO);
        basket.add("product1", "myproduct", 5, Promotion.PAY_ONE_TAKE_TWO);
        basket.add("product2", "myproduct2", 10, Promotion.NONE);
        basket.add("product3", "myproduct3", 10, Promotion.TEN_PERCENT_DISCOUNT);
        basket.add("product4", "myproduct4", 10, Promotion.FIFTY_PERCENT_DISCOUNT);

        basket.consolidateItems();

        when(pricingService.getPrice("product1")).thenReturn(10.0);
        when(pricingService.getPrice("product2")).thenReturn(3.99);
        when(pricingService.getPrice("product3")).thenReturn(2.0);
        when(pricingService.getPrice("product4")).thenReturn(2.0);
        RetailPriceCheckoutStep retailPriceCheckoutStep = new RetailPriceCheckoutStep(pricingService);

        retailPriceCheckoutStep.execute(checkoutContext);
        Mockito.verify(checkoutContext).setRetailPriceTotal(5*14+10+3.99*10+1.8*10+1*10);

    }

}
