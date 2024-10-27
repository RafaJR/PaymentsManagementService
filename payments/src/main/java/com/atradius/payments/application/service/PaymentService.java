package com.atradius.payments.application.service;


import com.atradius.payments.domain.model.Payment;
import com.atradius.payments.infrastructure.adapter.json.JsonDataLoader;
import com.atradius.payments.infrastructure.dto.DebtDTO;
import com.atradius.payments.infrastructure.dto.TotalPaidDTO;
import org.springframework.stereotype.Service;

import java.util.List;
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
        final List<Payment> payments = jsonDataLoader.loadPayments();

        // Group by user and sum the amounts, then return as a list of PaymentDTO
        return payments.stream()
                .collect(Collectors.groupingBy(Payment::getUser, Collectors.summingDouble(Payment::getAmount)))
                .entrySet().stream()
                .map(entry -> DebtDTO.builder()
                        .user(entry.getKey())
                        .debt(entry.getValue())
                        .build())
                .toList();
    }
}
