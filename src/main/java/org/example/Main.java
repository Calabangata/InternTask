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

        Product product1 = new Product(1, "Danish Muffin", BigDecimal.valueOf(0.52), BigDecimal.valueOf(0.80), true, "none");
        Product product2 = new Product(2, "Granny’s Cup Cake", BigDecimal.valueOf(0.38), BigDecimal.valueOf(1.20), true, "30% off");
        Product product3 = new Product(3, "Frenchy’s Croissant", BigDecimal.valueOf(0.41), BigDecimal.valueOf(0.90), false, "none");
        Product product4 = new Product(4, "Crispy chips", BigDecimal.valueOf(0.60), BigDecimal.valueOf(1.00), false, "Buy 2, get 3rd free");

        company.addProduct(product1);
        company.addProduct(product2);
        company.addProduct(product3);
        company.addProduct(product4);

        Client client1 = new Client(1, "ABC Distribution", BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.00), BigDecimal.valueOf(0.02));
        Client client2 = new Client(2, "DEF Foods", BigDecimal.valueOf(0.04), BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.02));
        Client client3 = new Client(3, "GHI Trade", BigDecimal.valueOf(0.03), BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.03));
        Client client4 = new Client(4, "JKL Kiosks", BigDecimal.valueOf(0.02), BigDecimal.valueOf(0.03), BigDecimal.valueOf(0.05));
        Client client5 = new Client(5, "MNO Vending", BigDecimal.ZERO, BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.07));

        company.addClient(client1);
        company.addClient(client2);
        company.addClient(client3);
        company.addClient(client4);
        company.addClient(client5);


//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter order details: ");
//        String input = scanner.nextLine();
//
//
//        Map<String, Map<Integer, Integer>> order = company.parseOrder(input);


        // Display parsed order details
//        for (Map.Entry<String, Map<Integer, Integer>> entry : order.entrySet()) {
//            String clientId = entry.getKey();
//
//            int intClientFromString = Integer.valueOf(clientId);
//            System.out.println("Integer from string: " + intClientFromString);
//
//            for(Client client : company.getClients()){
//                if(client.getId() == intClientFromString){
//                    System.out.println("Client: " + client.getName());
//                }
//            }
//
//            Map<Integer, Integer> orderDetails = entry.getValue();
//            System.out.println("Client ID: " + clientId);
//            System.out.println("Order Details:");
//            for (Map.Entry<Integer, Integer> item : orderDetails.entrySet()) {
//                int productId = item.getKey();
//                int quantity = item.getValue();
//                System.out.println("Product ID: " + productId + ", Quantity: " + quantity);
//            }
//        }
        System.out.println("Price before margin: " + product1.getUnitCost());
        product1.calculateStandardUnitPrice();
        System.out.println("Price after margin: " + product1.getUnitCost());



        //End of main method
    }

    // Method to parse the input string and extract order details
//    public static Map<String, Map<Integer, Integer>> parseOrder(String input) {
//        Map<String, Map<Integer, Integer>> orderDetails = new HashMap<>();
//
//        // Split input string by comma to separate elements
//        String[] elements = input.split(",");
//        // Get client ID from the first element
//        String clientId = elements[0];
//        // Create map to store order details for the client
//        Map<Integer, Integer> clientOrder = new HashMap<>();
//
//        // Iterate over elements starting from index 1
//        for (int i = 1; i < elements.length; i++) {
//            // Split element by equal sign to separate product ID from quantity
//            String[] parts = elements[i].split("=");
//            if (parts.length == 2) {
//                try {
//                    int productId = Integer.parseInt(parts[0]);
//                    int quantity = Integer.parseInt(parts[1]);
//                    clientOrder.put(productId, quantity);
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid format for product ID or quantity: " + elements[i]);
//                }
//            } else {
//                System.out.println("Invalid format for order element: " + elements[i]);
//            }
//        }
//        // Add client ID and order details to the map
//        orderDetails.put(clientId, clientOrder);
//
//        return orderDetails;
//    }
}