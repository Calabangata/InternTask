package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        Company company = new Company();
         //Create and add products
        company.fillCompanyWithData();

        Scanner scanner = new Scanner(System.in);
        String input = "";
        while(true) {
            System.out.println("Enter order details: ");
            input = scanner.nextLine();
            if(!isValidInputFormat(input)){
                System.out.println("Invalid input format. Please try again!");
                scanner.nextLine();
                continue;
            }
            break;
        }


        Map<String, Map<Integer, Integer>> order = company.parseOrder(input);

        company.calculateClientOrder(company, order);



    }

    public static boolean isValidInputFormat(String input) {
        String[] parts = input.split(",");
        if (parts.length < 2) {
            return false; // Input must have at least one product
        }

        try{
            int clientId = Integer.parseInt(parts[0]);
            if(clientId <= 0){
                return false;
            }
        } catch (NumberFormatException e){
            return false;
        }

        for (int i = 1; i < parts.length; i++) {
            String[] productInfo = parts[i].split("=");
            if (productInfo.length != 2) {
                return false; // Each product info must have a product ID and quantity
            }
            try {
                int productId = Integer.parseInt(productInfo[0]);
                int quantity = Integer.parseInt(productInfo[1]);
                if (productId <= 0 || quantity <= 0) {
                    return false; // Product ID and quantity must be positive
                }
            } catch (NumberFormatException e) {
                return false; // Unable to parse product ID or quantity as integers
            }
        }
        return true;
    }

}