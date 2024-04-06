package org.example;

import junit.framework.TestCase;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


public class CompanyTest {

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

}
