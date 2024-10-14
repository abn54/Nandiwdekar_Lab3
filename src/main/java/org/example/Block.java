package org.example;

/*
 * Project: Lab3 Database Assignment
 * Purpose Details: Represents a block in the blockchain containing customer data.
 * Course: IST 242
 * Author: Aayudh Nandiwdekar
 * Date Developed: 10/08/2024
 * Last Date Changed:
 * Revision:
 */

public class Block {
    private int index;
    private String previousHash;
    private String data;
    private String hash;

    public Block(int index, String previousHash, String data) {
        this.index = index;
        this.previousHash = previousHash;
        this.data = data;
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String hashData = index + previousHash + data;
        return Integer.toString(hashData.hashCode());
    }

    public int getIndex() {
        return index;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getData() {
        return data;
    }

    public String getHash() {
        return hash;
    }

    @Override
    public String toString() {
        return "Block{" +
                "index=" + index +
                ", previousHash='" + previousHash + '\'' +
                ", data='" + data + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
