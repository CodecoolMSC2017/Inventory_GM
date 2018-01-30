package com.codecool;

import java.util.ArrayList;
import java.util.List;

public abstract class Store implements StorageCapable {

    public static List<Product> products = new ArrayList<>();
    public static StorageCapable sc;

    {
        sc = new StorageCapable() {
            @Override
            public List<Product> getAllProduct() {
                return products;
            }

            @Override
            public void storeCDProduct(String name, int price, int tracks) {
                CDProduct cd = new CDProduct(name, price, tracks);
                storeProduct(cd);
            }

            @Override
            public void storeBookProduct(String name, int price, int pages) {
                BookProduct book = new BookProduct(name, price, pages);
                storeProduct(book);
            }
        };
    }

    private void saveToXml(Product product) {

    }

    protected void storeProduct(Product product) {
        PersistentStore.storeProduct(product);
    }

    protected void createProduct(String type, String name, int price, int size) {
        try {
            if (type.toLowerCase().equals("book")) {
                //BookProduct book = new BookProduct(name, price, size);
                storeBookProduct(name, price, size);
            } else if (type.toLowerCase().equals("cd")) {
                //CDProduct cd = new CDProduct(name, price, size);
                storeCDProduct(name, price, size);

            }
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            System.out.println("Not valid type! (Try cd or book!)");
        }
    }


    public List<Product> loadProducts() {
        // XML-ből tölti a products listát
        return null;
    }

    public void store(Product product) {
        storeProduct(product);
        saveToXml(product);

    }
}
