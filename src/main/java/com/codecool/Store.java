package com.codecool;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public abstract class Store implements StorageCapable {

    public List<Product> products = new ArrayList<>();

    private void saveToXml(Product product) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Store");
            doc.appendChild(rootElement);

            Element product1 = doc.createElement("Product");
            rootElement.appendChild(product1);

            Attr attr = doc.createAttribute("type");
            if (product instanceof BookProduct) {
                attr.setValue("book");
                product1.setAttributeNode(attr);

                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(product.getName()));
                product1.appendChild(name);

                Element price = doc.createElement("price");
                price.appendChild(doc.createTextNode(Integer.toString(product.getPrice())));
                product1.appendChild(price);

                Element numOfPages = doc.createElement("pages");
                numOfPages.appendChild(doc.createTextNode(Integer.toString(((BookProduct) product).getNumOfPage())));
                product1.appendChild(numOfPages);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("Products.xml"));
                transformer.transform(source, result);


            } else if (product instanceof CDProduct){

                attr.setValue("cd");
                product1.setAttributeNode(attr);

                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(product.getName()));
                product1.appendChild(name);

                Element price = doc.createElement("price");
                price.appendChild(doc.createTextNode(Integer.toString(product.getPrice())));
                product1.appendChild(price);

                Element numOfTracks = doc.createElement("tracks");
                numOfTracks.appendChild(doc.createTextNode(Integer.toString(((CDProduct) product).getNumOfTracks())));
                product1.appendChild(numOfTracks);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("Products.xml"));
                transformer.transform(source, result);
            }
        } catch (ParserConfigurationException pcex) {
            pcex.printStackTrace();
        } catch (TransformerException tfex) {
            tfex.printStackTrace();
        }
    }

    protected abstract void storeProduct(Product product);

    protected Product createProduct(String type, String name, int price, int size) {
        Product product = null;
        try {
            if (type.toLowerCase().equals("book")) {
                product = new BookProduct(name, price, size);
                storeBookProduct(name, price, size);
            } else if (type.toLowerCase().equals("cd")) {
                product = new CDProduct(name, price, size);
                storeCDProduct(name, price, size);

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
        CDProduct cd = new CDProduct(name, price, tracks);
        storeProduct(cd);
    }

    @Override
    public void storeBookProduct(String name, int price, int pages) {
        BookProduct book = new BookProduct(name, price, pages);
        storeProduct(book);
    }

    public List<Product> loadProducts() {
        return null;
    }

    public void store(Product product){
        storeProduct(product);
        saveToXml(product);
    }
}
