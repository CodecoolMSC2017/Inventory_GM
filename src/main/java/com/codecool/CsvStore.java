package com.codecool;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CsvStore extends Store implements StorageCapable {

    private List<Product> products = getProducts();
    private final String CSV_SEPARATOR = ",";

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
