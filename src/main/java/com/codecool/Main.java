package com.codecool;

public class Main {
    public static void main(String[] args) {

        PersistentStore ps = new PersistentStore();
        StoreManager manager = new StoreManager();
        manager.addStorage(ps);
        manager.addBookProduct("Black", 500, 5);
        manager.addCDProduct("Infected Mushroom II", 5000, 15);
        ps.store("Products.xml");
        System.out.println(manager.listProducts());
        System.out.println(manager.getTotalProductPrice());

        CsvPersistentStore csvPs = new CsvPersistentStore();
        StoreManager managerCsv = new StoreManager();
        managerCsv.addStorage(csvPs);
        managerCsv.addCDProduct("Master of puppets", 1500, 10);
        managerCsv.addBookProduct("Bölcsek Köve", 2500, 500);
        managerCsv.addCDProduct("Four horsemen", 5000, 12);
        csvPs.store("Products.csv");
        System.out.println(managerCsv.listProducts());
        System.out.println(managerCsv.getTotalProductPrice());

    }

}
