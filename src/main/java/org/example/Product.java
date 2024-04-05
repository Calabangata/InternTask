package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {

    private int id;

    private String name;

    private BigDecimal unitCost;

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
    }

    public void calculateStandardUnitPrice(){
        if(!isMarginPercentage) {
            this.unitCost = unitCost.add(margin);
        } else {
            BigDecimal percentToAdd = this.getUnitCost().multiply(BigDecimal.valueOf(margin.doubleValue()));
            this.unitCost = unitCost.add(percentToAdd);
        }
        unitCost = unitCost.setScale(2, RoundingMode.HALF_UP);

        BigDecimal finalUnitPrice = applyPromotion(unitCost);

        // Update unit cost
        unitCost = finalUnitPrice.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal applyPromotion(BigDecimal standardPrice){

        switch (productPromotion){
            case NONE:
                return standardPrice;
            case THIRTY_PERCENT_OFF:
                BigDecimal discount = standardPrice.multiply(BigDecimal.valueOf(0.3));
                return standardPrice.subtract(discount);
            case BUY_TWO_GET_THIRD_FREE:
                //TODO
                // Calculate total cost considering free items
//                int quantityOrdered = 3; // Total quantity required for promotion
//                int paidQuantity = quantityOrdered - 1; // Calculate number of items to be paid (2 out of 3)
//                BigDecimal totalCost = standardPrice.multiply(BigDecimal.valueOf(paidQuantity));
                // Calculate final unit cost
//                return totalCost.divide(BigDecimal.valueOf(quantityOrdered), 2, RoundingMode.HALF_UP);
                return standardPrice;
            default: throw new IllegalArgumentException("Invalid promotion: " + productPromotion);
        }


        //return BigDecimal.valueOf(0);
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
                ", price=" + unitCost +
                ", margin=" + margin +
                ", isMarginPercentage=" + isMarginPercentage +
                ", productPromotion='" + productPromotion + '\'' +
                '}';
    }
}
