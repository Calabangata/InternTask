package org.example;

import java.math.BigDecimal;

public class Client {

    private int id;

    private String name;

    private BigDecimal basicDiscount;

    private BigDecimal discountForOrderAbove10K;
    private BigDecimal discountForOrderAbove30K;

    public Client(int id, String name, BigDecimal basicDiscount, BigDecimal discountForOrderAbove10K, BigDecimal discountForOrderAbove30K) {
        this.id = id;
        this.name = name;
        this.basicDiscount = basicDiscount;
        this.discountForOrderAbove10K = discountForOrderAbove10K;
        this.discountForOrderAbove30K = discountForOrderAbove30K;
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

    public BigDecimal getBasicDiscount() {
        return basicDiscount;
    }

    public void setBasicDiscount(BigDecimal basicDiscount) {
        this.basicDiscount = basicDiscount;
    }

    public BigDecimal getDiscountForOrderAbove10K() {
        return discountForOrderAbove10K;
    }

    public void setDiscountForOrderAbove10K(BigDecimal discountForOrderAbove10K) {
        this.discountForOrderAbove10K = discountForOrderAbove10K;
    }

    public BigDecimal getDiscountForOrderAbove30K() {
        return discountForOrderAbove30K;
    }

    public void setDiscountForOrderAbove30K(BigDecimal discountForOrderAbove30K) {
        this.discountForOrderAbove30K = discountForOrderAbove30K;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", basicDiscount=" + basicDiscount +
                ", discountForOrderAbove10K=" + discountForOrderAbove10K +
                ", discountForOrderAbove30K=" + discountForOrderAbove30K +
                '}';
    }
}
