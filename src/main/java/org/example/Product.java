package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {

    private int id;

    private String name;

    private BigDecimal unitCost;

    private BigDecimal promotionalPrice;

    private BigDecimal margin;

    private Boolean isMarginPercentage;

    private ProductPromotion productPromotion;

    public Product(int id, String name, BigDecimal unitCost, BigDecimal margin, Boolean isMarginPercentage, ProductPromotion productPromotion) {
        this.id = id;
        this.name = name;
        this.unitCost = unitCost;
        this.margin = margin;
        this.isMarginPercentage = isMarginPercentage;
        this.productPromotion = productPromotion;
        this.promotionalPrice = BigDecimal.valueOf(0);
    }

    public void calculateStandardUnitPrice(){

        if(!isMarginPercentage) {
            this.unitCost = unitCost.add(margin);
        } else {
            BigDecimal percentToAdd = this.getUnitCost().multiply(BigDecimal.valueOf(margin.doubleValue()));
            this.unitCost = unitCost.add(percentToAdd);
        }
        unitCost = unitCost.setScale(2, RoundingMode.HALF_UP);
        applyPromotion(unitCost);
        promotionalPrice = promotionalPrice.setScale(5, RoundingMode.HALF_UP);
    }

    public void applyPromotion(BigDecimal standardPrice){

        switch (productPromotion){
            case NONE:
                return;
            case THIRTY_PERCENT_OFF:
                BigDecimal discount = standardPrice.multiply(BigDecimal.valueOf(0.3));
                promotionalPrice = standardPrice.subtract(discount);
                return;
            case BUY_TWO_GET_THIRD_FREE:
                //This would not be implemented here
                return;
            default: throw new IllegalArgumentException("Invalid promotion: " + productPromotion);
        }
    }

    public BigDecimal getPromotionalPrice() {
        return promotionalPrice;
    }

    public void setPromotionalPrice(BigDecimal promotionalPrice) {
        this.promotionalPrice = promotionalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public BigDecimal getMargin() {
        return margin;
    }

    public void setMargin(BigDecimal margin) {
        this.margin = margin;
    }

    public Boolean getMarginPercentage() {
        return isMarginPercentage;
    }

    public void setMarginPercentage(Boolean marginPercentage) {
        isMarginPercentage = marginPercentage;
    }

    public ProductPromotion getProductPromotion() {
        return productPromotion;
    }

    public void setProductPromotion(ProductPromotion productPromotion) {
        this.productPromotion = productPromotion;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unitCost=" + unitCost +
                ", promotionalPrice=" + promotionalPrice +
                ", margin=" + margin +
                ", isMarginPercentage=" + isMarginPercentage +
                ", productPromotion=" + productPromotion +
                '}';
    }
}
