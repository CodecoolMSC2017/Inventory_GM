package com.codecool;

import java.util.List;

public class StoreManager {
    public List<Product> products;

    public StoreManager() {
        this.products = Store.products;
    }

    public String addStorage(StorageCapable storage) {
        return null;
    }

    public void addCDProduct(String name, int price, int tracks) {
        Store.sc.storeCDProduct(name, price, tracks);
    }

    public void addBookProduct(String name, int price, int pages) {
        Store.sc.storeBookProduct(name, price, pages);
    }

    public String listProducts() {
        List<Product> listOfProducts = Store.sc.getAllProduct();
        String products = "";
        for (int i = 0; i < listOfProducts.size(); i++) {
            if (i == listOfProducts.size() - 1) {
                products += listOfProducts.get(i).getName();
            } else {
                products += listOfProducts.get(i).getName() + ", ";
            }
        }
        return products;
    }

    public int getTotalProductPrice() {
        int sumOfPrices = 0;
        List<Product> listOfProducts = Store.sc.getAllProduct();
        for (int i = 0; i < listOfProducts.size() ; i++) {
            sumOfPrices += listOfProducts.get(i).getPrice();
        }
        return sumOfPrices;
    }
}
