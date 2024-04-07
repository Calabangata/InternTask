package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Company company = new Company();
         //Create and add products
        company.fillCompanyWithData();

        //INPUT USING FILE
        String input = "";
        File file = new File("input.txt");
        try{
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                input = scanner.nextLine();
                if(!company.isValidInputFormat(input)){
                    System.out.println("Invalid input format. Please check the file");
                }
            }
            scanner.close();
        } catch (FileNotFoundException e){
            System.out.println("File not found: " + e.getMessage());
        }

        //INPUT USING CONSOLE
//        Scanner scanner = new Scanner(System.in);
//        String input = "";
//        while(true) {
//            System.out.println("Enter order details: ");
//            input = scanner.nextLine();
//            if(!company.isValidInputFormat(input)){
//                System.out.println("Invalid input format. Please try again!");
//                continue;
//            }
//            break;
//        }
        Map<String, Map<Integer, Integer>> order = company.parseOrder(input);
        company.calculateClientOrder(company, order);
    }
}