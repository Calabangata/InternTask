package org.example;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static junit.framework.TestCase.assertEquals;

public class ProductTest {

    @Test
    public void testIfPriceForProductWithMarginIsCalculatedCorrectly(){
        Product product = new Product(1, "TestProduct", BigDecimal.valueOf(1.00), BigDecimal.valueOf(0.40), false, ProductPromotion.THIRTY_PERCENT_OFF);
        product.calculateStandardUnitPrice();
        BigDecimal expectedUnitPrice = BigDecimal.valueOf(1.40).setScale(2, RoundingMode.HALF_UP);
        BigDecimal expectedPromotionalPrice = BigDecimal.valueOf(0.98).setScale(5,RoundingMode.HALF_UP);
        assertEquals(product.getUnitCost(), expectedUnitPrice);
        assertEquals(product.getPromotionalPrice(), expectedPromotionalPrice);

    }
    @Test
    public void testIfPriceForProductWithMarginPercentageIsCalculatedCorrectly(){
        Product product = new Product(1, "TestProduct", BigDecimal.valueOf(1.00), BigDecimal.valueOf(0.04), true, ProductPromotion.THIRTY_PERCENT_OFF);
        product.calculateStandardUnitPrice();
        BigDecimal expectedUnitPrice = BigDecimal.valueOf(1.04).setScale(2, RoundingMode.HALF_UP);
        BigDecimal expectedPromotionalPrice = BigDecimal.valueOf(0.728).setScale(5,RoundingMode.HALF_UP);
        assertEquals(product.getUnitCost(), expectedUnitPrice);
        assertEquals(product.getPromotionalPrice(), expectedPromotionalPrice);
    }
}
