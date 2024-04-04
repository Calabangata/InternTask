package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {

    private int id;

    private String name;

    private BigDecimal unitCost;

    private BigDecimal margin;

    private Boolean isMarginPercentage;

    private String productPromotion;

    public Product(int id, String name, BigDecimal unitCost, BigDecimal margin, Boolean isMarginPercentage, String productPromotion) {
        this.id = id;
        this.name = name;
        this.unitCost = unitCost;
        this.margin = margin;
        this.isMarginPercentage = isMarginPercentage;
        this.productPromotion = productPromotion;
    }

    public BigDecimal calculateStandardUnitPrice(){
        if(isMarginPercentage == false) {
            this.unitCost = unitCost.add(margin);
        } else {
            BigDecimal percentToAdd = this.getUnitCost().multiply(BigDecimal.valueOf(margin.doubleValue()));

            this.unitCost = unitCost.add(percentToAdd);
        }
        unitCost = unitCost.setScale(2, RoundingMode.HALF_UP);
        return unitCost;
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

    public String getProductPromotion() {
        return productPromotion;
    }

    public void setProductPromotion(String productPromotion) {
        this.productPromotion = productPromotion;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + unitCost +
                ", margin=" + margin +
                ", isMarginPercentage=" + isMarginPercentage +
                ", productPromotion='" + productPromotion + '\'' +
                '}';
    }
}