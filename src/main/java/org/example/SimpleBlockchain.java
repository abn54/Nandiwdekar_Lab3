package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Project: Lab3 Database Assignment
 * Purpose Details: Manages a simple blockchain for storing customer data.
 * Course: IST 242
 * Author: Aayudh Nandiwdekar
 * Date Developed: 10/08/2024
 * Last Date Changed: 10/14/2024
 * Revision: 1
 */

public class SimpleBlockchain {
    private List<Block> blockchain;

    public SimpleBlockchain() {
        blockchain = new ArrayList<>();
        blockchain.add(new Block(0, "0", "Genesis Block"));
    }

    public void menu(Customer customer) {
        System.out.println("\nBlockchain Operations:");
        System.out.println("1. Add Customer Block");
        System.out.println("2. View Blockchain");
        System.out.println("3. Return to Main Menu");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                addBlock(customer);
                break;
            case 2:
                displayBlockchain();
                break;
            case 3:
                System.out.println("Returning to main menu...");
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu...");
                break;
        }
    }

    public void addBlock(Customer customer) {
        int index = blockchain.size();
        String previousHash = blockchain.get(index - 1).getHash();
        String data = "Customer ID: " + customer.getId() + ", Name: " + customer.getName() + ", Email: " + customer.getEmail();
        Block newBlock = new Block(index, previousHash, data);
        blockchain.add(newBlock);
        System.out.println("New block added to the blockchain.");
    }

    public void displayBlockchain() {
        for (Block block : blockchain) {
            System.out.println(block);
        }
    }
}
