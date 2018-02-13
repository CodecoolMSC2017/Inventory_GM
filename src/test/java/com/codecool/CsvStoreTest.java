package com.codecool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvStoreTest {
    CsvPersistentStore ps;
    Product cd;
    BufferedReader br;
    StoreManager manager;

    @BeforeEach
    void setUp() {
        ps = new CsvPersistentStore();
        cd = null;
        manager = new StoreManager();
        manager.addStorage(ps);
        File csv = new File("Test.csv");
        manager.addBookProduct("test",1,1);
        ps.store("Test.csv");
        ps.saveToCsv("Test.csv");




    }

    @Test
    void createProduct() {
        cd = ps.createProduct("CD", "Metallica", 5000, 12);
        assertEquals("Metallica", cd.getName());
        assertEquals(5000, cd.getPrice());
    }

    @Test
    void store() throws IOException {
        boolean isEmpty = true;
        try {
            br = new BufferedReader(new FileReader("Test.csv"));
            if (br.readLine() != null) {
                isEmpty = false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertFalse(isEmpty);
        assertEquals("test,1,1",br.readLine());
    }
}