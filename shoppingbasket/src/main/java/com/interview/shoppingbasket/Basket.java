package com.interview.shoppingbasket;

import java.util.*;
import java.util.stream.Collectors;

public class Basket {
    private List<BasketItem> items = new ArrayList<>();

    public void add(String productCode, String productName, int quantity, Promotion promotion) {
        BasketItem basketItem = new BasketItem();
        basketItem.setProductCode(productCode);
        basketItem.setProductName(productName);
        basketItem.setQuantity(quantity);
        basketItem.setPromotion(promotion);

        items.add(basketItem);
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void consolidateItems() {
            List<BasketItem> consolidateList = new ArrayList<>();
            consolidateList.addAll(getItems());
            for (BasketItem item : getItems()) {

                BasketItem consolidateItem = new BasketItem();
                IntSummaryStatistics productSummarized = getItems().stream()
                        .filter(i -> i.getProductCode().equals(item.getProductCode()))
                        .collect(Collectors.summarizingInt((p -> p.getQuantity())));

                if (productSummarized.getCount() > 1) {
                    consolidateItem.setProductCode(item.getProductCode());
                    consolidateItem.setProductName(item.getProductName());
                    consolidateItem.setProductRetailPrice(item.getProductRetailPrice());
                    consolidateItem.setQuantity((int) productSummarized.getSum());
                    consolidateItem.setPromotion(item.getPromotion());

                    if(!consolidateList.contains(consolidateItem)){
                        consolidateList.removeAll(getItems().stream()
                                .filter(i -> i.getProductCode().equals(item.getProductCode()))
                                .collect(Collectors.toList()));
                        consolidateList.add(consolidateItem);
                    }
                }
            }
            items = new ArrayList<>();
            items.addAll(consolidateList);
    }

    public BasketItem getByCode(String code) {
        List<BasketItem> items = getItems().stream()
                .filter(i -> i.getProductCode().equals(code))
                .collect(Collectors.toList());
        return items.get(0);
    }
}
