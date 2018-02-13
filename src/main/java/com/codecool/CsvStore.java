package com.codecool;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CsvStore implements StorageCapable {

    private List<Product> products = new ArrayList<>();

    protected abstract void storeProduct(Product product);

    private final String CSV_SEPARATOR = ",";


    public List<Product> getProducts() {
        return products;
    }

    protected Product createProduct(String type, String name, int price, int size) {
        Product product = null;
        try {
            if (type.toLowerCase().equals("book")) {
                product = new BookProduct(name, price, size);
            } else if (type.toLowerCase().equals("cd")) {
                product = new CDProduct(name, price, size);
            }

        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            System.out.println("Not valid type! (Try cd or book!)");
        }
        return product;
    }

    @Override
    public List<Product> getAllProduct() {
        return products;
    }

    @Override
    public void storeCDProduct(String name, int price, int tracks) {
        storeProduct(createProduct("book", name, price, tracks));
    }

    @Override
    public void storeBookProduct(String name, int price, int pages) {
        storeProduct(createProduct("book", name, price, pages));
    }

    public void saveToCsv(String fileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
            StringBuffer header = new StringBuffer();
            header.append("Name");
            header.append(CSV_SEPARATOR);
            header.append("Price");
            header.append(CSV_SEPARATOR);
            header.append("Pages/Tracks");
            bw.write(header.toString());
            bw.newLine();
            for (Product product : products) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(product.getName());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getPrice());
                oneLine.append(CSV_SEPARATOR);
                if (product instanceof CDProduct) {
                    oneLine.append(((CDProduct) product).getNumOfTracks());
                } else if (product instanceof BookProduct) {
                    oneLine.append(((BookProduct) product).getNumOfPage());
                }
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();

        } catch (IOException e) {
        }
    }

    public void store(String filename){
        saveToCsv(filename);
    }
}
