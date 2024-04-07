package org.example;

import junit.framework.TestCase;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import static junit.framework.TestCase.*;


public class CompanyTest {
    //testing the validation of input
    @Test
    public void testInputFormat_ClientIdIsMissing(){
        Company company = new Company();
        assertFalse(company.isValidInputFormat("1=10"));
    }
    @Test
    public void testInputFormat_OrderOfProductsIsMissing(){
        Company company = new Company();
        assertFalse(company.isValidInputFormat("1"));
    }
    @Test
    public void testInputFormat_ClientIdIsNotNumber(){
        Company company = new Company();
        assertFalse(company.isValidInputFormat("ab,1=10"));
    }
    @Test
    public void testInputFormat_ClientIdIsNegative(){
        Company company = new Company();
        assertFalse(company.isValidInputFormat("-1,1=10"));
    }
    @Test
    public void testInputFormat_ProductIsNotNumber(){
        Company company = new Company();
        assertFalse(company.isValidInputFormat("2,h=10"));
    }
    @Test
    public void testInputFormat_ProductQuantityIsNegative(){
        Company company = new Company();
        assertFalse(company.isValidInputFormat("1,1=-10"));
    }

    @Test
    public void testInputFormat_CorrectOrder(){
        Company company = new Company();
        company.fillCompanyWithData();
        assertTrue(company.isValidInputFormat("2,1=200,3=4000"));
    }
    @Test
    public void testInputFormat_ProductIdIsHigherThanAvailableProductsList(){
        Company company = new Company();
        company.fillCompanyWithData();//4 products are added
        assertFalse(company.isValidInputFormat("2,1=200,5=4000"));
    }
    //Testing the volume discount method
    @Test
    public void testIfVolumeDiscountAppliesForOrderBiggerThan30K(){
        Company company = new Company();
        Client client = new Client(1, "TestClient", BigDecimal.valueOf(0.02), BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.07));
        BigDecimal actualOrderTotal = company.setVolumeDiscountToOrder(BigDecimal.valueOf(30001), client);
        BigDecimal expectedOrderTotal = BigDecimal.valueOf(27900.93).setScale(2, RoundingMode.HALF_UP);
        assertEquals(expectedOrderTotal, actualOrderTotal);
    }
    @Test
    public void testIfVolumeDiscountAppliesForOrderBiggerThan10K(){
        Company company = new Company();
        Client client = new Client(1, "TestClient", BigDecimal.valueOf(0.02), BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.07));
        BigDecimal actualOrderTotal = company.setVolumeDiscountToOrder(BigDecimal.valueOf(10001), client);
        BigDecimal expectedOrderTotal = BigDecimal.valueOf(9500.95).setScale(2, RoundingMode.HALF_UP);
        assertEquals(expectedOrderTotal, actualOrderTotal);
    }
    @Test
    public void testIfOrderTotalIsTheSameForOrderIsNotMoreThan10K(){
        Company company = new Company();
        Client client = new Client(1, "TestClient", BigDecimal.valueOf(0.02), BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.07));
        BigDecimal actualOrderTotal = company.setVolumeDiscountToOrder(BigDecimal.valueOf(10000), client);
        BigDecimal expectedOrderTotal = BigDecimal.valueOf(10000).setScale(2, RoundingMode.HALF_UP);
        assertEquals(expectedOrderTotal, actualOrderTotal);
    }
    @Test
    public void testIfTheClientOrderIsCalculatedCorrectly(){
        Company company = new Company();
        company.fillCompanyWithData();
        Map<String, Map<Integer, Integer>> order = company.parseOrder("5,1=10000,2=500,4=20000"); //Order Total should be 28856.41
        BigDecimal actualOrderTotal =  company.calculateClientOrder(company, order);
        BigDecimal expectedOrderTotal = BigDecimal.valueOf(28856.41).setScale(2, RoundingMode.HALF_UP);
        assertEquals(expectedOrderTotal, actualOrderTotal);
    }

}
