package com.atradius.payments.application.service;


import com.atradius.payments.domain.model.Order;
import com.atradius.payments.domain.model.Payment;
import com.atradius.payments.domain.model.Product;
import com.atradius.payments.infrastructure.adapter.json.JsonDataLoader;
import com.atradius.payments.infrastructure.dto.DebtDTO;
import com.atradius.payments.infrastructure.dto.TotalPaidDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * PaymentService is a Spring service responsible for handling the business logic related to payments.
 * It makes use of JsonDataLoader to load payment, order, and product data from JSON files.
 */
@Service
public class PaymentService {

    // JsonDataLoader instance used for loading payment, order, and product data from JSON files.
    private final JsonDataLoader jsonDataLoader;

    /**
     * Constructs a PaymentService with the specified JsonDataLoader.     *
     *
     * @param jsonDataLoader The JsonDataLoader instance used to load payment, order, and product data from JSON files.
     */
    public PaymentService(JsonDataLoader jsonDataLoader) {
        this.jsonDataLoader = jsonDataLoader;
    }


    /**
     * Calculates the total payments by user.
     *
     * @return A list of PaymentDTO where each item represents the total amount paid by each user.
     */
    public List<TotalPaidDTO> calculateTotalPayments() {
        final List<Payment> payments = jsonDataLoader.loadPayments();

        // Group by user and sum the amounts, then return as a list of PaymentDTO
        return payments.stream()
                .collect(Collectors.groupingBy(Payment::getUser, Collectors.summingDouble(Payment::getAmount)))
                .entrySet().stream()
                .map(entry -> TotalPaidDTO.builder()
                        .user(entry.getKey())
                        .totalPaid(entry.getValue())
                        .build())
                .toList();
    }

    public List<DebtDTO> calculateUsersDebt() {

        final List<Order> orders = jsonDataLoader.loadOrders();
        final List<Product> products = jsonDataLoader.loadProducts();

        // Map of product prices for quick lookup
        Map<String, Map<String, Double>> productPriceMap = products.stream()
                .collect(Collectors.toMap(Product::getDrinkName, Product::getPrices));

        // Calculate total money spent by each user
        Map<String, Double> totalSpentByUser = orders.stream()
                .collect(Collectors.groupingBy(Order::getUser,
                        Collectors.summingDouble(order -> productPriceMap.get(order.getDrink()).get(order.getSize()))));

        // Calculate total money paid by each user using existing method
        Map<String, Double> totalPaidByUser = calculateTotalPayments().stream()
                .collect(Collectors.toMap(TotalPaidDTO::getUser, TotalPaidDTO::getTotalPaid));

        // Calculate the debt for each user
        return totalPaidByUser.keySet().stream()
                .map(user -> DebtDTO.builder()
                        .user(user)
                        .debt((totalPaidByUser.get(user) - totalSpentByUser.getOrDefault(user, 0.0)) * -1)
                        .build())
                .toList();

    }
}
