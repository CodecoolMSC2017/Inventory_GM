package com.codecool;

public class CsvPersistentStore extends CsvStore {
    public void storeProduct(Product product) {
        getProducts().add(product);
    }
}