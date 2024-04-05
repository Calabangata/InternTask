package org.example;

import java.math.BigDecimal;
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
                continue;
            }
            break;
        }


        Map<String, Map<Integer, Integer>> order = company.parseOrder(input);

        int productId = 0;
        int quantity = 0;
        BigDecimal orderTotal = BigDecimal.valueOf(0);
        Client tmpClient = new Client();

         //Display parsed order details
        for (Map.Entry<String, Map<Integer, Integer>> entry : order.entrySet()) {
            String clientId = entry.getKey();

            //int intClientFromString = Integer.valueOf(clientId);


            for(Client client : company.getClients()){
                if(client.getId() == Integer.parseInt(clientId)){
                    System.out.println("Client: " + client.getName());
                    tmpClient = client;
                }
            }
            System.out.println(tmpClient);

            Map<Integer, Integer> orderDetails = entry.getValue();

            System.out.println("Order Details: ");
            for (Map.Entry<Integer, Integer> item : orderDetails.entrySet()) {
                productId = item.getKey();
                quantity = item.getValue();
                //System.out.println("Product ID: " + productId + ", Quantity: " + quantity);

                    for (Product product : company.getProducts()) {
                        if (product.getId() == productId) {
                            System.out.println("Ordered product: " + product.getName() + " Quantity: " + quantity);
                            System.out.println();
                            orderTotal = product.getUnitCost().multiply(BigDecimal.valueOf(quantity));
                            System.out.println("Total cost for product before discounts" + product.getName() + ": " + orderTotal);

                            if(orderTotal.doubleValue() > 30000.00){
                                //TODO
                            }

                        }
                    }
            }
        }

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