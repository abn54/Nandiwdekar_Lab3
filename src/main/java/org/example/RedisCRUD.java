package org.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import java.util.Scanner;

/*
 * Project: Lab3 Database Assignment
 * Purpose Details: Handles CRUD operations for Redis database.
 * Course: IST 242
 * Author: Aayudh Nandiwdekar
 * Date Developed: 10/08/2024
 * Last Date Changed:
 * Revision:
 */

public class RedisCRUD {
    private Jedis jedis;

    public RedisCRUD() {
        try {
            jedis = new Jedis("localhost");
            // Test connection
            jedis.ping();
            System.out.println("Connected to Redis server successfully.");
        } catch (JedisConnectionException e) {
            System.out.println("Could not connect to Redis: " + e.getMessage());
        }
    }

    public void menu(Customer customer) {
        System.out.println("\nRedis Database Operations:");
        System.out.println("1. Create Customer");
        System.out.println("2. Read Customer");
        System.out.println("3. Update Customer");
        System.out.println("4. Delete Customer");
        System.out.println("5. Return to Main Menu");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                createCustomer(customer);
                break;
            case 2:
                readCustomer(customer.getId());
                break;
            case 3:
                updateCustomer(customer);
                break;
            case 4:
                deleteCustomer(customer.getId());
                break;
            case 5:
                System.out.println("Returning to main menu...");
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu...");
                break;
        }
    }

    public void createCustomer(Customer customer) {
        jedis.hset("customer:" + customer.getId(), "name", customer.getName());
        jedis.hset("customer:" + customer.getId(), "email", customer.getEmail());
        System.out.println("Customer created successfully.");
    }

    public void readCustomer(String id) {
        if (jedis.exists("customer:" + id)) {
            String name = jedis.hget("customer:" + id, "name");
            String email = jedis.hget("customer:" + id, "email");
            System.out.println("Customer ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void updateCustomer(Customer customer) {
        if (jedis.exists("customer:" + customer.getId())) {
            jedis.hset("customer:" + customer.getId(), "name", customer.getName());
            jedis.hset("customer:" + customer.getId(), "email", customer.getEmail());
            System.out.println("Customer updated successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void deleteCustomer(String id) {
        jedis.del("customer:" + id);
        System.out.println("Customer deleted successfully.");
    }
}
