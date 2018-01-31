package com.codecool;

public class Main {
    public static void main(String[] args) {
        PersistentStore ps = new PersistentStore();
        StoreManager manager = new StoreManager();
        manager.addStorage(ps);
        manager.addCDProduct("Master of puppets", 1500, 10);
        manager.addBookProduct("Bölcsek Köve", 2500, 500);
        manager.addCDProduct("Akkezdet", 5000, 12);
        manager.addBookProduct("Csicskák notesze", 500, 5);
        ps.store("Products.xml");
        ps.loadProducts("Products.xml");
        System.out.println(manager.listProducts());
        System.out.println(manager.getTotalProductPrice());

    }

}
