package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Company {

    private final String name = "Everyday Snacks";

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
        Product product1 = new Product(1, "Danish Muffin", BigDecimal.valueOf(0.52), BigDecimal.valueOf(0.80), true, ProductPromotion.NONE);
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

        // Splitting input string by comma to separate elements
        String[] elements = input.split(",");
        // Getting client ID from the first element
        String clientId = elements[0];
        // Using 2 maps. The first map's key is the client id, the value is another map, whose key
        // represents the product id, and the value is the quantity ordered
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

    public BigDecimal calculateClientOrder(Company company, Map<String, Map<Integer, Integer>> order) {
        int i = 1;
        int cnt = 0;
        int productId = 0;
        int quantity = 0;
        BigDecimal orderTotal = BigDecimal.valueOf(0);
        BigDecimal lineTotal = BigDecimal.valueOf(0);
        BigDecimal buyTwoGetThirdFreeDiscountPrice;
        BigDecimal basicClientDiscount;
        Client tmpClient = new Client();
        BigDecimal totalBeforeDiscounts = BigDecimal.valueOf(0);

        //Display parsed order details
        for (Map.Entry<String, Map<Integer, Integer>> entry : order.entrySet()) {
            String clientId = entry.getKey();
            for(Client client : company.getClients()){
                if(client.getId() == Integer.parseInt(clientId)){
                    tmpClient = client;
                }
            }

            System.out.println("Client: " + tmpClient.getName());
            System.out.println();
            System.out.println("Product\t\t\t\t|  Quantity  | Standard Unit Price | Promotional Unit Price | Line Total");
            System.out.println("------------------------------------------------------------------------------------------------------");

            Map<Integer, Integer> orderDetails = entry.getValue();

            for (Map.Entry<Integer, Integer> item : orderDetails.entrySet()) {
                productId = item.getKey();
                quantity = item.getValue();

                for (Product product : company.getProducts()) {
                    if (product.getId() == productId) {

                        if(product.getPromotionalPrice().doubleValue() == 0){
                            lineTotal = product.getUnitCost().multiply(BigDecimal.valueOf(quantity));
                        } else {
                            lineTotal = product.getPromotionalPrice().multiply(BigDecimal.valueOf(quantity));
                        }
                        //IMPLEMENT BUY 2 GET 3RD FREE LOGIC
                        if(product.getProductPromotion() == ProductPromotion.BUY_TWO_GET_THIRD_FREE){
                            while (i <= quantity){
                                if ( i % 3 == 0 ) {
                                    cnt++;
                                }
                                i++;
                            }
                            buyTwoGetThirdFreeDiscountPrice = product.getUnitCost().multiply(BigDecimal.valueOf(cnt));
                            lineTotal = lineTotal.subtract(buyTwoGetThirdFreeDiscountPrice);
                        }

                        lineTotal = lineTotal.setScale(2, RoundingMode.HALF_UP);
                        orderTotal = orderTotal.add(lineTotal);
                        totalBeforeDiscounts = orderTotal;

                        System.out.printf("%-15s  %-15s  %-15s  %-25s %-25s\n", product.getName() + "\t\t", quantity,
                                "EUR " + product.getUnitCost(), (product.getPromotionalPrice().doubleValue() > 0 ? "EUR " + product.getPromotionalPrice() : ""),"EUR " + lineTotal);
                    }
                }
            }
            //ACCOUNT BASIC CLIENT DISCOUNT
            basicClientDiscount = orderTotal.multiply(tmpClient.getBasicDiscount());
            orderTotal = orderTotal.subtract(basicClientDiscount);

            System.out.println("\n\nTotal Before Client Discounts:\t\t EUR " + totalBeforeDiscounts + "\n");

            //ACCOUNT VOLUME DISCOUNT
            orderTotal = company.setVolumeDiscountToOrder(orderTotal, tmpClient).setScale(2, RoundingMode.HALF_UP);

            System.out.println("Order Total Amount: \t\t\t\t" + "EUR " + orderTotal);



        }
    return orderTotal;
    }

    public BigDecimal setVolumeDiscountToOrder(BigDecimal orderTotal, Client tmpClient) {
        if(orderTotal.doubleValue() <= 10000){
            System.out.println("There is no Additional Discount For This Order!\n");

        } else if(orderTotal.doubleValue() > 30000.00){

            BigDecimal volumeDiscount = orderTotal.multiply(tmpClient.getDiscountForOrderAbove30K());
            orderTotal = orderTotal.subtract(volumeDiscount).setScale(2, RoundingMode.HALF_UP);
            System.out.println("Additional Volume Discount at " + tmpClient.getDiscountForOrderAbove30K().multiply(BigDecimal.valueOf(100)) + "%: EUR " + volumeDiscount.setScale(2,RoundingMode.HALF_UP) + "\n");

        } else if(orderTotal.doubleValue() > 10000.00){
            BigDecimal volumeDiscount = orderTotal.multiply(tmpClient.getDiscountForOrderAbove10K());
            orderTotal = orderTotal.subtract(volumeDiscount).setScale(2, RoundingMode.HALF_UP);
            System.out.println("Additional Volume Discount at " + tmpClient.getDiscountForOrderAbove10K().multiply(BigDecimal.valueOf(100)) + "%: EUR " + volumeDiscount.setScale(2,RoundingMode.HALF_UP) + "\n");
        }
        return orderTotal.setScale(2, RoundingMode.HALF_UP);
    }

    public boolean isValidInputFormat(String input) {
        String[] parts = input.split(",");
        if (parts.length < 2) {
            return false; // Input must have at least one product
        }
        try{
            int clientId = Integer.parseInt(parts[0]);
            if(clientId <= 0 || clientId > this.getClients().size()){
                //Client must not be larger than the available clients
                //assuming that the id's start from 1 and after new client is
                //added, the id increments
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
                if (productId <= 0 || quantity <= 0 || productId > products.size()) {
                    return false; // Product ID and quantity must be positive
                    // productId can not be larger than the available products
                    //assuming that product id's go from 1,2,3...and so on
                }
            } catch (NumberFormatException e) {
                return false; // Unable to parse product ID or quantity as integers
            }
        }
        return true;
    }
}
