package com.interview.shoppingbasket;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PromotionCheckoutStep implements CheckoutStep{

    private PromotionsService promotionsService;

    PromotionCheckoutStep(PromotionsService promotionsService) { this.promotionsService = promotionsService;}
    @Override
    public void execute(CheckoutContext checkoutContext) {
        Basket basket = checkoutContext.getBasket();
        List<Promotion> promotions = promotionsService.getPromotions(basket);
    }
}
