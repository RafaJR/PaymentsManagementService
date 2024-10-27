package com.atradius.payments.api.controller;

import com.atradius.payments.infrastructure.dto.ApiResponseDto;
import com.atradius.payments.infrastructure.dto.DebtDTO;
import com.atradius.payments.infrastructure.dto.TotalPaidDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PaymentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Test method to validate the total payments retrieved from the endpoint.
     *
     * @throws Exception if an error occurs during the request or processing the response.
     *                   <p>
     *                   This test makes a GET request to the "/payments/totalPayments" endpoint and verifies the response.
     *                   It checks for an HTTP 200 OK status and deserializes the response into an ApiResponseDto
     *                   containing a list of TotalPaidDTO objects. The test then asserts the response status,
     *                   status code, success flag, and the size of the data. Additionally, it verifies the
     *                   total payments for specific users.
     */
    @Test
    void getTotalPaymentsTest1() throws Exception {
        // Calling the endpoint to retrive real data
        ResponseEntity<String> response = restTemplate.getForEntity("/payments/totalPayments", String.class);

        // Deserialize the response into ApiResponseDto
        ApiResponseDto<List<TotalPaidDTO>> apiResponse = objectMapper.readValue(response.getBody(), new TypeReference<>() {
        });
        // First, assert the HTTP Status and data retrieving
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(apiResponse.getStatus()).isEqualTo("OK");
        assertThat(apiResponse.getStatusCode()).isEqualTo(200);
        assertThat(apiResponse.isSuccess()).isTrue();
        assertThat(apiResponse.getData()).hasSize(8);

        // Asserting expected results: total paid by user using the 'assertUserTotalPaid' method
        assertUserTotalPaid(apiResponse.getData(), "nick", 143.0);
        assertUserTotalPaid(apiResponse.getData(), "zoey", 101.0);
        assertUserTotalPaid(apiResponse.getData(), "rochelle", 95.0);
        assertUserTotalPaid(apiResponse.getData(), "bill", 77.0);
        assertUserTotalPaid(apiResponse.getData(), "louis", 12.0);
        assertUserTotalPaid(apiResponse.getData(), "francis", 112.0);
        assertUserTotalPaid(apiResponse.getData(), "ellis", 24.0);
        assertUserTotalPaid(apiResponse.getData(), "coach", 69.0);

    }

    /**
     * Asserts that the total paid amount for a specific user matches the expected value.
     *
     * @param result            the list of TotalPaidDTO objects containing user payment information
     * @param user              the username whose total paid amount is to be verified
     * @param expectedTotalPaid the expected total paid amount for the specified user
     */
    private void assertUserTotalPaid(List<TotalPaidDTO> result, String user, double expectedTotalPaid) {
        TotalPaidDTO dto = result.stream()
                .filter(totalPaidDTO -> totalPaidDTO.getUser().equals(user))
                .findFirst()
                .orElseThrow(() -> new AssertionError("User not found: " + user));

        assertThat(dto.getTotalPaid()).isEqualTo(expectedTotalPaid);
    }

    /**
     * Test method to validate the users' debt retrieved from the endpoint.
     *
     * @throws Exception if an error occurs during the request or processing the response.
     *                   <p>
     *                   This test makes a GET request to the "/payments/debt" endpoint and verifies the response.
     *                   It checks for an HTTP 200 OK status and deserializes the response into an ApiResponseDto
     *                   containing a list of DebtDTO objects. The test then asserts the response status,
     *                   status code, success flag, and the size of the data. Additionally, it verifies the
     *                   debt for specific users.
     */
    @Test
    void getUsersDebtTest1() throws Exception {
        // Calling the endpoint to retrieve real data
        ResponseEntity<String> response = restTemplate.getForEntity("/payments/debt", String.class);

        // Deserialize the response into ApiResponseDto
        ApiResponseDto<List<DebtDTO>> apiResponse = objectMapper.readValue(response.getBody(), new TypeReference<>() {});

        // First, assert the HTTP Status and data retrieving
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(apiResponse.getStatus()).isEqualTo("OK");
        assertThat(apiResponse.getStatusCode()).isEqualTo(200);
        assertThat(apiResponse.isSuccess()).isTrue();
        assertThat(apiResponse.getData()).hasSize(8);

        // Asserting expected results: debt by user using the 'assertUserDebt' method
        assertUserDebt(apiResponse.getData(), "nick", -106.5);
        assertUserDebt(apiResponse.getData(), "zoey", -56.0);
        assertUserDebt(apiResponse.getData(), "rochelle", -42.75);
        assertUserDebt(apiResponse.getData(), "bill", -29.25);
        assertUserDebt(apiResponse.getData(), "louis", 37.5);
        assertUserDebt(apiResponse.getData(), "francis", -66.5);
        assertUserDebt(apiResponse.getData(), "ellis", 41.75);
        assertUserDebt(apiResponse.getData(), "coach", 4.0);
    }

    /**
     * Asserts that the debt amount for a specific user matches the expected value.
     *
     * @param result     the list of DebtDTO objects containing user debt information
     * @param user       the username whose debt amount is to be verified
     * @param expectedDebt the expected debt amount for the specified user
     */
    private void assertUserDebt(List<DebtDTO> result, String user, double expectedDebt) {
        DebtDTO dto = result.stream()
                .filter(debtDTO -> debtDTO.getUser().equals(user))
                .findFirst()
                .orElseThrow(() -> new AssertionError("User not found: " + user));

        assertThat(dto.getDebt()).isEqualTo(expectedDebt);
    }

}