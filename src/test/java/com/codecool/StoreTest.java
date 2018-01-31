package com.codecool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {
    PersistentStore ps;
    CDProduct cd;
    BookProduct book;
    List<Product> testProducts;


    @BeforeEach
    void setUp() {
        ps = new PersistentStore();
        testProducts = ps.loadProducts("Test.xml");
    }

    @Test
    void getAllProduct() {
        assertEquals(ps.getAllProduct().size(), ps.getProducts().size());
    }

    @Test
    void storeCDProduct() {
        boolean isContains = false;
        ps.storeCDProduct("Converting vegetarians 2", 5000, 12);
        for (int i = 0; i < ps.getProducts().size(); i++) {
            if (ps.getAllProduct().get(i).getName().equals("Converting vegetarians 2")) {
                isContains = true;
            }
        }
        assertTrue(isContains);
    }

    @Test
    void storeBookProduct() {
        boolean isContains = false;
        ps.storeCDProduct("It", 6000, 500);
        for (int i = 0; i < ps.getProducts().size(); i++) {
            if (ps.getAllProduct().get(i).getName().equals("It")) {
                isContains = true;
            }
        }
        assertTrue(isContains);
    }

    @Test
    void loadProductsThrowsException() {
        boolean isExThrown = false;
        try {
            ps.loadProducts("Products.xml");
        } catch (Exception ex) {
            isExThrown = true;
        }
        assertFalse(isExThrown);
    }

    @Test
    void loadProducts() {
        boolean isListSizeIncreased = false;
        int listSizeBeforeLoad = ps.getProducts().size();
        ps.loadProducts("Products.xml");
        if (ps.getProducts().size() > listSizeBeforeLoad) {
            isListSizeIncreased = true;
        }
        assertTrue(isListSizeIncreased);
    }

    @Test
    void loadProductsTest() {
        ps.loadProducts("Test.xml");
        assertEquals(testProducts.size(), ps.getProducts().size());
        assertEquals("Valami", testProducts.get(0).getName());
    }

    @Test
    void store() {
        boolean isEquals = false;
        ps.storeCDProduct("Valami", 5, 5);
        ps.store("Test.xml");
        if (testProducts == ps.getProducts()) {
            isEquals = true;
        }
        assertTrue(isEquals);
    }
}