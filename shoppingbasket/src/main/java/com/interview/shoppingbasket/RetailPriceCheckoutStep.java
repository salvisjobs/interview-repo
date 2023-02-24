package com.interview.shoppingbasket;

public class RetailPriceCheckoutStep implements CheckoutStep {
    private PricingService pricingService;
    private double retailTotal;

    public RetailPriceCheckoutStep(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @Override
    public void execute(CheckoutContext checkoutContext) {
        Basket basket = checkoutContext.getBasket();
        retailTotal = 0.0;
        for (BasketItem basketItem: basket.getItems()) {
            double price = pricingService.getPrice(basketItem.getProductCode());
            basketItem.setProductRetailPrice(price);
            retailTotal += applyPromotion(basketItem, price);
        }
        checkoutContext.setRetailPriceTotal(retailTotal);
    }

    public double applyPromotion(BasketItem item, double price) {
        double retailPartial = 0.0;
        switch (item.getPromotion()){
            case PAY_ONE_TAKE_TWO:
                retailPartial = item.getQuantity()*(price/2);
                if(!(item.getQuantity() % 2 == 0)){
                    retailPartial = (retailPartial - (price/2)) + price;
                }
                break;
            case TEN_PERCENT_DISCOUNT:
                retailPartial = item.getQuantity()*(price - (price * 0.1) );
                break;
            case FIFTY_PERCENT_DISCOUNT:
                retailPartial = item.getQuantity()*(price - (price * 0.5));
                break;
            default:
                retailPartial = item.getQuantity()*(price);
                break;
        }
        return retailPartial;
    }
}
