package com.codecool;

public class Main {
    public static void main(String[] args) {
        PersistentStore ps = new PersistentStore();
        CsvPersistentStore csvPs = new CsvPersistentStore();
        StoreManager manager = new StoreManager();
        manager.addStorage(csvPs);
        manager.addCDProduct("Master of puppets", 1500, 10);
        manager.addBookProduct("Bölcsek Köve", 2500, 500);
        manager.addCDProduct("Four horsemen", 5000, 12);
        manager.addBookProduct("Black", 500, 5);
        manager.addCDProduct("Infected Mushroom II", 5000, 15);
        csvPs.store("Products.csv");
        ps.loadProducts("Products.xml");
        System.out.println(manager.listProducts());
        System.out.println(manager.getTotalProductPrice());

    }

}
