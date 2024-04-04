package org.example;

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

    //public Client findClient()
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
