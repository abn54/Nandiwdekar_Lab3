package org.example;

import java.util.Scanner;

/*
 * Project: Lab3 Database Assignment
 * Purpose Details: Provides a user menu for interacting with different databases for customer management.
 * Course: IST 242
 * Author: Aayudh Nandiwdekar
 * Date Developed: 10/08/2024
 * Last Date Changed:
 * Revision:
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MysqlCRUD mysqlCRUD = new MysqlCRUD();
        MongoCRUD mongoCRUD = new MongoCRUD();
        RedisCRUD redisCRUD = new RedisCRUD();
        SimpleBlockchain blockchain = new SimpleBlockchain();

        // Collect customer information once
        System.out.println("Enter customer ID:");
        String id = scanner.nextLine(); // Change this to String
        System.out.println("Enter customer name:");
        String name = scanner.nextLine();
        System.out.println("Enter customer email:");
        String email = scanner.nextLine();

        // Create a Customer object
        Customer customer = new Customer(id, name, email); // Change id type in Customer constructor

        // Database operation menu
        while (true) {
            System.out.println("\nSelect a database option for CRUD operations:");
            System.out.println("1. MySQL");
            System.out.println("2. MongoDB");
            System.out.println("3. Redis");
            System.out.println("4. Blockchain");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    mysqlCRUD.menu(customer); // Call the menu for MySQL
                    break;
                case 2:
                    mongoCRUD.menu(customer);
                    break;
                case 3:
                    redisCRUD.menu(customer);
                    break;
                case 4:
                    blockchain.menu(customer);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
