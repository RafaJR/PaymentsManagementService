package com.atradius.payments.api.controller;


import com.atradius.payments.application.service.PaymentService;
import com.atradius.payments.infrastructure.dto.ApiResponseDto;
import com.atradius.payments.infrastructure.dto.DebtDTO;
import com.atradius.payments.infrastructure.dto.TotalPaidDTO;
import com.atradius.payments.infrastructure.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payments")
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/totalPayments")
    public ResponseEntity<ApiResponseDto<List<TotalPaidDTO>>> getTotalPayments() {

        log.info(Constants.TOTAL_PAYMENTS_ENDPOINT_INVOKED);

        try {
            // Calculate total payments
            List<TotalPaidDTO> listTotalPaidDTO = paymentService.calculateTotalPayments();

            // Build success response
            ApiResponseDto<List<TotalPaidDTO>> response = ApiResponseDto.<List<TotalPaidDTO>>builder()
                    .status(HttpStatus.OK.name())
                    .statusCode(HttpStatus.OK.value())
                    .message(Constants.PAYMENTS_RETRIVED_SUCCESSFULLY)
                    .success(true)
                    .data(listTotalPaidDTO)
                    .build();

            log.info(Constants.TOTAL_PAYMENTS_ENDPOINT_COMPLETED);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error(Constants.PAYMENTS_RETRIVING_ERROR, e);

            // Build error response
            ApiResponseDto<List<TotalPaidDTO>> response = ApiResponseDto.<List<TotalPaidDTO>>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(e.getMessage())
                    .success(false)
                    .data(null)
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/debt")
    public ResponseEntity<ApiResponseDto<List<DebtDTO>>> getUsersDebt() {
        log.info(Constants.DEBTS_ENDPOINT_INVOKED);

        try {
            // Calculate total payments
            List<DebtDTO> listDebtDTO = paymentService.calculateUsersDebt();

            // Build success response
            ApiResponseDto<List<DebtDTO>> response = ApiResponseDto.<List<DebtDTO>>builder()
                    .status(HttpStatus.OK.name())
                    .statusCode(HttpStatus.OK.value())
                    .message(Constants.DEBTS_RETRIVED_SUCCESSFULLY)
                    .success(true)
                    .data(listDebtDTO)
                    .build();

            log.info(Constants.DEBTS_ENDPOINT_COMPLETED);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error(Constants.DEBTS_RETRIVING_ERROR, e);

            // Build error response
            ApiResponseDto<List<DebtDTO>> response = ApiResponseDto.<List<DebtDTO>>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(e.getMessage())
                    .success(false)
                    .data(null)
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
