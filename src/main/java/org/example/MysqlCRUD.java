package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
 * Project: Lab3 Database Assignment
 * Purpose Details: Handles CRUD operations for MySQL database.
 * Course: IST 242
 * Author: Aayudh Nandiwdekar
 * Date Developed: 10/08/2024
 * Last Date Changed:
 * Revision:
 */

public class MysqlCRUD {
    private String url = "jdbc:mysql://localhost:3306/testdb"; // Ensure this database exists
    private String user = "root"; // Your MySQL username
    private String password = "IST888IST888"; // Your MySQL password
    private Connection connection;

    public MysqlCRUD() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL successfully.");
        } catch (SQLException e) {
            System.out.println("Could not connect to MySQL: " + e.getMessage());
        }
    }

    public void menu(Customer customer) {
        System.out.println("\nMySQL Database Operations:");
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
                createCustomer(customer); // Create a new customer
                break;
            case 2:
                Customer retrievedCustomer = readCustomer(customer.getId()); // Read the customer
                if (retrievedCustomer != null) {
                    System.out.println("Customer Details: " + retrievedCustomer);
                }
                break;
            case 3:
                System.out.println("Enter new customer name:");
                String newName = scanner.nextLine();
                System.out.println("Enter new customer email:");
                String newEmail = scanner.nextLine();
                customer.setName(newName);
                customer.setEmail(newEmail);
                updateCustomer(customer); // Update the customer
                break;
            case 4:
                deleteCustomer(customer.getId()); // Delete the customer
                break;
            case 5:
                System.out.println("Returning to main menu...");
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu...");
                break;
        }
    }

    // Create
    public void createCustomer(Customer customer) {
        String query = "INSERT INTO customers (id, name, email) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, customer.getId());
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getEmail());
            pstmt.executeUpdate();
            System.out.println("Customer created successfully.");
        } catch (SQLException e) {
            System.out.println("Could not create customer: " + e.getMessage());
        }
    }

    // Read
    public Customer readCustomer(String id) {
        String query = "SELECT * FROM customers WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                return new Customer(id, name, email);
            } else {
                System.out.println("Customer not found.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Could not read customer: " + e.getMessage());
            return null;
        }
    }

    // Update
    public void updateCustomer(Customer customer) {
        String query = "UPDATE customers SET name = ?, email = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setString(3, customer.getId());
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Customer updated successfully.");
            } else {
                System.out.println("Customer not found.");
            }
        } catch (SQLException e) {
            System.out.println("Could not update customer: " + e.getMessage());
        }
    }

    // Delete
    public void deleteCustomer(String id) {
        String query = "DELETE FROM customers WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Customer deleted successfully.");
            } else {
                System.out.println("Customer not found.");
            }
        } catch (SQLException e) {
            System.out.println("Could not delete customer: " + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("MySQL connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Could not close MySQL connection: " + e.getMessage());
        }
    }
}
