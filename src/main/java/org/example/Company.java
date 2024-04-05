package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Company {

    private final String name = "EveryDay Snacks";

    private List<Product> products;

    private List<Client> clients;

    public Company() {
        this.products = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void addProduct(Product product){
        if(!products.contains(product)){
            products.add(product);
            product.calculateStandardUnitPrice();
        }

    }

    public void addClient(Client client){
        if(!clients.contains(client)){
            clients.add(client);
        }
    }

    public void removeProduct(Product product){
            products.remove(product);
    }

    public void removeClient(Client client){
            clients.remove(client);
    }

    public void fillCompanyWithData(){
        Product product1 = new Product(1, "Danish Muffin", BigDecimal.valueOf(0.52), BigDecimal.valueOf(0.80), false, ProductPromotion.NONE);
        Product product2 = new Product(2, "Granny’s Cup Cake", BigDecimal.valueOf(0.38), BigDecimal.valueOf(1.20), true, ProductPromotion.THIRTY_PERCENT_OFF);
        Product product3 = new Product(3, "Frenchy’s Croissant", BigDecimal.valueOf(0.41), BigDecimal.valueOf(0.90), false, ProductPromotion.NONE);
        Product product4 = new Product(4, "Crispy chips", BigDecimal.valueOf(0.60), BigDecimal.valueOf(1.00), false, ProductPromotion.BUY_TWO_GET_THIRD_FREE);

        this.addProduct(product1);
        this.addProduct(product2);
        this.addProduct(product3);
        this.addProduct(product4);

        Client client1 = new Client(1, "ABC Distribution", BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.00), BigDecimal.valueOf(0.02));
        Client client2 = new Client(2, "DEF Foods", BigDecimal.valueOf(0.04), BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.02));
        Client client3 = new Client(3, "GHI Trade", BigDecimal.valueOf(0.03), BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.03));
        Client client4 = new Client(4, "JKL Kiosks", BigDecimal.valueOf(0.02), BigDecimal.valueOf(0.03), BigDecimal.valueOf(0.05));
        Client client5 = new Client(5, "MNO Vending", BigDecimal.ZERO, BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.07));

        this.addClient(client1);
        this.addClient(client2);
        this.addClient(client3);
        this.addClient(client4);
        this.addClient(client5);
    }

    public Map<String, Map<Integer, Integer>> parseOrder(String input) {
        Map<String, Map<Integer, Integer>> orderDetails = new HashMap<>();

        // Split input string by comma to separate elements
        String[] elements = input.split(",");
        // Get client ID from the first element
        String clientId = elements[0];
        // Create map to store order details for the client
        Map<Integer, Integer> clientOrder = new HashMap<>();

        // Iterate over elements starting from index 1
        for (int i = 1; i < elements.length; i++) {
            // Split element by equal sign to separate product ID from quantity
            String[] parts = elements[i].split("=");
            if (parts.length == 2) {
                try {
                    int productId = Integer.parseInt(parts[0]);
                    int quantity = Integer.parseInt(parts[1]);
                    clientOrder.put(productId, quantity);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid format for product ID or quantity: " + elements[i]);
                }
            } else {
                System.out.println("Invalid format for order element: " + elements[i]);
            }
        }
        // Add client ID and order details to the map
        orderDetails.put(clientId, clientOrder);

        return orderDetails;
    }
}
