package com.atradius.payments.domain.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Product {
    @JsonProperty("drink_name")
    private String drinkName;
    @JsonProperty("prices")
    private Map<String, Double> prices;
}