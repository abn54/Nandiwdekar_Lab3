package org.example;

import com.mongodb.client.*;
import org.bson.Document;
import java.util.Scanner;

/*
 * Project: Lab3 Database Assignment
 * Purpose Details: Handles CRUD operations for MongoDB database.
 * Course: IST 242
 * Author: Aayudh Nandiwdekar
 * Date Developed: 10/08/2024
 * Last Date Changed:
 * Revision:
 */

public class MongoCRUD {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public MongoCRUD() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("testdb");
        collection = database.getCollection("customers");
    }

    public void menu(Customer customer) {
        System.out.println("\nMongoDB Database Operations:");
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
        Document doc = new Document("id", customer.getId())
                .append("name", customer.getName())
                .append("email", customer.getEmail());
        collection.insertOne(doc);
        System.out.println("Customer created successfully.");
    }

    public void readCustomer(String id) {
        Document doc = collection.find(new Document("id", id)).first();
        if (doc != null) {
            System.out.println("Customer ID: " + doc.getString("id"));
            System.out.println("Name: " + doc.getString("name"));
            System.out.println("Email: " + doc.getString("email"));
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void updateCustomer(Customer customer) {
        Document query = new Document("id", customer.getId());
        Document update = new Document("$set", new Document("name", customer.getName())
                .append("email", customer.getEmail()));
        collection.updateOne(query, update);
        System.out.println("Customer updated successfully.");
    }

    public void deleteCustomer(String id) {
        Document query = new Document("id", id);
        collection.deleteOne(query);
        System.out.println("Customer deleted successfully.");
    }
}
