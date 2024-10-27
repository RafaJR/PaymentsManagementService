package com.atradius.payments.infrastructure.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DebtDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String user;
    private Double debt;
}