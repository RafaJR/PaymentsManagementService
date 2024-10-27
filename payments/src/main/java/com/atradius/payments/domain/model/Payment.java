package com.atradius.payments.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Payment {
    @JsonProperty("user")
    private String user;
    @JsonProperty("amount")
    private Double amount;
}
