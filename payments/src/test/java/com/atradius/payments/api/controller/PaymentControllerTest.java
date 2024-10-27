package com.atradius.payments.api.controller;

import com.atradius.payments.infrastructure.dto.ApiResponseDto;
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

    @Test
    void getTotalPaymentsTest1() throws Exception {
        // When: Perform the GET request
        ResponseEntity<String> response = restTemplate.getForEntity("/payments/totalPayments", String.class);

        // Then: Assertions
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Deserialize the response into ApiResponseDto
        ApiResponseDto<List<TotalPaidDTO>> apiResponse = objectMapper.readValue(response.getBody(), new TypeReference<>() {
        });

        assertThat(apiResponse.getStatus()).isEqualTo("OK");
        assertThat(apiResponse.getStatusCode()).isEqualTo(200);
        assertThat(apiResponse.isSuccess()).isTrue();
        assertThat(apiResponse.getData()).hasSize(8);

        // Expected results
        assertUserTotalPaid(apiResponse.getData(), "nick", 143.0);
        assertUserTotalPaid(apiResponse.getData(), "zoey", 101.0);
        assertUserTotalPaid(apiResponse.getData(), "rochelle", 95.0);
        assertUserTotalPaid(apiResponse.getData(), "bill", 77.0);
        assertUserTotalPaid(apiResponse.getData(), "louis", 12.0);
        assertUserTotalPaid(apiResponse.getData(), "francis", 112.0);
        assertUserTotalPaid(apiResponse.getData(), "ellis", 24.0);
        assertUserTotalPaid(apiResponse.getData(), "coach", 69.0);
    }

    private void assertUserTotalPaid(List<TotalPaidDTO> result, String user, double expectedTotalPaid) {
        TotalPaidDTO dto = result.stream()
                .filter(totalPaidDTO -> totalPaidDTO.getUser().equals(user))
                .findFirst()
                .orElseThrow(() -> new AssertionError("User not found: " + user));

        assertThat(dto.getTotalPaid()).isEqualTo(expectedTotalPaid);
    }

}