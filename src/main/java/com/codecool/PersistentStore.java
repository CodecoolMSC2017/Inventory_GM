package com.codecool;

public class PersistentStore {

    public static void storeProduct(Product product) {
        Store.sc.getAllProduct().add(product);
    }
}
