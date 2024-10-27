package com.atradius.payments.infrastructure.adapter.json;

import com.atradius.payments.domain.model.Order;
import com.atradius.payments.domain.model.Payment;
import com.atradius.payments.domain.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

/**
 * JsonDataLoader is a Spring component responsible for loading data from JSON files.
 * <p>
 * The paths to the JSON files for payments, products, and orders are injected via
 * application properties. This class provides methods to load data for payments,
 * products, and orders into their respective lists.
 */
@Component
public class JsonDataLoader {

    /**
     * Paths to the JSON files containing payment data.
     * These values are injected from the application properties using the keys as 'json.paths.*'.
     */
    @Value("${json.paths.payments}")
    private String paymentsPath;

    @Value("${json.paths.products}")
    private String productsPath;

    @Value("${json.paths.orders}")
    private String ordersPath;

    /**
     * Loads and returns a list of payments from the JSON file specified by the paymentsPath.
     *
     * @return A list of payments loaded from the JSON file.
     */
    public List<Payment> loadPayments() {
        return loadData(paymentsPath, new TypeReference<List<Payment>>() {
        });
    }

    /**
     * Loads and returns a list of products from the JSON file specified by the productsPath.
     *
     * @return A list of products loaded from the JSON file.
     */
    public List<Product> loadProducts() {
        return loadData(productsPath, new TypeReference<List<Product>>() {
        });
    }

    /**
     * Loads and returns a list of orders from the JSON file specified by the ordersPath.
     *
     * @return A list of orders loaded from the JSON file.
     */
    public List<Order> loadOrders() {
        return loadData(ordersPath, new TypeReference<List<Order>>() {
        });
    }

    /**
     * Loads data from a JSON file located at the specified file path and converts it to a list of objects using the provided type reference.
     *
     * @param filePath      The path to the JSON file to be loaded.
     * @param typeReference The type reference indicating the type of list elements.
     * @return A list of objects read from the JSON file and converted to the specified type.
     * @throws RuntimeException if there is an error reading from the file or converting the data.
     */
    private <T> List<T> loadData(String filePath, TypeReference<List<T>> typeReference) {
        try (InputStream inputStream = getClass().getResourceAsStream(filePath)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputStream, typeReference);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load data from " + filePath, e);
        }
    }
}