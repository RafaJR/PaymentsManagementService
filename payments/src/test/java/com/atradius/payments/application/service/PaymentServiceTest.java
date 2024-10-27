package com.atradius.payments.application.service;

import com.atradius.payments.infrastructure.adapter.json.JsonDataLoader;
import com.atradius.payments.infrastructure.dto.DebtDTO;
import com.atradius.payments.infrastructure.dto.TotalPaidDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private JsonDataLoader jsonDataLoader;

    /**
     * Tests the calculateTotalPayments method of the PaymentService class.
     * This unit test verifies that the calculateTotalPayments method correctly calculates
     * the total payments by user and returns an expected list of TotalPaidDTO objects.
     *
     * It performs the following checks:
     * 1. Verifies the size of the result list.
     * 2. Asserts the total amount paid for each user against the expected value.
     */
    @Test
    void calculateTotalPaymentsTest1() {

        // Calling the service to retrive real data
        List<TotalPaidDTO> result = paymentService.calculateTotalPayments();

        // First, assert the result size
        assertEquals(8, result.size());

        // Then, assert the expected total paid value for each user using the 'assertUserTotalPaid' method
        assertUserTotalPaid(result, "nick", 143.0);
        assertUserTotalPaid(result, "zoey", 101.0);
        assertUserTotalPaid(result, "rochelle", 95.0);
        assertUserTotalPaid(result, "bill", 77.0);
        assertUserTotalPaid(result, "louis", 12.0);
        assertUserTotalPaid(result, "francis", 112.0);
        assertUserTotalPaid(result, "ellis", 24.0);
        assertUserTotalPaid(result, "coach", 69.0);
    }

    /**
     * Asserts that the total amount paid by a specific user matches the expected value.
     *
     * @param result The list of TotalPaidDTO objects representing the total payments by users.
     * @param user The username for which the total paid amount is to be asserted.
     * @param expectedTotalPaid The expected total amount paid by the specified user.
     */
    private void assertUserTotalPaid(List<TotalPaidDTO> result, String user, double expectedTotalPaid) {
        Optional<TotalPaidDTO> dto = result.stream()
                .filter(totalPaidDTO -> totalPaidDTO.getUser().equals(user))
                .findFirst();

        assertEquals(expectedTotalPaid, dto.get().getTotalPaid());
    }

    /**
     * Tests the calculateUsersDebt method of the PaymentService class.
     * This unit test verifies that the calculateUsersDebt method correctly calculates
     * the debt by user and returns an expected list of DebtDTO objects.
     *
     * It performs the following checks:
     * 1. Verifies the size of the result list.
     * 2. Asserts the debt value for each user against the expected value.
     */
    @Test
    void calculateUsersDebtTest1() {
        // Calling the service to retrieve real data
        List<DebtDTO> result = paymentService.calculateUsersDebt();

        // First, assert the result size
        assertEquals(8, result.size());

        // Then, assert the expected debt value for each user using the 'assertUserDebt' method
        assertUserDebt(result, "nick", -106.5);
        assertUserDebt(result, "zoey", -56.0);
        assertUserDebt(result, "rochelle", -42.75);
        assertUserDebt(result, "bill", -29.25);
        assertUserDebt(result, "louis", 37.5);
        assertUserDebt(result, "francis", -66.5);
        assertUserDebt(result, "ellis", 41.75);
        assertUserDebt(result, "coach", 4.0);
    }

    /**
     * Asserts that the debt amount for a specific user matches the expected value.
     *
     * @param result The list of DebtDTO objects representing the debt by users.
     * @param user The username for which the debt amount is to be asserted.
     * @param expectedDebt The expected debt amount for the specified user.
     */
    private void assertUserDebt(List<DebtDTO> result, String user, double expectedDebt) {
        Optional<DebtDTO> dto = result.stream()
                .filter(debtDTO -> debtDTO.getUser().equals(user))
                .findFirst();

        assertEquals(expectedDebt, dto.get().getDebt());
    }
}