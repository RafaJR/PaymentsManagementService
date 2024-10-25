package com.atradius.payments.infrastructure.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ApiResponseDto<T> {

    private String status;
    private Integer statusCode;
    private String message;
    private boolean success;
    private T data;

}
